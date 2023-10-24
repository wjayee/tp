package seedu.address.model.animal;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdmissionDateTest {

    @Test
    public void testConstructor_withValidDates() {
        assertDoesNotThrow(() -> new AdmissionDate("2023-10-23"));
        assertDoesNotThrow(() -> new AdmissionDate("20231023"));
        assertDoesNotThrow(() -> new AdmissionDate("1 Jan 2023"));
        assertDoesNotThrow(() -> new AdmissionDate("1 January 2023"));
    }

    @Test
    public void testConstructor_withNull() {
        assertThrows(NullPointerException.class, () -> new AdmissionDate(null));
    }

    @Test
    public void testConstructor_withInvalidDate() {
        List<String> invalidDateInputs = List.of(
                " ", //space
                "", //empty
                "abc123", //alphaNumeric
                "1*1*2*", //NumericAndSymbols
                "01012023", //DDMMYYYY
                "01-01-2023", //DD-MM-YYYY
                "12302023", //MMDDYYYY
                "12-30-2023", //MM-DD-YYYY
                "40309999"); //Out of calendar
        for (String invalidDate : invalidDateInputs) {
            assertThrows(IllegalArgumentException.class, () -> new AdmissionDate(invalidDate));
        }
    }

    @Test
    public void testIsValidDate_withValidDates() {
        assertTrue(AdmissionDate.isValidDate("2023-10-23"));
        assertTrue(AdmissionDate.isValidDate("20231023"));
        assertTrue(AdmissionDate.isValidDate("1 Jan 2023"));
        assertTrue(AdmissionDate.isValidDate("1 January 2023"));
    }

    @Test
    public void testIsValidDate_withNull() {
        assertThrows(NullPointerException.class, () -> AdmissionDate.isValidDate(null));
    }

    @Test
    public void testIsValidDate_withInvalidDates() {
        List<String> invalidDateInputs = List.of(
                " ", //space
                "", //empty
                "abc123", //alphaNumeric
                "1*1*2*", //NumericAndSymbols
                "01012023", //DDMMYYYY
                "01-01-2023", //DD-MM-YYYY
                "12302023", //MMDDYYYY
                "12-30-2023", //MM-DD-YYYY
                "40309999"); //Out of calendar
        for (String invalidDate : invalidDateInputs) {
            assertFalse(() -> AdmissionDate.isValidDate(invalidDate));
        }
    }

    @Test
    public void testEquals_withNull() {
        AdmissionDate admissionDate = new AdmissionDate("2023-01-01");
        assertFalse(admissionDate.equals(null));
    }

    @Test
    public void testEquals_withEqualDate() {
        AdmissionDate admissionDate = new AdmissionDate("2023-01-01");
        assertTrue(admissionDate.equals(new AdmissionDate("2023-01-01")));
    }

    @Test
    public void testEquals_withNotEqualDate() {
        AdmissionDate admissionDate = new AdmissionDate("2023-01-01");
        assertFalse(admissionDate.equals(new AdmissionDate("2023-10-10")));
    }

    @Test
    public void testHashCode() {
        AdmissionDate admissionDate1 = new AdmissionDate("2023-01-01");
        AdmissionDate admissionDate2 = new AdmissionDate("2023-01-01");
        assertEquals(admissionDate1.hashCode(), admissionDate2.hashCode());

    }

    @Test
    public void testToString() {
        AdmissionDate admissionDate = new AdmissionDate("2023-01-01");
        assertEquals(admissionDate.toString(), "2023-01-01");
    }
}

