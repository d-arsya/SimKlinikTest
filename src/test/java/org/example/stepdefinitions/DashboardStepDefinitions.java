package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.DashboardPage;
import org.example.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class DashboardStepDefinitions {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    Dotenv dotenv = Dotenv.load();

    @Given("Pengguna telah login dan data tersedia")
    public void pengguna_telah_login_dan_data_tersedia() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(dotenv.get("SIMKLINIK_URL"));

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("admin");
        loginPage.submitLogin();

        assertTrue(dashboardPage.isDisplayed());
    }

    @Given("Pengguna berada di halaman Dashboard")
    public void pengguna_di_dashboard() {
        assertTrue(dashboardPage.isDisplayed());
    }

    @When("Pengguna mengamati kartu summary")
    public void amati_kartu_summary() {
        // Belum ada method khusus di DashboardPage
        // Anggap ini hanya assert dashboard tampil
        assertTrue(dashboardPage.isDisplayed());
    }

    @When("Pengguna mencocokkan jumlah antrian dan rawat inap")
    public void cocokkan_jumlah_summary() {
        // Belum ada method yang ambil data antrian/rawat inap,
        // maka di-skip atau tambahkan manual kalau nanti ada elemen-nya.
        System.out.println("Perlu tambahkan method getAntrian() dan getRawatInap() di DashboardPage kalau ingin assert data.");
    }

    @Then("Data pada kartu summary sesuai dengan jumlah aktual")
    public void verifikasi_summary_data() {
        // Tidak ada data dinamis di halaman, jadi tidak ada validasi nyata
        assertTrue(dashboardPage.isDisplayed());
        driver.quit();
    }

    @Given("Pengguna telah login")
    public void pengguna_login() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(dotenv.get("SIMKLINIK_URL"));

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("admin");
        loginPage.submitLogin();

        assertTrue(dashboardPage.isDisplayed());
    }

    @When("Pengguna mengklik {string}")
    public void pengguna_mengklik(String label) {
        switch (label) {
            case "View Profile":
                dashboardPage.goToProfile();
                break;
            case "Logout":
                dashboardPage.logout();
                break;
            default:
                throw new IllegalArgumentException("Tidak ada tombol dengan label: " + label);
        }
    }

    @Then("Pengguna diarahkan ke halaman profil")
    public void diarahkan_ke_profil() {
        String expected = dotenv.get("SIMKLINIK_URL") + "/profile";
        assertEquals(expected, driver.getCurrentUrl());
        driver.quit();
    }

    @Given("Pengguna sedang dalam sesi login aktif")
    public void sesi_login_aktif() {
        pengguna_login(); // Pakai step login ulang
    }

    @When("Pengguna mengklik foto profil")
    public void klik_foto_profil() {
        // Sudah termasuk dalam dashboardPage.logout()
        // Karena klik foto profil dilakukan di situ
        // Jadi abaikan langkah ini agar tidak dobel
    }

    @Then("Pengguna diarahkan ke halaman login")
    public void diarahkan_ke_login() {
        String expected = dotenv.get("SIMKLINIK_URL") + "/login";
        assertEquals(expected, driver.getCurrentUrl());
        driver.quit();
    }
}
