package garstka.jakub.config;

import static garstka.jakub.config.Constants.NOT_IN_THE_RANGE_MESSAGE;

public class InvalidNumberExceptions extends RuntimeException {
    public InvalidNumberExceptions(String message) {
        super(message);
    }

    public InvalidNumberExceptions(Integer lowestValue, Integer highestValue) {
        super(String.format(NOT_IN_THE_RANGE_MESSAGE, lowestValue, highestValue));
    }
}