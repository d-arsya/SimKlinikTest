package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.DashboardPage;
import org.example.page.QueuePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class QueueStepDefinitions {

    WebDriver driver;
    QueuePage queuePage;
    DashboardPage dashboardPage;
    Dotenv dotenv = Dotenv.load();

    private void startDriver() {
        if (driver == null) {
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }
    }

    @Given("Pengguna telah login dan berada di halaman Antrian")
    public void pengguna_di_halaman_antrian() {
        startDriver();
        dashboardPage = new DashboardPage(driver);
        dashboardPage.goToQueue();
        driver.get(dotenv.get("SIMKLINIK_URL") + "/queue");
        queuePage = new QueuePage(driver);

        assertTrue(queuePage.isDisplayed());
    }

    @And("Pengguna memilih tab {string} dan sub-tab {string}")
    public void pilih_tab_dan_subtab(String tab, String subTab) {
        queuePage.newPatienPopup();
        if (subTab.equalsIgnoreCase("Owner Baru")) {
            queuePage.newOwner();
        } else if (subTab.equalsIgnoreCase("Owner Lama")) {
            queuePage.oldOwner();
        }
    }

    @When("Pengguna mengisi data Owner Baru dan klik {string}")
    public void isi_data_owner_baru(String submit) {
        var owner = queuePage.newPatientNewOwner;
        owner.setName("Udin");
        owner.setPhone("0896" + System.currentTimeMillis());
        owner.setGender("Laki-Laki");
        owner.setProvince("DI Yogyakarta");
        owner.setCity("Kota Yogyakarta");
        owner.setDistrict("Umbulharjo");
        owner.setVillage("Muja Muju");
        owner.setAddress("Jl Melati 9");
        owner.submitOwner();
    }

    @And("Pengguna mengisi data Pasien Baru dan klik {string}")
    public void isi_data_pasien_baru(String submit) {
        var pasien = queuePage.newPatientNewOwner;
        pasien.setPatientName("Mpus");
        pasien.setPatientGender("Jantan");
        pasien.setColor("Oren");
        pasien.setAnimal("Kucing");
        pasien.setVariant("Persia");
        pasien.setBirth("2022-01-01");
        pasien.submitPatient();
    }

    @And("Pengguna mengklik {string} pada tab Konfirmasi")
    public void klik_konfirmasi(String tombol) {
        queuePage.newPatientNewOwner.confirmPatient();
    }

    @And("Pengguna mengisi data Pemeriksaan dan klik {string}")
    public void isi_pemeriksaan(String tombol) {
        var check = queuePage.newPatientNewOwner;
        check.setWeight(3);
        check.setPulse(85);
        check.setTemp(38);
        check.setBreath(28);
        check.setService("Konsultasi");
        check.submitCheckup();
    }

    @Then("Data pasien dan owner baru tersimpan dan muncul di Antrian serta halaman Pasien")
    public void validasi_data_baru_masuk() {
        assertTrue(queuePage.getQueAmount() > 0);
        driver.quit();
    }

    @And("Nomor HP yang akan digunakan sudah terdaftar")
    public void hp_terdaftar() {
        // dummy: anggap sudah ada
    }

    @When("Pengguna mengisi form dengan nomor HP yang sama dan klik {string}")
    public void isi_hp_duplikat(String submit) {
        var owner = queuePage.newPatientNewOwner;
        owner.setName("Doni");
        owner.setPhone("089999999999");
        owner.setGender("Laki-Laki");
        owner.setProvince("DI Yogyakarta");
        owner.setCity("Kota Yogyakarta");
        owner.setDistrict("Umbulharjo");
        owner.setVillage("Pandeyan");
        owner.setAddress("Jl Kenanga 10");
        String alert = owner.submitOwner();
        assertTrue(alert.contains("sudah terdaftar"));
    }

    @And("Owner lama sudah terdaftar")
    public void owner_lama_ada() {
        // dummy: sudah ada
    }

    @And("Pengguna memilih Owner Lama dan mengisi data Pasien")
    public void pilih_owner_lama_dan_isi_pasien() {
        var form = queuePage.newPatientOldOwner;
        form.submitOwner();
        form.setPatientName("Ciko");
        form.setPatientGender("Jantan");
        form.setColor("Hitam");
        form.setAnimal("Kucing");
        form.setVariant("Anggora");
        form.setBirth("2021-12-01");
        form.submitPatient();
    }

    @And("Pengguna klik {string}")
    public void klik_submit_pasien(String button) {
        queuePage.newPatientOldOwner.confirmPatient();
    }

    @And("Pengguna mengisi Pemeriksaan Awal dan klik {string}")
    public void isi_checkup_lama(String button) {
        var check = queuePage.newPatientOldOwner;
        check.setWeight(4);
        check.setPulse(80);
        check.setTemp(37);
        check.setBreath(27);
        check.setService("Sterilisasi");
        check.submitCheckup();
    }

    @Then("Data pasien tersimpan dan muncul di Antrian serta halaman Pasien")
    public void validasi_pasien_baru_masuk() {
        assertTrue(queuePage.getQueAmount() > 0);
        driver.quit();
    }

    @And("Pasien lama sudah terdaftar")
    public void pasien_lama_siap() {
        // dummy: sudah ada
    }

    @When("Pengguna memilih tab {string}")
    public void pilih_tab_pasien_lama(String tab) {
        queuePage.oldPatientPopup();
    }

    @And("Pengguna klik ikon {string}")
    public void klik_plus_pasien(String ikon) {
        queuePage.oldPatient.submitPatient();
    }

    @And("Pengguna mengisi Pemeriksaan Awal")
    public void isi_pemeriksaan_pasien_lama() {
        var check = queuePage.oldPatient;
        check.setWeight(5);
        check.setPulse(76);
        check.setTemp(37);
        check.setBreath(26);
        check.setService("Cek rutin");
        check.submitCheckup();
    }

    @Then("Data pasien lama tersimpan dan muncul di tabel Antrian serta halaman Pasien")
    public void validasi_pasien_lama_masuk() {
        assertTrue(queuePage.getQueAmount() > 0);
        driver.quit();
    }

    @And("Data pasien sudah tersedia")
    public void data_pasien_sudah_ada() {
        // dummy data
    }

//    @When("Pengguna mengklik tombol {string}")
//    public void klik_btn_search(String tombol) {
//        driver.findElement(By.id("btn-search-queue")).click();
//    }
//
//    @And("Pengguna memasukkan keyword pencarian")
//    public void input_keyword() {
//        driver.findElement(By.id("search-queue")).sendKeys("Mpus");
//    }

    @Then("Data Owner atau Pasien ditampilkan sesuai dengan keyword")
    public void hasil_pencarian_sesuai() {
        assertTrue(driver.getPageSource().contains("Mpus"));
        driver.quit();
    }
}
