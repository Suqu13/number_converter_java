package garstka.jakub.controllers;

import garstka.jakub.config.exceptions.InvalidNumeralSystemException;
import garstka.jakub.models.NumeralSystem;
import garstka.jakub.services.ConversionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static garstka.jakub.config.Constants.INVALID_NUMERAL_SYSTEM_MASSAGE;

@RestController
@RequestMapping(ConversionController.CONVERSION_CONTROLLER_BASE_URL)
@AllArgsConstructor
public class ConversionController {

    public static final String CONVERSION_CONTROLLER_BASE_URL = "/api/v1/conversion";

    private final ConversionService conversionService;

    @GetMapping("/convert")
        public String convert(@RequestParam(value = "decimal_value") Double decimalValue, @RequestParam(value = "numeral_system") String numeralSystemName) {
        try {
            NumeralSystem numeralSystem = NumeralSystem.valueOf(numeralSystemName);
            return conversionService.convert(decimalValue, numeralSystem);
        } catch (IllegalArgumentException e) {
            throw new InvalidNumeralSystemException(INVALID_NUMERAL_SYSTEM_MASSAGE);
        }

    }
}
