//Richard Pettitt, ST10090394
//PROG5121 Part 1 POE
package st10090394;

import java.util.Scanner;

public class ST10090394 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login(scanner);

        String registeredUser = login.registerUser(scanner);

        System.out.println("Please log in.");
        System.out.print("Enter your username: ");
        String enteredUsername = scanner.nextLine();

        System.out.print("Enter your password: ");
        String enteredPassword = scanner.nextLine();

        if (registeredUser != null) {
            boolean isLoggedIn = login.loginUser(enteredUsername, enteredPassword);
            System.out.println(login.returnLoginStatus(isLoggedIn));
        } else {
            System.out.println(login.returnLoginStatus(false));
        }

        scanner.close();
    }
}
