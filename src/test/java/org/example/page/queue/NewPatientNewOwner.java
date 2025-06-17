package org.example.page.queue;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewPatientNewOwner {
    WebDriver driver;
    WebDriverWait wait;
    Dotenv dotenv = Dotenv.load();

    public NewPatientNewOwner(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void setName(String name){
        driver.findElement(By.xpath("//*[@id=\"ownerForm\"]//input[@name=\"name\"]")).sendKeys(name);
    }
    public void setPhone(String phone){
        driver.findElement(By.xpath("//*[@id=\"ownerForm\"]//input[@name=\"phone\"]")).sendKeys(phone);
    }
    public void setGender(String gender){
        WebElement genderOption = driver.findElement(By.xpath("//*[@id=\"ownerForm\"]//select[@name=\"gender\"]"));
        Select genderSelect = new Select(genderOption);
        genderSelect.selectByVisibleText(gender);
    }
    public void setProvince(String province){
        WebElement provinceOption = driver.findElement(By.xpath("//*[@id=\"ownerForm\"]//select[@name=\"province\"]"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"ownerForm\"]//select[@name=\"province\"]"), province));
        Select provinceSelect = new Select(provinceOption);
        provinceSelect.selectByVisibleText(province);
    }
    public void setCity(String city){
        WebElement cityOption = driver.findElement(By.xpath("//*[@id=\"ownerForm\"]//select[@name=\"city\"]"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"ownerForm\"]//select[@name=\"city\"]"), city));
        Select citySelect = new Select(cityOption);
        citySelect.selectByVisibleText(city);
    }
    public void setDistrict(String district) {
        WebElement districtOption = driver.findElement(By.xpath("//*[@id=\"ownerForm\"]//select[@name=\"district\"]"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"ownerForm\"]//select[@name=\"district\"]"), district));
        Select districtSelect = new Select(districtOption);
        districtSelect.selectByVisibleText(district);
    }
    public void setVillage(String village) {
        WebElement villageOption = driver.findElement(By.xpath("//*[@id=\"ownerForm\"]//select[@name=\"village\"]"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"ownerForm\"]//select[@name=\"village\"]"), village));
        Select villageSelect = new Select(villageOption);
        villageSelect.selectByIndex(1);
    }
    public void setAddress(String address) {
        driver.findElement(By.xpath("//*[@id=\"ownerForm\"]//input[@name=\"address\"]")).sendKeys(address);
    }
    public String submitOwner(){
        driver.findElement(By.xpath("//*[@id=\"ownerForm\"]/div[7]/button")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
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
