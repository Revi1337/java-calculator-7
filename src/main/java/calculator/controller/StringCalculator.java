package calculator.controller;

import calculator.io.InputHandler;
import calculator.io.OutputHandler;
import calculator.model.InputText;
import calculator.model.Tokens;
import calculator.model.UserInput;

public class StringCalculator {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public StringCalculator(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public void calculate() {
        outputHandler.showEntryMessage();
        UserInput userInput = inputHandler.getUserInput();
        InputText inputText = new InputText(userInput.getDelimiter(), userInput.getPlainText());
        Tokens tokens = inputText.tokenize();
        int totalCost = tokens.sum();
        outputHandler.showCalculatedValue(totalCost);
    }
}