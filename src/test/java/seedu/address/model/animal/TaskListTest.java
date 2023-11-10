package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    private final List<Task> EMPTY_TASK_LIST = new ArrayList<>();

    private final List<Task> NON_EMPTY_TASK_LIST = List.of(
                    new Task("Walk dog"),
                    new Task("Feed dog"),
                    new Task("Medicate dog"));

    @Test
    public void testGetTasks_withEmptyTaskList() {
        TaskList taskList = new TaskList();
        assertEquals(EMPTY_TASK_LIST, taskList.getTasks());
    }

    @Test
    public void testGetTasks_withNonEmptyTaskList() {
        TaskList taskList = new TaskList();
        for (Task task : NON_EMPTY_TASK_LIST) {
            taskList.addTask(task);
        }
        assertEquals(NON_EMPTY_TASK_LIST, taskList.getTasks());
    }




}
