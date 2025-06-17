package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.LoginPage;
import org.example.page.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileStepDefinitions {

    WebDriver driver;
    LoginPage loginPage;
    ProfilePage profilePage;
    Dotenv dotenv = Dotenv.load();

    @Given("Pengguna telah login dan berada di halaman Profil")
    public void pengguna_telah_login_dan_berada_di_halaman_profil() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(dotenv.get("SIMKLINIK_URL") + "/login");

        loginPage = new LoginPage(driver);
        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("admin");
        loginPage.submitLogin();

        driver.get(dotenv.get("SIMKLINIK_URL") + "/profile");
        profilePage = new ProfilePage(driver);
        assertTrue(profilePage.isDisplayed());
    }

    @When("Pengguna mengklik tombol {string}, mengubah nama dan spesialis, lalu klik {string}")
    public void pengguna_mengklik_tombol_edit(String tombolEdit, String tombolSubmit) {
        profilePage.goToEdit();
        profilePage.setName("Admin Baru");
        profilePage.setSpecialization("Dokter Umum");
        profilePage.submitEditForm();
    }

    @Then("Profil pengguna berhasil diperbarui dan muncul notifikasi sukses")
    public void profil_pengguna_berhasil_diperbarui() {
        String name = profilePage.getName();
        String specialization = profilePage.getSpecialization();

        assertEquals("Admin Baru", name);
        assertEquals("Dokter Umum", specialization);

        driver.quit();
    }

    @Given("Pengguna berada di halaman Edit Profil")
    public void pengguna_berada_di_halaman_edit_profil() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(dotenv.get("SIMKLINIK_URL") + "/login");

        loginPage = new LoginPage(driver);
        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("admin");
        loginPage.submitLogin();

        driver.get(dotenv.get("SIMKLINIK_URL") + "/profile");
        profilePage = new ProfilePage(driver);
        profilePage.goToEdit();
    }

    @When("Pengguna mengosongkan kolom nama dan menekan tombol {string}")
    public void pengguna_mengosongkan_kolom_nama_dan_menekan_tombol_submit(String tombolSubmit) {
        profilePage.clearName();
        profilePage.submitEditForm();
    }

    @Then("Sistem menampilkan pesan kesalahan {string} dan data tidak tersimpan")
    public void sistem_menampilkan_pesan_kesalahan(String pesan) {
        assertTrue(profilePage.isAlertDisplayed());
        driver.quit();
    }
}
