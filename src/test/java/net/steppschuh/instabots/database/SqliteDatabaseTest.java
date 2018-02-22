package net.steppschuh.instabots.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class SqliteDatabaseTest {

    private SqliteDatabase sqliteDatabase;

    @Before
    public void setUp() throws Exception {
        sqliteDatabase = new SqliteDatabase();
    }

    @After
    public void tearDown() throws Exception {
        sqliteDatabase.getConnection().close();
    }

    @Test
    public void connect() {
        Connection connection = sqliteDatabase.getConnection();
    }

}