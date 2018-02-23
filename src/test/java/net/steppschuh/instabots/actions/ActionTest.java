package net.steppschuh.instabots.actions;

import net.steppschuh.instabots.Javagram;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.*;

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