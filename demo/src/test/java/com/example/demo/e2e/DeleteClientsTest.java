package com.example.demo.e2e;

import java.time.Duration;
import java.util.List;

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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DeleteClientsTest {

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
    public void SystemTest_DeleteClient_Size() {
        //Busca pagina y la maximiza
        driver.get(BASE_URL + "admin/clientes/all");
        driver.manage().window().maximize();

        //Espera a que carguen los datos de clientes
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("elemento")));
        List<WebElement> liCliente = driver.findElements(By.className("elemento"));
        int initialSize = liCliente.size();
        int expectedSize = initialSize - 2;
        String pathConfirm = "/html/body/div/div/div[6]/button[3]";

        //Borra a dos clientes
        for (int i = 0; i < 2; i++) {
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("delete")));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", deleteButton);

            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pathConfirm)));
            confirmButton.click();
        }

        //Verifica que el tamaño de la lista de clientes disminuyó en 2
        Assertions.assertThat(expectedSize).isEqualTo(driver.findElements(By.className("elemento")).size());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
