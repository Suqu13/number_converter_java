package garstka.jakub.config.exceptions;

import static garstka.jakub.config.Constants.NOT_IN_THE_RANGE_MESSAGE;

public class InvalidNumberException extends RuntimeException {

    public InvalidNumberException(Integer lowestValue, Integer highestValue) {
        super(String.format(NOT_IN_THE_RANGE_MESSAGE, lowestValue, highestValue));
    }

    public InvalidNumberException(String message) {
        super(message);
    }
}