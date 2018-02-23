package net.steppschuh.instabots.actions;

import net.steppschuh.instabots.Javagram;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.*;

public class ActionTest {

    @Before
    public void setUp() throws Exception {
        Javagram.getInstance().logIn();
    }

    @After
    public void tearDown() throws Exception {
        Javagram.getChromeDriver().quit();
    }

}