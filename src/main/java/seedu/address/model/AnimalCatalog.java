package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.UniqueAnimalList;

public class AnimalCatalog implements ReadOnlyAnimalCatalog {

    private final UniqueAnimalList animals;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        animals = new UniqueAnimalList();
    }

    public AnimalCatalog() {}

    public AnimalCatalog(ReadOnlyAnimalCatalog toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setAnimals(List<Animal> animals) {
        try {
            this.animals.setAnimals(animals);
        } catch (Exception e) {

        }
    }

    public void resetData(ReadOnlyAnimalCatalog newData) {
        requireNonNull(newData);

        setAnimals(newData.getAnimalList());
    }

    public boolean hasAnimal(Animal animal) {
        requireNonNull(animal);
        return animals.contains(animal);
    }

    public void addAnimal(Animal a) {
        try {
            animals.add(a);
        } catch (Exception e) {

        }
    }

    public void setAnimal(Animal target, Animal editedAnimal) {
        try {
            requireNonNull(editedAnimal);

            animals.setAnimal(target, editedAnimal);

        } catch (Exception e) {

        }
    }

    public void removeAnimal(Animal key) {
        try {
            animals.remove(key);
        } catch (Exception e) {

        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("animals", animals)
                .toString();
    }

    @Override
    public ObservableList<Animal> getAnimalList() {return animals.asUnmodifiableObservableList();}

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AnimalCatalog)) {
            return false;
        }

        AnimalCatalog otherAnimalCatalog = (AnimalCatalog) other;
        return animals.equals(otherAnimalCatalog.animals);
    }

    @Override
    public int hashCode() {
        return animals.hashCode();
    }

}
