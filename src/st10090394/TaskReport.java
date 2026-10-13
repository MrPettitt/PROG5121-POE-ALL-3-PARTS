//Richard Pettitt, ST10090394
//PROG5121 Part 3 POE
//Reference List:
/* Farrell, J. 2018. Java Programming. Ninth Edition. Boston: Cengage Learning */
package st10090394;

import java.util.ArrayList;
import java.util.List;

// Report and search logic for the EasyKanban task list (kept separate from the dialogs so it can be unit tested)
public class TaskReport {

    // all tasks with the status "Done"
    public static List<Task> doneTasks(List<Task> tasks) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if ("Done".equals(task.getTaskStatus())) {
                result.add(task);
            }
        }
        return result;
    }

    // the task with the longest duration (the first one if there is a tie), or null if there are no tasks
    public static Task longestTask(List<Task> tasks) {
        Task longest = null;
        for (Task task : tasks) {
            if (longest == null || task.getTaskDuration() > longest.getTaskDuration()) {
                longest = task;
            }
        }
        return longest;
    }

    // the first task with the given name (ignoring case), or null if not found
    public static Task findByName(List<Task> tasks, String taskName) {
        if (taskName == null) {
            return null;
        }
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskName.trim())) {
                return task;
            }
        }
        return null;
    }

    // all tasks assigned to the given developer (ignoring case)
    public static List<Task> findByDeveloper(List<Task> tasks, String developer) {
        List<Task> result = new ArrayList<>();
        if (developer == null) {
            return result;
        }
        for (Task task : tasks) {
            if (task.getDeveloperDetails().equalsIgnoreCase(developer.trim())) {
                result.add(task);
            }
        }
        return result;
    }

    // removes the first task with the given name; returns true if one was removed
    public static boolean deleteByName(List<Task> tasks, String taskName) {
        Task task = findByName(tasks, taskName);
        return task != null && tasks.remove(task);
    }

    // full details of every task, separated by a blank line
    public static String fullReport(List<Task> tasks) {
        StringBuilder report = new StringBuilder();
        for (Task task : tasks) {
            report.append(task.printTaskDetails()).append("\n\n");
        }
        return report.toString().trim();
    }
}
