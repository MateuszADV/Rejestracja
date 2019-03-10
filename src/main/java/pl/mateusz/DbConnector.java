package pl.mateusz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    private static final String SQL_LINK = "jdbc:sqlite:rekrutacja.db";
    private static final String SQL_USER = "sa";
    private static final String SQL_PASS = "sa";
    private static final String SQL_CLASS = "org.sqlite.JDBC";

    private static DbConnector connector = new DbConnector();
    public static DbConnector getInstance() {
        return connector;
    }

    private Connection connection;

    private DbConnector() {
        connect();
    }

    private void connect() {
        try {
            Class.forName(SQL_CLASS);
            connection = DriverManager.getConnection(SQL_LINK,SQL_USER,SQL_PASS);
//            System.out.println("połączono");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
