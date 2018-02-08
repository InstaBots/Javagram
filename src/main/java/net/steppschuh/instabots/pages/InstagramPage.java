package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.Javagram;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class InstagramPage {

    public static final Logger LOGGER = Javagram.LOGGER;
    public static final long TIMEOUT_DURATION_DEFAULT = TimeUnit.SECONDS.toMillis(10);
    public static final String HOME_URL = "https://www.instagram.com/";

    protected ChromeDriver chromeDriver;

    public InstagramPage(@Nonnull ChromeDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public abstract void load();

    public long getSuggestedTimeoutInSeconds() {
        return TimeUnit.MILLISECONDS.toSeconds(TIMEOUT_DURATION_DEFAULT);
    }

}
