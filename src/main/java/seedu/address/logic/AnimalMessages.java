package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.animal.Animal;

/**
 * Container for user visible messages.
 */
public class AnimalMessages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ANIMAL_DISPLAYED_INDEX = "The animal index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d animals listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code animal} for display to the user.
     */
    public static String format(Animal animal) {
        final StringBuilder builder = new StringBuilder();
        builder.append(animal.getName())
                .append("; ID: ")
                .append(animal.getPetId())
                .append("; Sex: ")
                .append(animal.getSex())
                .append("; Species: ")
                .append(animal.getSpecies())
                .append("; Breed: ")
                .append(animal.getBreed())
                .append("; DOB: ")
                .append(animal.getDateOfBirth())
                .append("; DOA: ")
                .append(animal.getAdmissionDate());
        return builder.toString();
    }

}
