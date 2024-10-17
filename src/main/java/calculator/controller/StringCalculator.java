package calculator.controller;

import calculator.io.InputHandler;
import calculator.io.OutputHandler;
import calculator.model.Token;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final String CUSTOM_DELIMITER = "^//(.*)\\\\n(.*)";
    private static final String COMPOSITE_DELIMITER = "[:,]";
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public StringCalculator(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public void calculate() {
        outputHandler.showEntryMessage();
        String userInput = inputHandler.getUserInput();

        Matcher customPatternMatcher = Pattern.compile(CUSTOM_DELIMITER).matcher(userInput);
        if (doesDelimiterIsCustom(customPatternMatcher)) {
            String delimiter = customPatternMatcher.group(1);

            if (doesDelimiterIsEmpty(delimiter)) {
                throw new IllegalArgumentException("커스텀 구분자가 필요합니다.");
            }
            if (doesDelimiterIsValid(delimiter)) {
                throw new IllegalArgumentException("커스텀 구분자의 길이는 1 이어야 합니다.");
            }
            if (doesDelimiterIsChar(delimiter)) {
                throw new IllegalArgumentException("커스텀 구분자는 문자여야 합니다.");
            }

            String strippedTokens = customPatternMatcher.group(2);
            String quotedDelimiter = Pattern.quote(delimiter);
            List<Token> tokens = convertToTokens(strippedTokens, quotedDelimiter);
            int totalCost = calculateTotalCosts(tokens);

            outputHandler.showCalculatedValue(totalCost);
            return;
        }

        List<Token> tokens = convertToTokens(userInput, COMPOSITE_DELIMITER);

        int totalCost = calculateTotalCosts(tokens);
        outputHandler.showCalculatedValue(totalCost);
    }

    private static List<Token> convertToTokens(String strippedTokens, String quotedDelimiter) {
        return Arrays.stream(strippedTokens.split(quotedDelimiter))
                .map(Token::new)
                .toList();
    }

    private static boolean doesDelimiterIsChar(String delimiter) {
        return Character.isDigit(delimiter.charAt(0));
    }

    private static boolean doesDelimiterIsValid(String delimiter) {
        return delimiter.length() > 1;
    }

    private static boolean doesDelimiterIsCustom(Matcher customPatternMatcher) {
        return customPatternMatcher.matches();
    }

    private static int calculateTotalCosts(List<Token> tokens) {
        return tokens.stream()
                .mapToInt(Token::getCost)
                .sum();
    }

    private static boolean doesDelimiterIsEmpty(String delimiter) {
        return delimiter.isEmpty();
    }

}
