package pl.mateusz;

import pl.mateusz.model.User;
import pl.mateusz.utils.Utils;

import java.util.Scanner;

public class Login {

    private User user;

    public static void userLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("    Zaloguj się");
        System.out.print("Podaj nzawę użytkownika: ");
        String name = scanner.nextLine();

        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();

        if (Utils.loged(name, password).getName() != null) {
            userMenuStart();
        }

    }
    public static void userMenu() {
        System.out.println("Menu");
        System.out.println("1. Zmień telefon");
        System.out.println("2. Zmień maila");
//        System.out.println("9. users");
        System.out.println("3. Wyloguj");
    }

    public static void userMenuStart() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 3) {
            userMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Zmiana numeru telefonu");
                    Utils.changePhone();
                    break;
                }
                case 2: {
                    System.out.println("Zmien maila");
                    Utils.changeEmail();
                    break;
                }
//            case 9: {
//                UsersDB.readUsersFromDB();
//                break;
//            }
                case 3: {
                    System.out.println("Wyloguj");
                    break;
                }
                default: {
                    System.out.println("nepoprawna wartość");
                    break;
                }
            }
        }
    }
}
