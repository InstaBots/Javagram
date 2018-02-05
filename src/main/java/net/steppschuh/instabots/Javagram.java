package net.steppschuh.instabots;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import net.steppschuh.instabots.pages.LogInPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.*;

public class Javagram {

    public static final Logger LOGGER = Logger.getLogger(Javagram.class.getName());

    private static Javagram instance;

    private Properties properties;

    private Javagram() {
        setupLogging();
        properties = loadProperties();
    }

    public static Javagram getInstance() {
        if (instance == null) {
            instance = new Javagram();
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance().start();
    }

    private void start() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver();

        //chromeDriver.get(InstagramPage.HOME);

        LogInPage logInPage = new LogInPage(chromeDriver);
        String user = properties.getProperty("INSTAGRAM_USER");
        String password = properties.getProperty("INSTAGRAM_PASSWORD");
        logInPage.logIn(user, password);

        chromeDriver.quit();
    }

    private Properties loadProperties() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("javagram.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Unable to load properties", e);
        }
        return properties;
    }

    private void setupLogging() {
        LOGGER.setLevel(Level.FINEST);
        //Handler handler = new StreamHandler(System.out, new SimpleFormatter());
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINEST);
        LOGGER.addHandler(handler);
        LOGGER.setUseParentHandlers(false);
    }

}
