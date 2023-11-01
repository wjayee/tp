package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.FEED;

import org.junit.jupiter.api.Test;



public class JsonAdaptedTaskTest {

    @Test
    public void testConstructor_nullDetails_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JsonAdaptedTask(null));
    }

    @Test
    public void toTaskModel_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask feedTask = new JsonAdaptedTask(FEED);
        assertEquals(FEED, feedTask.toTaskType());
    }

}
