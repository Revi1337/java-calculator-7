package calculator;

import calculator.controller.StringCalculator;
import calculator.io.InputHandler;
import calculator.io.OutputHandler;

public class Application {

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        StringCalculator stringCalculator = new StringCalculator(inputHandler, outputHandler);
        stringCalculator.calculate();
    }

}