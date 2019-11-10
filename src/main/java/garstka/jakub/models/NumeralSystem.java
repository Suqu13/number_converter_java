package garstka.jakub.models;

import garstka.jakub.config.exceptions.InvalidNumeralSystemException;

import static garstka.jakub.config.Constants.INVALID_NUMERAL_SYSTEM_MASSAGE;

public enum NumeralSystem {
    ROMAN,
    HEXADECIMAL;

    public static NumeralSystem lookUp(String name) {
        try {
            return NumeralSystem.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new InvalidNumeralSystemException(INVALID_NUMERAL_SYSTEM_MASSAGE);
        }
    }
}
