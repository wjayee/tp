package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.AnimalModel.PREDICATE_SHOW_ALL_ANIMALS;

import seedu.address.model.AnimalModel;

/**
 * Lists all animals in the catalog to the user.
 */
public class ListAnimalCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all animals";


    @Override
    public CommandResult execute(AnimalModel model) {
        requireNonNull(model);
        model.updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
