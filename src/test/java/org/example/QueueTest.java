package org.example;

import org.example.page.DashboardPage;
import org.example.page.LoginPage;
import org.example.page.ProfilePage;
import org.example.page.QueuePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class QueueTest {
    WebDriver driver;
    DashboardPage dashboardPage;
    LoginPage loginPage;
    QueuePage queuePage;

    @BeforeEach
    void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://simklinik.madanateknologi.web.id");

        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        queuePage = new QueuePage(driver);

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
        dashboardPage.goToQueue();
    }

    @Test
    void newPatientNewOwner(){
        Assertions.assertTrue(queuePage.isDisplayed());
        queuePage.newPatientNewOwner();
        queuePage.newPatientNewOwner.setName("Kamal");
        queuePage.newPatientNewOwner.setGender("Laki-laki");
        queuePage.newPatientNewOwner.setPhone("6289636055420");
        queuePage.newPatientNewOwner.setProvince("Daerah Istimewa Yogyakarta");
        queuePage.newPatientNewOwner.setCity("Kota Yogyakarta");
        queuePage.newPatientNewOwner.setDistrict("Jetis");
        queuePage.newPatientNewOwner.setVillage("Coper");
        queuePage.newPatientNewOwner.setAddress("Halo ini solo");
        String owner = queuePage.newPatientNewOwner.submitOwner();
        Assertions.assertEquals("Data berhasil disimpan!",owner);

        queuePage.newPatientNewOwner.setPatientName("Pig Pinky");
        queuePage.newPatientNewOwner.setPatientGender("Jantan");
        queuePage.newPatientNewOwner.setBirth("2024-10-12");
        queuePage.newPatientNewOwner.setAnimal("Kucing");
        queuePage.newPatientNewOwner.setVariant("Persia");
        queuePage.newPatientNewOwner.setColor("Hitam");
        String patient = queuePage.newPatientNewOwner.submitPatient();
        Assertions.assertEquals("Data berhasil disimpan!",patient);
        queuePage.newPatientNewOwner.confirmPatient();
    }
}
