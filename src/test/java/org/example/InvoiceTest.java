package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class InvoiceTest {
    WebDriver driver;
    DashboardPage dashboardPage;
    LoginPage loginPage;
    InvoicePage invoicePage;
    Dotenv dotenv = Dotenv.load();

    @BeforeEach
    void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(dotenv.get("SIMKLINIK_URL"));

        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        invoicePage = new InvoicePage(driver);
        login();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    void login(){
        loginPage.fillEmail("super@super.com");
        loginPage.fillPassword("super");
        loginPage.submitLogin();
    }

    @Test
    void verifyInvoice(){
        dashboardPage.goToInvoice();
        Assertions.assertTrue(invoicePage.isDisplayed());
        String code = invoicePage.getFirstInvoiceCode();
        invoicePage.goToFirstInvoiceCode();
        Assertions.assertEquals(code,invoicePage.getInvoiceCode());
    }

    @Test
    void addDiscount(){
        dashboardPage.goToInvoice();
        Assertions.assertTrue(invoicePage.isDisplayed());
        invoicePage.goToFirstUnpaidInvoice();
        String amount = invoicePage.getAmount();
        invoicePage.addDisc(20);
        Assertions.assertTrue(invoicePage.getDisc().startsWith("20"));
        Assertions.assertFalse(invoicePage.getAmount().equals(amount));
    }

    @Test
    void addDiscountService(){
        dashboardPage.goToInvoice();
        Assertions.assertTrue(invoicePage.isDisplayed());
        invoicePage.goToFirstUnpaidInvoice();
        invoicePage.addDiscService();
        Assertions.assertTrue(invoicePage.getAmount().equals("Rp 0"));
    }

    @Test
    void removeDiscount(){
        dashboardPage.goToInvoice();
        Assertions.assertTrue(invoicePage.isDisplayed());
        invoicePage.goToFirstUnpaidInvoice();
        String amount = invoicePage.getAmount();
        invoicePage.addDisc(20);
        Assertions.assertTrue(invoicePage.getDisc().startsWith("20"));
        Assertions.assertFalse(invoicePage.getAmount().equals(amount));

        invoicePage.removeDisc();
        Assertions.assertTrue(invoicePage.getDisc().equals("0%"));
        Assertions.assertTrue(invoicePage.getAmount().equals(amount));
    }
}
