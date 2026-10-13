//Richard Pettitt, ST10090394
//PROG5121 Part 2 POE
package st10090394;

import javax.swing.JOptionPane;

public class Task {

    private String taskName;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskStatus;
    private static int totalHours = 0;

    private static int taskCounter = 0;
    private int taskNumber;

    // Constructor
    public Task(String taskName, String taskDescription, String developerDetails, int taskDuration) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        totalHours += taskDuration;
        taskNumber = ++taskCounter;
        this.taskStatus = "To Do"; //Defualt Status
    }

    // Task ID format: first two letters of the task name : task number : last three letters of the developer details
    public String createTaskID(String taskName, int taskNumber, String developerDetails) {
        String namePart = taskName.substring(0, Math.min(2, taskName.length()));
        String devPart = developerDetails.substring(Math.max(0, developerDetails.length() - 3));
        return namePart.toUpperCase() + ":" + taskNumber + ":" + devPart.toUpperCase();
    }

    public String printTaskDetails() {
        String taskID = createTaskID(taskName, taskNumber, developerDetails);

        String details = "Task name: " + taskName + "\n"
                + "Task Number: " + taskNumber + "\n"
                + "Task Description: " + taskDescription + "\n"
                + "Developer Details: " + developerDetails + "\n"
                + "Task Duration: " + taskDuration + "hrs\n"
                + "Task ID: " + taskID + "\n"
                + "Task Status: " + taskStatus;
        return details;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void updateTaskStatus() {
        String[] options = {"To Do", "Done", "Doing"};
        int choice = JOptionPane.showOptionDialog(null, "Select task status:", "Update Task Status",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice >= 0 && choice < options.length) {
            taskStatus = options[choice];
        }
    }

    // total hours
    public static int returnTotalHours() {
        return totalHours;
    }

    // Display Task Details using JOptionPane
    public void displayTaskDetails() {
        JOptionPane.showMessageDialog(null, printTaskDetails(), "Task Details", JOptionPane.INFORMATION_MESSAGE);
    }
}
//Reference List:
/* Farrell, J. 2018. Java Programming. Ninth Edition. Boston: Cengage Learning */
