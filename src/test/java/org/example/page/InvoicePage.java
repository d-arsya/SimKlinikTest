package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InvoicePage {
    WebDriver driver;

    public InvoicePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed(){
        return driver.getCurrentUrl().endsWith("invoice");
    }

    public int getFirstPaidInvoiceCode(){
        int link = 0;
        for (int i = 1; i < 10; i++) {
            String ai = driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+i+"]/td[9]/a")).getAttribute("href");
            if (!ai.endsWith("edit")){
                link = i;
                break;
            }
        }
        return link;
    }

    public int getFirstUnpaidInvoiceCode(){
        int link = 0;
        for (int i = 1; i < 10; i++) {
            String ai = driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+i+"]/td[9]/a")).getAttribute("href");
            if (ai.endsWith("edit")){
                link = i;
                break;
            }
        }
        return link;
    }

    public String getFirstInvoiceCode(){
        return driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+getFirstPaidInvoiceCode()+"]/td[1]")).getText();
    }

    public void goToFirstInvoiceCode(){
        driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+getFirstPaidInvoiceCode()+"]/td[9]/a")).click();
    }

    public void goToFirstUnpaidInvoice(){
        driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+getFirstUnpaidInvoiceCode()+"]/td[9]/a")).click();
    }

    public String getInvoiceCode(){
        return driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div/div[2]/p[2]")).getText();
    }

    public void addDisc(int discount){
        driver.findElement(By.id("discount-check")).click();
        driver.findElement(By.id("discount-value")).sendKeys(Integer.toString(discount));
    }

    public void addDiscService(){
        driver.findElement(By.id("gratis-pemeriksaan")).click();
    }

    public void removeDisc(){
        driver.findElement(By.id("discount-check")).click();
    }

    public String getDisc(){
        return driver.findElement(By.id("disc-val")).getText();
    }
    public String getAmount(){
        return driver.findElement(By.id("total-harga")).getText();
    }
}
