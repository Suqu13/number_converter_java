package garstka.jakub.models;

import garstka.jakub.config.exceptions.InvalidNumeralSystemException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumeralSystemTest {

    @Test
    public void lookUpValidNumeralSystem() {
        NumeralSystem numeralSystemRomain = NumeralSystem.ROMAIN;
        NumeralSystem numeralSystemHexadecimal = NumeralSystem.HEXADECIMAL;

        String romainNumeralSystemName = "ROMAIN";
        String hexadecimalNumeralSystemName = "HEXADECIMAL";

        assertEquals(numeralSystemRomain, NumeralSystem.lookUp(romainNumeralSystemName));
        assertEquals(numeralSystemHexadecimal, NumeralSystem.lookUp(hexadecimalNumeralSystemName));
    }

    @Test
    public void lookUpInvalidNumeralSystem() {
        String invalidNumeralSystemName = "INVALID";

        assertThrows(InvalidNumeralSystemException.class, () -> NumeralSystem.lookUp(invalidNumeralSystemName));
    }
}