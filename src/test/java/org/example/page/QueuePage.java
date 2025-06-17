package org.example.page;

import org.example.page.queue.NewPatientNewOwner;
import org.example.page.queue.NewPatientOldOwner;
import org.example.page.queue.OldPatient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QueuePage {
    WebDriver driver;
    WebDriverWait wait;
    public NewPatientNewOwner newPatientNewOwner;
    public OldPatient oldPatient;
    public NewPatientOldOwner newPatientOldOwner;

    public QueuePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
    public void oldPatientPopup(){
        driver.findElement(By.xpath("//*[@id=\"modal-old-patient\"]/button")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("table-old-patient"))));
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

    public void goToFirstQueue(){
        driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr[1]/td[7]/div/a")).click();
    }

    public int getQueAmount(){
        String amount = driver.findElement(By.xpath("/html/body/div/div[2]/main/div[1]/div[1]/div/p/span")).getText();
        return Integer.parseInt(amount);
    }
}