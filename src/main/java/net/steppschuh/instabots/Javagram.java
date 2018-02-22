package net.steppschuh.instabots;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.steppschuh.instabots.database.Database;
import net.steppschuh.instabots.pages.ExplorePage;
import net.steppschuh.instabots.pages.LogInPage;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Javagram {

    public static final Logger LOGGER = Logger.getLogger(Javagram.class.getName());

    private static final String PROPERTIES_FILE_NAME = "javagram.properties";

    private static Javagram instance;

    private Properties properties;
    private Database database;

    private Javagram() {
        setupLogging();
        setupProperties();
        database = new Database();
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

        try {
            LogInPage logInPage = new LogInPage(chromeDriver);
            String user = properties.getProperty("INSTAGRAM_USER");
            String password = properties.getProperty("INSTAGRAM_PASSWORD");
            logInPage.logIn(user, password);

            ExplorePage explorePage = new ExplorePage(chromeDriver, "natgeo");
            explorePage.load();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        chromeDriver.quit();
    }

    private void setupProperties() {
        properties = new Properties();
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
            if (in == null) {
                throw new IOException("Properties file not found at 'src/main/resources/"
                        + PROPERTIES_FILE_NAME + "'. Please refer to the documentation.");
            }
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load properties", e);
        }
    }

    public void setupLogging() {
        LOGGER.setLevel(Level.FINEST);
        //Handler handler = new StreamHandler(System.out, new SimpleFormatter());
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINEST);
        LOGGER.addHandler(handler);
        LOGGER.setUseParentHandlers(false);
    }

}
