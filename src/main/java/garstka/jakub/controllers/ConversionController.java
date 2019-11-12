package garstka.jakub.controllers;

import garstka.jakub.config.exceptions.InvalidNumeralSystemException;
import garstka.jakub.models.NumeralSystem;
import garstka.jakub.services.ConversionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static garstka.jakub.config.Constants.*;

@Api(tags = CONVERSION_CONTROLLER_TAG)
@RestController
@RequestMapping(ConversionController.CONVERSION_CONTROLLER_BASE_URL)
@AllArgsConstructor
public class ConversionController {

    public static final String CONVERSION_CONTROLLER_BASE_URL = "/api/v1/conversion";

    private final ConversionService conversionService;

    @ApiOperation(CONVERT_FUNCTION_DESCRIPTION)
    @GetMapping("/convert")
        public String convert(
                @ApiParam("Number in arabic numeral system provided to conversion")
                @RequestParam(value = "decimal_value") Double decimalValue,
                @ApiParam("Value indicating a number system of a final result. Exemplary parameters: ROMAN, HEXADECIMAL")
                @RequestParam(value = "numeral_system") String numeralSystemName) {
        try {
            NumeralSystem numeralSystem = NumeralSystem.valueOf(numeralSystemName);
            return conversionService.convert(decimalValue, numeralSystem);
        } catch (IllegalArgumentException e) {
            throw new InvalidNumeralSystemException(INVALID_NUMERAL_SYSTEM_MASSAGE);
        }

    }
}
