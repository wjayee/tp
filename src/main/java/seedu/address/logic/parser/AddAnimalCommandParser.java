package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliAnimalSyntax.PREFIX_SPECIES;

import java.util.stream.Stream;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddAnimalCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.animal.AdmissionDate;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Breed;
import seedu.address.model.animal.DateOfBirth;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.PetId;
import seedu.address.model.animal.Sex;
import seedu.address.model.animal.Species;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddAnimalCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAnimalCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_DATE_OF_BIRTH,
                                            PREFIX_DATE_OF_ADMISSION, PREFIX_SPECIES, PREFIX_SEX, PREFIX_BREED);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID, PREFIX_DATE_OF_BIRTH,
                PREFIX_DATE_OF_ADMISSION, PREFIX_SPECIES, PREFIX_SEX, PREFIX_BREED)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                                                    AddAnimalCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ID, PREFIX_DATE_OF_BIRTH, PREFIX_DATE_OF_ADMISSION,
                                                PREFIX_SPECIES, PREFIX_SEX, PREFIX_BREED);
        Name name = AnimalParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        PetId id = AnimalParserUtil.parsePetId(argMultimap.getValue(PREFIX_ID).get());
        DateOfBirth dob = AnimalParserUtil.parseDateOfBirth(argMultimap.getValue(PREFIX_DATE_OF_BIRTH).get());
        AdmissionDate doa = AnimalParserUtil.parseAdmissionDate(argMultimap.getValue(PREFIX_DATE_OF_ADMISSION).get());
        Species species = AnimalParserUtil.parseSpecies(argMultimap.getValue(PREFIX_SPECIES).get());
        Sex sex = AnimalParserUtil.parseSex(argMultimap.getValue(PREFIX_SEX).get());
        Breed breed = AnimalParserUtil.parseBreed(argMultimap.getValue(PREFIX_BREED).get());

        Animal animal = new Animal(name, id, species, breed, sex, doa, dob);


        return new AddAnimalCommand(animal);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
