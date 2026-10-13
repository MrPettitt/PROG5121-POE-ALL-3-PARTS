//Richard Pettitt, ST10090394
//PROG5121 Part 2 POE
package st10090394;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class ST10090394 {

    public static void main(String[] args) {
        Login login = new Login();
        Scanner scanner = new Scanner(System.in);

        // register first, then log in with the registered details
        login.registerUser(scanner);

        String enteredUsername = JOptionPane.showInputDialog("Enter your username: ");
        String enteredPassword = JOptionPane.showInputDialog("Enter your password: ");

        boolean isLoggedIn = login.loginUser(enteredUsername, enteredPassword);
        System.out.println(login.returnLoginStatus(isLoggedIn));
        scanner.close();

        if (!isLoggedIn) {
            return;
        }

        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        int menu = 0;
        while (menu != 3) {
            Integer choice = readInt("Menu:\n"
                    + "Option 1) Add tasks\n"
                    + "Option 2) Show report\n"
                    + "Option 3) Quit");
            if (choice == null) {
                // Cancel on the menu quits the program
                break;
            }
            menu = choice;

            switch (menu) {
                case 1:
                    addTasks();
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                    break;

                case 3:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Please select a valid option.");
                    break;
            }
        }
    }

    // Prompts for a whole number; returns null if the user cancels. Re-asks on invalid input.
    private static Integer readInt(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(message);
            if (input == null) {
                return null;
            }
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a whole number.");
            }
        }
    }

    // Option 1: capture the details of one or more tasks
    private static void addTasks() {
        Integer numTasks = readInt("Enter number of Tasks ");
        if (numTasks == null || numTasks <= 0) {
            return;
        }

        int totalHours = 0;
        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter Task Name");
            if (taskName == null || taskName.trim().isEmpty()) {
                break; // Cancel (or blank), return to the menu
            }

            String taskDescription = JOptionPane.showInputDialog("Enter Task Description");
            if (taskDescription == null) {
                break;
            }

            String developerDetails = JOptionPane.showInputDialog("Enter Task Developer Details");
            if (developerDetails == null || developerDetails.trim().isEmpty()) {
                break;
            }

            Integer taskDuration = readInt("Enter Task Duration in hours");
            if (taskDuration == null) {
                break;
            }

            Task task = new Task(taskName.trim(), taskDescription, developerDetails.trim(), taskDuration);
            task.updateTaskStatus();
            JOptionPane.showMessageDialog(null, task.printTaskDetails());
            totalHours += taskDuration;
        }

        JOptionPane.showMessageDialog(null, "Your total task hours are " + totalHours);
    }
}
//Reference List:
/* Farrell, J. 2018. Java Programming. Ninth Edition. Boston: Cengage Learning */
