package org.example.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {
    private static WebDriver driver;

    public DriverManager(){
        this.driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new EdgeDriver(); // or ChromeDriver if you prefer
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
