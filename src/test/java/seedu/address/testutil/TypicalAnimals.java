package seedu.address.testutil;

import seedu.address.model.animal.Animal;

/**
 * A utility class containing a list of {@code Animal} objects to be used in tests.
 */
public class TypicalAnimals {

    public static final Animal TOFU = new AnimalBuilder().withName("Tofu").withPetId("2")
            .withDateOfBirth("01/01/2019").withSex("M").withSpecies("Dog").withBreed("Retriever")
            .withAdmissionDate("01/01/2020").build();

    public static final Animal MUFFIN = new AnimalBuilder().withName("Muffin").withPetId("3")
            .withDateOfBirth("02/02/2019").withSex("F").withSpecies("Dog").withBreed("Jack Russell Terrier")
            .withAdmissionDate("02/02/2020").build();

    //TODO: Add more animals if needed
}
