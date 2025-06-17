package org.example;

import com.aventstack.extentreports.Status;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.DashboardPage;
import org.example.page.LoginPage;
import org.example.page.ProfilePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class DashboardTest extends BaseTest {

    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    Dotenv dotenv = Dotenv.load();

    @BeforeMethod
    public void setUp(Method method) {
        test = extent.createTest(method.getName());

        driver = setupDriver();
        driver.get(dotenv.get("SIMKLINIK_URL"));

        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        login();
    }

    public void login() {
        loginPage.fillEmail("kamaluddin.arsyad05@gmail.com");
        loginPage.fillPassword("admin");
        loginPage.submitLogin();
    }

    @Test
    public void viewProfile() {
        Assert.assertTrue(dashboardPage.isDisplayed());
        Assert.assertTrue(dashboardPage.isCurrentUser("Nama Baru Arsyad"));
        dashboardPage.goToProfile();
        Assert.assertTrue(profilePage.isDisplayed());
        test.log(Status.PASS, "User berhasil mengakses halaman profil.");
    }

    @Test
    public void logout() {
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isDisplayed());
        test.log(Status.PASS, "User berhasil logout.");
    }
}
