package seedu.address.logic.parser;

import static java.util.Arrays.stream;
import static seedu.address.logic.AnimalMessages.*;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.UnmarkTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkTaskCommand object
 */
public class UnmarkTaskCommandParser implements AnimalParser<UnmarkTaskCommand> {

    private static final Logger logger = LogsCenter.getLogger(UnmarkTaskCommandParser.class);

    @Override
    public UnmarkTaskCommand parse(String args) throws ParseException {

        logger.fine("Arguments passed to unmark parser: " + args);

        if (args.isEmpty()) {
            throw new ParseException(getAnimalHelpMessage());
        }

        String trimmedArgs = args.trim();

        if (trimmedArgs.length() < 3) {
            throw new ParseException(getTaskHelpMessage());
        }

        String[] indexLists = trimmedArgs.split("\\s+", 2);

        // checks if animal index is a number
        if (!indexLists[0].matches("\\d+")) {
            throw new ParseException("Animal " + MESSAGE_INVALID_DOUBLE_INDEX);
        }

        // checks if animal index is an integer
        if (Double.parseDouble(indexLists[0]) % 1 != 0) {
            throw new ParseException("Animal " + MESSAGE_INVALID_DOUBLE_INDEX);
        }

        int animalIntIndex = Integer.parseInt(indexLists[0]);

        // checks if animal index provided is negative
        if (animalIntIndex <= 0) {
            throw new ParseException("Animal " + MESSAGE_NEGATIVE_INDEX);
        }


        // checks to ensure task index provided are valid

        // checks if task indexes provided are numbers
        if (!indexLists[1].matches("\\d+(\\s+\\d+)*")) {
            throw new ParseException("Task " + MESSAGE_INVALID_STRING_INDEX);
        }

        double[] checkTaskIndexes = stream(indexLists[1].split("\\s+"))
                .map(Double::parseDouble)
                .mapToDouble(Double::doubleValue)
                .toArray();

        // check if indexes provided are negative
        if (stream(checkTaskIndexes).anyMatch(index -> index <= 0)) {
            throw new ParseException("Task " + MESSAGE_NEGATIVE_INDEX);
        }

        // check if indexes provided are integers
        if (stream(checkTaskIndexes).anyMatch(index -> index % 1 != 0)) {
            throw new ParseException("Task " + MESSAGE_INVALID_DOUBLE_INDEX);
        }


        Index animalIndex = Index.fromOneBased(animalIntIndex);
        Index[] taskIndexes = stream(indexLists[1].split("\\s+"))
                .map(Integer::parseInt)
                .map(Index::fromOneBased)
                .toArray(Index[]::new);


        return new UnmarkTaskCommand(animalIndex, taskIndexes);
    }

    private static String getAnimalHelpMessage() {
        return AnimalMessages.getFormattedHelpMessage(
                MESSAGE_MISSING_ANIMAL_INDEX, UnmarkTaskCommand.MESSAGE_USAGE);
    }

    private static String getTaskHelpMessage() {
        return AnimalMessages.getFormattedHelpMessage(
                MESSAGE_MISSING_TASK_INDEX, UnmarkTaskCommand.MESSAGE_USAGE);
    }
}
