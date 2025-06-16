package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed(){
        return driver.getCurrentUrl().endsWith("profile");
    }

    public  void goToEdit(){
        driver.findElement(By.xpath("/html/body/div/div[2]/main/div[2]/div[1]/a")).click();
    }

    public void setName(String name){
        driver.findElement(By.xpath("//*[@id=\"submitForm\"]//input[@name=\"name\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"submitForm\"]//input[@name=\"name\"]")).sendKeys(name);
    }

    public void setSpecialization(String specialization){
        driver.findElement(By.xpath("//*[@id=\"submitForm\"]//input[@name=\"specialization\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"submitForm\"]//input[@name=\"specialization\"]")).sendKeys(specialization);
    }

    public void submitEditForm(){
        driver.findElement(By.xpath("//*[@id=\"submitForm\"]")).submit();
    }

    public String getName(){
        return driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/p[2]")).getText();
    }

    public String getSpecialization(){
        return driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/p[4]")).getText();
    }
}
