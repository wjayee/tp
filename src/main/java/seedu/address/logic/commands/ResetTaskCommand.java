package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.AnimalModel.PREDICATE_SHOW_ALL_ANIMALS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;

/**
 * Resets all tasks of the animals in the catalog.
 */
public class ResetTaskCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "reset";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Resets all tasks of the animal, identified by the index number "
            + "used in the displayed animal list, as uncompleted. "
            + "\nParameters: ANIMAL_INDEX (must be a positive integer)";

    public static final String MESSAGE_SUCCESS = "All tasks are have been reset";

    @Override
    public CommandResult execute(AnimalModel model) throws CommandException {
        requireNonNull(model);

        model.resetTasks();

        model.updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
