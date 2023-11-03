package seedu.address.logic.parser;

import static java.util.Arrays.stream;
import static seedu.address.logic.AnimalMessages.MESSAGE_MISSING_ANIMAL_INDEX;
import static seedu.address.logic.AnimalMessages.MESSAGE_MISSING_TASK_INDEX;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.MarkTaskCommand;
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


        Index animalIndex = Index.fromOneBased(Integer.parseInt(indexLists[0]));
        Index[] taskIndex = stream(indexLists[1].split("\\s+"))
                .map(Integer::parseInt)
                .map(Index::fromOneBased)
                .toArray(Index[]::new);

        return new UnmarkTaskCommand(animalIndex, taskIndex);
    }

    private static String getAnimalHelpMessage() {
        return AnimalMessages.getFormattedHelpMessage(
                MESSAGE_MISSING_ANIMAL_INDEX, MarkTaskCommand.MESSAGE_USAGE);
    }

    private static String getTaskHelpMessage() {
        return AnimalMessages.getFormattedHelpMessage(
                MESSAGE_MISSING_TASK_INDEX, MarkTaskCommand.MESSAGE_USAGE);
    }
}
