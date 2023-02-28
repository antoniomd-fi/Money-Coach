package com.example.moneycoach;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v95.network.model.AuthChallengeResponse;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.*;
@Ignore
public class SeleniumTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\USER\\Descargass\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("D:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void veryfySwaggerUI(){
        driver.get("http://localhost:3000/swagger-ui/index.html");
        String title = driver.getTitle();
        assertEquals(title, "Swagger UI");
    }

    @Test
    public void verifyEndPoints(){
        driver.get("http://localhost:3000/swagger-ui/index.html");

        assertNotNull(RelativeLocator.with(By.className("operation-tag-content")));

    }

    @Test
    public void verifyButtons(){
        driver.get("http://localhost:3000/swagger-ui/index.html");

        assertNotNull(RelativeLocator.with(By.className("try-out__btn")));
    }


}
