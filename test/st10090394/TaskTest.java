package st10090394;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    public void testGetters() {
        Task task = newTask();
        assertEquals("Login Feature", task.getTaskName());
        assertEquals("Create the login feature", task.getTaskDescription());
        assertEquals("Robyn Harrison", task.getDeveloperDetails());
        assertEquals(8, task.getTaskDuration());
    }

    @Test
    public void testDefaultStatusIsToDo() {
        assertEquals("To Do", newTask().getTaskStatus());
    }

    @Test
    public void testSetters() {
        Task task = newTask();
        task.setTaskStatus("Done");
        task.setTaskName("Fix bug");
        task.setDeveloperDetails("Mike Smith");
        task.setTaskDuration(3);
        assertEquals("Done", task.getTaskStatus());
        assertEquals("Fix bug", task.getTaskName());
        assertEquals("Mike Smith", task.getDeveloperDetails());
        assertEquals(3, task.getTaskDuration());
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
        assertEquals(first.getTaskNumber() + 1, second.getTaskNumber());
    }

    @Test
    public void testReturnTotalHoursAccumulates() {
        int before = Task.returnTotalHours();
        Task task = new Task("Fix", "Fix a bug", "Dev Person", 5);
        assertEquals(before + 5, Task.returnTotalHours());
        task.setTaskDuration(9);
        assertEquals(before + 9, Task.returnTotalHours());
    }

    @Test
    public void testDescriptionOfExactly50CharactersIsAccepted() {
        assertTrue(Task.checkTaskDescription("a".repeat(50)));
    }

    @Test
    public void testDescriptionOver50CharactersIsRejected() {
        assertFalse(Task.checkTaskDescription("a".repeat(51)));
    }

    @Test
    public void testShortAndEmptyDescriptionsAreAccepted() {
        assertTrue(Task.checkTaskDescription("Create Login to authenticate users"));
        assertTrue(Task.checkTaskDescription(""));
    }

    @Test
    public void testNullDescriptionIsRejected() {
        assertFalse(Task.checkTaskDescription(null));
    }
}
