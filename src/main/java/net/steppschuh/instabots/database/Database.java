package net.steppschuh.instabots.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    public static final Logger LOGGER = Logger.getLogger(Database.class.getName());

    private static final String DB_NAME = "javagram";
    private static final String DB_URL = "jdbc:sqlite:" + DB_NAME + ".db";

    private static final String COMMAND_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS";

    private static final String TABLE_USERS = "users";

    private Connection connection;

    public Database() {
        connect();
        createTables();
    }

    private void connect() {
        LOGGER.fine("Connecting to database: " + DB_NAME);
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unable to connect to database", e);
            throw new RuntimeException(e);
        }
    }

    private void createTables() {
        StringBuilder sqlBuilder = new StringBuilder();

        // users table
        sqlBuilder.append(COMMAND_CREATE_TABLE).append(" ").append(TABLE_USERS)
                .append(" (\n")
                .append(" id text PRIMARY KEY,\n")
                .append(" name text\n")
                .append(" );");

        try {
            Statement statement = connection.createStatement();
            statement.execute(sqlBuilder.toString());
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Unable to create tables", e);
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
