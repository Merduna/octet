package service;

import model.Constants;

public class InputValidationService {
    
    // Parses the user input to an integer. If no valid integer is provided, the default invalid value will be used.
    public int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            return Constants.DEFAULT_INVALID_VALUE;
        } 
    }

    // Checks whether the integer is in the valid range.
    public boolean isValidRange(int value, int minValue, int maxValue) {
        return (value >= minValue && value <= maxValue);
    }

    // Checks whether the user input is a valid decimal.
    public boolean isValidDecimal(String input, int minValue, int maxValue) {
        int parsedValue = parseInt(input);
        return isValidRange(parsedValue, minValue, maxValue);
    }


    // Checks whether the user input is a 2 digit hexadecimal number.
    public boolean validHexadecimal(String input) {
        if (input.length() != 2) {
            return false;
        }
        return (new String(Constants.HEX_CHARS).contains("" + input.charAt(0))) && (new String(Constants.HEX_CHARS).contains("" + input.charAt(1)));        
    }
    
    // Checks whether the user input is an 8 digit binary number.
    public boolean validBinary(String input) {
        if (!(input.length() == 8)) return false;
        for (int i = 0; i < input.length(); i++) {
            if (!((input.charAt(i) == '0') || (input.charAt(i) == '1'))) return false;
        }
        return true;
    }
}
