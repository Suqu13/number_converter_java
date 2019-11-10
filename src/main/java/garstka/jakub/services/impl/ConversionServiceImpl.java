package garstka.jakub.services.impl;

import garstka.jakub.config.exceptions.InvalidNumberException;

import garstka.jakub.config.exceptions.InvalidNumeralSystemException;
import garstka.jakub.models.NumeralSystem;
import garstka.jakub.services.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static garstka.jakub.config.Constants.*;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Override
    public String convert(Double decimalValue, NumeralSystem numeralSystem) throws RuntimeException {
        switch (numeralSystem) {
            case ROMAN:
                int integerPart = decimalValue.intValue();
                if (integerPart != decimalValue)
                    throw new InvalidNumberException(FRACTION_NOT_SUPPORTED_BY_ROMAN_NUMERAL_SYSTEM);
                return convertToRoman(decimalValue.intValue());
            case HEXADECIMAL:
                return convertToHexadecimal(decimalValue);
            default:
                throw  new InvalidNumeralSystemException(INVALID_NUMERAL_SYSTEM_MASSAGE);
        }
    }

    private String convertToRoman(Integer value) throws InvalidNumberException {
        if (value > HIGHEST_ROMAN_NUMBER || value < LOWEST_ROMAN_NUMBER)
            throw new InvalidNumberException(HIGHEST_ROMAN_NUMBER, LOWEST_ROMAN_NUMBER);

        StringBuilder result = new StringBuilder();
        result.append(romanDecoder(value / 1000, "M", "", ""));
        value %= 1000;
        result.append(romanDecoder(value / 100, "C", "D", "M"));
        value %= 100;
        result.append(romanDecoder(value / 10, "X", "L", "C"));
        value %= 10;
        result.append(romanDecoder(value, "I", "V", "X"));
        return result.toString();
    }

    private String romanDecoder(Integer value, String one, String five, String ten) {
        switch (value) {
            case EDGE_CASE_WITH_ZERO:
                return "";
            case EDGE_CASE_WITH_MULTIPLE_FOUR:
                return one.concat(five);
            case EDGE_CASE_WITH_MULTIPLE_NINE:
                return one.concat(ten);
            default:
                return (value < 5) ?
                        String.join("", Collections.nCopies(value, one))
                        :
                        five.concat(String.join("", Collections.nCopies(value - 5, one)));
        }
    }

    private String convertToHexadecimal(Double value) {
        StringBuilder result = new StringBuilder();
        if (value < 0) {
            result.append("-");
            value = Math.abs(value);
        }

        int integerPart = value.intValue();
        double fractionPart = value - integerPart;

        result.append(hexadecimalIntegersDecoder(integerPart));
        result.append(hexadecimalFractionsDecoder(fractionPart));

        return result.toString();
    }

    private String hexadecimalIntegersDecoder(Integer integerValue) {
        StringBuilder result = new StringBuilder();
        if (integerValue == 0) {
            result.append(0);
        } else {
            int value = integerValue % BASE_HEXADECIMAL_SYSTEM_NUMBER;
            integerValue = (integerValue - value) / BASE_HEXADECIMAL_SYSTEM_NUMBER;
            while (integerValue - value != 0) {
                result.append(HEXADECIMAL_DIGITS[value]);
                value = integerValue % BASE_HEXADECIMAL_SYSTEM_NUMBER;
                integerValue = (integerValue - value) / BASE_HEXADECIMAL_SYSTEM_NUMBER;
            }
            result.reverse();
        }
        return result.toString();
    }

    private String hexadecimalFractionsDecoder(Double fractionValue) {
        StringBuilder result = new StringBuilder();
        if (fractionValue != 0) {
            result.append(".");
            int integerPart;
            double fractionPart = fractionValue;
            while (fractionPart != 0) {
                integerPart = (int) (fractionPart * BASE_HEXADECIMAL_SYSTEM_NUMBER);
                fractionPart = fractionPart * BASE_HEXADECIMAL_SYSTEM_NUMBER - integerPart;
                result.append(HEXADECIMAL_DIGITS[integerPart]);
            }
        }
        return result.toString();
    }
}
