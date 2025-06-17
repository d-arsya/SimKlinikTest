package org.example;

import com.aventstack.extentreports.Status;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.DashboardPage;
import org.example.page.ForgotPage;
import org.example.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class AuthenticationTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ForgotPage forgotPage;
    Dotenv dotenv = Dotenv.load();

    @BeforeMethod
    public void setUp(Method method) {
        test = extent.createTest(method.getName());
        driver = setupDriver();

        driver.get(dotenv.get("SIMKLINIK_URL"));
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        forgotPage = new ForgotPage(driver);
    }

    @Test
    public void positiveLoginTest() {
        Assert.assertTrue(loginPage.isDisplayed());
        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("admin");
        loginPage.submitLogin();
        Assert.assertTrue(dashboardPage.isDisplayed());
        test.log(Status.PASS, "Login berhasil, dashboard ditampilkan.");
    }

    @Test
    public void negativeLoginTest() {
        Assert.assertTrue(loginPage.isDisplayed());
        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("adminwroong");
        loginPage.submitLogin();
        Assert.assertTrue(loginPage.isFailed());
        test.log(Status.PASS, "Login gagal sesuai ekspektasi.");
    }

    @Test
    public void emptyFieldTest() {
        Assert.assertTrue(loginPage.isDisplayed());
        loginPage.submitLogin();
        Assert.assertTrue(loginPage.isAlertDisplayed());
        test.log(Status.PASS, "Login gagal karena field kosong memunculkan alert.");
    }

    @Test
    public void unauthenticatedUser() {
        Assert.assertTrue(loginPage.isDisplayed());
        driver.get(dotenv.get("SIMKLINIK_URL") + "/dashboard");
        Assert.assertEquals(driver.getCurrentUrl(), dotenv.get("SIMKLINIK_URL") + "/login");
        test.log(Status.PASS, "User tidak bisa mengakses dashboard tanpa login.");
    }

    @Test
    public void forgotPassword() {
        Assert.assertTrue(loginPage.isDisplayed());
        loginPage.goToForgotPage();
        forgotPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        forgotPage.submitForm();
        Assert.assertTrue(forgotPage.isSuccess());
        test.log(Status.PASS, "Permintaan reset password berhasil dikirim.");
    }
}
