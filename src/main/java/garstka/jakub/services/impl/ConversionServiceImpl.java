package garstka.jakub.services.impl;

import garstka.jakub.config.InvalidNumberExceptions;

import garstka.jakub.models.NumeralSystem;
import garstka.jakub.services.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static garstka.jakub.config.Constants.HIGHEST_ROMAIN_NUMBER;
import static garstka.jakub.config.Constants.LOWEST_ROMAIN_NUMBER;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Override
    public String convert(Double decimalValue, NumeralSystem numeralSystem) throws InvalidNumberExceptions {
        switch (numeralSystem) {
            case ROMAIN:
                return convertToRomain(decimalValue.intValue());
            case HEXADECIMAL:
                return convertToHexadecimal(decimalValue);
            default:
                return "Conversion not supported";
        }
    }

    private String convertToRomain(Integer value) throws InvalidNumberExceptions {
        if (value > HIGHEST_ROMAIN_NUMBER || value < LOWEST_ROMAIN_NUMBER)
            throw new InvalidNumberExceptions(HIGHEST_ROMAIN_NUMBER, LOWEST_ROMAIN_NUMBER);

        StringBuilder result = new StringBuilder();
        result.append(romainHelper(value / 1000, "M", "", ""));
        value %= 1000;
        result.append(romainHelper(value / 100, "C", "D", "M"));
        value %= 100;
        result.append(romainHelper(value / 10, "X", "L", "C"));
        value %= 10;
        result.append(romainHelper(value, "I", "V", "X"));
        return result.toString();
    }

    private String romainHelper(Integer value, String one, String five, String ten) {
        switch (value) {
            case 0:
                return "";
            case 4:
                return one.concat(five);
            case 9:
                return one.concat(ten);
            default:
                return (value < 5) ?
                        String.join("", Collections.nCopies(value, one))
                        :
                        five.concat(String.join("", Collections.nCopies(value - 5, one)));
        }
    }

    private String convertToHexadecimal(Double value) {
        String[] hexadecimalNumbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        StringBuilder result = new StringBuilder();
        if (value < 0) {
            result.append("-");
            value = Math.abs(value);
        }

        int integerPart = value.intValue();
        double fractionPart = value - integerPart;

        if (integerPart == 0) {
            result.append(0);
        } else {
            result.append(hexadecimalHelperIntegers(integerPart, hexadecimalNumbers));
        }

        if (fractionPart != 0)
            result.append(".".concat(hexadecimalHelperFractions(fractionPart, hexadecimalNumbers)));

        return result.toString();
    }

    private String hexadecimalHelperIntegers(Integer integerValue, String[] hexadecimalNumbers) {
        StringBuilder result = new StringBuilder();
        int value = integerValue % 16;
        int integerValueHolder = (integerValue - value) / 16;
        while (integerValueHolder - value != 0) {
            result.append(hexadecimalNumbers[value]);
            value = integerValueHolder % 16;
            integerValueHolder = (integerValueHolder - value) / 16;
        }
        result.reverse();
        return result.toString();
    }

    private String hexadecimalHelperFractions(Double fractionValue, String[] hexadecimalNumbers) {
        StringBuilder result = new StringBuilder();
        int integerPart;
        double fractionPart = fractionValue;
        while (fractionPart != 0) {
            integerPart = (int) (fractionPart * 16);
            fractionPart = fractionPart * 16 - integerPart;
            result.append(hexadecimalNumbers[integerPart]);
        }
        return result.toString();
    }
}
