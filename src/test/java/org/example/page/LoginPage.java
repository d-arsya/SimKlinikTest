package org.example.page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed(){
        return driver.findElement(By.xpath("/html/body/div/div[1]/div/form/div[4]/button")).isDisplayed();
    }

    public void fillEmail(String email){
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
    }

    public void fillPassword(String password){
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
    }

    public void submitLogin(){
        driver.findElement(By.xpath("/html/body/div/div[1]/div/form/div[4]/button")).click();
    }

    public boolean isFailed(){
        String actualText = driver.findElement(By.xpath("//*[@id=\"toast-success\"]/div[2]")).getText();
        return actualText.equals("Gagal login");
    }

    public boolean isAlertDisplayed(){
        String validationMessage = (String) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].validationMessage;", driver.findElement(By.xpath("//*[@id=\"email\"]")));

        return validationMessage != null && !validationMessage.isEmpty();
    }

    public void goToForgotPage(){
        driver.findElement(By.linkText("Forgot password?")).click();
    }
}
