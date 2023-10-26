package res.service;

public class CalcService {

    // Char array with the hexadecimal digits
    public char[] hexChars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    
    // Checks whether the user input is an integer with a value of max 255.
    public boolean validDecimal(String input) {
        try {
            int i = Integer.parseInt(input);
            return (i <= 255);
        } catch (Exception e) {
            return false;
        }  
    }

    // Checks whether the user input is a 2 digit hexadecimal number.
    public boolean validHexadecimal(String input) {
        if (input.length() != 2) {
            return false;
        }
        return (new String(hexChars).contains("" + input.charAt(0))) && (new String(hexChars).contains("" + input.charAt(1)));        
    }
    
    // Checks whether the user input is an 8 digit binary number.
    public boolean validBinary(String input) {
        if (!(input.length() == 8)) return false;
        for (int i = 0; i < input.length(); i++) {
            if (!((input.charAt(i) == '0') || (input.charAt(i) == '1'))) return false;
        }
        return true;
    }

    // Converts a decimal number with a value of max 255 into a 2 digit hexadecimal number.
    public String decToHex(int decimal) {
        char firstDigit = hexChars[decimal / 16];
        char secondDigit = hexChars[decimal % 16];
        return "" + firstDigit + secondDigit;
    }

    // Converts a 2 digit hexadecimal into a decimal number.
    public int hexToDec(String hexadecimal) {
        int firstDigit = 0;
        int secondDigit = 0;
        for (int i = 0; i < hexChars.length; i++) {
            if (hexChars[i] == hexadecimal.charAt(0)) {
                firstDigit = i;
            }
            if (hexChars[i] == hexadecimal.charAt(1)) {
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
