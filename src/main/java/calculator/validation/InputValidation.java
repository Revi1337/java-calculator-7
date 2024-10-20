package calculator.validation;

public abstract class InputValidation {

    public static void validateCustomDelimiter(String delimiter) {
        validateCustomDelimiterIsEmpty(delimiter);
        validateCustomDelimiterIsString(delimiter);
    }

    private static void validateCustomDelimiterIsEmpty(String delimiter) {
        if (delimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자는 비어있을 수 없습니다.");
        }
    }

    private static void validateCustomDelimiterIsString(String delimiter) {
        try {
            int ignored = Integer.parseInt(delimiter);
            throw new IllegalArgumentException("커스텀 구분자는 숫자일 수 없습니다.");
        } catch (NumberFormatException ignored) {
        }
    }
}