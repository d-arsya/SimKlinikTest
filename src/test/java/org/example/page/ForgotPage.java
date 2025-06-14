package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPage {
    WebDriver driver;

    public ForgotPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillEmail(String email){
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
    }

    public void submitForm(){
        driver.findElement(By.xpath("/html/body/div/div[1]/div/form/div[2]/button")).click();
    }

    public boolean isDisplayed(){
        return driver.getCurrentUrl().endsWith("forgot");
    }

    public boolean isSuccess() {
        try {
            // Wait for the toast message to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toastMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id=\"toast-success\"]/div[2]")
                    )
            );

            // Get the text and compare
            String actualText = toastMessage.getText();
            return actualText.equals("Link reset password terkirim");
        } catch (Exception e) {
            return false; // Return false if element not found or text doesn't match
        }
    }
}
