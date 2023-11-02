package seedu.address.logic.commands;

import java.util.Arrays;
import java.util.stream.Collectors;

import seedu.address.model.AnimalModel;
import seedu.address.model.CommandEnum;

/**
 * Format full help instructions for every command for display. Matches partial words case-insensitively.
 * E.g. If we have the add command and addtask command, an input of "ad", "Ad", "adD" will return the help
 * messages of these commands.
 */
public class HelpAnimalCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions. "
        + "Can specify a Command to view the format and an example of the Command's usage.\n"
        + "Parameters: "
        + "COMMAND_NAME (case-insensitive)";

    public static final String EXAMPLE_USAGE = "Example: " + COMMAND_WORD + "add";
    public static final String MESSAGE_UNRECOGNIZED_COMMAND_FORMAT = "Command: %s not recognized!";
    private final String targetCommandWord;

    public HelpAnimalCommand(String targetCommandWord) {
        this.targetCommandWord = targetCommandWord;
    }

    @Override
    public CommandResult execute(AnimalModel model) {
        // Show just the usage guide for Help command.
        if (targetCommandWord.isEmpty()) {
            return new CommandResult(MESSAGE_USAGE, true, false);
        }

        String helpMessage = Arrays.stream(CommandEnum.values())
            .filter(cmd -> cmd.getCommandWord().contains(targetCommandWord.toLowerCase()))
            .map(CommandEnum::getHelpMessage)
            .collect(Collectors.joining("\n\n")); // leaves a blank line in between help messages.

        // For cases where the input is not empty, do not trigger the HelpWindow pop-up.
        if (helpMessage.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_UNRECOGNIZED_COMMAND_FORMAT, targetCommandWord));
        }

        return new CommandResult(helpMessage);
    }
}
