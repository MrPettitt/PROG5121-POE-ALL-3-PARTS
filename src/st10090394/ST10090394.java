//Richard Pettitt, ST10090394
//PROG5121 Part 3 POE
//Reference List:
/* Farrell, J. 2018. Java Programming. Ninth Edition. Boston: Cengage Learning */
package st10090394;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ST10090394 {

    public static void main(String[] args) {
        Login login = new Login();
        Scanner scanner = new Scanner(System.in);

        // register first, then log in with the registered details
        boolean isRegistered = login.registerUser(scanner);
        scanner.close();
        if (!isRegistered) {
            System.out.println(login.returnLoginStatus(false));
            return;
        }

        String enteredUsername = JOptionPane.showInputDialog("Enter your username: ");
        String enteredPassword = JOptionPane.showInputDialog("Enter your password: ");

        boolean isLoggedIn = login.loginUser(enteredUsername, enteredPassword);
        System.out.println(login.returnLoginStatus(isLoggedIn));
        if (!isLoggedIn) {
            return;
        }

        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        List<Task> taskList = new ArrayList<>();
        boolean sampleLoaded = false;

        int menu = 0;
        while (menu != 3) {
            Integer choice = readInt("Menu:\n"
                    + "Option 1) Add tasks\n"
                    + "Option 2) Show report\n"
                    + "Option 3) Quit\n"
                    + "Option 4) Load sample data");
            if (choice == null) {
                break; // Cancel on the menu quits the program
            }
            menu = choice;

            switch (menu) {
                case 1:
                    addTasks(taskList);
                    break;

                case 2:
                    showReportMenu(taskList);
                    break;

                case 3:
                    break;

                case 4:
                    if (sampleLoaded) {
                        JOptionPane.showMessageDialog(null, "Sample data has already been loaded.");
                    } else {
                        List<Task> sample = SampleData.sampleTasks();
                        taskList.addAll(sample);
                        sampleLoaded = true;
                        JOptionPane.showMessageDialog(null, sample.size()
                                + " sample tasks loaded. Use the report menu to view them.");
                    }
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

    // Prompts for a task description of at most 50 characters; returns null if the user cancels
    private static String readDescription() {
        while (true) {
            String description = JOptionPane.showInputDialog("Enter Task Description (maximum 50 characters)");
            if (description == null) {
                return null;
            }
            if (Task.checkTaskDescription(description)) {
                JOptionPane.showMessageDialog(null, "Task successfully captured");
                return description;
            }
            JOptionPane.showMessageDialog(null, "Please enter a task description of no more than 50 characters.");
        }
    }

    // Option 1: capture the details of one or more tasks
    private static void addTasks(List<Task> taskList) {
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

            String taskDescription = readDescription();
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
            taskList.add(task);
            totalHours += taskDuration;
        }

        JOptionPane.showMessageDialog(null, "Your total task hours are " + totalHours);
    }

    // Option 2: the report menu
    private static void showReportMenu(List<Task> taskList) {
        int reportOption = 0;
        while (reportOption != 7) {
            Integer choice = readInt("Report Menu:\n"
                    + "Option 1) Display tasks with status 'Done'\n"
                    + "Option 2) Display task with longest duration\n"
                    + "Option 3) Search for a task by Task Name\n"
                    + "Option 4) Search tasks by Developer\n"
                    + "Option 5) Delete a task by Task Name\n"
                    + "Option 6) Display full details of all tasks\n"
                    + "Option 7) Go back to main menu");
            if (choice == null) {
                return; // Cancel goes back to the main menu
            }
            reportOption = choice;

            switch (reportOption) {
                case 1:
                    showDoneTasks(taskList);
                    break;

                case 2:
                    showLongestTask(taskList);
                    break;

                case 3:
                    searchByTaskName(taskList);
                    break;

                case 4:
                    searchByDeveloper(taskList);
                    break;

                case 5:
                    deleteTask(taskList);
                    break;

                case 6:
                    if (taskList.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No tasks found.");
                    } else {
                        JOptionPane.showMessageDialog(null, TaskReport.fullReport(taskList));
                    }
                    break;

                case 7:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Please select a valid option.");
                    break;
            }
        }
    }

    private static void showDoneTasks(List<Task> taskList) {
        List<Task> done = TaskReport.doneTasks(taskList);
        if (done.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks with status 'Done' found.");
            return;
        }
        StringBuilder text = new StringBuilder();
        for (Task task : done) {
            text.append("Developer: ").append(task.getDeveloperDetails())
                    .append(", Task Name: ").append(task.getTaskName())
                    .append(", Task Duration: ").append(task.getTaskDuration())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, text.toString());
    }

    private static void showLongestTask(List<Task> taskList) {
        Task longest = TaskReport.longestTask(taskList);
        if (longest == null) {
            JOptionPane.showMessageDialog(null, "No tasks found.");
        } else {
            JOptionPane.showMessageDialog(null, "Developer: " + longest.getDeveloperDetails()
                    + ", Task Duration: " + longest.getTaskDuration());
        }
    }

    private static void searchByTaskName(List<Task> taskList) {
        String name = JOptionPane.showInputDialog("Enter Task Name to search");
        if (name == null) {
            return;
        }
        Task task = TaskReport.findByName(taskList, name);
        if (task == null) {
            JOptionPane.showMessageDialog(null, "Task not found.");
        } else {
            JOptionPane.showMessageDialog(null, "Task Name: " + task.getTaskName()
                    + ", Developer: " + task.getDeveloperDetails()
                    + ", Task Status: " + task.getTaskStatus());
        }
    }

    private static void searchByDeveloper(List<Task> taskList) {
        String developer = JOptionPane.showInputDialog("Enter Developer to search");
        if (developer == null) {
            return;
        }
        List<Task> found = TaskReport.findByDeveloper(taskList, developer);
        if (found.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks assigned to the developer found.");
            return;
        }
        StringBuilder text = new StringBuilder();
        for (Task task : found) {
            text.append("Task Name: ").append(task.getTaskName())
                    .append(", Task Status: ").append(task.getTaskStatus())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, text.toString());
    }

    private static void deleteTask(List<Task> taskList) {
        String name = JOptionPane.showInputDialog("Enter Task Name to delete");
        if (name == null) {
            return;
        }
        if (TaskReport.deleteByName(taskList, name)) {
            JOptionPane.showMessageDialog(null, "Task deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }
}
