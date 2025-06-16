package org.example.page;

import org.example.page.diagnose.DiagnosePatientPage;
import org.example.page.patient.PatientDetailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PatientPage {
    WebDriver driver;

    public PatientPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed(){
        return driver.getCurrentUrl().endsWith("patient");
    }

    public String getFirstPatientRecordCode(){
        return driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody[1]/tr/td[2]")).getText();
    }

    public void goToFirstPatient(){
        driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody[1]/tr/td[7]/div/a")).click();
    }

    public String getPatientRecord(){
        return driver.findElement(By.xpath("/html/body/div/div[2]/main/div[1]/div/div/div[2]")).getText();
    }

    public DiagnosePatientPage getDiagnosePage(){
        return new DiagnosePatientPage(driver);
    }

    public PatientDetailPage getPatientDetailPage(){
        return new PatientDetailPage(driver);
    }
}
