package res.controller;

import java.util.Random;
import java.util.Scanner;

import res.service.CalcService;

public class Controller {
    private CalcService calcService = new CalcService();
    private Random rand = new Random();
    private Scanner scan = new Scanner(System.in);

    /* 'B' and 'b' for binary conversions
     * 'H' and 'h' for hexadecimal conversions
     * 'N' and 'n' for conversions between binary and hexadecimal*/
    private char[] questionTypeOptions = {'B','b','H','h','N','n'};
    
    public void numberQuestion(char type) {    
        int question = rand.nextInt(256);
        if (type == 'R') {
            type = questionTypeOptions[rand.nextInt(6)];
        }
        String solution = setSolution(type, question);
        String questionString = getQuestionString(type, question);
        promptQuestion(type, questionString);
        String answer = scan.nextLine(); 
        showSolution(questionString, answer, solution);        
    }

    public String setSolution(char type, int question) {
        if (type == 'B' || type == 'n') {
            return calcService.decToBin(question);         
        } else if (type == 'H' || type == 'N') {
            return calcService.decToHex(question);
        }
        return question + "";
    }

    public String getQuestionString(char type, int question) {
        if (type == 'b' || type == 'N') {
            return calcService.decToBin(question);         
        } else if (type == 'h' || type == 'n') {
            return calcService.decToHex(question);
        }
        return question + "";
    }
    
    public void promptQuestion(char type, String questionString) {
        String prompt = "";
        switch(type) {
            case 'B':
                prompt = "Provide the 8 bit binary equivalent of this decimal number: " + questionString;
                break;
            case 'H':
                prompt = "Provide the 2 digit hexadecimal equivalent of this decimal number: " + questionString;
                break;
            case 'b':
                prompt = "Provide the decimal equivalent of this 8 bit binary number: " + questionString;
                break;
            case 'h':
                prompt = "Provide the decimal equivalent of this hexadecimal number: " + questionString;
                break;
            case 'N':
                prompt = "Provide the hexadecimal equivalent of this binary number: " + questionString;
                break;
            case 'n':
                prompt = "Provide the binary equivalent of this hexadecimal number: " + questionString;
                break;
        }
        System.out.println(prompt);
    }

    public void showSolution(String question, String answer, String solution) {
        if (answer.equals(solution)) {          
            System.out.println("Correct!\nOriginal number: "+ question +  "\nYour input: " + answer + "\nCorrect answer: " + solution);
        } else {
            System.out.println("Incorrect!\nOriginal number: "+ question +  "\nYour input: " + answer + "\nCorrect answer: " + solution);
        }  
    }

    public void numberConversion() {

        // Prompt user to input a decimal, 2 digit hexadecimal or 8 digit binary number with a decimal value of max 255.
        String message = "";
        System.out.print("Please enter a number with a max. value of:\n - 255 (decimal)\n - 11111111 (binary)\n - FF (hexadecimal)\n\nYour input: "); 
        String userInput = scan.nextLine();

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
