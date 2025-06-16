package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.DashboardPage;
import org.example.page.LoginPage;
import org.example.page.ProfilePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class ProfileTest {
    WebDriver driver;
    DashboardPage dashboardPage;
    LoginPage loginPage;
    ProfilePage profilePage;
    Dotenv dotenv = Dotenv.load();

    @BeforeEach
    void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(dotenv.get("SIMKLINIK_URL"));

        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        login();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    void login(){
        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("admin");
        loginPage.submitLogin();
    }

    @Test
    void updateProfile(){
        dashboardPage.goToProfile();
        Assertions.assertTrue(profilePage.isDisplayed());
        profilePage.goToEdit();
        String name = "Nama Baru Arsyad";
        String specialization = "Kucing Gila";
        profilePage.setName(name);
        profilePage.setSpecialization(specialization);
        profilePage.submitEditForm();
        String newName = profilePage.getName();
        String newSpecialization = profilePage.getSpecialization();
        Assertions.assertEquals(name,newName);
        Assertions.assertEquals(specialization,newSpecialization);
    }

    @Test
    void updateProfileNegative(){
        dashboardPage.goToProfile();
        Assertions.assertTrue(profilePage.isDisplayed());
        profilePage.goToEdit();
        profilePage.clearName();
        profilePage.submitEditForm();
        Assertions.assertTrue(profilePage.isAlertDisplayed());
    }
}
