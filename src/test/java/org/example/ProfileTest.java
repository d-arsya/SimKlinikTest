package org.example;

import com.aventstack.extentreports.Status;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.page.DashboardPage;
import org.example.page.LoginPage;
import org.example.page.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class ProfileTest extends BaseTest {

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
    public void updateProfile() {
        dashboardPage.goToProfile();
        Assert.assertTrue(profilePage.isDisplayed());

        profilePage.goToEdit();
        String name = "Nama Baru Arsyad";
        String specialization = "Kucing Gila";
        profilePage.setName(name);
        profilePage.setSpecialization(specialization);
        profilePage.submitEditForm();

        Assert.assertEquals(profilePage.getName(), name);
        Assert.assertEquals(profilePage.getSpecialization(), specialization);
        test.log(Status.PASS, "User berhasil mengubah profil.");
    }

    @Test
    public void updateProfileNegative() {
        dashboardPage.goToProfile();
        Assert.assertTrue(profilePage.isDisplayed());

        profilePage.goToEdit();
        profilePage.clearName();
        profilePage.submitEditForm();

        Assert.assertTrue(profilePage.isAlertDisplayed());
        test.log(Status.PASS, "User gagal mengubah profil karena field kosong.");
    }
}
