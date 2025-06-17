
package org.example.page.queue;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OldPatient {
    WebDriver driver;
    WebDriverWait wait;

    public OldPatient(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void submitPatient(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"table-old-patient\"]/tbody/tr[1]")));

        WebElement button = driver.findElement(By.xpath("//*[@id=\"table-old-patient\"]/tbody/tr[1]/td[7]/button"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm-old-patient")));
        driver.findElement(By.xpath("//*[@id=\"confirm-old-patient\"]/button[2]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("old-patient-checkup")));
    }



    public void setWeight(int weight){
        driver.findElement(By.xpath("//*[@id=\"old-patient-checkup\"]//input[@id=\"weight\"]")).sendKeys(Integer.toString(weight));
    }
    public void setPulse(int pulse){
        driver.findElement(By.xpath("//*[@id=\"old-patient-checkup\"]//input[@id=\"pulse\"]")).sendKeys(Integer.toString(pulse));
    }
    public void setTemp(int temp){
        driver.findElement(By.xpath("//*[@id=\"old-patient-checkup\"]//input[@id=\"temperature\"]")).sendKeys(Integer.toString(temp));
    }
    public void setBreath(int breath){
        driver.findElement(By.xpath("//*[@id=\"old-patient-checkup\"]//input[@id=\"breath\"]")).sendKeys(Integer.toString(breath));
    }
    public void setService(String service){
        WebElement genderOption = driver.findElement(By.xpath("//*[@id=\"old-patient-checkup\"]//select[@name=\"service_id\"]"));
        Select genderSelect = new Select(genderOption);
        genderSelect.selectByVisibleText(service);
    }

    public void submitCheckup(){
        driver.findElement(By.xpath("//*[@id=\"old-patient-checkup\"]/div[7]/button")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("old-patient-checkup"))));
    }
}
