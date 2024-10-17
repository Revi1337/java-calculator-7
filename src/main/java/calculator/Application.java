package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.bytebuddy.pool.TypePool.Resolution.Illegal;

public class Application {

    private static final String CUSTOM_DELIMITER = "^//(.*)\\\\n(.*)";
    private static final String COMPOSITE_DELIMITER = "[:,]";

    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
//        String userInput = "1,2:3";
//        String userInput = "1,2,3";
//        String userInput = "16";
//        String userInput = "";
//        String userInput = "//;\\n1";
//        String userInput = "//^\\n1^2^3";
//        String userInput = "//;\\n1;1;2;3;4;1;2;3;4;1;2;3;1;2;3;4";
//        String userInput = "//s\\n1s1ssss2s3s4s1s2s3s4s1s2s3s1s2s3s4";

//        String userInput = "//;o\\n1;1;2;3;4;1;2;3;4;1;2;3;1;2;3;4";
//        String userInput = "//\\n1123412341231234";
//        String userInput = "s";
//        String userInput = "1,2v3,4";
        String userInput = Console.readLine();

        Matcher customPatternMatcher = Pattern.compile(CUSTOM_DELIMITER).matcher(userInput);
        boolean isCustomPatternMatch = customPatternMatcher.matches();
        if (isCustomPatternMatch) {
            String delimiter = customPatternMatcher.group(1);
            String quotedDelimiter = Pattern.quote(delimiter);
            if (delimiter.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자가 필요합니다.");
            }
            if (delimiter.length() > 1) {
                throw new IllegalArgumentException("커스텀 구분자의 길이는 1 이어야 합니다.");
            }
            if (Character.isDigit(delimiter.charAt(0))) {
                throw new IllegalArgumentException("커스텀 구분자는 문자여야 합니다.");
            }
            String strippedTokens = customPatternMatcher.group(2);
            String[] tokens = strippedTokens.split(quotedDelimiter);

            int sum = Arrays.stream(tokens)
                    .filter(string -> !string.isEmpty())
                    .mapToInt(Application::parsePositiveInt)
                    .sum();

            System.out.println("결과 : " + sum);
            return;
        }

        String[] tokens = userInput.split(COMPOSITE_DELIMITER);
        int sum = Arrays.stream(tokens)
                .filter(string -> !string.isEmpty())
                .mapToInt(Application::parsePositiveInt)
                .sum();
        System.out.println("결과 : " + sum);
    }

    private static int parsePositiveInt(String str) {
        try {
            int integer = Integer.parseInt(str);
            if (integer < 0) {
                throw new IllegalArgumentException("음수는 사용할 수 없습니다.");
            }
            return integer;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 사용할 수 있습니다.");
        }
    }

}




//package calculator;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Application {
//
//    private static final String CUSTOM_DELIMITER = "^//(.*)\\\\n(.*)";
////    private static final String COMPOSITE_DELIMITER = "^(.*?)([,:])(.*)";
//    private static final String COMPOSITE_DELIMITER = "^(.*?)([,:])(.*)";
//
//    private static final Pattern CUSTOM_PATTERN = Pattern.compile(CUSTOM_DELIMITER);
//    private static final Pattern COMPOSITE_PATTERN = Pattern.compile(COMPOSITE_DELIMITER);
//
//    public static void main(String[] args) {
//        System.out.println("덧셈할 문자열을 입력해 주세요.");
//        String userInput = "1,2:3";
////        String userInput = "1,2,3";
////        String userInput = "-1,2,3";
////        String userInput = "";
////        String userInput = "//;\\n1";
////        String userInput = "//;\\n1";
////        String userInput = "//;\\n1;1;2;3;4;1;2;3;4;1;2;3;1;2;3;4";
////        String userInput = "";
////        String userInput = "//;\\n1";
////        String userInput = Console.readLine();
//
//        Matcher customPatternMatcher = CUSTOM_PATTERN.matcher(userInput);
//        boolean isCustomPatternMatch = customPatternMatcher.matches();
//        if (isCustomPatternMatch) {
//            String delimiter = Pattern.quote(customPatternMatcher.group(2));
//            String strippedTokens = customPatternMatcher.group(3);
//            String[] tokens = strippedTokens.split(delimiter);
//
//            int sum = Arrays.stream(tokens)
//                    .filter(string -> !string.isEmpty())
//                    .mapToInt(Application::parsePositiveInt)
//                    .sum();
//
//            System.out.println("결과 : " + sum);
//            return;
//        }
//
//
//
////        Matcher compositePatternMatcher = COMPOSITE_PATTERN.matcher(userInput);
////        List<String> result = new ArrayList<>();
////        int lastIndex = 0;
////        while (compositePatternMatcher.find()) {
////            result.add(userInput.substring(lastIndex, compositePatternMatcher.start()));
////            lastIndex = compositePatternMatcher.end();
////        }
////        result.add(userInput.substring(lastIndex));
////        System.out.println("result = " + result);
//
//        Matcher compositePatternMatcher = COMPOSITE_PATTERN.matcher(userInput);
//        boolean isCompositePatternMatch = compositePatternMatcher.matches();
//        if (isCompositePatternMatch) {
//            String delimiter = Pattern.quote(compositePatternMatcher.group(1));
//            String strippedTokens = compositePatternMatcher.group(2);
//            String[] tokens = strippedTokens.split(delimiter);
//
//            int sum = Arrays.stream(tokens)
//                    .filter(string -> !string.isEmpty())
//                    .mapToInt(Application::parsePositiveInt)
//                    .sum();
//
//            System.out.println("결과 : " + sum);
//            return;
//        }
//
//        throw new IllegalArgumentException("입력이 잘못되었습니다.");
//    }
//
//    private static int parsePositiveInt(String str) {
//        try {
//            int integer = Integer.parseInt(str);
//            if (integer < 0) {
//                throw new IllegalArgumentException("음수는 사용할 수 없습니다.");
//            }
//            return integer;
//        } catch (NumberFormatException e) {
//            throw new IllegalArgumentException("숫자만 사용할 수 있습니다.");
//        }
//    }
//
//}







//package calculator;
//
//import camp.nextstep.edu.missionutils.Console;
//import java.util.Arrays;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Application {
//
//    private static final String COMPOSITE_DELIMITER = ":|,";
//    private static final String CUSTOM_DELIMITER = "//(.*)\\\\n(.*)";
//
//    private static final Pattern COMPOSITE_PATTERN = Pattern.compile(COMPOSITE_DELIMITER);
//    private static final Pattern CUSTOM_PATTERN = Pattern.compile(CUSTOM_DELIMITER);
//
//    public static void main(String[] args) {
//        System.out.println("덧셈할 문자열을 입력해 주세요.");
////        String userInput = "1,2:3";
////        String userInput = "1,2,3";
////        String userInput = "-1,2,3";
////        String userInput = "";
////        String userInput = "//;\\n1";
////        String userInput = "//;\\n1";
////        String userInput = "//;\\n1;1;2;3;4;1;2;3;4;1;2;3;1;2;3;4";
////        String userInput = "";
////        String userInput = "//;\\n1";
//        String userInput = Console.readLine();
//
//        Matcher customPatternMatcher = CUSTOM_PATTERN.matcher(userInput);
//        boolean isCustomPatternMatch = customPatternMatcher.matches();
//        if (isCustomPatternMatch) {
//            String delimiter = Pattern.quote(customPatternMatcher.group(1));
//            String strippedTokens = customPatternMatcher.group(2);
//            String[] tokens = strippedTokens.split(delimiter);
//            int sum = Arrays.stream(tokens)
//                    .filter(string -> !string.isEmpty())
//                    .mapToInt(Application::parsePositiveInt)
//                    .sum();
//
//            System.out.println("결과 : " + sum);
//            return;
//        }
//
//        Matcher compositePatternMatcher = COMPOSITE_PATTERN.matcher(userInput);
//        boolean isCompositePatternMatch = compositePatternMatcher.matches();
//        if (isCompositePatternMatch) {
//            String[] tokens = userInput.split(COMPOSITE_DELIMITER);
//            int sum = Arrays.stream(tokens)
//                    .filter(string -> !string.isEmpty())
//                    .mapToInt(Application::parsePositiveInt)
//                    .sum();
//            System.out.println("결과 : " + sum);
//        }
//    }
//
//    private static int parsePositiveInt(String str) {
//        try {
//            int integer = Integer.parseInt(str);
//            if (integer < 0) {
//                throw new IllegalArgumentException("음수는 사용할 수 없습니다.");
//            }
//            return integer;
//        } catch (NumberFormatException e) {
//            throw new IllegalArgumentException("숫자만 사용할 수 있습니다.");
//        }
//    }
//
//}
