package garstka.jakub.services.impl;

import garstka.jakub.config.InvalidNumberExceptions;
import garstka.jakub.models.NumeralSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
    void throwExceptionWhenValueLowerThanOne() {
        NumeralSystem romain = NumeralSystem.ROMAIN;

        Double decimalValue_0 = 0.0;
        Double decimalValue_minus_1 = -1.0;
        Double decimalValue_minus_4000 = -4000.0;

        assertThrows(InvalidNumberExceptions.class, () -> {conversionService.convert(decimalValue_0, romain);});
        assertThrows(InvalidNumberExceptions.class, () -> {conversionService.convert(decimalValue_minus_1, romain);});
        assertThrows(InvalidNumberExceptions.class, () -> {conversionService.convert(decimalValue_minus_4000, romain);});
    }

    @Test
    void throwExceptionWhenValueHigherThanTreeThousandNineHundredNinetyNine() {
        NumeralSystem romain = NumeralSystem.ROMAIN;

        Double decimalValue_4000 = 4000.0;
        Double decimalValue_10000 = 10000.0;

        assertThrows(InvalidNumberExceptions.class, () -> {conversionService.convert(decimalValue_4000, romain);});
        assertThrows(InvalidNumberExceptions.class, () -> {conversionService.convert(decimalValue_10000, romain);});
    }


}