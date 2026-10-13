package st10090394;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link TaskReport}.
 *
 * @author Richard Pettitt, ST10090394
 */
public class TaskReportTest {

    private List<Task> tasks;
    private Task create;
    private Task add;
    private Task fix;

    @BeforeEach
    public void setUp() {
        create = new Task("Create Login", "Create the login", "Mike Smith", 5);
        add = new Task("Add Reports", "Add the reports", "Edward Harrison", 8);
        fix = new Task("Fix Bug", "Fix a bug", "Mike Smith", 11);
        create.setTaskStatus("To Do");
        add.setTaskStatus("Doing");
        fix.setTaskStatus("Done");
        tasks = new ArrayList<>(List.of(create, add, fix));
    }

    @Test
    public void testDoneTasks() {
        List<Task> done = TaskReport.doneTasks(tasks);
        assertEquals(1, done.size());
        assertSame(fix, done.get(0));
    }

    @Test
    public void testDoneTasksEmptyWhenNoneDone() {
        fix.setTaskStatus("Doing");
        assertTrue(TaskReport.doneTasks(tasks).isEmpty());
    }

    @Test
    public void testLongestTask() {
        assertSame(fix, TaskReport.longestTask(tasks));
    }

    @Test
    public void testLongestTaskWithTieReturnsFirst() {
        add.setTaskDuration(11);
        assertSame(add, TaskReport.longestTask(tasks));
    }

    @Test
    public void testLongestTaskOfEmptyListIsNull() {
        assertNull(TaskReport.longestTask(new ArrayList<>()));
    }

    @Test
    public void testFindByName() {
        assertSame(add, TaskReport.findByName(tasks, "Add Reports"));
        assertSame(add, TaskReport.findByName(tasks, "  add reports "));
    }

    @Test
    public void testFindByNameNotFound() {
        assertNull(TaskReport.findByName(tasks, "Missing"));
        assertNull(TaskReport.findByName(tasks, null));
    }

    @Test
    public void testFindByDeveloper() {
        List<Task> found = TaskReport.findByDeveloper(tasks, "mike smith");
        assertEquals(2, found.size());
        assertTrue(found.contains(create));
        assertTrue(found.contains(fix));
    }

    @Test
    public void testFindByDeveloperNotFound() {
        assertTrue(TaskReport.findByDeveloper(tasks, "Nobody").isEmpty());
        assertTrue(TaskReport.findByDeveloper(tasks, null).isEmpty());
    }

    @Test
    public void testDeleteByName() {
        assertTrue(TaskReport.deleteByName(tasks, "Add Reports"));
        assertEquals(2, tasks.size());
        assertFalse(tasks.contains(add));
    }

    @Test
    public void testDeleteByNameNotFound() {
        assertFalse(TaskReport.deleteByName(tasks, "Missing"));
        assertEquals(3, tasks.size());
    }

    @Test
    public void testFullReportContainsEveryTask() {
        String report = TaskReport.fullReport(tasks);
        assertTrue(report.contains("Task name: Create Login"));
        assertTrue(report.contains("Task name: Add Reports"));
        assertTrue(report.contains("Task name: Fix Bug"));
    }

    @Test
    public void testFullReportOfEmptyListIsEmpty() {
        assertEquals("", TaskReport.fullReport(new ArrayList<>()));
    }

    // tests using the shared sample data

    @Test
    public void testSampleDataDoneTask() {
        List<Task> done = TaskReport.doneTasks(TestData.sampleTasks());
        assertEquals(1, done.size());
        assertEquals("Samantha Paulson", done.get(0).getDeveloperDetails());
        assertEquals("Create Reports", done.get(0).getTaskName());
        assertEquals(2, done.get(0).getTaskDuration());
    }

    @Test
    public void testSampleDataLongestTask() {
        Task longest = TaskReport.longestTask(TestData.sampleTasks());
        assertEquals("Glenda Oberholzer", longest.getDeveloperDetails());
        assertEquals(11, longest.getTaskDuration());
    }

    @Test
    public void testSampleDataSearchByTaskName() {
        Task found = TaskReport.findByName(TestData.sampleTasks(), "Create Login");
        assertEquals("Mike Smith", found.getDeveloperDetails());
        assertEquals("To Do", found.getTaskStatus());
    }

    @Test
    public void testSampleDataSearchByDeveloper() {
        List<Task> found = TaskReport.findByDeveloper(TestData.sampleTasks(), "Edward Harrison");
        assertEquals(1, found.size());
        assertEquals("Create Add Features", found.get(0).getTaskName());
        assertEquals("Doing", found.get(0).getTaskStatus());
    }

    @Test
    public void testSampleDataDeleteTask() {
        List<Task> sample = TestData.sampleTasks();
        assertTrue(TaskReport.deleteByName(sample, "Create Reports"));
        assertEquals(3, sample.size());
        assertNull(TaskReport.findByName(sample, "Create Reports"));
    }

    @Test
    public void testSampleDataFullReport() {
        String report = TaskReport.fullReport(TestData.sampleTasks());
        assertTrue(report.contains("Developer Details: Glenda Oberholzer"));
        assertTrue(report.contains("Task Duration: 11hrs"));
        assertTrue(report.contains("Task Status: Done"));
    }

    @Test
    public void testSampleDataDescriptionsAreWithinLimit() {
        for (Task task : TestData.sampleTasks()) {
            assertTrue(Task.checkTaskDescription(task.getTaskDescription()));
        }
    }
}
