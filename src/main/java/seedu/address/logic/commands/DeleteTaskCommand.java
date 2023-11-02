package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.AnimalModel.PREDICATE_SHOW_ALL_ANIMALS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Task;
import seedu.address.model.animal.TaskList;

/**
 * Deletes a specific task from the task list of an animal.
 */

public class DeleteTaskCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "deletetask";

    public static final String EXAMPLE_USAGE = "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a specific task from an animal's task list.\n"
            + "Parameters: ANIMALINDEX (must be a positive integer) TASKINDEX\n"
            + EXAMPLE_USAGE;

    public static final String MESSAGE_SUCCESS = "Task deleted for %s: %s";

    private final Index targetAnimalIndex;

    private final Index targetTaskIndex;

    /**
     * @param targetAnimalIndex of the animal in the filtered animal list to edit
     * @param targetTaskIndex of the task in the animal task list to delete
     */
    public DeleteTaskCommand(Index targetAnimalIndex, Index targetTaskIndex) {
        requireNonNull(targetAnimalIndex);
        requireNonNull(targetTaskIndex);

        this.targetAnimalIndex = targetAnimalIndex;
        this.targetTaskIndex = targetTaskIndex;
    }

    @Override
    public CommandResult execute(AnimalModel model) throws CommandException {
        requireNonNull(model);
        List<Animal> lastShownList = model.getFilteredAnimalList();

        if (targetAnimalIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(AnimalMessages.MESSAGE_INVALID_ANIMAL_DISPLAYED_INDEX);
        }

        Animal animalToEdit = lastShownList.get(targetAnimalIndex.getZeroBased());
        List<Task> taskList = animalToEdit.getTasks();

        if (targetTaskIndex.getZeroBased() >= taskList.size()) {
            throw new CommandException(AnimalMessages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Animal editedAnimal = createUpdatedTaskAnimal(animalToEdit, targetTaskIndex);

        model.setAnimal(animalToEdit, editedAnimal);
        model.updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedAnimal.getName(), AnimalMessages.format(targetTaskIndex)));
    }

    /**
     * Creates and returns an {@code Animal} with the details of {@code animalToEdit}
     * after deleting the tasks with {@code targetTaskIndex}.
     */
    private static Animal createUpdatedTaskAnimal(Animal animalToEdit, Index targetTaskIndex) {
        assert animalToEdit != null;

        Animal editedAnimal = createAnimalWithSameAttributes(animalToEdit);
        editedAnimal.deleteTask(targetTaskIndex);

        return editedAnimal;
    }

    private static Animal createAnimalWithSameAttributes(Animal source) {
        return new Animal(source.getName(), source.getPetId(), source.getSpecies(),
                source.getBreed(), source.getSex(), source.getAdmissionDate(),
                source.getDateOfBirth());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetAnimalIndex.equals(((DeleteTaskCommand) other).targetAnimalIndex)
                && targetTaskIndex.equals(((DeleteTaskCommand) other).targetTaskIndex));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetAnimalIndex)
                .add("task", targetTaskIndex)
                .toString();
    }
}

