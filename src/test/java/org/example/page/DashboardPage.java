package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    WebDriver driver;

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
        driver.findElement(By.xpath("/html/body/div/div[2]/main/div/div[1]/a")).click();
    }

    public void goToQueue(){
        driver.findElement(By.xpath("//*[@id=\"logo-sidebar\"]/div/ul/li[2]/a")).click();
    }
}
