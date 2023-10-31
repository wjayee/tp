package seedu.address.model.animal;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents the TaskList containing tasks that are addressed to the {@link Animal}
 *
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList that stores Tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Returns the taskList.
     * @return the taskList containing tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns all tasks in the TaskList as a string, separated by a newline
     * @return A string of tasks, separated by a newline
     */
    public String getTasksAsString() {
        return taskList.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Checks if the taskList does not contain any tasks.
     *
     * @return A boolean indicating whether taskList is empty or not.
     */
    public boolean isEmptyTaskList() {
        return taskList.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskList that = (TaskList) o;
        return taskList.equals(that.taskList);
    }
    @Override
    public int hashCode() {
        return Objects.hash(taskList);
    }

}
