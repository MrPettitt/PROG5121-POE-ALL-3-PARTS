package st10090394;

import java.util.List;

/**
 * Sample EasyKanban tasks shared by the tests (see {@link SampleData}).
 *
 * @author Richard Pettitt, ST10090394
 */
public final class TestData {

    private TestData() {
    }

    public static List<Task> sampleTasks() {
        return SampleData.sampleTasks();
    }
}
