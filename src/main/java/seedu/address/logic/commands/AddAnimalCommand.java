package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_SPECIES;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.Animal;

/**
 * Adds a animal to the catalog.
 */

public class AddAnimalCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an animal to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ID + "PetID "
            + PREFIX_DATE_OF_BIRTH + "DATE_OF_BIRTH "
            + PREFIX_DATE_OF_ADMISSION + "DATE_OF_ADMISSION "
            + PREFIX_SPECIES + "SPECIES "
            + PREFIX_SEX + "SEX "
            + PREFIX_BREED + "BREED "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Pookie "
            + PREFIX_ID + "1234"
            + PREFIX_DATE_OF_BIRTH + "01/01/2019 "
            + PREFIX_DATE_OF_ADMISSION + "01/01/2019 "
            + PREFIX_SPECIES + "Dog "
            + PREFIX_SEX + "M "
            + PREFIX_BREED + "Poodle";

    public static final String MESSAGE_SUCCESS = "New animal added: %1$s";

    public static final String MESSAGE_DUPLICATE_ANIMAL = "This animal already exists in the address book";

    private final Animal toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Animal}
     */
    public AddAnimalCommand(Animal animal) {
        requireNonNull(animal);
        toAdd = animal;
    }

    @Override
    public CommandResult execute(AnimalModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasAnimal(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ANIMAL);
        }

        model.addAnimal(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, AnimalMessages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAnimalCommand // instanceof handles nulls
                && toAdd.equals(((AddAnimalCommand) other).toAdd));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
