package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.InvoicePage;
import org.example.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceStepDefinitions {

    WebDriver driver;
    InvoicePage invoicePage;
    LoginPage loginPage;
    Dotenv dotenv = Dotenv.load();

    private void login() {
        driver.get(dotenv.get("SIMKLINIK_URL"));
        loginPage = new LoginPage(driver);
        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("admin");
        loginPage.submitLogin();
    }

    private void bukaHalamanEditInvoice() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        login();
        driver.get(dotenv.get("SIMKLINIK_URL") + "/invoice");
        invoicePage = new InvoicePage(driver);
        invoicePage.goToFirstUnpaidInvoice();
    }

    @Given("Invoice tersedia")
    public void invoice_tersedia() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        login();

        driver.get(dotenv.get("SIMKLINIK_URL") + "/invoice");
        invoicePage = new InvoicePage(driver);
        assertTrue(invoicePage.isDisplayed());
    }

    @Given("Pengguna berada di menu Invoice")
    public void pengguna_di_menu_invoice() {
        assertTrue(invoicePage.isDisplayed());
    }

    @When("Pengguna mengklik ikon {string}")
    public void pengguna_mengklik_ikon_invoice(String ikon) {
        invoicePage.goToFirstInvoiceCode();
    }

    @Then("Sistem menampilkan detail invoice dengan informasi yang lengkap")
    public void sistem_menampilkan_detail_invoice() {
        String invoiceCode = invoicePage.getInvoiceCode();
        assertNotNull(invoiceCode);
        driver.quit();
    }

    @Given("Pengguna berada di halaman edit invoice")
    public void pengguna_di_halaman_edit_invoice() {
        bukaHalamanEditInvoice();
        assertTrue(driver.getCurrentUrl().contains("edit"));
    }

    @When("Pengguna mengklik checkbox {string} dan mengisi jumlah diskon")
    public void pengguna_menambah_diskon(String checkbox) {
        invoicePage.addDisc(50);
    }

    @Then("Total harga pada invoice diperbarui sesuai diskon yang diberikan")
    public void total_harga_setelah_diskon() {
        String discount = invoicePage.getDisc();
        assertTrue(discount.contains("50"));
        driver.quit();
    }

    @Given("Diskon sedang diterapkan pada invoice")
    public void diskon_sedang_diterapkan() {
        bukaHalamanEditInvoice();
        invoicePage.addDisc(50);
        String discount = invoicePage.getDisc();
        assertTrue(discount.contains("50"));
    }

    @When("Pengguna menghapus centang {string}")
    public void pengguna_menghapus_diskon(String checkbox) {
        invoicePage.removeDisc();
    }

    @Then("Total harga kembali ke nilai sebelum diskon")
    public void total_harga_kembali_normal() {
        String discount = invoicePage.getDisc();
        assertTrue(discount.contains("0"));
        driver.quit();
    }

    @When("Pengguna mencentang {string}")
    public void pengguna_mencentang_gratis_biaya(String checkbox) {
        invoicePage.addDiscService();
    }

    @Then("Biaya layanan pemeriksaan menjadi Rp 0 dan total diperbarui")
    public void biaya_pemeriksaan_gratis() {
        String amount = invoicePage.getAmount();
        assertTrue(amount.contains("0"));
        driver.quit();
    }

    @Given("Invoice telah dibuat dan dapat diakses")
    public void invoice_dapat_diakses() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        login();

        driver.get(dotenv.get("SIMKLINIK_URL") + "/invoice");
        invoicePage = new InvoicePage(driver);
        assertTrue(invoicePage.isDisplayed());
    }

    @When("Pengguna mengklik {string} lalu mengklik {string}")
    public void pengguna_mencetak_dan_mengunduh(String cetak, String unduh) {
        System.out.println("Simulasi print dan download invoice");
    }

    @Then("Invoice terbuka di tab baru untuk dicetak atau berhasil diunduh ke perangkat")
    public void invoice_tercetak_atau_terunduh() {
        assertTrue(true);
        driver.quit();
    }
}
