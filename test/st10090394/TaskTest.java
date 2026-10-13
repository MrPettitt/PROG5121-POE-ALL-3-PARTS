package st10090394;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Task}. Methods that open dialogs (updateTaskStatus,
 * displayTaskDetails) are not tested because they need a display.
 *
 * @author Richard Pettitt, ST10090394
 */
public class TaskTest {

    private static Task newTask() {
        return new Task("Login Feature", "Create the login feature", "Robyn Harrison", 8);
    }

    @Test
    public void testCreateTaskID() {
        assertEquals("LO:3:SON", newTask().createTaskID("Login Feature", 3, "Robyn Harrison"));
    }

    @Test
    public void testCreateTaskIDHandlesShortInput() {
        assertEquals("A:1:AB", newTask().createTaskID("a", 1, "ab"));
    }

    @Test
    public void testDefaultStatusIsToDo() {
        assertEquals("To Do", newTask().getTaskStatus());
    }

    @Test
    public void testSetAndGetTaskStatus() {
        Task task = newTask();
        task.setTaskStatus("Done");
        assertEquals("Done", task.getTaskStatus());
    }

    @Test
    public void testPrintTaskDetails() {
        String details = newTask().printTaskDetails();
        assertTrue(details.contains("Task name: Login Feature"));
        assertTrue(details.contains("Task Description: Create the login feature"));
        assertTrue(details.contains("Developer Details: Robyn Harrison"));
        assertTrue(details.contains("Task Duration: 8hrs"));
        assertTrue(details.contains(":SON"));
        assertTrue(details.contains("Task Status: To Do"));
    }

    @Test
    public void testTaskNumbersIncrease() {
        Task first = newTask();
        Task second = newTask();
        assertTrue(second.printTaskDetails().contains("Task Number: "
                + (extractNumber(first.printTaskDetails()) + 1)));
    }

    @Test
    public void testReturnTotalHoursAccumulates() {
        int before = Task.returnTotalHours();
        new Task("Fix", "Fix a bug", "Dev Person", 5);
        new Task("Add", "Add a feature", "Dev Person", 7);
        assertEquals(before + 12, Task.returnTotalHours());
    }

    private static int extractNumber(String details) {
        String marker = "Task Number: ";
        int start = details.indexOf(marker) + marker.length();
        int end = details.indexOf('\n', start);
        return Integer.parseInt(details.substring(start, end));
    }
}
