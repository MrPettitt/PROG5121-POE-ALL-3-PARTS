//Richard Pettitt, ST10090394
//PROG5121 Part 2 POE
package st10090394;

import java.util.Scanner;

public class Login {

    // instance variables
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    // constructor
    public Login() {
        this.firstName = "";
        this.lastName = "";
        this.username = "";
        this.password = "";
    }

    // getters and setters for instance variables
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

    // method to check username
    public boolean checkUserName(String username) {
        if (username.length() <= 5 && username.contains("_")) {
            System.out.println("Username successfully captured");
            return true;
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            return false;
        }
    }

    // method to check password complexity
    public boolean checkPasswordComplexity(String password) {
        if (password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$")) {
            System.out.println("Password successfully captured");
            return true;
        } else {
            System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
            return false;
        }
    }

    // method to register a user with the entered information
    public String registerUser() {
        System.out.println("Welcome " + firstName + " " + lastName + ", it is great to see you again.");
        return getUsername() + getPassword();
    }

    // method to register a user with user input
    public String registerUser(Scanner scanner) {
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

        return registerUser();
    }

    // method to check if login information is valid
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername != null && enteredPassword != null
                && enteredUsername.equals(getUsername())
                && enteredPassword.equals(getPassword());
    }

    // method to return login status as a string
    public String returnLoginStatus(boolean isLoggedIn) {
        return isLoggedIn ? "Login successful" : "Login failed";
    }
}
//Reference List:
/* Farrell, J. 2018. Java Programming. Ninth Edition. Boston: Cengage Learning */
