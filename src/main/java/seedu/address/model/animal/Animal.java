package seedu.address.model.animal;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.animal.healthStatus.HealthStatus;
import seedu.address.model.animal.notes.Notes;

/**
 * Represents an Animal in the catalog.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Animal {
    private final Name name;
    private final PetId petId;
    private final DateOfBirth dateOfBirth;
    private final Sex sex;
    private final Species species;
    private final Breed breed;
    private final AdmissionDate admissionDate;
    private final HealthStatus healthStatus;
    private final Notes otherNotes;

    public Animal(Name name, PetId petId, Species species, Breed breed, Sex sex,
                  AdmissionDate admissionDate, DateOfBirth dateOfBirth) {
        this.name = name;
        this.petId = petId;
        this.sex = sex;
        this.species = species;
        this.breed = breed;
        this.admissionDate = admissionDate;
        this.dateOfBirth = dateOfBirth;
        this.healthStatus = new HealthStatus();
        this.otherNotes = new Notes();
    }

    public Name getName() {
        return name;
    }

    public PetId getPetId() {
        return petId;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public AdmissionDate getAdmissionDate() {
        return admissionDate;
    }

    public Sex getSex() {
        return sex;
    }

    public Species getSpecies() {
        return species;
    }

    public Breed getBreed() {
        return breed;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public Notes getNotes() {
        return otherNotes;
    }

    // ----------------- These getter methods are used for JSON serialization by JsonAdaptedAnimal--------------
    // NOTE: For Animal attributes that simply stores a string as its underlying implementation, with its toString()
    // method returning the string as-is, toString() is called to use it for deserialization.
    // This must be taken into account when modifying the existing toString() methods.
    public String getNameForSerialization() {
        return name.toString();
    }

    public String getPetIdForSerialization() {
        return petId.toString();
    }

    public String getDateOfBirthForSerialization() {
        return dateOfBirth.getDateOfBirth().toString();
    }

    public String getAdmissionDateForSerialization() {
        return admissionDate.getAdmissionDate().toString();
    }

    public String getSexForSerialization() {
        return sex.getSerializableForm();
    }

    public String getSpeciesForSerialization() {
        return species.toString();
    }

    public String getBreedForSerialization() {
        return breed.toString();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("id", petId)
                .add("sex", sex)
                .add("species", species)
                .add("breed", breed)
                .add("admissionDate", admissionDate)
                .add("dateOfBirth", dateOfBirth)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, petId, sex, species, breed, admissionDate, dateOfBirth);
    }

    public boolean isSameAnimal(Animal otherAnimal) {
        if (otherAnimal == this) {
            return true;
        }

        return otherAnimal != null
                && otherAnimal.getPetId().equals(getPetId());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Animal)) {
            return false;
        }

        Animal otherAnimal = (Animal) other;
        return name.equals(otherAnimal.name)
                && petId.equals(otherAnimal.petId)
                && dateOfBirth.equals(otherAnimal.dateOfBirth)
                && sex.equals(otherAnimal.sex)
                && species.equals(otherAnimal.species)
                && breed.equals(otherAnimal.breed)
                && admissionDate.equals(otherAnimal.admissionDate);
    }
}
