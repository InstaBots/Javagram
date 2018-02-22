package net.steppschuh.instabots.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() throws Exception {
        database = new Database();
    }

    @After
    public void tearDown() throws Exception {
        database.getConnection().close();
    }

    @Test
    public void connect() {
        Connection connection = database.getConnection();
    }

}