package org.example;

import com.aventstack.extentreports.Status;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.DashboardPage;
import org.example.page.LoginPage;
import org.example.page.ProfilePage;
import org.example.page.QueuePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class QueueTest extends BaseTest {
    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private QueuePage queuePage;
    Dotenv dotenv = Dotenv.load();

    @BeforeMethod
    void setUp(Method method) {
        test = extent.createTest(method.getName());

        driver = setupDriver();
        driver.manage().window().maximize();
        driver.get(dotenv.get("SIMKLINIK_URL"));

        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        queuePage = new QueuePage(driver);

        login();
    }

    void login(){
        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("admin");
        loginPage.submitLogin();
        dashboardPage.goToQueue();
    }

    @Test
    void newPatientNewOwner(){
        Assert.assertTrue(queuePage.isDisplayed());
        int queBefore = queuePage.getQueAmount();
        queuePage.newPatientNewOwner();
        queuePage.newPatientNewOwner.setName("Kamal");
        queuePage.newPatientNewOwner.setGender("Laki-laki");
        queuePage.newPatientNewOwner.setPhone("6289636055426");
        queuePage.newPatientNewOwner.setProvince("Daerah Istimewa Yogyakarta");
        queuePage.newPatientNewOwner.setCity("Kota Yogyakarta");
        queuePage.newPatientNewOwner.setDistrict("Jetis");
        queuePage.newPatientNewOwner.setVillage("Coper");
        queuePage.newPatientNewOwner.setAddress("Halo ini solo");
        String owner = queuePage.newPatientNewOwner.submitOwner();
        Assert.assertEquals("Data berhasil disimpan!",owner);

        queuePage.newPatientNewOwner.setPatientName("Pig Pinky");
        queuePage.newPatientNewOwner.setPatientGender("Jantan");
        queuePage.newPatientNewOwner.setBirth("2024-11-20");
        queuePage.newPatientNewOwner.setAnimal("Kucing");
        queuePage.newPatientNewOwner.setVariant("Persia");
        queuePage.newPatientNewOwner.setColor("Hitam");
        String patient = queuePage.newPatientNewOwner.submitPatient();
        Assert.assertEquals("Data berhasil disimpan!",patient);
//        queuePage.newPatientNewOwner.confirmPatient();
        queuePage.newPatientNewOwner.setWeight(100);
        queuePage.newPatientNewOwner.setPulse(100);
        queuePage.newPatientNewOwner.setTemp(100);
        queuePage.newPatientNewOwner.setBreath(100);
        queuePage.newPatientNewOwner.setService("Pemeriksaan Umum");
        queuePage.newPatientNewOwner.submitCheckup();
        Assert.assertTrue(queuePage.isDisplayed());
        Assert.assertEquals(queBefore+1,queuePage.getQueAmount());
        test.log(Status.PASS, "Berhasil menambahkan pasien baru owner baru.");
    }

    @Test
    void newPatientOldOwner(){
        Assert.assertTrue(queuePage.isDisplayed());
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
        Assert.assertEquals("Data berhasil disimpan!",patient);
        queuePage.newPatientOldOwner.setWeight(100);
        queuePage.newPatientOldOwner.setPulse(100);
        queuePage.newPatientOldOwner.setTemp(100);
        queuePage.newPatientOldOwner.setBreath(100);
        queuePage.newPatientOldOwner.setService("Pemeriksaan Umum");
        queuePage.newPatientOldOwner.submitCheckup();
        Assert.assertTrue(queuePage.isDisplayed());
        Assert.assertEquals(queBefore+1,queuePage.getQueAmount());
        test.log(Status.PASS, "Berhasil menambahkan pasien baru dengan owner lama.");
    }

    @Test
    void oldPatient(){
        Assert.assertTrue(queuePage.isDisplayed());
        int queBefore = queuePage.getQueAmount();
        queuePage.oldPatientPopup();

        queuePage.oldPatient.submitPatient();

        queuePage.oldPatient.setWeight(100);
        queuePage.oldPatient.setPulse(100);
        queuePage.oldPatient.setTemp(100);
        queuePage.oldPatient.setBreath(100);
        queuePage.oldPatient.setService("Pemeriksaan Umum");
        queuePage.oldPatient.submitCheckup();
        Assert.assertTrue(queuePage.isDisplayed());
        Assert.assertEquals(queBefore+1,queuePage.getQueAmount());
        test.log(Status.PASS, "Berhasil menambahkan pasien lama.");
    }
}