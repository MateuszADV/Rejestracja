package pl.mateusz;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDB {

    public static void addUserToDB() {
        DbConnector connector = DbConnector.getInstance();

        try {
            Statement statement = connector.getConnection().createStatement();
//            statement.execute("CREATE TABLE users(name VARCHAR(25), password VARCHAR(25), email VARCHAR(100), phone VARCHAR (20))" );
//            statement.execute("CREATE TABLE users(name CHAR(25), password CHAR(25), email CHAR(100), phone CHAR(20))" );
//            statement.execute("INSERT INTO users VALUES('Mateusz', '12345', 'mat@gmail.com', '123 456 789')");
//            statement.execute("INSERT INTO users VALUES('ADA', '45678', 'ada@gmail.com', '321 654 987')");
//            statement.execute("INSERT INTO users VALUES('Jacek', '45678', 'jacek@hotmail.com', '741 528 569')");
//            statement.execute("INSERT INTO users VALUES('Marek', '1We@jfrge', 'marek@interia.com', '852 879 569')");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void readUsersFromDB() {
        DbConnector connector = DbConnector.getInstance();

        String name;
        String password;
        String email;
        String phone;

        try {
            Statement statement = connector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            System.out.printf("\033[0;33m"  +"| %-12s | %-12s | %-20s | %-15s|\u001B[0m \n", "Imie", "Hasło", "Email", "Phone","\n");
            System.out.println("-----------------------------------------------------------------------");

            while (resultSet.next()) {
                name = resultSet.getString("name");
                password = resultSet.getString("password");
                email = resultSet.getString("email");
                phone = resultSet.getString("phone");

                System.out.printf("| %-12s | %-12s | %-20s | %-15s|\n", name, password, email, phone);
            }

            System.in.read();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
