package net.steppschuh.instabots;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.steppschuh.instabots.bots.Bot;
import net.steppschuh.instabots.bots.PostLikingBot;
import net.steppschuh.instabots.database.Database;
import net.steppschuh.instabots.database.SqliteDatabase;
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
    private ChromeDriver chromeDriver;

    private Javagram() {
        setupLogging();
        setupProperties();
        setupChromeDriver();
        database = new SqliteDatabase();
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
        try {
            LogInPage logInPage = new LogInPage();
            String user = properties.getProperty("INSTAGRAM_USER");
            String password = properties.getProperty("INSTAGRAM_PASSWORD");
            logInPage.logIn(user, password);

            Bot bot = new PostLikingBot();
            bot.start();

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
                throw new IOException("Properties file not found at 'src/main/resources/" + PROPERTIES_FILE_NAME
                        + "'.\nPlease refer to the documentation: https://github.com/InstaBots/Javagram");
            }
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load properties", e);
        }
    }

    private void setupLogging() {
        LOGGER.setLevel(Level.FINEST);
        //Handler handler = new StreamHandler(System.out, new SimpleFormatter());
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINEST);
        LOGGER.addHandler(handler);
        LOGGER.setUseParentHandlers(false);
    }

    private void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
    }

    public static Database getDatabase() {
        return getInstance().database;
    }

    public static ChromeDriver getChromeDriver() {
        return getInstance().chromeDriver;
    }

}
