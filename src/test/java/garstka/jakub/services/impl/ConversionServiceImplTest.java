package garstka.jakub.services.impl;

import garstka.jakub.config.InvalidNumberExceptions;
import garstka.jakub.models.NumeralSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ConversionServiceImplTest {

    private ConversionServiceImpl conversionService;

    @BeforeEach
    void setUp() {
        conversionService = new ConversionServiceImpl();
    }

    @Test
    void convertExemplaryEdgeCasesForRomainNumeralSystem() {
        NumeralSystem romain = NumeralSystem.ROMAIN;

        Double decimal_1 = 1.0;
        Double decimal_4 = 4.0;
        Double decimal_9 = 9.0;
        Double decimal_19 = 19.0;
        Double decimal_49 = 49.0;
        Double decimal_59 = 59.0;
        Double decimal_99 = 99.0;
        Double decimal_545 = 545.0;
        Double decimal_3999 = 3999.0;

        String romain_1 = "I";
        String romain_4 = "IV";
        String romain_9 = "IX";
        String romain_19 = "XIX";
        String romain_49 = "XLIX";
        String romain_59 = "LIX";
        String romain_99 = "XCIX";
        String romain_545 = "DXLV";
        String romain_3999 = "MMMCMXCIX";

        assertEquals(romain_1, conversionService.convert(decimal_1, romain));
        assertEquals(romain_4, conversionService.convert(decimal_4, romain));
        assertEquals(romain_9, conversionService.convert(decimal_9, romain));
        assertEquals(romain_19, conversionService.convert(decimal_19, romain));
        assertEquals(romain_49, conversionService.convert(decimal_49, romain));
        assertEquals(romain_59, conversionService.convert(decimal_59, romain));
        assertEquals(romain_99, conversionService.convert(decimal_99, romain));
        assertEquals(romain_545, conversionService.convert(decimal_545, romain));
        assertEquals(romain_3999, conversionService.convert(decimal_3999, romain));
    }

    @Test
    void throwExceptionWhenValueLowerThanOneToRomainNumeralSystem() {
        NumeralSystem romain = NumeralSystem.ROMAIN;

        Double decimalValue_0 = 0.0;
        Double decimalValue_minus_1 = -1.0;
        Double decimalValue_minus_4000 = -4000.0;

        assertThrows(InvalidNumberExceptions.class, () -> {conversionService.convert(decimalValue_0, romain);});
        assertThrows(InvalidNumberExceptions.class, () -> {conversionService.convert(decimalValue_minus_1, romain);});
        assertThrows(InvalidNumberExceptions.class, () -> {conversionService.convert(decimalValue_minus_4000, romain);});
    }

    @Test
    void throwExceptionWhenValueHigherThanTreeThousandNineHundredNinetyNineToRomainNumeralSystem() {
        NumeralSystem romain = NumeralSystem.ROMAIN;

        Double decimalValue_4000 = 4000.0;
        Double decimalValue_10000 = 10000.0;

        assertThrows(InvalidNumberExceptions.class, () -> {conversionService.convert(decimalValue_4000, romain);});
        assertThrows(InvalidNumberExceptions.class, () -> {conversionService.convert(decimalValue_10000, romain);});
    }

    @Test
    void convertIntegerValueToHexadecimalNumeralSystem() {
        NumeralSystem hexadecimal = NumeralSystem.HEXADECIMAL;

        Double decimal_10 = 10.0;
        Double decimal_100 = 100.0;
        Double decimal_245 = 245.0;

        String hexadecimal_10 = "A";
        String hexadecimal_100 = "64";
        String hexadecimal_245 = "F5";

        assertEquals(hexadecimal_10, conversionService.convert(decimal_10, hexadecimal));
        assertEquals(hexadecimal_100, conversionService.convert(decimal_100, hexadecimal));
        assertEquals(hexadecimal_245, conversionService.convert(decimal_245, hexadecimal));
    }

    private Boolean checkIfNumbersWithFractionAreAlmostEqual(String value, String valueToCompare) {
        valueToCompare = valueToCompare.substring(0, valueToCompare.length() - 1);
        value = value.substring(0, valueToCompare.length());
        return value.equals(valueToCompare);
    }

    @Test
    void convertFractionValueToHexadecimalNumeralSystem() {
        NumeralSystem hexadecimal = NumeralSystem.HEXADECIMAL;

        Double decimal_0dot25 = 0.25;
        Double decimal_0dot8 = 0.80;
        Double decimal_0dot223 = 0.223;


        String hexadecimal_0dot25 = "0.4";
        String hexadecimal_0dot8 = "0.CCCCCCCCCCCCCCCCCCCD";
        String hexadecimal_0dot223 = "0.3916872B020C49BA5E35";

        assertTrue(checkIfNumbersWithFractionAreAlmostEqual(hexadecimal_0dot25, conversionService.convert(decimal_0dot25, hexadecimal)));
        assertTrue(checkIfNumbersWithFractionAreAlmostEqual(hexadecimal_0dot8, conversionService.convert(decimal_0dot8, hexadecimal)));
        assertTrue(checkIfNumbersWithFractionAreAlmostEqual(hexadecimal_0dot223, conversionService.convert(decimal_0dot223, hexadecimal)));
    }

    @Test
    void convertMixedValueToHexadecimalNumeralSystem() {
        NumeralSystem hexadecimal = NumeralSystem.HEXADECIMAL;

        Double decimal_1dot25 = 1.25;
        Double decimal_91dot8 = 91.80;
        Double decimal_199dot223 = 199.223;

        String hexadecimal_1dot25 = "1.4";
        String hexadecimal_91dot8 = "5B.CCCCCCCCCCCCCCCCCCCD";
        String hexadecimal_199dot223  = "C7.3916872B020C49BA5E35";

        assertTrue(checkIfNumbersWithFractionAreAlmostEqual(hexadecimal_1dot25, conversionService.convert(decimal_1dot25, hexadecimal)));
        assertTrue(checkIfNumbersWithFractionAreAlmostEqual(hexadecimal_91dot8, conversionService.convert(decimal_91dot8, hexadecimal)));
        assertTrue(checkIfNumbersWithFractionAreAlmostEqual(hexadecimal_199dot223, conversionService.convert(decimal_199dot223, hexadecimal)));
    }

    @Test
    void convertNegativeMixedValueToHexadecimalNumeralSystem() {
        NumeralSystem hexadecimal = NumeralSystem.HEXADECIMAL;

        Double decimal_minus_1dot25 = -1.25;
        Double decimal_minus_91dot8 = -91.80;
        Double decimal_minus_199dot223 = -199.223;

        String hexadecimal_minus_1dot25 = "-1.4";
        String hexadecimal_minus_91dot8 = "-5B.CCCCCCCCCCCCCCCCCCCD";
        String hexadecimal_minus_199dot223  = "-C7.3916872B020C49BA5E35";

        assertTrue(checkIfNumbersWithFractionAreAlmostEqual(hexadecimal_minus_1dot25, conversionService.convert(decimal_minus_1dot25, hexadecimal)));
        assertTrue(checkIfNumbersWithFractionAreAlmostEqual(hexadecimal_minus_91dot8, conversionService.convert(decimal_minus_91dot8, hexadecimal)));
        assertTrue(checkIfNumbersWithFractionAreAlmostEqual(hexadecimal_minus_199dot223, conversionService.convert(decimal_minus_199dot223, hexadecimal)));
    }

}