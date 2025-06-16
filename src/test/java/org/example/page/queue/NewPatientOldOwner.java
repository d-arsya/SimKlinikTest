package org.example.page.queue;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;

public class NewPatientOldOwner {
    WebDriver driver;
    Dotenv dotenv = Dotenv.load();
    public NewPatientOldOwner(WebDriver driver){
        this.driver = driver;
    }
}
