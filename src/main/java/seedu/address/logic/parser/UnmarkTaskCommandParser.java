package seedu.address.logic.parser;

import static java.util.Arrays.stream;
import static seedu.address.logic.AnimalMessages.MESSAGE_MISSING_TASK_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.UnmarkTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkTaskCommand object
 */
public class UnmarkTaskCommandParser implements AnimalParser<UnmarkTaskCommand> {

    @Override
    public UnmarkTaskCommand parse(String args) throws ParseException {
        String[] indexLists = args.trim().split("\\s+", 2);

        Index animalIndex = Index.fromOneBased(Integer.parseInt(indexLists[0]));
        Index[] taskIndex = stream(indexLists[1].split("\\s+"))
                .map(Integer::parseInt)
                .map(Index::fromOneBased)
                .toArray(Index[]::new);

        if (taskIndex.length == 0) {
            throw new ParseException(getHelpMessage());
        }

        return new UnmarkTaskCommand(animalIndex, taskIndex);
    }

    private static String getHelpMessage() {
        return AnimalMessages.getFormattedHelpMessage(
                MESSAGE_MISSING_TASK_INDEX, UnmarkTaskCommand.MESSAGE_USAGE);
    }
}
