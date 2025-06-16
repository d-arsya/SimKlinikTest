package org.example.page.diagnose;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DiagnosePatientPage {
    WebDriver driver;
    WebDriverWait wait;

    public DiagnosePatientPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillSymptom(String symptom){
        driver.findElement(By.xpath("//*[@id=\"checkup\"]//textarea[@name=\"symptom\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"checkup\"]//textarea[@name=\"symptom\"]")).sendKeys(symptom);
    }
    public void fillAnamnesis(String anamnesis){
        driver.findElement(By.xpath("//*[@id=\"checkup\"]//textarea[@name=\"symptom\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"checkup\"]//textarea[@name=\"symptom\"]")).sendKeys(anamnesis);
    }

    public void fillDiagnose(int index){
        WebElement genderOption = driver.findElement(By.xpath("//form[@id=\"checkup\"]//select[@id=\"diagnoses-option\"]"));
        Select genderSelect = new Select(genderOption);
        genderSelect.selectByIndex(index);
        driver.findElement(By.xpath("//*[@id=\"checkup\"]/div/div[2]/div[1]/div[1]/button")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//form[@id=\"checkup\"]//select[@id=\"diagnoses-option\"]"),"value",""));
    }

    public void fillService(int index){
        WebElement genderOption = driver.findElement(By.xpath("//form[@id=\"checkup\"]//select[@id=\"services-option\"]"));
        Select genderSelect = new Select(genderOption);
        genderSelect.selectByIndex(index);
        driver.findElement(By.xpath("//*[@id=\"checkup\"]/div/div[2]/div[2]/div[1]/button")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//form[@id=\"checkup\"]//select[@id=\"services-option\"]"),"value",""));
    }

    public void fillDrug(int amount){
        WebElement genderOption = driver.findElement(By.xpath("//form[@id=\"checkup\"]//select[@id=\"drug-category\"]"));
        Select genderSelect = new Select(genderOption);
        genderSelect.selectByIndex(1);
        wait.until(driver -> {
            List<WebElement> options = driver.findElements(By.xpath("//form[@id='checkup']//select[@id='drug-option']/option"));
            return options.size() > 0;
        });
        WebElement drugOption = driver.findElement(By.xpath("//form[@id=\"checkup\"]//select[@id=\"drug-option\"]"));
        Select drugSelect = new Select(drugOption);
        drugSelect.selectByIndex(1);
        driver.findElement(By.xpath("//form[@id=\"checkup\"]//input[@id=\"drug-quantity\"]")).sendKeys(Integer.toString(amount));
        driver.findElement(By.xpath("//*[@id=\"checkup\"]/div/div[3]/div[1]/div[1]/div[3]/div/button")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//form[@id=\"checkup\"]//select[@id=\"drug-option\"]"),"value",""));
    }

    public void submitToEnd(){
        driver.findElement(By.xpath("//*[@id=\"btn-submit\"]")).click();
    }

    public void submitToInpatient(){
        driver.findElement(By.xpath("//*[@id=\"btn-inpatient\"]")).click();
    }

    public String getAnamnesa(){
        return driver.findElement(By.xpath("/html/body/div/div[2]/main/div[3]/div[4]/div[2]")).getText();
    }
}
