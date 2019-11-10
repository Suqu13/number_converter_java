package garstka.jakub.services;

import garstka.jakub.config.exceptions.InvalidNumberException;
import garstka.jakub.models.NumeralSystem;
import org.springframework.stereotype.Service;

@Service
public interface ConversionService {
    String convert(Double decimalValue, NumeralSystem numeralSystem) throws InvalidNumberException;
}
