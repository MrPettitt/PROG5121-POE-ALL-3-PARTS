//Richard Pettitt, ST10090394
//PROG5121 Part 3 POE
//Reference List:
/* Farrell, J. 2018. Java Programming. Ninth Edition. Boston: Cengage Learning */
package st10090394;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Login {

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Login() {
        this.firstName = "";
        this.lastName = "";
        this.username = "";
        this.password = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkUserName(String username) {
        if (username.length() <= 5 && username.contains("_")) {
            System.out.println("Username successfully captured");
            return true;
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            return false;
        }
    }

    public boolean checkPasswordComplexity(String password) {
        String pattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";
        if (Pattern.matches(pattern, password)) {
            System.out.println("Password successfully captured");
            return true;
        } else {
            System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
            return false;
        }
    }

    public boolean registerUser(Scanner scanner) {
        System.out.print("Enter your first name: ");
        firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        lastName = scanner.nextLine();

        boolean isValidUsername = false;
        boolean isValidPassword = false;

        while (!isValidUsername) {
            System.out.print("Enter a username (must contain an underscore and be no more than 5 characters long): ");
            username = scanner.nextLine();
            isValidUsername = checkUserName(username);
        }

        while (!isValidPassword) {
            System.out.print("Enter a password (at least 8 characters long, contains a capital letter, a number, and a special character): ");
            password = scanner.nextLine();
            isValidPassword = checkPasswordComplexity(password);
        }

        return isValidUsername && isValidPassword;
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername != null && enteredPassword != null
                && enteredUsername.equals(getUsername())
                && enteredPassword.equals(getPassword());
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        return isLoggedIn ? "Login successful" : "Login failed";
    }
}
