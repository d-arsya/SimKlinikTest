package org.example.page;

import org.openqa.selenium.WebDriver;

public class DashboardPage {
    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed(){
        return driver.getCurrentUrl().endsWith("dashboard");
    }
}
