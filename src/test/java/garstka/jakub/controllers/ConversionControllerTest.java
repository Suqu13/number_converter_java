package garstka.jakub.controllers;

import garstka.jakub.config.exceptions.InvalidNumberException;
import garstka.jakub.models.NumeralSystem;
import garstka.jakub.services.ConversionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static garstka.jakub.config.Constants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ConversionControllerTest {

    @Mock
    ConversionService conversionService;

    @InjectMocks
    ConversionController conversionController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(conversionController)
                .setControllerAdvice(new RestControllerExceptionHandler())
                .build();
    }

    @Test
    public void convertTenToRomanNumber() throws Exception {
        //given
        String romainTen = "X";

        //when
        when(conversionService.convert(anyDouble(), any(NumeralSystem.class))).thenReturn(romainTen);

        //then
        mockMvc.perform(get(ConversionController.CONVERSION_CONTROLLER_BASE_URL + "/convert")
                .param("decimal_value", "10")
                .param("numeral_system", "ROMAN"))
                .andExpect(status().isOk())
                .andExpect(content().string(romainTen));
    }

    @Test
    public void convertTenToHexadecimalNumber() throws Exception {
        //given
        String hexadecimalTen = "A";

        //when
        when(conversionService.convert(anyDouble(), any(NumeralSystem.class))).thenReturn(hexadecimalTen);

        //then
        mockMvc.perform(get(ConversionController.CONVERSION_CONTROLLER_BASE_URL + "/convert")
                .param("decimal_value", "10")
                .param("numeral_system", "HEXADECIMAL"))
                .andExpect(status().isOk())
                .andExpect(content().string(hexadecimalTen));
    }

    @Test
    public void convertTenToInvalidNumeralSystem() throws Exception {
        //then
        mockMvc.perform(get(ConversionController.CONVERSION_CONTROLLER_BASE_URL + "/convert")
                .param("decimal_value", "10")
                .param("numeral_system", "InvalidNumeralSystem"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(INVALID_NUMERAL_SYSTEM_MASSAGE));
    }

    @Test
    public void convertInvalidNumberToRomanNumber() throws Exception {
        //when
        when(conversionService.convert(anyDouble(), any(NumeralSystem.class)))
                .thenThrow(new InvalidNumberException(LOWEST_ROMAN_NUMBER, HIGHEST_ROMAN_NUMBER));

        //then
        mockMvc.perform(get(ConversionController.CONVERSION_CONTROLLER_BASE_URL + "/convert")
                .param("decimal_value", "-1")
                .param("numeral_system", "ROMAN"))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(content().string(String.format(NOT_IN_THE_RANGE_MESSAGE, LOWEST_ROMAN_NUMBER, HIGHEST_ROMAN_NUMBER)));
    }
}