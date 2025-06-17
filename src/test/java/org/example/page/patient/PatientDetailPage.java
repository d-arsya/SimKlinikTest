package org.example.page.patient;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PatientDetailPage {
    WebDriver driver;
    Dotenv dotenv = Dotenv.load();

    public PatientDetailPage(WebDriver driver){
        this.driver = driver;
    }

    public void goToEditPatient(){
        driver.findElement(By.xpath("/html/body/div/div[2]/main/div[2]/div[1]/a")).click();
    }

    public String getName(){
        return driver.findElement(By.xpath("/html/body/div/div[2]/main/div[2]/div[2]/div/div[2]/div[1]")).getText();
    }

    public void setName(String name){
        driver.findElement(By.xpath("//input[@name=\"name\"]")).clear();
        driver.findElement(By.xpath("//input[@name=\"name\"]")).sendKeys(name);
    }

    public void submitEditForm(){
        driver.findElement(By.xpath("//form[@id=\"submitForm\"]")).submit();
    }

    public void goToDiagnoseHistory(){
        driver.findElement(By.xpath("/html/body/div/div[2]/main/div[3]/table/tbody/tr[1]/td[7]/a")).click();
    }

    public String getFirstAnamnesa(){
        return driver.findElement(By.xpath("/html/body/div/div[2]/main/div[3]/table/tbody/tr[1]/td[3]")).getText();
    }
}
