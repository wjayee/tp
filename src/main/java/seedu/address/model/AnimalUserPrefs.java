package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class AnimalUserPrefs implements AnimalReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path catalogFilePath = Paths.get("data" , "catalog.json");
    /**
     * Creates a {@code AnimalUserPrefs} with default values.
     */
    public AnimalUserPrefs() {}

    /**
     * Creates a {@code AnimalUserPrefs} with the prefs in {@code userPrefs}.
     */
    public AnimalUserPrefs(AnimalReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code AnimalUserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(AnimalReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setCatalogFilePath(newUserPrefs.getCatalogFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getCatalogFilePath() {
        return catalogFilePath;
    }

    public void setCatalogFilePath(Path catalogFilePath) {
        requireNonNull(catalogFilePath);
        this.catalogFilePath = catalogFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AnimalUserPrefs)) {
            return false;
        }

        AnimalUserPrefs otherUserPrefs = (AnimalUserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && catalogFilePath.equals(otherUserPrefs.catalogFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, catalogFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + catalogFilePath);
        return sb.toString();
    }

}