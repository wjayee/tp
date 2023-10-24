package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_SPECIES;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalCatalog;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.NameContainsKeywordsPredicate;

/**
 * Contains helper methods for testing commands.
 */
public class AnimalCommandTestUtil {

    public static final String VALID_NAME_POOKIE = "Pookie";
    public static final String VALID_NAME_TOFU = "Tofu";
    public static final String VALID_ID_POOKIE = "1111";
    public static final String VALID_ID_TOFU = "1234";
    public static final String VALID_DATE_OF_BIRTH_POOKIE = "2020-12-12";
    public static final String VALID_DATE_OF_BIRTH_TOFU = "2019-10-10";
    public static final String VALID_ADMISSION_DATE_POOKIE = "2023-12-12";
    public static final String VALID_ADMISSION_DATE_TOFU = "2019-11-11";
    public static final String VALID_SEX_POOKIE = "MALE";
    public static final String VALID_SEX_TOFU = "FEMALE";
    public static final String VALID_SPECIES_POOKIE = "Dog";
    public static final String VALID_SPECIES_TOFU = "Cat";
    public static final String VALID_BREED_POOKIE = "Poodle";
    public static final String VALID_BREED_TOFU = "British Shorthair";



    public static final String INDEX_NAME_POOKIE = " " + PREFIX_NAME + VALID_NAME_POOKIE;
    public static final String INDEX_NAME_TOFU = " " + PREFIX_NAME + VALID_NAME_TOFU;
    public static final String INDEX_ID_POOKIE = " " + PREFIX_ID + VALID_ID_POOKIE;
    public static final String INDEX_ID_TOFU = " " + PREFIX_ID + VALID_ID_TOFU;
    public static final String INDEX_DATE_OF_BIRTH_POOKIE = " " + PREFIX_DATE_OF_BIRTH + VALID_DATE_OF_BIRTH_POOKIE;
    public static final String INDEX_DATE_OF_BIRTH_TOFU = " " + PREFIX_DATE_OF_BIRTH + VALID_DATE_OF_BIRTH_TOFU;
    public static final String INDEX_ADMISSION_DATE_POOKIE = " " + PREFIX_DATE_OF_ADMISSION
            + VALID_ADMISSION_DATE_POOKIE;
    public static final String INDEX_ADMISSION_DATE_TOFU = " " + PREFIX_DATE_OF_ADMISSION + VALID_ADMISSION_DATE_TOFU;
    public static final String INDEX_SEX_POOKIE = " " + PREFIX_SEX + VALID_SEX_POOKIE;
    public static final String INDEX_SEX_TOFU = " " + PREFIX_SEX + VALID_SEX_TOFU;
    public static final String INDEX_SPECIES_POOKIE = " " + PREFIX_SPECIES + VALID_SPECIES_POOKIE;
    public static final String INDEX_SPECIES_TOFU = " " + PREFIX_SPECIES + VALID_SPECIES_TOFU;
    public static final String INDEX_BREED_POOKIE = " " + PREFIX_BREED + VALID_BREED_POOKIE;
    public static final String INDEX_BREED_TOFU = " " + PREFIX_BREED + VALID_BREED_TOFU;

    public static final String INDEX_INVALID_NAME = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INDEX_INVALID_ID = " " + PREFIX_ID + "911a"; // 'a' not allowed in ID
    public static final String INDEX_INVALID_DATE_OF_BIRTH = " " + PREFIX_DATE_OF_BIRTH + "2022-13-10"; // no such date
    public static final String INDEX_INVALID_DATE_OF_ADMISSION = " "
            + PREFIX_DATE_OF_ADMISSION + "22-13-2022"; // wrong date format
    public static final String INDEX_INVALID_SEX = " " + PREFIX_SEX + "others"; // Can only be male or female
    public static final String INDEX_INVALID_SPECIES = " " + PREFIX_SPECIES
            + "Waterboard2"; // '2' not allowed in species
    public static final String INDEX_INVALID_BREED = " " + PREFIX_BREED + "Water-board"; // '-' not allowed in breed
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    //    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    //    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    //
    //    static {
    //        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
    //                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
    //                .withTags(VALID_TAG_FRIEND).build();
    //        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
    //                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
    //                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    //    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(AnimalCommand command, AnimalModel actualModel,
                                            CommandResult expectedCommandResult, AnimalModel expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(AnimalCommand, AnimalModel, CommandResult, AnimalModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(AnimalCommand command, AnimalModel actualModel, String expectedMessage,
                                            AnimalModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the animal catalog, filtered animal list and selected animal in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(AnimalCommand command, AnimalModel actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AnimalCatalog animalCatalog = new AnimalCatalog(actualModel.getAnimalCatalog());
        List<Animal> expectedFilteredList = new ArrayList<>(actualModel.getFilteredAnimalList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(animalCatalog, actualModel.getAnimalCatalog());
        assertEquals(expectedFilteredList, actualModel.getFilteredAnimalList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the animal at the given {@code targetIndex} in the
     * {@code model}'s animal catalog.
     */
    public static void showAnimalAtIndex(AnimalModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAnimalList().size());

        Animal animal = model.getFilteredAnimalList().get(targetIndex.getZeroBased());
        final String[] splitName = animal.getName().fullName.split("\\s+");
        model.updateFilteredAnimalList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredAnimalList().size());
    }

}