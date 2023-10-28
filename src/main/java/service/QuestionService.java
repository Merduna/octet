package service;

import java.util.Random;

import model.Constants;
import model.Question;

public class QuestionService {
    
    private Random rand = new Random();
    private ConversionService conversionService = new ConversionService();
    public Question question;

    public Question newQuestion(char type) {
        question = new Question();
        if (type == Constants.RANDOM_TO_RANDOM) {
            type = Constants.QUESTION_TYPE_OPTIONS[rand.nextInt(6)];
        }
        question.setType(type);
        int questionInt = rand.nextInt(256);
        setQuestion(question, questionInt);
        setSolution(question, questionInt);
        return question;
    }
        
    public void setQuestion(Question question, int questionInt) {
        if (question.getType() == Constants.BINARY_TO_DECIMAL || question.getType() == Constants.BINARY_TO_HEX) {
            question.setQuestion(conversionService.decToBin(questionInt) + "");         
        } else if (question.getType() == Constants.HEX_TO_DECIMAL || question.getType() == Constants.HEX_TO_BINARY) {
            question.setQuestion(conversionService.decToHex(questionInt));
        } else {
        question.setQuestion(questionInt + "");
        }
    }

    public void setSolution(Question question, int questionInt) {
        if (question.getType() == Constants.DECIMAL_TO_BINARY || question.getType() == Constants.HEX_TO_BINARY) {
            question.setSolution(conversionService.decToBin(questionInt) + "");         
        } else if (question.getType() == Constants.DECIMAL_TO_HEX || question.getType() == Constants.BINARY_TO_HEX) {
            question.setSolution(conversionService.decToHex(questionInt));
        } else {
        question.setSolution(questionInt + "");
        }
    }
}
        