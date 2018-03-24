package net.steppschuh.instabots.bots;

import net.steppschuh.instabots.Javagram;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Logger;

public abstract class Bot {

    public static final Logger LOGGER = Javagram.LOGGER;

    protected ChromeDriver chromeDriver;
    protected boolean isRunning;

    public Bot() {
        this.chromeDriver = Javagram.getChromeDriver();
    }

    public void start() {
        if (isRunning) {
            return;
        }
        LOGGER.fine("Starting bot: " + this);
        isRunning = true;
        onStart();
    }

    protected abstract void onStart();

    public void stop() {
        if (!isRunning) {
            return;
        }
        LOGGER.fine("Stopping bot: " + this);
        isRunning = false;
        onStop();
    }

    protected abstract void onStop();

}
