package org.example;

import com.aventstack.extentreports.Status;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.*;
import org.example.page.diagnose.DiagnosePatientPage;
import org.example.page.patient.PatientDetailPage;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Method;

public class PatientTest extends BaseTest {
    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private QueuePage queuePage;
    private PatientPage patientPage;
    private InpatientPage inpatientPage;
    Dotenv dotenv = Dotenv.load();

    @BeforeMethod
    public void setUp(Method method) {
        test = extent.createTest(method.getName());

        driver = setupDriver();
        driver.manage().window().maximize();
        driver.get(dotenv.get("SIMKLINIK_URL"));

        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        queuePage = new QueuePage(driver);
        patientPage = new PatientPage(driver);
        inpatientPage = new InpatientPage(driver);
    }

    void login(String role){
        if (role.equals("super")) {
            loginPage.fillEmail("super@super.com");
            loginPage.fillPassword("super");
            loginPage.submitLogin();
        } else if (role.equals("doctor")) {
            loginPage.fillEmail("doctor@doctor.com");
            loginPage.fillPassword("doctor");
            loginPage.submitLogin();
        }
    }

    @Test
    public void firstPatientVerification(){
        login("doctor");
        dashboardPage.goToPatient();
        String code = patientPage.getFirstPatientRecordCode();
        patientPage.goToFirstPatient();
        String record = patientPage.getPatientRecord();
        Assert.assertTrue(record.contains(code));
        test.log(Status.PASS, "Kode pasien sesuai pada halaman detail");
    }

    @Test
    public void firstPatientDiagnoseToEnd(){
        login("doctor");
        dashboardPage.goToQueue();
        Assert.assertTrue(queuePage.isDisplayed());
        int queAmount = queuePage.getQueAmount();
        queuePage.goToFirstQueue();
        DiagnosePatientPage diagnosePatientPage = patientPage.getDiagnosePage();

        diagnosePatientPage.fillAnamnesis("Gatau");
        diagnosePatientPage.fillSymptom("Coba gejala");
        diagnosePatientPage.fillDiagnose(1);
        diagnosePatientPage.fillService(1);
        diagnosePatientPage.fillDrug(2);
        diagnosePatientPage.submitToEnd();

        Assert.assertTrue(queuePage.isDisplayed());
        Assert.assertEquals(queAmount - 1, queuePage.getQueAmount());
        test.log(Status.PASS, "Pasien berhasil didiagnosa dan keluar dari antrian");
    }

    @Test
    public void firstPatientDiagnoseToInpatient(){
        login("doctor");
        dashboardPage.goToInpatient();
        int inpatientAmount = inpatientPage.getInpatientAmount();
        dashboardPage.goToQueue();
        Assert.assertTrue(queuePage.isDisplayed());
        int queAmount = queuePage.getQueAmount();
        queuePage.goToFirstQueue();
        DiagnosePatientPage diagnosePatientPage = patientPage.getDiagnosePage();

        diagnosePatientPage.fillAnamnesis("Gatau");
        diagnosePatientPage.fillSymptom("Coba gejala");
        diagnosePatientPage.fillDiagnose(1);
        diagnosePatientPage.fillService(1);
        diagnosePatientPage.fillDrug(2);
        diagnosePatientPage.submitToInpatient();

        Assert.assertTrue(queuePage.isDisplayed());
        Assert.assertEquals(queAmount - 1, queuePage.getQueAmount());
        dashboardPage.goToInpatient();
        Assert.assertEquals(inpatientAmount + 1, inpatientPage.getInpatientAmount());
        test.log(Status.PASS, "Pasien berhasil masuk ke rawat inap");
    }

    @Test
    public void firstPatientEdit(){
        login("super");
        dashboardPage.goToPatient();
        patientPage.goToFirstPatient();
        PatientDetailPage patientDetailPage = patientPage.getPatientDetailPage();
        String newName = "Pasien Nina";
        patientDetailPage.goToEditPatient();
        patientDetailPage.setName(newName);
        patientDetailPage.submitEditForm();
        Assert.assertEquals(patientDetailPage.getName(), newName);
        test.log(Status.PASS, "Edit nama pasien berhasil");
    }

    @Test
    public void firstPatientDetail(){
        login("super");
        dashboardPage.goToPatient();
        patientPage.goToFirstPatient();
        PatientDetailPage patientDetailPage = patientPage.getPatientDetailPage();
        DiagnosePatientPage diagnosePatientPage = patientPage.getDiagnosePage();
        String anamnesa = patientDetailPage.getFirstAnamnesa();
        patientDetailPage.goToDiagnoseHistory();
        Assert.assertEquals(diagnosePatientPage.getAnamnesa(), anamnesa);
        test.log(Status.PASS, "Detail riwayat anamnesa sesuai");
    }
}
