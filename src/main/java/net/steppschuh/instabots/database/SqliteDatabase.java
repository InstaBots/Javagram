package net.steppschuh.instabots.database;

import net.steppschuh.instabots.Javagram;
import net.steppschuh.instabots.actions.Action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqliteDatabase implements Database {

    public static final Logger LOGGER = Javagram.LOGGER;

    private static final String DB_NAME = "javagram";
    private static final String DB_URL = "jdbc:sqlite:" + DB_NAME + ".db";

    private static final String COMMAND_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS";

    private static final String TABLE_USERS = "users";

    private Connection connection;

    public SqliteDatabase() {
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

    @Override
    public void persistPerformedAction(Action action) {
        // TODO: implement
        LOGGER.fine("Persisting performed action: " + action);
    }

    @Override
    public void persistQueuedAction(Action action) {
        // TODO: implement
        LOGGER.fine("Persisting queued action: " + action);
    }

    @Override
    public List<Action> getQueuedActions() {
        // TODO: implement
        return new ArrayList<>();
    }

    public Connection getConnection() {
        return connection;
    }

}
