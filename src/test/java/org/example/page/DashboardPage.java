package org.example.page;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    WebDriver driver;
    Dotenv dotenv = Dotenv.load();
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed(){
        return driver.getCurrentUrl().endsWith("dashboard");
    }

    public boolean isCurrentUser(String name){
        return driver.findElement(By.xpath("/html/body/div/div[2]/main/div/div[3]/div/h2")).getText().contains(name);
    }

    public void logout(){
        driver.findElement(By.xpath("//*[@id=\"profile-menu-button\"]/img")).click();
        driver.findElement(By.xpath("//*[@id=\"profile-menu\"]/ul/li/form")).submit();
    }

    public void goToProfile(){
        driver.get(dotenv.get("SIMKLINIK_URL")+"/profile");

    }

    public void goToQueue(){
        driver.get(dotenv.get("SIMKLINIK_URL")+"/queue");
    }
    public void goToInvoice(){
        driver.get(dotenv.get("SIMKLINIK_URL")+"/invoice");
    }

    public void goToPatient(){
        driver.get(dotenv.get("SIMKLINIK_URL")+"/patient");
    }

    public void goToInpatient(){
        driver.get(dotenv.get("SIMKLINIK_URL")+"/inpatient");
    }
}
