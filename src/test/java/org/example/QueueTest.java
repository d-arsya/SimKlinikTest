package org.example;

import io.github.cdimascio.dotenv.Dotenv;
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
    Dotenv dotenv = Dotenv.load();

    @BeforeEach
    void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(dotenv.get("SIMKLINIK_URL"));

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
        int queBefore = queuePage.getQueAmount();
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
        queuePage.newPatientNewOwner.setBirth("2024-11-20");
        queuePage.newPatientNewOwner.setAnimal("Kucing");
        queuePage.newPatientNewOwner.setVariant("Persia");
        queuePage.newPatientNewOwner.setColor("Hitam");
        String patient = queuePage.newPatientNewOwner.submitPatient();
        Assertions.assertEquals("Data berhasil disimpan!",patient);
//        queuePage.newPatientNewOwner.confirmPatient();
        queuePage.newPatientNewOwner.setWeight(100);
        queuePage.newPatientNewOwner.setPulse(100);
        queuePage.newPatientNewOwner.setTemp(100);
        queuePage.newPatientNewOwner.setBreath(100);
        queuePage.newPatientNewOwner.setService("Pemeriksaan Umum");
        queuePage.newPatientNewOwner.submitCheckup();
        Assertions.assertTrue(queuePage.isDisplayed());
        Assertions.assertEquals(queBefore+1,queuePage.getQueAmount());
    }

    @Test
    void newPatientOldOwner(){
        Assertions.assertTrue(queuePage.isDisplayed());
        int queBefore = queuePage.getQueAmount();
        queuePage.newPatientOldOwner();
        queuePage.newPatientOldOwner.submitOwner();

        queuePage.newPatientOldOwner.setPatientName("Pig Pinky");
        queuePage.newPatientOldOwner.setPatientGender("Jantan");
        queuePage.newPatientOldOwner.setBirth("2024-11-20");
        queuePage.newPatientOldOwner.setAnimal("Kucing");
        queuePage.newPatientOldOwner.setVariant("Persia");
        queuePage.newPatientOldOwner.setColor("Hitam");
        String patient = queuePage.newPatientOldOwner.submitPatient();
        Assertions.assertEquals("Data berhasil disimpan!",patient);
        queuePage.newPatientOldOwner.setWeight(100);
        queuePage.newPatientOldOwner.setPulse(100);
        queuePage.newPatientOldOwner.setTemp(100);
        queuePage.newPatientOldOwner.setBreath(100);
        queuePage.newPatientOldOwner.setService("Pemeriksaan Umum");
        queuePage.newPatientOldOwner.submitCheckup();
        Assertions.assertTrue(queuePage.isDisplayed());
        Assertions.assertEquals(queBefore+1,queuePage.getQueAmount());
    }

    @Test
    void oldPatient(){
        Assertions.assertTrue(queuePage.isDisplayed());
        int queBefore = queuePage.getQueAmount();
        queuePage.oldPatientPopup();

        queuePage.oldPatient.submitPatient();

        queuePage.oldPatient.setWeight(100);
        queuePage.oldPatient.setPulse(100);
        queuePage.oldPatient.setTemp(100);
        queuePage.oldPatient.setBreath(100);
        queuePage.oldPatient.setService("Pemeriksaan Umum");
        queuePage.oldPatient.submitCheckup();
        Assertions.assertTrue(queuePage.isDisplayed());
        Assertions.assertEquals(queBefore+1,queuePage.getQueAmount());
    }
}
