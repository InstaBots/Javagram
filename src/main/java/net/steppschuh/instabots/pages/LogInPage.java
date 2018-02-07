package net.steppschuh.instabots.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nonnull;

public class LogInPage extends InstagramPage {

    public static final String LOG_IN_URL = "https://www.instagram.com/accounts/login/";

    private WebElement userInput;
    private WebElement passwordInput;
    private WebElement logInButton;

    private boolean loggedIn = false;

    public LogInPage(@Nonnull ChromeDriver chromeDriver) {
        super(chromeDriver);
    }

    @Override
    public void load() {
        chromeDriver.get(LOG_IN_URL);

        userInput = chromeDriver.findElement(By.name("username"));
        passwordInput = chromeDriver.findElement(By.name("password"));
        logInButton = chromeDriver.findElement(By.tagName("button"));
    }

    public void logIn(@Nonnull String user, @Nonnull String password) throws IllegalStateException {
        LOGGER.fine("Initiating log in for user: " + user);

        // navigate to log in page
        load();

        // enter username
        userInput.sendKeys(user);

        // enter password
        passwordInput.sendKeys(password);

        // perform login
        logInButton.click();

        // wait for success or error state
        WebDriverWait wait = new WebDriverWait(chromeDriver, getSuggestedTimeoutInSeconds());
        wait.until(ExpectedConditions.or(
                ExpectedConditions.elementToBeClickable(By.className("logged-in")),
                ExpectedConditions.elementToBeClickable(By.id("slfErrorAlert"))
        ));

        // check if we're still on the log in page
        if (LOG_IN_URL.equals(chromeDriver.getCurrentUrl())) {
            loggedIn = false;
            WebElement errorMessage = chromeDriver.findElement(By.id("slfErrorAlert"));
            throw new IllegalStateException("Log in failed: " + errorMessage.getText());
        } else {
            loggedIn = true;
            LOGGER.fine("Log in succeeded");
        }
    }

}
