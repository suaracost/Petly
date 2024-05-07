package com.example.demo.e2e;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UseCase1 {

    private final String BASE_URL = "http://localhost:4200/#/";

    private WebDriver driver;
    private WebDriverWait wait;

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

        //Ingresa los datos e intenta iniciar sessión
        WebElement inputCedula = driver.findElement(By.id("cedula"));
        WebElement inputContra = driver.findElement(By.id("contra"));

        inputCedula.sendKeys("1000000051");
        inputContra.sendKeys("Petly12");

        String pathBoton = "//html//body//app-root//app-vet-login//div//div//form//button";
        WebElement botonInicio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathBoton)));
        botonInicio.click();

        //Le da a volver para poner los datos bien
        WebElement botonVolver = wait.until(ExpectedConditions.elementToBeClickable(By.className("swal2-confirm")));
        botonVolver.click();

        inputContra.sendKeys("3");
        botonInicio.click();

        //Espera que cargue la pagina principal
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("elemento")));

        //Click para ir a la pagina de agregar cliente
        String pathAgregarCliente = "/html/body/app-root/app-vet-mostrar-mascota-todas/app-vet-sidebar/div/div[3]/ul/li[4]/a";
        WebElement botonAgregarCliente = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathAgregarCliente)));
        botonAgregarCliente.click();

        //Llena los datos del cliente
        WebElement inputCedulaCliente = driver.findElement(By.id("cedula"));
        inputCedulaCliente.sendKeys("19");

        WebElement inputNombreCliente = driver.findElement(By.id("nombre"));
        inputNombreCliente.sendKeys("Alejandro");

        WebElement inputApellidoCliente = driver.findElement(By.id("apellido"));
        inputApellidoCliente.sendKeys("Suarez");

        WebElement inputMailCliente = driver.findElement(By.id("correo"));
        inputMailCliente.sendKeys("alejandro@gmail.com");

        WebElement inputCelularCliente = driver.findElement(By.id("celular"));
        inputCelularCliente.sendKeys("123");

        String pathBotonCliente = "/html/body/app-root/app-vet-registrar-cliente/body/form/div[2]/div[4]/button[2]";
        WebElement botonRegistrarCliente = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathBotonCliente)));
        botonRegistrarCliente.click();

        WebElement botonRegistrar = wait.until(ExpectedConditions.elementToBeClickable(By.className("swal2-confirm")));
        botonRegistrar.click();

        //Espera que cargue la pagina principal
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("elemento")));

        //Click para ir a la pagina de agregar mascota
        String pathAgregarMascota = "/html/body/app-root/app-vet-mostrar-cliente-todos/app-vet-sidebar/div/div[3]/ul/li[2]";
        WebElement botonAgregar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathAgregarMascota)));
        botonAgregar.click();

        //Llena los datos de la mascota
        WebElement inputNombreMascota = driver.findElement(By.id("nombre"));
        inputNombreMascota.sendKeys("Apolo");

        WebElement inputRaza = driver.findElement(By.id("raza"));
        inputRaza.sendKeys("Golden");

        WebElement inputEdad = driver.findElement(By.id("edad"));
        inputEdad.sendKeys("5");

        WebElement inputPeso = driver.findElement(By.id("peso"));
        inputPeso.sendKeys("10");

        WebElement inputEnfermedad = driver.findElement(By.id("enfermedad"));
        inputEnfermedad.sendKeys("Nada");

        WebElement inputFoto = driver.findElement(By.id("foto"));
        inputFoto.sendKeys("https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg");

        WebElement inputCedulaRegistro = driver.findElement(By.id("cedulaDueno"));
        inputCedulaRegistro.sendKeys("1");

        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-primary")));
        registerButton.click();

        //Le da a volver para poner los datos bien
        WebElement botonVolver2 = wait.until(ExpectedConditions.elementToBeClickable(By.className("swal2-confirm")));
        botonVolver2.click();

        inputCedulaRegistro.sendKeys("9");
        registerButton.click();

        WebElement botonRegistrar2 = wait.until(ExpectedConditions.elementToBeClickable(By.className("swal2-confirm")));
        botonRegistrar2.click();

        //Espera que cargue la pagina principal
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("elemento")));

        //El veterinario cierra sessión
        String pathCerrarSesion = "/html/body/app-root/app-vet-mostrar-mascota-todas/app-vet-sidebar/div/div[3]/ul/li[7]/a";
        WebElement botonCerrarSesion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathCerrarSesion)));
        botonCerrarSesion.click();

        //El cliente inicia sessión
        String pathLogin = "/html/body/app-root/app-landing/body/header/div[3]/button/a";
        WebElement botonLogin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathLogin)));
        botonLogin.click();

        WebElement inputLoginCliente = driver.findElement(By.id("cedula"));
        inputLoginCliente.sendKeys("19");

        String pathLoginCliente = "/html/body/app-root/app-cliente-login/div/div/form/button";
        WebElement botonInicioCliente = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathLoginCliente)));
        botonInicioCliente.click();

        //Espera que cargue la pagina principal
        WebElement verInfoMascota = wait.until(ExpectedConditions.elementToBeClickable(By.className("verInfo")));
        verInfoMascota.click();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
