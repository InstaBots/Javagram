package net.steppschuh.instabots.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.steppschuh.instabots.Javagram;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Handler;
import java.util.logging.Level;

import static org.junit.Assert.*;

public class InstagramPageTest {

    protected static ChromeDriver chromeDriver;

    @BeforeClass
    public static void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();

        Javagram.getInstance().setupLogging();
        for (Handler handler : Javagram.LOGGER.getHandlers()) {
            handler.setLevel(Level.WARNING);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        chromeDriver.quit();
    }

}