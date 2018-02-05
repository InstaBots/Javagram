package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.Javagram;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Logger;

public class InstagramPage {

    public static final String HOME = "https://www.instagram.com";

    public static final Logger LOGGER = Javagram.LOGGER;

    protected ChromeDriver chromeDriver;

    public InstagramPage(ChromeDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

}
