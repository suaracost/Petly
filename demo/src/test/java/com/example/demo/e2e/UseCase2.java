package com.example.demo.e2e;

import com.example.demo.entidad.Droga;
import com.example.demo.servicio.DrogaService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UseCase2 {

    private final String BASE_URL = "http://localhost:4200/#/";

    private WebDriver driver;
    private WebDriverWait wait;

    @Autowired
    DrogaService drogaService;

    @BeforeEach
    public void init() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--disable-extensions");

        this.driver = new ChromeDriver(chromeOptions);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void SystemTest_UseCase_Complete() {
        //Busca pagina y la maximiza
        driver.get(BASE_URL + "login/veterinario");
        driver.manage().window().maximize();

        //Guardar la informacion antes de recetar la droga
        Float gananciasAntes = drogaService.totalGananciasDrogas();
        Droga drogaAntes = drogaService.SearchByNombre("ACOLAN");

        //Ingresa los datos e intenta iniciar sessión
        WebElement inputCedula = driver.findElement(By.id("cedula"));
        WebElement inputContra = driver.findElement(By.id("contra"));

        inputCedula.sendKeys("1000000051");
        inputContra.sendKeys("Petly123");

        String pathBoton = "//html//body//app-root//app-vet-login//div//div//form//button";
        WebElement botonInicio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathBoton)));
        botonInicio.click();

        //Espera que cargue la pagina principal
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("elemento")));

        //Le da al boton de ver detalles de una mascota
        WebElement infoButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("informa")));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", infoButton);

        //Espera que cargue la pagina
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tratamientoNuevo")));

        //Selecciona el medicamento y lo receta
        WebElement selectElement = driver.findElement(By.id("tratamientoNuevo"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("ACOLAN");

        WebElement botonTratamiento = wait.until(ExpectedConditions.elementToBeClickable(By.className("nevoTratamiento-btn")));
        botonTratamiento.click();

        WebElement botonConfirmarTratamiento = wait.until(ExpectedConditions.elementToBeClickable(By.className("swal2-confirm")));
        botonConfirmarTratamiento.click();

        //Espera que cargue la pagina
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("elemento")));

        //Le da al boton de ver detalles de una mascota
        WebElement infoButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.className("informa")));
        JavascriptExecutor executor2 = (JavascriptExecutor)driver;
        executor2.executeScript("arguments[0].click();", infoButton2);

        //Espera que cargue la pagina
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tratamientoNuevo")));

        //El veterinario cierra sesion
        String logoutPath = "/html/body/app-root/app-vet-mostrar-mascota/app-vet-sidebar/div/div[3]/ul/li[7]/a";
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(logoutPath)));
        logoutButton.click();

        //El administrador inicia sessión
        WebElement loginAdmin = wait.until(ExpectedConditions.elementToBeClickable(By.className("loginButton")));
        loginAdmin.click();

        WebElement otherLogin = wait.until(ExpectedConditions.elementToBeClickable(By.className("otherLogin")));
        otherLogin.click();

        WebElement inputCedulaAdmin = driver.findElement(By.id("cedula"));
        WebElement inputContraAdmin = driver.findElement(By.id("contra"));

        inputCedulaAdmin.sendKeys("1000000000");
        inputContraAdmin.sendKeys("Admin");

        String pathBotonAdmin = "/html/body/app-root/app-vet-login/div/div/form/button";
        WebElement botonInicioAdmin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathBotonAdmin)));
        botonInicioAdmin.click();

        //Espera que cargue el dashboard
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("elemento")));

        //Verifica que las ganancias aumentaron
        Float gananciasDespues = drogaService.totalGananciasDrogas();
        Droga drogaDespues = drogaService.SearchByNombre("ACOLAN");

        Assertions.assertThat(gananciasDespues).isGreaterThan(gananciasAntes);
        Assertions.assertThat(drogaDespues.getUnidadesVendidas()).isEqualTo(drogaAntes.getUnidadesVendidas()+1);
    }

    @AfterEach
    void tearDown() {
        //TODO: quitar comentario
        //driver.quit();
    }
}
