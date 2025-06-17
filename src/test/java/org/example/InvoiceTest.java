package org.example;

import com.aventstack.extentreports.Status;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.example.BaseTest.extent;

public class InvoiceTest extends BaseTest {
    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private InvoicePage invoicePage;
    Dotenv dotenv = Dotenv.load();

    @BeforeMethod
    void setUp(Method method) {
        test = extent.createTest(method.getName());

        driver = setupDriver();
        driver.get(dotenv.get("SIMKLINIK_URL"));

        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        invoicePage = new InvoicePage(driver);
        login();
    }

    void login(){
        loginPage.fillEmail("super@super.com");
        loginPage.fillPassword("super");
        loginPage.submitLogin();
    }

    @Test
    public void verifyInvoice(){
        dashboardPage.goToInvoice();
        Assert.assertTrue(invoicePage.isDisplayed());
        String code = invoicePage.getFirstInvoiceCode();
        invoicePage.goToFirstInvoiceCode();
        Assert.assertEquals(code,invoicePage.getInvoiceCode());
        test.log(Status.PASS, "Kode invoice pada detail sesuai dengan yang ditampilkan sebelumnya");
    }

    @Test
    public void addDiscount(){
        dashboardPage.goToInvoice();
        Assert.assertTrue(invoicePage.isDisplayed());
        invoicePage.goToFirstUnpaidInvoice();
        String amount = invoicePage.getAmount();
        invoicePage.addDisc(20);
        Assert.assertTrue(invoicePage.getDisc().startsWith("20"));
        Assert.assertFalse(invoicePage.getAmount().equals(amount));
        test.log(Status.PASS, "Berhasil menambahkan diskon");
    }

    @Test
    public void addDiscountService(){
        dashboardPage.goToInvoice();
        Assert.assertTrue(invoicePage.isDisplayed());
        invoicePage.goToFirstUnpaidInvoice();
        invoicePage.addDiscService();
        Assert.assertTrue(invoicePage.getAmount().equals("Rp 0"));
        test.log(Status.PASS, "Total invoice menjadi Rp 0 setelah diskon service diterapkan");
    }

    @Test
    public void removeDiscount(){
        dashboardPage.goToInvoice();
        Assert.assertTrue(invoicePage.isDisplayed());
        invoicePage.goToFirstUnpaidInvoice();
        String amount = invoicePage.getAmount();
        invoicePage.addDisc(20);
        Assert.assertTrue(invoicePage.getDisc().startsWith("20"));
        Assert.assertFalse(invoicePage.getAmount().equals(amount));

        invoicePage.removeDisc();
        Assert.assertTrue(invoicePage.getDisc().equals("0%"));
        Assert.assertTrue(invoicePage.getAmount().equals(amount));
        test.log(Status.PASS, "Diskon berhasil dihapus dan total kembali ke nilai semula");
    }
}
