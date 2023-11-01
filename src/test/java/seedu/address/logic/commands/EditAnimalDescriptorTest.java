package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.AnimalCommandTestUtil.DESC_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.DESC_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_ADMISSION_DATE_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_BREED_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_DATE_OF_BIRTH_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_NAME_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_SEX_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_SPECIES_TOFU;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditAnimalCommand.EditAnimalDescriptor;
import seedu.address.testutil.EditAnimalDescriptorBuilder;

public class EditAnimalDescriptorTest {

    @Test
    public void testEquals_SameValues_ReturnsTrue() {
        EditAnimalDescriptor descriptorWithSameValues = new EditAnimalDescriptor(DESC_POOKIE);
        assertEquals(DESC_POOKIE, descriptorWithSameValues);
    }

    @Test
    public void testEquals_SameObject_ReturnsTrue() {
        assertEquals(DESC_POOKIE, DESC_POOKIE);
    }

    @Test
    public void testEquals_Null_ReturnsFalse() {
        assertNotEquals(null, DESC_POOKIE);
    }

    @Test
    public void testEquals_DifferentTypes_ReturnsFalse() {
        assertNotEquals(5, DESC_POOKIE);
    }

    @Test
    public void testEquals_DifferentValues_ReturnsFalse() {
        assertNotEquals(DESC_POOKIE, DESC_TOFU);
    }

    @Test
    public void testEquals_DifferentName_ReturnsFalse() {
        EditAnimalDescriptor editedPookie = new EditAnimalDescriptorBuilder(DESC_POOKIE)
                .withName(VALID_NAME_TOFU)
                .build();
        assertNotEquals(DESC_POOKIE, editedPookie);
    }

    @Test
    public void testEquals_DifferentSex_ReturnsFalse() {
        EditAnimalDescriptor editedPookie = new EditAnimalDescriptorBuilder(DESC_POOKIE)
                .withSex(VALID_SEX_TOFU)
                .build();
        assertNotEquals(DESC_POOKIE, editedPookie);
    }

    @Test
    public void testEquals_DifferentSpecies_ReturnsFalse() {
        EditAnimalDescriptor editedPookie = new EditAnimalDescriptorBuilder(DESC_POOKIE)
                .withSpecies(VALID_SPECIES_TOFU)
                .build();
        assertNotEquals(DESC_POOKIE, editedPookie);
    }

    @Test
    public void testEquals_DifferentBreed_ReturnsFalse() {
        EditAnimalDescriptor editedPookie = new EditAnimalDescriptorBuilder(DESC_POOKIE)
                .withBreed(VALID_BREED_TOFU)
                .build();
        assertNotEquals(DESC_POOKIE, editedPookie);
    }

    @Test
    public void testEquals_DifferentDateOfBirth_ReturnsFalse() {
        EditAnimalDescriptor editedPookie = new EditAnimalDescriptorBuilder(DESC_POOKIE)
                .withDateOfBirth(VALID_DATE_OF_BIRTH_TOFU)
                .build();
        assertNotEquals(DESC_POOKIE, editedPookie);
    }

    @Test
    public void testEquals_DifferentAdmissionDate_ReturnsFalse() {
        EditAnimalDescriptor editedPookie = new EditAnimalDescriptorBuilder(DESC_POOKIE)
                .withAdmissionDate(VALID_ADMISSION_DATE_TOFU)
                .build();
        assertNotEquals(DESC_POOKIE, editedPookie);
    }

    @Test
    public void toStringMethod() {
        EditAnimalDescriptor animalDescriptor = new EditAnimalDescriptor();
        String expected = EditAnimalDescriptor.class.getCanonicalName() + "{name="
                + animalDescriptor.getName().orElse(null) + ", dateOfBirth="
                + animalDescriptor.getDateOfBirth().orElse(null) + ", admissionDate="
                + animalDescriptor.getAdmissionDate().orElse(null) + ", species="
                + animalDescriptor.getSpecies().orElse(null) + ", breed="
                + animalDescriptor.getBreed().orElse(null) + ", sex="
                + animalDescriptor.getSex().orElse(null) + "}";
        assertEquals(expected, animalDescriptor.toString());
    }
}
