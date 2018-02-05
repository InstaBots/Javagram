package net.steppschuh.instabots.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LogInPage extends InstagramPage {

    public static final String URL = "https://www.instagram.com/accounts/login/";

    public LogInPage(ChromeDriver chromeDriver) {
        super(chromeDriver);
    }

    public void logIn(String user, String password) {
        LOGGER.fine("Initiating log in");

        chromeDriver.get(URL);

        WebElement userInput = chromeDriver.findElement(By.name("username"));
        userInput.sendKeys(user);

        WebElement passwordInput = chromeDriver.findElement(By.name("password"));
        passwordInput.sendKeys(password);

        WebElement logInButton = chromeDriver.findElement(By.tagName("button"));
        logInButton.click();
    }

}
