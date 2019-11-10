package garstka.jakub.models;

import garstka.jakub.config.exceptions.InvalidNumeralSystemException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumeralSystemTest {

    @Test
    public void lookUpValidNumeralSystem() {
        NumeralSystem numeralSystemRoman = NumeralSystem.ROMAN;
        NumeralSystem numeralSystemHexadecimal = NumeralSystem.HEXADECIMAL;

        String romanNumeralSystemName = "ROMAN";
        String hexadecimalNumeralSystemName = "HEXADECIMAL";

        assertEquals(numeralSystemRoman, NumeralSystem.lookUp(romanNumeralSystemName));
        assertEquals(numeralSystemHexadecimal, NumeralSystem.lookUp(hexadecimalNumeralSystemName));
    }

    @Test
    public void lookUpInvalidNumeralSystem() {
        String invalidNumeralSystemName = "INVALID";

        assertThrows(InvalidNumeralSystemException.class, () -> NumeralSystem.lookUp(invalidNumeralSystemName));
    }
}