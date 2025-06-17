package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.*;
import org.example.page.diagnose.DiagnosePatientPage;
import org.example.page.patient.PatientDetailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class PatientStepDefinitions {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    PatientPage patientPage;
    PatientDetailPage patientDetailPage;
    DiagnosePatientPage diagnosePatientPage;
    QueuePage queuePage;

    Dotenv dotenv = Dotenv.load();

    private void startDriver() {
        if (driver == null) {
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }
    }

    // ===================== TC-PATIENT-001 =====================
    @Given("Pasien memiliki histori")
    public void pasien_memiliki_histori() {
        startDriver();
        driver.get(dotenv.get("SIMKLINIK_URL") + "/login");
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        patientPage = new PatientPage(driver);

        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("admin");
        loginPage.submitLogin();

        dashboardPage.goToPatient();
        assertTrue(driver.getCurrentUrl().contains("/patient"));
    }

    @When("Pengguna mengklik ikon {string} pada pasien")
    public void pengguna_mengklik_ikon_pada_pasien(String ikon) {
        patientPage.goToFirstPatient();
    }

    @Then("Sistem menampilkan detail {string} dan histori pasien dengan data yang akurat")
    public void sistem_menampilkan_detail_dan_histori_pasien(String aboutLabel) {
        patientDetailPage = new PatientDetailPage(driver);
        assertTrue(patientDetailPage.getName().length() > 0);
        driver.quit();
    }

    // ===================== TC-PATIENT-002 & TC-PATIENT-003 =====================
    @Given("Pengguna login sebagai {string}")
    public void pengguna_login_sebagai(String role) {
        startDriver();
        driver.get(dotenv.get("SIMKLINIK_URL") + "/login");
        loginPage = new LoginPage(driver);

        if (role.equalsIgnoreCase("dokter")) {
            loginPage.fillEmail("doctor@doctor.com");
            loginPage.fillPassword("doctor");
        } else {
            loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
            loginPage.fillPassword("admin");
        }

        loginPage.submitLogin();

        // Verifikasi diarahkan ke halaman antrian
        queuePage = new QueuePage(driver);
        boolean diarahkanKeQueue = driver.getCurrentUrl().contains("/queue") || queuePage.isDisplayed();
        assertTrue(diarahkanKeQueue, "Pengguna tidak diarahkan ke halaman antrian setelah login");
    }

    @Given("Pengguna berada di halaman Form Pemeriksaan")
    public void pengguna_berada_di_halaman_form_pemeriksaan() {
        queuePage = new QueuePage(driver);
        queuePage.goToFirstQueue();
        diagnosePatientPage = new DiagnosePatientPage(driver);
        assertTrue(driver.getCurrentUrl().contains("diagnosis"));
    }

    @When("Pengguna mengklik {string} dan mengisi Anamnesa, Gejala, Diagnosis, Layanan, serta Obat")
    public void pengguna_mengisi_form(String aksi) {
        diagnosePatientPage.fillAnamnesis("Anamnesa");
        diagnosePatientPage.fillSymptom("Gejala");
        diagnosePatientPage.fillDiagnose(1);
        diagnosePatientPage.fillService(1);
        diagnosePatientPage.fillDrug(1);
    }

    @When("Pengguna mengklik tombol {string}")
    public void pengguna_mengklik_tombol(String tombol) {
        if (tombol.equals("Submit")) {
            diagnosePatientPage.submitToEnd();
        } else if (tombol.equals("Tambah ke Rawat Inap")) {
            diagnosePatientPage.submitToInpatient();
        }
    }

    @Then("Data pemeriksaan tersimpan dan sistem mengarahkan ke halaman Invoice")
    public void ke_halaman_invoice() {
        assertTrue(driver.getCurrentUrl().contains("invoice"));
        driver.quit();
    }

    @Then("Data pemeriksaan tersimpan dan sistem mengarahkan ke halaman Rawat Inap")
    public void ke_halaman_rawat_inap() {
        assertTrue(driver.getCurrentUrl().contains("inpatient"));
        driver.quit();
    }

    // ===================== TC-PATIENT-004 =====================
    @Given("Pengguna berada di halaman Detail Pasien")
    public void ke_detail_pasien() {
        pasien_memiliki_histori();
        patientPage.goToFirstPatient();
        patientDetailPage = new PatientDetailPage(driver);
    }

    @When("Pengguna mengklik {string} pada halaman Detail Pasien")
    public void pengguna_mengklik_edit_profile(String aksi) {
        if (aksi.equals("Edit Profile")) {
            patientDetailPage.goToEditPatient();
        }
    }

    @When("Mengubah nama pasien dan menekan tombol {string}")
    public void ubah_nama_pasien_dan_submit(String tombol) {
        patientDetailPage.setName("Pasien Baru Uji");
        patientDetailPage.submitEditForm();
    }

    @Then("Nama pasien ter-update dan ditampilkan pada seluruh halaman terkait")
    public void nama_pasien_ter_update() {
        assertTrue(patientDetailPage.getName().contains("Pasien Baru Uji"));
        driver.quit();
    }

    // ===================== TC-PATIENT-005 =====================
    @Given("Pengguna telah login dan pasien memiliki riwayat pemeriksaan")
    public void pengguna_login_dan_pasien_berriwayat() {
        pasien_memiliki_histori();
    }

    @Given("Pengguna berada di halaman Daftar Pasien")
    public void pengguna_berada_di_halaman_daftar_pasien() {
        dashboardPage = new DashboardPage(driver);
        patientPage = new PatientPage(driver);

        dashboardPage.goToPatient();
        assertTrue(driver.getCurrentUrl().contains("/patient"));
    }

    @When("Pengguna mengklik tombol {string} pada pasien yang dipilih")
    public void klik_aksi_pasien(String aksi) {
        patientPage.goToFirstPatient();
    }

    @When("Pengguna berada di tabel Histori Pemeriksaan")
    public void lihat_tabel_histori() {
        patientDetailPage = new PatientDetailPage(driver);
        assertNotNull(patientDetailPage.getFirstAnamnesa());
    }

    @When("Pengguna mengklik ikon {string} pada baris histori")
    public void klik_detail_histori(String ikon) {
        patientDetailPage.goToDiagnoseHistory();
    }

    @Then("Sistem menampilkan halaman history pemeriksaan dan rekam medis pasien")
    public void tampilkan_histori_diagnosa() {
        diagnosePatientPage = new DiagnosePatientPage(driver);
        assertNotNull(diagnosePatientPage.getAnamnesa());
        driver.quit();
    }
}
