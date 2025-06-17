package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.DashboardPage;
import org.example.page.ForgotPage;
import org.example.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationStepDefinitions {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ForgotPage forgotPage;
    Dotenv dotenv = Dotenv.load();

    @Given("Pengguna berada di halaman login")
    public void pengguna_di_halaman_login() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(dotenv.get("SIMKLINIK_URL"));

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        forgotPage = new ForgotPage(driver);
        assertTrue(loginPage.isDisplayed());
    }

    @When("Pengguna mengisi email valid dan password valid")
    public void input_credential_benar() {
        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("admin");
    }

    @When("Pengguna mengisi email valid dan password tidak valid")
    public void input_password_salah() {
        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("salahpass");
    }

    @When("Pengguna menekan tombol login")
    public void klik_login() {
        loginPage.submitLogin();
    }

    @Then("Pengguna diarahkan ke halaman dashboard")
    public void redirect_dashboard() {
        assertTrue(dashboardPage.isDisplayed());
        driver.quit();
    }

    @Then("Sistem menampilkan error login")
    public void tampilkan_error_login() {
        assertTrue(loginPage.isFailed());
        driver.quit();
    }

    @When("Pengguna menekan tombol login tanpa mengisi field")
    public void klik_login_field_kosong() {
        loginPage.submitLogin();
    }

    @Then("Sistem menampilkan alert bahwa field harus diisi")
    public void tampilkan_alert_kosong() {
        assertTrue(loginPage.isAlertDisplayed());
        driver.quit();
    }

    @Given("Pengguna tidak login")
    public void pengguna_belum_login() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @When("Pengguna akses halaman dashboard langsung")
    public void akses_dashboard_langsung() {
        driver.get(dotenv.get("SIMKLINIK_URL"));
    }

    @Then("Sistem mengarahkan ke halaman login")
    public void redirect_ke_login() {
        String expectedLoginUrl = dotenv.get("SIMKLINIK_URL") + "/login";
        assertEquals(expectedLoginUrl, driver.getCurrentUrl());
        driver.quit();
    }

    @When("Pengguna menekan link “Forgot Password”")
    public void klik_forgot_link() {
        loginPage.goToForgotPage();
        assertTrue(forgotPage.isDisplayed());
    }

    @When("Pengguna mengisi email dan menekan submit")
    public void isi_email_dan_submit() {
        forgotPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        forgotPage.submitForm();
    }

    @Then("Sistem mengirimkan reset password ke email")
    public void cek_reset_password_terkirim() {
        assertTrue(forgotPage.isSuccess());
        driver.quit();
    }
}
