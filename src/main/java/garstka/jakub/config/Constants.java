package garstka.jakub.config;

public class Constants {
    public static final String NOT_IN_THE_RANGE_MESSAGE = "Provided value have to be in the range %s - %s";

    public static final String INVALID_NUMERAL_SYSTEM_MASSAGE = "Provided numeral system is not supported";

    public static final String FRACTION_NOT_SUPPORTED_BY_ROMAIN_NUMERAL_SYSTEM = "Provided number contains fraction part which is not supported by Romain Numeral System";
    public static final Integer LOWEST_ROMAIN_NUMBER = 0;
    public static final Integer HIGHEST_ROMAIN_NUMBER = 3999;

    public static final int EDGE_CASE_WITH_ZERO = 0;
    public static final int EDGE_CASE_WITH_MULTIPLE_FOUR = 4;
    public static final int EDGE_CASE_WITH_MULTIPLE_NINE = 9;

    public static final String[] HEXADECIMAL_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    public static final Integer BASE_HEXADECIMAL_SYSTEM_NUMBER = 16;
}
