//Richard Pettitt, ST10090394
//PROG5121 Part 3 POE
//Reference List:
/* Farrell, J. 2018. Java Programming. Ninth Edition. Boston: Cengage Learning */
package st10090394;

import java.util.ArrayList;
import java.util.List;

// Sample EasyKanban tasks, used by the "Load sample data" menu option and by the unit tests
//   Mike Smith        / Create Login        / 5  / To Do
//   Edward Harrison   / Create Add Features / 8  / Doing
//   Samantha Paulson  / Create Reports      / 2  / Done
//   Glenda Oberholzer / Add Arrays          / 11 / To Do
public final class SampleData {

    private SampleData() {
    }

    public static List<Task> sampleTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(create("Create Login", "Create the login feature", "Mike Smith", 5, "To Do"));
        tasks.add(create("Create Add Features", "Add the new features", "Edward Harrison", 8, "Doing"));
        tasks.add(create("Create Reports", "Create the reports", "Samantha Paulson", 2, "Done"));
        tasks.add(create("Add Arrays", "Add arrays to the code", "Glenda Oberholzer", 11, "To Do"));
        return tasks;
    }

    private static Task create(String name, String description, String developer, int duration, String status) {
        Task task = new Task(name, description, developer, duration);
        task.setTaskStatus(status);
        return task;
    }
}
