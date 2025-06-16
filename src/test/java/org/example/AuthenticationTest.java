package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.DashboardPage;
import org.example.page.ForgotPage;
import org.example.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class AuthenticationTest {
    WebDriver driver;
    DashboardPage dashboardPage;
    LoginPage loginPage;
    ForgotPage forgotPage;
    Dotenv dotenv = Dotenv.load();

    @BeforeEach
    void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(dotenv.get("SIMKLINIK_URL"));

        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        forgotPage = new ForgotPage(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void positiveLoginTest(){
        Assertions.assertTrue(loginPage.isDisplayed());
        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("admin");
        loginPage.submitLogin();
        Assertions.assertTrue(dashboardPage.isDisplayed());
    }

    @Test
    void negativeLoginTest(){
        Assertions.assertTrue(loginPage.isDisplayed());
        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("adminwroong");
        loginPage.submitLogin();
        Assertions.assertTrue(loginPage.isFailed());
    }

    @Test
    void emptyFieldTest(){
        Assertions.assertTrue(loginPage.isDisplayed());
        loginPage.submitLogin();
        Assertions.assertTrue(loginPage.isAlertDisplayed());
    }

    @Test
    void unauthenticatedUser(){
        Assertions.assertTrue(loginPage.isDisplayed());
        driver.get(dotenv.get("SIMKLINIK_URL")+"/dashboard");
        Assertions.assertTrue(driver.getCurrentUrl().equals(dotenv.get("SIMKLINIK_URL")+"/login"));
    }

    @Test
    void forgotPassword(){
        Assertions.assertTrue(loginPage.isDisplayed());
        loginPage.goToForgotPage();
        forgotPage.isDisplayed();
        forgotPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        forgotPage.submitForm();
        Assertions.assertTrue(forgotPage.isSuccess());
    }
}
