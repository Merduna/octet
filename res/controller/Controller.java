package res.controller;

import java.util.Scanner;

import res.model.Constants;
import res.model.Question;
import res.service.CalcService;
import res.service.QuestionService;

public class Controller {
    private CalcService calcService = new CalcService();
    private QuestionService questionService = new QuestionService();
    private Scanner scan = new Scanner(System.in);
    
    public void numberQuestion(char type) {
        Question newQuestion = questionService.newQuestion(type);
        promptQuestion(newQuestion);
        newQuestion.setAnswer(scan.nextLine()); 
        showSolution(newQuestion);        
    }
   
    public void promptQuestion(Question question) {
        String prompt = "";
        switch(question.getType()) {
            case Constants.DECIMAL_TO_BINARY:
                prompt = "Provide the 8 bit binary equivalent of this decimal number: " + question.getQuestion();
                break;
            case Constants.DECIMAL_TO_HEX:
                prompt = "Provide the 2 digit hexadecimal equivalent of this decimal number: " + question.getQuestion();
                break;
            case Constants.BINARY_TO_DECIMAL:
                prompt = "Provide the decimal equivalent of this 8 bit binary number: " + question.getQuestion();
                break;
            case Constants.HEX_TO_DECIMAL:
                prompt = "Provide the decimal equivalent of this hexadecimal number: " + question.getQuestion();
                break;
            case Constants.BINARY_TO_HEX:
                prompt = "Provide the hexadecimal equivalent of this binary number: " + question.getQuestion();
                break;
            case Constants.HEX_TO_BINARY:
                prompt = "Provide the binary equivalent of this hexadecimal number: " + question.getQuestion();
                break;
        }
        System.out.println(prompt);
    }

    public void showSolution(Question question) {
        if (question.getAnswer().equals(question.getSolution())) {          
            System.out.println("Correct!\nOriginal number: "+ question.getQuestion() +  "\nYour input: " + question.getAnswer() + "\nCorrect answer: " + question.getSolution());
        } else {
            System.out.println("Incorrect!\nOriginal number: "+ question.getQuestion() +  "\nYour input: " + question.getAnswer() + "\nCorrect answer: " + question.getSolution());
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
