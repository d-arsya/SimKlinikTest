package org.example.page;

import org.example.page.queue.NewPatientNewOwner;
import org.example.page.queue.NewPatientOldOwner;
import org.example.page.queue.OldPatient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InpatientPage {
    WebDriver driver;
    public NewPatientNewOwner newPatientNewOwner;
    public OldPatient oldPatient;
    public NewPatientOldOwner newPatientOldOwner;

    public InpatientPage(WebDriver driver) {
        this.driver = driver;
        this.newPatientNewOwner = new NewPatientNewOwner(driver);
        this.newPatientOldOwner = new NewPatientOldOwner(driver);
        this.oldPatient = new OldPatient(driver);
    }

    public boolean isDisplayed(){
        return  driver.getCurrentUrl().endsWith("inpatient");
    }

    public void goToFirstQueue(){
        driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr[1]/td[7]/div/a")).click();
    }

    public int getInpatientAmount(){
        String amount = driver.findElement(By.xpath("/html/body/div/div[2]/main/div[1]/div[1]/div/p/span")).getText();
        return Integer.parseInt(amount);
    }
}
