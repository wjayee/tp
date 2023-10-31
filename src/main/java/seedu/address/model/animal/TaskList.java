package seedu.address.model.animal;

import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

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
