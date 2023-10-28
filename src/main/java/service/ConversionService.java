package service;
import model.Constants;

public class ConversionService {   
    
    // Converts a decimal number with a value of max 255 into a 2 digit hexadecimal number.
    public String decToHex(int decimal) {
        char firstDigit = Constants.HEX_CHARS[decimal / 16];
        char secondDigit = Constants.HEX_CHARS[decimal % 16];
        return "" + firstDigit + secondDigit;
    }

    // Converts a 2 digit hexadecimal into a decimal number.
    public int hexToDec(String hexadecimal) {
        int firstDigit = 0;
        int secondDigit = 0;
        for (int i = 0; i < Constants.HEX_CHARS.length; i++) {
            if (Constants.HEX_CHARS[i] == hexadecimal.charAt(0)) {
                firstDigit = i;
            }
            if (Constants.HEX_CHARS[i] == hexadecimal.charAt(1)) {
                secondDigit = i;
            }
        }
        return (firstDigit*16 + secondDigit);
    }

    // Converts a decimal number with a max value of 255 into an 8 digit binary number.
    public String decToBin(int decimal) {
        char[] octet = {'0','0','0','0','0','0','0','0'};
        for (int i = 0; i < 8; i++) {
            if (decimal >= Math.pow(2, 7-i)) {
                decimal -= Math.pow(2, 7-i);
                octet[i] = '1';
            }
        }
        return new String(octet);
    }

    // Converts an 8 digit binary number into a decimal number.
    public int binToDec(String binary) {
        int decimal = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                decimal += Math.pow(2, 7-i);
            }
        }
        return decimal;
    }
}
