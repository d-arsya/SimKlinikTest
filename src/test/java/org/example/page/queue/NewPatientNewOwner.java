package org.example.page.queue;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        driver.switchTo().alert().accept();
        return alert.getText();
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
        WebElement cityOption = driver.findElement(By.xpath("//*[@id=\"patientForm\"]//select[@name=\"variant_id\"]"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"patientForm\"]//select[@name=\"variant_id\"]"), variant));
        Select citySelect = new Select(cityOption);
        citySelect.selectByVisibleText(variant);
    }
    public void setBirth(String birth){
        driver.findElement(By.xpath("//*[@id=\"patientForm\"]//select[@name=\"birth\"]")).sendKeys(birth);
    }
    public String submitPatient(){
        driver.findElement(By.xpath("//*[@id=\"patientForm\"]/div[7]/button")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        return alert.getText();
    }

    public void confirmPatient(){
        driver.findElement(By.xpath("//*[@id=\"modal-add-queue\"]/div/div/div[3]/div[3]/div/button[2]")).click();
    }
}
