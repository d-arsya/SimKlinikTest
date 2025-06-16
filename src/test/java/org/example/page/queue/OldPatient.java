package org.example.page.queue;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;

public class OldPatient {
    WebDriver driver;
    Dotenv dotenv = Dotenv.load();
    public OldPatient(WebDriver driver){
        this.driver= driver;
    }
}
