package org.example.page.queue;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewPatientOldOwner {
    WebDriver driver;
    WebDriverWait wait;

    public NewPatientOldOwner(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void submitOwner(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"table-old-owner-new-patient\"]/tbody/tr[1]/td[5]/button")));
        driver.findElement(By.xpath("//*[@id=\"table-old-owner-new-patient\"]/tbody/tr[1]/td[5]/button")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("patientForm"))));
    }

    public void setPatientName(String name){
        driver.findElement(By.xpath("//*[@id=\"patientForm\"]//input[@name=\"name\"]")).sendKeys(name);
    }
    public void setPatientGender(String gender){
        WebElement genderOption = driver.findElement(By.xpath("//*[@id=\"patientForm\"]//select[@name=\"gender\"]"));
        Select genderSelect = new Select(genderOption);
        genderSelect.selectByVisibleText(gender);
    }
    public void setColor(String color){
        WebElement genderOption = driver.findElement(By.xpath("//*[@id=\"patientForm\"]//select[@name=\"color_id\"]"));
        Select genderSelect = new Select(genderOption);
        genderSelect.selectByVisibleText(color);
    }
    public void setAnimal(String animal){
        WebElement genderOption = driver.findElement(By.xpath("//*[@id=\"patientForm\"]//select[@name=\"animal_id\"]"));
        Select genderSelect = new Select(genderOption);
        genderSelect.selectByVisibleText(animal);
    }
    public void setVariant(String variant){
        WebElement cityOption = driver.findElement(By.xpath("//*[@id=\"patientForm\"]//select[@name=\"type_id\"]"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"patientForm\"]//select[@name=\"type_id\"]"), variant));
        Select citySelect = new Select(cityOption);
        citySelect.selectByVisibleText(variant);
    }
    public void setBirth(String birth){
        WebElement dateInput = driver.findElement(By.xpath("//*[@id=\"patientForm\"]//input[@name=\"birth\"]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", dateInput, birth);
    }
    public String submitPatient(){
        driver.findElement(By.xpath("//*[@id=\"patientForm\"]/div[7]/button")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("form-new-owner-new-patient"))));
        return text;
    }

    public void confirmPatient(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("confirm-add-button-new-patient"))));
        driver.findElement(By.id("confirm-add-button-new-patient")).click();
    }

    public void setWeight(int weight){
        driver.findElement(By.xpath("//*[@id=\"form-new-owner-new-patient\"]//input[@id=\"weight\"]")).sendKeys(Integer.toString(weight));
    }
    public void setPulse(int pulse){
        driver.findElement(By.xpath("//*[@id=\"form-new-owner-new-patient\"]//input[@id=\"pulse\"]")).sendKeys(Integer.toString(pulse));
    }
    public void setTemp(int temp){
        driver.findElement(By.xpath("//*[@id=\"form-new-owner-new-patient\"]//input[@id=\"temperature\"]")).sendKeys(Integer.toString(temp));
    }
    public void setBreath(int breath){
        driver.findElement(By.xpath("//*[@id=\"form-new-owner-new-patient\"]//input[@id=\"breath\"]")).sendKeys(Integer.toString(breath));
    }
    public void setService(String service){
        WebElement genderOption = driver.findElement(By.xpath("//*[@id=\"form-new-owner-new-patient\"]//select[@name=\"service_id\"]"));
        Select genderSelect = new Select(genderOption);
        genderSelect.selectByVisibleText(service);
    }

    public void submitCheckup(){
        driver.findElement(By.xpath("//*[@id=\"form-new-owner-new-patient\"]/div[7]/button")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("form-new-owner-new-patient"))));
    }
}