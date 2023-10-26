package res.controller;

import java.util.Scanner;

import res.service.CalcService;

public class Controller {
    private CalcService calcService = new CalcService();
        
    public void numberConversion() {

        // Prompt user to input a decimal, 2 digit hexadecimal or 8 digit binary number with a decimal value of max 255.
        Scanner scan = new Scanner(System.in);
        String message = "";
        System.out.print("Please enter a number with a max. value of:\n - 255 (decimal)\n - 11111111 (binary)\n - FF (hexadecimal)\n\nYour input: "); 
        String userInput = scan.nextLine();
        scan.close();

        // Check whether the input is valid and handles the conversion
        if (calcService.validBinary(userInput)) {
            message = "\nYou've entered a binary number: " + userInput;
            handleNumberConversion(calcService.binToDec(userInput), message);

        } else if (calcService.validHexadecimal(userInput)) {
            message = "\nYou've entered a hexadecimal number: " + userInput;
            handleNumberConversion(calcService.hexToDec(userInput), message);

            // Many hexadecimal numbers are also valid decimal numbers
            if (calcService.validDecimal(userInput)) {
            message = "\nIt is a valid decimal number as well: " + userInput;
            handleNumberConversion(Integer.parseInt(userInput), message);
            }

        } else if (calcService.validDecimal(userInput)) {
            message = "\nYou've entered a decimal number: " + userInput;
            handleNumberConversion(Integer.parseInt(userInput), message);

        } else {
            System.out.println("You've entered "+ userInput + ".\nThis is not a valid number of max:\n - 255 (decimal)\n - 11111111 (binary)\n - FF (hexadecimal) " + userInput);
        }
    }
    
    public void handleNumberConversion(int decimalValue, String message) {
        System.out.println(message);
        System.out.println("\nThe decimal equivalent is: " + decimalValue);
        System.out.println("The binary equivalent is: " + calcService.decToBin(decimalValue));
        System.out.println("The hexadecimal equivalent is: " + calcService.decToHex(decimalValue));
    }
}
