package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.utils.ExtentReportManager;
import org.example.utils.ScreenshotUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    protected static ExtentReports extent;
    protected ExtentTest test;
    protected WebDriver driver;

    @BeforeSuite
    public void setupExtentReport() {
        extent = ExtentReportManager.getInstance();
    }

    @AfterMethod
    public void captureResultAndCleanup(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-success")));
                Thread.sleep(1000);
            } catch (Exception e) {
                // abaikan jika toast tidak muncul
            }

            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());
            test.log(Status.FAIL, "Test Gagal: " + result.getThrowable());
            test.addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Berhasil: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Dilewati: " + result.getName());
        }

        if (driver != null) {
            driver.quit();
            test.log(Status.INFO, "Browser ditutup.");
        }
    }


    @AfterSuite
    public void flushExtentReport() {
        extent.flush();
    }

    protected WebDriver setupDriver() {
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
