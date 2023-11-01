package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliAnimalSyntax.BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.PET_ID;
import static seedu.address.logic.parser.CliAnimalSyntax.SEX;
import static seedu.address.logic.parser.CliAnimalSyntax.SPECIES;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.SearchAnimalCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.animal.KeywordPredicate;

/**
 * Parses input arguments and creates a new SearchAnimalCommand object
 */
public class SearchAnimalCommandParser implements AnimalParser<SearchAnimalCommand> {

    private static final Prefix[] MANDATORY_PREFIXES = CliAnimalSyntax.getMandatoryPrefixes().toArray(Prefix[]::new);

    /**
     * Parses the given {@code String} of arguments in the context of the SearchAnimalCommand
     * and returns an SearchAnimalCommand object for execution.
     *
     * @param args the arguments to be parsed.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchAnimalCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, MANDATORY_PREFIXES);

        if (!ArgumentMultimap.areSomePrefixesPresent(argMultimap, MANDATORY_PREFIXES)) {
            throw new ParseException(getHelpMessage());
        }

        // ParseException containing the duplicated prefixes separated by whitespace is thrown.
        argMultimap.verifyNoDuplicatePrefixesFor(MANDATORY_PREFIXES);

        //Obtain values of the different fields
        String name = argMultimap.getValue(NAME).orElse("");
        String id = argMultimap.getValue(PET_ID).orElse("");
        String dob = argMultimap.getValue(DATE_OF_BIRTH).orElse("");
        String doa = argMultimap.getValue(DATE_OF_ADMISSION).orElse("");
        String species = argMultimap.getValue(SPECIES).orElse("");
        String sex = argMultimap.getValue(SEX).orElse("");
        String breed = argMultimap.getValue(BREED).orElse("");


        List<String> keywords = Arrays.asList(name, id, dob, doa, species, sex, breed);

        //Remove empty fields
        keywords = keywords.stream()
                .filter(keyword -> !keyword.isEmpty()).collect(Collectors.toList());

        //keywords should not be empty
        assert(!keywords.isEmpty());

        return new SearchAnimalCommand(new KeywordPredicate(keywords));
    }

    private static String getHelpMessage() {
        return AnimalMessages.getFormattedHelpMessage(AnimalMessages.MESSAGE_INVALID_SEARCH_KEYWORDS,
                SearchAnimalCommand.EXAMPLE_USAGE);
    }
}
