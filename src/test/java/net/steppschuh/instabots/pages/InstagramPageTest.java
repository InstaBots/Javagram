package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.Javagram;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.logging.Handler;
import java.util.logging.Level;

public class InstagramPageTest {

    @BeforeClass
    public static void setUp() throws Exception {
        for (Handler handler : Javagram.LOGGER.getHandlers()) {
            handler.setLevel(Level.WARNING);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Javagram.getChromeDriver().quit();
    }

}