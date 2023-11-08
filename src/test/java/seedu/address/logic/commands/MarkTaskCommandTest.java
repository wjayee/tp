package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.AnimalCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AnimalCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAnimals.TOFU;
import static seedu.address.testutil.TypicalAnimals.getTypicalCatalog;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH;
import static seedu.address.testutil.TypicalTasks.FED;
import static seedu.address.testutil.TypicalTasks.WALKED;

import org.junit.jupiter.api.Test;
import seedu.address.model.AnimalCatalog;
import seedu.address.model.AnimalModel;
import seedu.address.model.AnimalModelManager;
import seedu.address.model.AnimalUserPrefs;
import seedu.address.model.animal.Animal;
import seedu.address.testutil.AnimalBuilder;

import java.util.List;

public class MarkTaskCommandTest {

    private AnimalModel model = new AnimalModelManager(getTypicalCatalog(), new AnimalUserPrefs());

    @Test
    public void execute_validAnimalTaskIndex_success() {

        Animal markedAnimal = new AnimalBuilder(TOFU).withTaskList(List.of(FED, WALKED)).build();

        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST, INDEX_FIRST, INDEX_SECOND);

        String expectedMessage = MarkTaskCommand.MESSAGE_SUCCESS;

        AnimalModel expectedModel = new AnimalModelManager(new AnimalCatalog(model.getAnimalCatalog()), new AnimalUserPrefs());
        expectedModel.setAnimal(TOFU, markedAnimal);

        assertCommandSuccess(markTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAnimalIndex_failure() {
        // Animal index exceeds number of animals in the list
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_SIXTH, INDEX_FIRST, INDEX_SECOND);

        String expectedMessage = MarkTaskCommand.MESSAGE_EXCESS_ANIMAL_INDEX;

        assertCommandFailure(markTaskCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidTaskIndex_failure() {
        // Task index exceeds number of tasks in the animal
        MarkTaskCommand markTaskCommand =
                new MarkTaskCommand(INDEX_FIRST, INDEX_FIRST, INDEX_SECOND, INDEX_THIRD);

        String expectedMessage = MarkTaskCommand.MESSAGE_EXCESS_TASK_INDEX;

        assertCommandFailure(markTaskCommand, model, expectedMessage);
        // Check that tasks are not marked as done
        assertTrue(model.getFilteredAnimalList()
                .get(INDEX_FIRST.getZeroBased())
                .getTaskList()
                .equals(TOFU.getTaskList()));
    }

}
