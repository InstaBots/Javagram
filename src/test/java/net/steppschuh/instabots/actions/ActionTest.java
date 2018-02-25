package net.steppschuh.instabots.actions;

import net.steppschuh.instabots.Javagram;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class ActionTest {

    @BeforeClass
    public static void setUp() throws Exception {
        Javagram.getInstance().logIn();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Javagram.getChromeDriver().quit();
    }

}