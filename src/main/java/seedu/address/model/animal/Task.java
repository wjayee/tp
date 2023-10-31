package seedu.address.model.animal;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Task addressed to the {@link Animal}
 *
 */
public class Task {

    public final boolean isDone;
    public final String description;

    public static final String MESSAGE_CONSRAINTS = "Description should not be empty!";
    public static final String VALIDATION_REGEX = "^.*\\S.*$";


    /**
     * Constructs a Task Object. isDone status of tasks are initialized to be false.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        requireNonNull(description);
        checkArgument(isValidTask(description), MESSAGE_CONSRAINTS);
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task Object. This method is only used as the constructor when converting
     * from a Jackson-friendly adapted Task to an actual Task Object.
     *
     * @param description Description of the task.
     * @param isDone isDone status of the task.
     */
    public Task(String description, boolean isDone) {
        requireNonNull(description);
        checkArgument(isValidTask(description), MESSAGE_CONSRAINTS);
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task.
     *
     * @return A boolean value that indicates whether a task has been done.
     */
    public boolean checkIsDone() {
        return this.isDone;
    }

    /**
     * Returns the status of the task in a String. X indicates completed, whitespace indicates otherwise.
     *
     * @return A String that indicates whether a task has been done.
     */
    public String getStausIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Checks whether the description of the task is valid using the regex expression. A task should not be empty,
     * and should not only contain whitespace characters.
     *
     * @param test Description of task to be tested.
     * @return Boolean value taht indicates whether a task is valid.
     */
    public static boolean isValidTask(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task that = (Task) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    /**
     * Returns the status of the task from the getStatusIcon enclosed within squre brackets,
     * along with a description of the task.
     *
     * @return A string containing Task status and description of task.
     */
    @Override
    public String toString() {
        return "[" + getStausIcon() + "] " + this.description;
    }

}
