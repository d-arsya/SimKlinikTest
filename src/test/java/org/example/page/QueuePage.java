package org.example.page;

import org.example.page.queue.NewPatientNewOwner;
import org.example.page.queue.NewPatientOldOwner;
import org.example.page.queue.OldPatient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class QueuePage {
    WebDriver driver;
    public NewPatientNewOwner newPatientNewOwner;
    public OldPatient oldPatient;
    public NewPatientOldOwner newPatientOldOwner;

    public QueuePage(WebDriver driver) {
        this.driver = driver;
        this.newPatientNewOwner = new NewPatientNewOwner(driver);
        this.newPatientOldOwner = new NewPatientOldOwner(driver);
        this.oldPatient = new OldPatient(driver);
    }

    public boolean isDisplayed(){
        return  driver.getCurrentUrl().endsWith("queue");
    }

    public void newPatienPopup(){
        driver.findElement(By.xpath("//*[@id=\"modal-add-queue\"]/button")).click();
    }
    public void oldPatienPopup(){
        driver.findElement(By.xpath("/html/body/div/div[2]/main/div[1]/div[2]/div/div[1]/button")).click();
    }
    public void newOwner(){
        driver.findElement(By.xpath("//*[@id=\"modal-add-queue\"]/div/div/div[2]/button[2]")).click();
    }
    public void oldOwner(){
        driver.findElement(By.xpath("//*[@id=\"modal-add-queue\"]/div/div/div[2]/button[1]")).click();
    }

    public void newPatientNewOwner(){
        newPatienPopup();
        newOwner();
    }
    public void newPatientOldOwner(){
        newPatienPopup();
        oldOwner();
    }
}
