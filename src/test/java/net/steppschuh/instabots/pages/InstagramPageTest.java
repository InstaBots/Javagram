package net.steppschuh.instabots.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class InstagramPageTest {

    protected static ChromeDriver chromeDriver;

    @BeforeClass
    public static void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        chromeDriver.quit();
    }

}