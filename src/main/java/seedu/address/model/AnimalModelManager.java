package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Filter;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.animal.Animal;
import seedu.address.model.person.Person;

public class AnimalModelManager implements AnimalModel{
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AnimalCatalog animalCatalog;

    private final UserPrefs userPrefs;

    private final FilteredList<Animal> filteredAnimals;

    public AnimalModelManager(ReadOnlyAnimalCatalog animalCatalog, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(animalCatalog, userPrefs);

        logger.fine("Initializing with address book: " + animalCatalog + " and user prefs " + userPrefs);

        this.animalCatalog = new AnimalCatalog(animalCatalog);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredAnimals = new FilteredList<>(this.animalCatalog.getAnimalList());
    }

    public AnimalModelManager() {
        this(new AnimalCatalog(), new UserPrefs());
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAnimalCatalogFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAnimalCatalogFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.animalCatalog.resetData(animalCatalog);
    }

    @Override
    public ReadOnlyAnimalCatalog getAnimalCatalog() {
        return animalCatalog;
    }

    @Override
    public boolean hasAnimal(Animal animal) {
        requireNonNull(animal);
        return animalCatalog.hasAnimal(animal);
    }

    @Override
    public void deleteAnimal(Animal target) {
        animalCatalog.removeAnimal(target);
    }

    @Override
    public void addAnimal(Animal animal) {
        animalCatalog.addAnimal(animal);
        updateFilteredAnimalList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setAnimal(Animal target, Animal editedAnimal) {
        requireAllNonNull(target, editedAnimal);

        animalCatalog.setAnimal(target, editedAnimal);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Animal> getFilteredAnimalList() {
        return filteredAnimals;
    }

    @Override
    public void updateFilteredAnimalList(Predicate<Animal> predicate) {
        requireNonNull(predicate);
        filteredAnimals.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        AnimalModelManager otherModelManager = (AnimalModelManager) other;
        return animalCatalog.equals(otherModelManager.animalCatalog)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredAnimals.equals(otherModelManager.filteredAnimals);
    }
}
