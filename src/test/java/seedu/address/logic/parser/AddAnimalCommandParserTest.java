package seedu.address.logic.parser;

import static seedu.address.logic.AnimalMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_ADMISSION_DATE_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_ADMISSION_DATE_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_BREED_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_BREED_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_DATE_OF_BIRTH_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_DATE_OF_BIRTH_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_ID_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_ID_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_INVALID_BREED;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_INVALID_DATE_OF_ADMISSION;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_INVALID_DATE_OF_BIRTH;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_INVALID_ID;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_INVALID_NAME;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_INVALID_SEX;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_INVALID_SPECIES;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_NAME_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_NAME_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_SEX_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_SEX_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_SPECIES_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.INDEX_SPECIES_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_ADMISSION_DATE_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_BREED_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_DATE_OF_BIRTH_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_ID_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_NAME_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_SEX_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_SPECIES_POOKIE;
import static seedu.address.logic.parser.AnimalCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AnimalCommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_SPECIES;
import static seedu.address.testutil.TypicalAnimals.TOFU;

import org.junit.jupiter.api.Test;

import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.AddAnimalCommand;
import seedu.address.model.animal.AdmissionDate;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Breed;
import seedu.address.model.animal.DateOfBirth;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.PetId;
import seedu.address.model.animal.Sex;
import seedu.address.model.animal.Species;
import seedu.address.testutil.AnimalBuilder;

public class AddAnimalCommandParserTest {
    private AddAnimalCommandParser parser = new AddAnimalCommandParser();
    @Test
    public void parse_allFieldsPresent_success() {
        Animal expectedAnimal = new AnimalBuilder(TOFU).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_NAME_TOFU + INDEX_ID_TOFU
                + INDEX_BREED_TOFU + INDEX_DATE_OF_BIRTH_TOFU + INDEX_ADMISSION_DATE_TOFU
                + INDEX_SEX_TOFU + INDEX_SPECIES_TOFU, new AddAnimalCommand(expectedAnimal));

    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedPersonString = INDEX_NAME_POOKIE + INDEX_ID_POOKIE
                + INDEX_BREED_POOKIE + INDEX_DATE_OF_BIRTH_POOKIE + INDEX_ADMISSION_DATE_POOKIE
                + INDEX_SEX_POOKIE + INDEX_SPECIES_POOKIE;

        // repeated name
        assertParseFailure(parser, validExpectedPersonString + INDEX_NAME_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // repeated id
        assertParseFailure(parser, validExpectedPersonString + INDEX_ID_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // repeated sex
        assertParseFailure(parser, validExpectedPersonString + INDEX_SEX_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(PREFIX_SEX));

        // repeated breed
        assertParseFailure(parser, validExpectedPersonString + INDEX_BREED_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(PREFIX_BREED));

        // repeated species
        assertParseFailure(parser, validExpectedPersonString + INDEX_SPECIES_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(PREFIX_SPECIES));

        // repeated date of birth
        assertParseFailure(parser, validExpectedPersonString + INDEX_DATE_OF_BIRTH_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE_OF_BIRTH));

        // repeated date of admission
        assertParseFailure(parser, validExpectedPersonString + INDEX_ADMISSION_DATE_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE_OF_ADMISSION));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(AnimalMessages.MESSAGE_INVALID_COMMAND_FORMAT,
                                                AddAnimalCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, INDEX_ID_POOKIE + INDEX_BREED_POOKIE + INDEX_DATE_OF_BIRTH_POOKIE
                        + INDEX_ADMISSION_DATE_POOKIE + INDEX_SEX_POOKIE + INDEX_SPECIES_POOKIE, expectedMessage);

        // missing id prefix
        assertParseFailure(parser, INDEX_NAME_POOKIE + INDEX_BREED_POOKIE + INDEX_DATE_OF_BIRTH_POOKIE
                        + INDEX_ADMISSION_DATE_POOKIE + INDEX_SEX_POOKIE + INDEX_SPECIES_POOKIE, expectedMessage);

        // missing sex prefix
        assertParseFailure(parser, INDEX_NAME_POOKIE + INDEX_ID_POOKIE + INDEX_BREED_POOKIE
                        + INDEX_DATE_OF_BIRTH_POOKIE + INDEX_ADMISSION_DATE_POOKIE + INDEX_SPECIES_POOKIE,
                        expectedMessage);

        // missing species prefix
        assertParseFailure(parser, INDEX_NAME_POOKIE + INDEX_ID_POOKIE + INDEX_BREED_POOKIE
                        + INDEX_DATE_OF_BIRTH_POOKIE + INDEX_ADMISSION_DATE_POOKIE + INDEX_SEX_POOKIE, expectedMessage);

        // missing breed prefix
        assertParseFailure(parser, INDEX_NAME_POOKIE + INDEX_ID_POOKIE
                        + INDEX_DATE_OF_BIRTH_POOKIE + INDEX_ADMISSION_DATE_POOKIE + INDEX_SEX_POOKIE, expectedMessage);

        // missing date of birth prefix
        assertParseFailure(parser, INDEX_NAME_POOKIE + INDEX_ID_POOKIE
                        + INDEX_BREED_POOKIE + INDEX_ADMISSION_DATE_POOKIE + INDEX_SEX_POOKIE, expectedMessage);

        // missing date of admission prefix
        assertParseFailure(parser, INDEX_NAME_POOKIE + INDEX_ID_POOKIE
                        + INDEX_BREED_POOKIE + INDEX_DATE_OF_BIRTH_POOKIE + INDEX_SEX_POOKIE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_POOKIE + VALID_ID_POOKIE + VALID_SEX_POOKIE + VALID_BREED_POOKIE
                        + VALID_SPECIES_POOKIE + VALID_DATE_OF_BIRTH_POOKIE + VALID_ADMISSION_DATE_POOKIE,
                        expectedMessage);
    }
    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INDEX_INVALID_NAME + INDEX_ID_POOKIE
                + INDEX_BREED_POOKIE + INDEX_DATE_OF_BIRTH_POOKIE + INDEX_ADMISSION_DATE_POOKIE
                + INDEX_SEX_POOKIE + INDEX_SPECIES_POOKIE, Name.MESSAGE_CONSTRAINTS);

        // invalid id
        assertParseFailure(parser, INDEX_NAME_POOKIE + INDEX_INVALID_ID
                + INDEX_BREED_POOKIE + INDEX_DATE_OF_BIRTH_POOKIE + INDEX_ADMISSION_DATE_POOKIE
                + INDEX_SEX_POOKIE + INDEX_SPECIES_POOKIE, PetId.MESSAGE_CONSTRAINTS);

        // invalid breed
        assertParseFailure(parser, INDEX_NAME_POOKIE + INDEX_ID_POOKIE
                + INDEX_INVALID_BREED + INDEX_DATE_OF_BIRTH_POOKIE + INDEX_ADMISSION_DATE_POOKIE
                + INDEX_SEX_POOKIE + INDEX_SPECIES_POOKIE, Breed.MESSAGE_CONSTRAINTS);

        // invalid species
        assertParseFailure(parser, INDEX_NAME_POOKIE + INDEX_ID_POOKIE
                + INDEX_BREED_POOKIE + INDEX_DATE_OF_BIRTH_POOKIE + INDEX_ADMISSION_DATE_POOKIE
                + INDEX_SEX_POOKIE + INDEX_INVALID_SPECIES, Species.MESSAGE_CONSTRAINTS);

        // invalid date of birth
        assertParseFailure(parser, INDEX_NAME_POOKIE + INDEX_ID_POOKIE
                + INDEX_BREED_POOKIE + INDEX_INVALID_DATE_OF_BIRTH + INDEX_ADMISSION_DATE_POOKIE
                + INDEX_SEX_POOKIE + INDEX_SPECIES_POOKIE, DateOfBirth.MESSAGE_CONSTRAINTS);

        // invalid date of admission
        assertParseFailure(parser, INDEX_NAME_POOKIE + INDEX_ID_POOKIE
                + INDEX_BREED_POOKIE + INDEX_DATE_OF_BIRTH_POOKIE + INDEX_INVALID_DATE_OF_ADMISSION
                + INDEX_SEX_POOKIE + INDEX_SPECIES_POOKIE, AdmissionDate.MESSAGE_CONSTRAINTS);

        // invalid sex
        assertParseFailure(parser, INDEX_NAME_POOKIE + INDEX_ID_POOKIE
                + INDEX_BREED_POOKIE + INDEX_DATE_OF_BIRTH_POOKIE + INDEX_ADMISSION_DATE_POOKIE
                + INDEX_INVALID_SEX + INDEX_SPECIES_POOKIE, Sex.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported, invalid name and date of birth
        assertParseFailure(parser, INDEX_INVALID_NAME + INDEX_ID_POOKIE
                + INDEX_BREED_POOKIE + INDEX_INVALID_DATE_OF_BIRTH + INDEX_ADMISSION_DATE_POOKIE
                + INDEX_SEX_POOKIE + INDEX_SPECIES_POOKIE, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        // invalid date of birth
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + INDEX_NAME_POOKIE + INDEX_ID_POOKIE
                + INDEX_BREED_POOKIE + INDEX_DATE_OF_BIRTH_POOKIE + INDEX_ADMISSION_DATE_POOKIE
                + INDEX_SEX_POOKIE + INDEX_SPECIES_POOKIE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAnimalCommand.MESSAGE_USAGE));

    }
}
