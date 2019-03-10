package pl.mateusz.utils;

import pl.mateusz.Constans;
import pl.mateusz.DbConnector;
import pl.mateusz.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Utils {

    static Scanner scanner = new Scanner(System.in);

    public static String checkPassword(String password) {
        Pattern pattern = Pattern.compile(Constans.REGEX_PASSWORD);
        String pass = password;
        while (!pattern.matcher(pass).matches()) {
            System.out.println("hasło jest nie poprawne");
            pass = scanner.nextLine();
        }
        return pass;
    }


    public static void saveUser(User user) {
        DbConnector connector = DbConnector.getInstance();
        try {
//            Statement statement = connector.getConnection().createStatement();
            PreparedStatement statement = connector.getConnection().prepareStatement("INSERT INTO users VALUES (?,?,?,?)");
//            statement.execute("INSERT INTO users VALUES(?,?,?,?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhon());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String checkLogin(String login) {

        //TODO do zmiany
        String name = login;

        while (!(Utils.checkLoginLength(name))) {
            System.out.println("podana nazwa użytkownika  " + name + "  ma mniej niż 3 litery." );
            System.out.println("podaj inną nazwę użytkownika: ");
            name = scanner.nextLine();
            while ((Utils.checkLoginExist(name))) {
                System.out.println("Podana nazwa użytkownika " + name + " już istnieje");
                System.out.println("Podaj inną nazwe:");
                name = scanner.nextLine();
            }
        }

        while ((Utils.checkLoginExist(name))) {
            System.out.println("Podana nazwa użytkownika " + name + " już istnieje");
            System.out.println("Podaj inną nazwe:");
            name = scanner.nextLine();
            while (!(Utils.checkLoginLength(name))) {
                System.out.println("podana nazwa użytkownika  " + name + "  ma mniej niż 3 litery.");
                System.out.println("podaj inną nazwę użytkownika: ");
                name = scanner.nextLine();
            }
        }
        return name;
    }

    public static Boolean checkLoginExist(String login) {
        DbConnector connector = DbConnector.getInstance();
        try {
           PreparedStatement statement = connector.getConnection()
                   .prepareStatement("SELECT name " +
                                          " FROM users " +
                                          "WHERE name = ?");
           statement.setString(1, login);
           ResultSet resultSet = statement.executeQuery();
            return !resultSet.getBoolean("name");
        } catch (SQLException e) {
//            e.printStackTrace();
            return false;
        }

    }
    public static Boolean checkLoginLength(String login) {
        if (login.length() < 3) {
            return false;
        }
        return true;
    }

    public static String checkPhone(String phone) {
        Pattern pattern = Pattern.compile(Constans.REGEX_PHONE);
        String phoneNumber = phone;
        while (!pattern.matcher(phoneNumber).matches()) {
            System.out.println("Podany numer telefony: " + phone + " jest niprawidłowy (123-456-789)");
            phoneNumber = scanner.nextLine();
        }
        return phoneNumber;
    }

    public static String checkEmail(String email) {
        Pattern pattern = Pattern.compile(Constans.REGEX_MAIL);
        String mail = email;
        while (!pattern.matcher(mail).matches()) {
            System.out.println("Podany mail jest niepoprawny");
            System.out.print("Podaj poprawny mail: ");
            mail = scanner.nextLine();
        }

        return mail;
    }

    public static User loged(String login, String password) {
        String log = login;
        String pass = password;
        if (checkUserExist(log, pass).getName() != null) {
            System.out.println("udane logowanie: \n" +
                    " Witaj " + checkUserExist(log, pass).getName());
        } else {
            System.out.println("login lub hasło są niepoprawne:");
        }
        return checkUserExist(log, pass);

    }

    private static User user = new User();
    public static User checkUserExist(String login, String password) {

        DbConnector connector = DbConnector.getInstance();
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(" SELECT * FROM users " +
                                                                                          " WHERE name = ? " +
                                                                                          "   AND password = ?");
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setPhon(resultSet.getString("phone"));
        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println(new User());
            return new User();
        }
        return user;
    }

    public static Boolean changePhone() {
        Scanner scanner =new Scanner(System.in);
        String phon;
        try {
            System.out.println("Stary numer telefonu: " + user.getPhon());
            System.out.print("Nowy numer telefonu");
            phon = scanner.nextLine();
            user.setPhon(Utils.checkPhone(phon));
            saveNewPhone(user);
            System.out.println("Numer telefon został zmieniony");
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void saveNewPhone(User user) {
        DbConnector connector = DbConnector.getInstance();

        try {
            PreparedStatement statement =connector.getConnection().prepareStatement("UPDATE users set phone = ? WHERE name = ? ");
            statement.setString(1, user.getPhon());
            statement.setString(2, user.getName());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Boolean changeEmail() {
        Scanner scanner =new Scanner(System.in);
        String email;
        try {
            System.out.println("Stary mail: " + user.getEmail());
            System.out.print("Nowy mail: ");
            email = scanner.nextLine();
            user.setPhon(Utils.checkPhone(email));
            saveNewPhone(user);
            System.out.println("Mail został zmieniony");
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void saveNewMail(User user) {
        DbConnector connector = DbConnector.getInstance();

        try {
            PreparedStatement statement =connector.getConnection().prepareStatement("UPDATE users set email = ? WHERE name = ? ");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getName());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
