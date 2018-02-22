package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.Javagram;
import net.steppschuh.instabots.utils.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class InstagramPage {

    public static final Logger LOGGER = Javagram.LOGGER;
    public static final long TIMEOUT_DURATION_DEFAULT = TimeUnit.SECONDS.toMillis(10);
    public static final String HOME_URL = "https://www.instagram.com/";

    protected ChromeDriver chromeDriver;

    public InstagramPage() {
        this.chromeDriver = Javagram.getChromeDriver();
    }

    public void load() {
        LOGGER.fine("Requesting: " + getUrl());
        chromeDriver.get(getUrl());

        if (!chromeDriver.findElements(By.className("error-container")).isEmpty()) {
            WebElement errorElement = chromeDriver.findElement(By.tagName("h2"));
            throw new IllegalArgumentException(errorElement.getText());
        }
    }

    public abstract String getUrl();

    public long getSuggestedTimeoutInSeconds() {
        return TimeUnit.MILLISECONDS.toSeconds(TIMEOUT_DURATION_DEFAULT);
    }

    public boolean hasElement(@Nonnull By by) {
        return WebElementUtil.hasElement(chromeDriver, by);
    }

    /*
        Getter & Setter
     */

    public ChromeDriver getChromeDriver() {
        return chromeDriver;
    }

}
