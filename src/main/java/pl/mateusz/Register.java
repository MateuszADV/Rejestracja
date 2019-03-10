package pl.mateusz;

import pl.mateusz.model.User;
import pl.mateusz.utils.Utils;

import java.util.Scanner;

public class Register {

    public static User rejestracja() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("REJESTRACJA\n");
        System.out.println("podaj nazwę użytkownika min 3 znaki");
        String name = scanner.nextLine();
        user.setName(Utils.checkLogin(name));
        System.out.println("Podaj hasło (min 8 znaków, musi zawierać 1 liczbę, jedną dużą litere, i zankspecjalny");
        String password = scanner.nextLine();
        user.setPassword(Utils.checkPassword(password));
        System.out.println("email");
        String email = scanner.nextLine();
        user.setEmail(Utils.checkEmail(email));
        System.out.println("telefon");
        String phone = scanner.nextLine();
        user.setPhon(Utils.checkPhone(phone));

        Utils.saveUser(user);

        System.out.println("--- REJESTRACJA SIĘ POWIODŁA ---");
        return user;
    }
}
