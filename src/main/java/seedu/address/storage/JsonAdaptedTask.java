package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.animal.Task;



/**
 * A Jackson-friendly adapted version of the {@link Task} model class.
 */
public class JsonAdaptedTask {
    public static final String DESCRIPTION_KEY = "description";
    public static final String TASK_STATUS_KEY = "taskStatus";
    private final String description;
    private final boolean taskStatus;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given Task details.
     * This constructor is used internally by Jackson to deserialize {@link Task} JSON objects.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty(DESCRIPTION_KEY) String description, @JsonProperty(TASK_STATUS_KEY) boolean taskStatus) {
        this.description = description;
        this.taskStatus = taskStatus;
    }

    /**
     * Converts a given {@code Task} into a {@code JsonAdaptedTask}, which can then be
     * serialized by Jackson into JSON objects.
     */
    public JsonAdaptedTask(Task source) {
        description = source.getDescription();
        taskStatus = source.checkIsDone();
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonValue
    public boolean getIsDone() {
        return taskStatus;
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Task toTaskType() throws IllegalValueException {
        if (!Task.isValidTask(description)) {
            throw new IllegalValueException(Task.MESSAGE_CONSTRAINTS);
        }
        return new Task(description, taskStatus);
    }

}
