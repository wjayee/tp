package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.animal.Task;
import seedu.address.model.animal.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * A Jackson-friendly adapted version of the {@link TaskList} model class.
 */
public class JsonAdaptedTask {
    private final String description;
    private final String taskStatus;


    @JsonCreator
    public JsonAdaptedTask(String description, String taskStatus) {
        this.description = description;
        this.taskStatus = taskStatus;
    }

    public JsonAdaptedTask(Task source) {
        description = source.getDescription();
        taskStatus = source.getStausIcon();
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonValue
    public String getIsDone() {
        return taskStatus;
    }

    public Task toTaskType() throws IllegalValueException {
        if (!Task.isValidTask(description)) {
            throw new IllegalValueException(Task.MESSAGE_CONSRAINTS);
        }
        boolean isDone = (taskStatus.equals("X"));
        return new Task(description, isDone);
    }

}
