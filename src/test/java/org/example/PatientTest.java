package org.example;

import org.example.page.*;
import org.example.page.diagnose.DiagnosePatientPage;
import org.example.page.patient.PatientDetailPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class PatientTest {
    WebDriver driver;
    DashboardPage dashboardPage;
    LoginPage loginPage;
    QueuePage queuePage;
    PatientPage patientPage;
    InpatientPage inpatientPage;

    @BeforeEach
    void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://simklinik.madanateknologi.web.id");

        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        queuePage = new QueuePage(driver);
        patientPage = new PatientPage(driver);
        inpatientPage = new InpatientPage(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    void login(String role){
        if (role=="super"){
            loginPage.fillEmail("super@super.com");
            loginPage.fillPassword("super");
            loginPage.submitLogin();
        }
        if (role=="doctor"){
            loginPage.fillEmail("doctor@doctor.com");
            loginPage.fillPassword("doctor");
            loginPage.submitLogin();
        }
    }

    @Test
    void firstPatientVerification(){
        login("doctor");
        dashboardPage.goToPatient();
        String code = patientPage.getFirstPatientRecordCode();
        patientPage.goToFirstPatient();
        String record = patientPage.getPatientRecord();
        Assertions.assertTrue(record.contains(code));
    }

    @Test
    void firstPatientDiagnoseToEnd(){
        login("doctor");
        dashboardPage.goToQueue();
        Assertions.assertTrue(queuePage.isDisplayed());
        int queAmount = queuePage.getQueAmount();
        queuePage.goToFirstQueue();
        DiagnosePatientPage diagnosePatientPage = patientPage.getDiagnosePage();

        diagnosePatientPage.fillAnamnesis("Gatau");
        diagnosePatientPage.fillSymptom("Coba gejala");
        diagnosePatientPage.fillDiagnose(1);
        diagnosePatientPage.fillService(1);
        diagnosePatientPage.fillDrug(2);
        diagnosePatientPage.submitToEnd();
        Assertions.assertTrue(queuePage.isDisplayed());
        Assertions.assertEquals(queAmount-1,queuePage.getQueAmount());
    }

    @Test
    void firstPatientDiagnoseToInpatient(){
        login("doctor");
        dashboardPage.goToInpatient();
        int inpatientAmount = inpatientPage.getInpatientAmount();
        dashboardPage.goToQueue();
        Assertions.assertTrue(queuePage.isDisplayed());
        int queAmount = queuePage.getQueAmount();
        queuePage.goToFirstQueue();
        DiagnosePatientPage diagnosePatientPage = patientPage.getDiagnosePage();

        diagnosePatientPage.fillAnamnesis("Gatau");
        diagnosePatientPage.fillSymptom("Coba gejala");
        diagnosePatientPage.fillDiagnose(1);
        diagnosePatientPage.fillService(1);
        diagnosePatientPage.fillDrug(2);
        diagnosePatientPage.submitToInpatient();
        Assertions.assertTrue(queuePage.isDisplayed());
        Assertions.assertEquals(queAmount-1,queuePage.getQueAmount());
        dashboardPage.goToInpatient();
        Assertions.assertEquals(inpatientAmount+1,inpatientPage.getInpatientAmount());
    }

    @Test
    void firstPatientEdit(){
        login("super");
        dashboardPage.goToPatient();
        patientPage.goToFirstPatient();
        PatientDetailPage patientDetailPage = patientPage.getPatientDetailPage();
        String newName = "Pasien Nina";
        patientDetailPage.goToEditPatient();
        patientDetailPage.setName(newName);
        patientDetailPage.submitEditForm();
        Assertions.assertEquals(newName,patientDetailPage.getName());
    }

    @Test
    void firstPatientDetail(){
        login("super");
        dashboardPage.goToPatient();
        patientPage.goToFirstPatient();
        PatientDetailPage patientDetailPage = patientPage.getPatientDetailPage();
        DiagnosePatientPage diagnosePatientPage = patientPage.getDiagnosePage();
        String anamnesa = patientDetailPage.getFirstAnamnesa();
        patientDetailPage.goToDiagnoseHistory();
        Assertions.assertEquals(anamnesa,diagnosePatientPage.getAnamnesa());
    }
}
