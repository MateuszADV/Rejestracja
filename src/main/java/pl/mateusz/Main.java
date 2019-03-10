package pl.mateusz;

import pl.mateusz.utils.Utils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UsersDB.addUserToDB();
        startMenu();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void menu() {
        System.out.println("Menu");
        System.out.println("1. Rejestracja");
        System.out.println("2. Logowanie");
//        System.out.println("9. users");
        System.out.println("3. Wyjście");
    }


    public static void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {
            menu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    Register.rejestracja();
                    break;
                }
                case 2: {
                    Login.userLogin();
                    break;
                }
//                case 9: {
//                    UsersDB.readUsersFromDB();
//                    break;
//                }
                case 3: {
                    System.out.println("Wyjście");
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
