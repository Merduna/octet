package controller;

import java.util.Scanner;

import model.Constants;
import model.Conversion;
import model.Question;
import service.ConversionService;
import service.QuestionService;
import service.InputValidationService;

public class Controller {
    private ConversionService conversionService = new ConversionService();
    private QuestionService questionService = new QuestionService();
    private InputValidationService inputValidationService = new InputValidationService();
    private Scanner scan = new Scanner(System.in);
    
    public void mainMenu() {
        String choice = "";
        while (choice != "3") {
            clearConsole();
            System.out.println("Welcome to Octet!");
            System.out.print("\nWhat do you want to do? \n1: Number Conversion Challenge \n2: Number Converter \n3: Quit\n\nEnter an option number to continue: ");
            choice = scan.nextLine();
            if (inputValidationService.isValidDecimal(choice, 1, 3)) {
                if (Integer.parseInt(choice) == 1) {
                    numberQuestion(Constants.RANDOM_TO_RANDOM);
                } else if (Integer.parseInt(choice) == 2) {
                    numberConversion();
                } else if (Integer.parseInt(choice) == 3) {
                    System.out.println("\nThank you for using Octet! Goodbye.");
                    System.exit(0);
                } else {
                    System.out.println("\nInvalid input: \"" + choice + "\"");
                    promptEnterKey("");
                } 
            } else {
                 System.out.println("\nInvalid input: \"" + choice + "\"");
                 promptEnterKey("");
            }
        }
    }
    
    public void numberQuestion(char type) {
        clearConsole();
        Question newQuestion = questionService.newQuestion(type);
        promptQuestion(newQuestion);
        newQuestion.setAnswer(scan.nextLine()); 
        showSolution(newQuestion);
        promptEnterKey("\nPress \"ENTER\" to return to the main menu ");        
    }
   
    public void promptQuestion(Question question) {
        String prompt = "";
        switch(question.getType()) {
            case Constants.DECIMAL_TO_BINARY:
                prompt = "Provide the 8 bit binary equivalent of this decimal number: " + question.getQuestion() + "\nYour answer: ";
                break;
            case Constants.DECIMAL_TO_HEX:
                prompt = "Provide the 2 digit hexadecimal equivalent of this decimal number: " + question.getQuestion() + "\nYour answer: ";
                break;
            case Constants.BINARY_TO_DECIMAL:
                prompt = "Provide the decimal equivalent of this 8 bit binary number: " + question.getQuestion() + "\nYour answer: ";
                break;
            case Constants.HEX_TO_DECIMAL:
                prompt = "Provide the decimal equivalent of this hexadecimal number: " + question.getQuestion() + "\nYour answer: ";
                break;
            case Constants.BINARY_TO_HEX:
                prompt = "Provide the hexadecimal equivalent of this binary number: " + question.getQuestion() + "\nYour answer: ";
                break;
            case Constants.HEX_TO_BINARY:
                prompt = "Provide the binary equivalent of this hexadecimal number: " + question.getQuestion() + "\nYour answer: ";
                break;
        }
        System.out.print(prompt);
    }

    public void showSolution(Question question) {
        if (question.getAnswer().equals(question.getSolution())) {          
            System.out.println("\nCorrect!\nOriginal number: "+ question.getQuestion() +  "\nYour input: " + question.getAnswer() + "\nCorrect answer: " + question.getSolution());
        } else {
            System.out.println("\nIncorrect!\nOriginal number: "+ question.getQuestion() +  "\nYour input: " + question.getAnswer() + "\nCorrect answer: " + question.getSolution());
        }  
    }

    public void promptEnterKey(String message) {
        if (message.length() == 0) {
            // Default message
            message = "Press \"ENTER\" to continue ";
        }
        System.out.print(message);
        scan.nextLine();
    }

    // Prompt user to input a decimal, 2 digit hexadecimal or 8 digit binary number with a decimal value of max 255.
    public String promptNumber() {
        String userNumber = "";
        while (true) {
            clearConsole();
            System.out.print("Please enter a number with a max. value of:\n- 255 (decimal)\n- 11111111 (8 digit binary)\n- FF (2 digit hexadecimal)\n\nYour input: ");
            userNumber = scan.nextLine();
            if (inputValidationService.isValidNumber(userNumber)) {
                return userNumber;
            } else {
                System.out.println("\nInvalid input: \"" + userNumber + "\"\n\nPlease enter a number with a decimal value of max. 255." +
                "\nWhen entering a non-decimal number, please enter exactly:\n- 2 digits for a hexadecimal number\n- 8 digits for a binary number\n");
                promptEnterKey("");
                return promptNumber();
            }
        }
    }
    
    public void numberConversion() {
        String userNumber = promptNumber();        
        Conversion conversion = conversionService.newConversion(userNumber);
        handleNumberConversion(conversion);       
        promptEnterKey("\nPress \"ENTER\" to return to the main menu ");
    }
    
    public void handleNumberConversion(Conversion conversion) {
        if (conversion.isDecimal()) {
            System.out.println("\nThe number you've entered is a decimal number: " + conversion.getOriginalValue());
            System.out.println("- The binary equivalent is: " + conversionService.decToBin(Integer.parseInt(conversion.getOriginalValue())));
            System.out.println("- The hexadecimal equivalent is: " + conversionService.decToHex(Integer.parseInt(conversion.getOriginalValue())));
        }
        if (conversion.isHexadecimal()) {
            if (conversion.isDecimal()) {
                System.out.println("\nThe number you've entered is a hexadecimal number as well: " + conversion.getOriginalValue());
            } else {
                System.out.println("\nThe number you've entered is a hexadecimal number: " + conversion.getOriginalValue());
            }            
            System.out.println("- The decimal equivalent is: " + conversionService.hexToDec(conversion.getOriginalValue()));
            System.out.println("- The binary equivalent is: " + conversionService.decToBin(conversionService.hexToDec(conversion.getOriginalValue())));
        }
        if (conversion.isBinary()) {
            System.out.println("\nThe number you've entered is a binary number: " + conversion.getOriginalValue());
            System.out.println("- The decimal equivalent is: " + conversionService.binToDec(conversion.getOriginalValue()));
            System.out.println("- The hexadecimal equivalent is: " + conversionService.decToHex(conversionService.binToDec(conversion.getOriginalValue())));
        }
    }

    public final static void clearConsole(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
}
