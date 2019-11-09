package garstka.jakub.controllers;

import garstka.jakub.models.NumeralSystem;
import garstka.jakub.services.ConversionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ConversionController.CONVERSION_CONTROLLER_BASE_URL)
public class ConversionController {

    public static final String CONVERSION_CONTROLLER_BASE_URL = "/api/v1/conversion";

    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/convert")
        public String convert(@RequestParam(value = "decimal_value") Double decimalValue, @RequestParam(value = "numeral_system") String numeralSystemName) {
        NumeralSystem numeralSystem = NumeralSystem.valueOf(numeralSystemName);
        return conversionService.convert(decimalValue, numeralSystem);
    }

    @GetMapping
    public Integer cos() { return 1;}
}
