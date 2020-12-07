package utils;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;

public class userInputData {

    private static final String spaceErrorCheckPattern = ".*[ ].*";
    private static final String integerOnlyPossiblePattern = "[1-9]+[0-9]*";
    private static final int MINIMUM_CAR_NAME = 1;
    private static final int MAXIMUM_CAR_NAME = 5;

    private static final String SPACE_ERROR = "[ERROR] 자동차 이름에 공백은 허용되지 않습니다.";
    private static final String DUPLICATION_ERROR = "[ERROR] 중복된 자동차 이름은 불가능합니다.";
    private static final String NAME_RANGE_ERROR = "[ERROR] 자동차의 이름은 " + MINIMUM_CAR_NAME +
        " 자리 이상 " + MAXIMUM_CAR_NAME + " 자리 이하여야 합니다.";
    private static final String NOT_INTEGER_ERROR = "[ERROR] 시도 횟수는 1 이상의 숫자만 입력 가능합니다.";
    private static final String NO_ERROR = "";

    private userInputData() {
    }

    public static boolean carNameErrorCheck(String[] carNames) {
        if (duplicationNameCheck(carNames)) {
            return true;
        }
        for (String carName : carNames) {
            if (spaceErrorCheck(carName) || availableRangeCheck(carName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean matchTryNumberErrorCheck(String tryNumber) {
        return (isRealIntegerCheck(tryNumber) || spaceErrorCheck(tryNumber));
    }

    private static boolean spaceErrorCheck(String userInput) {
        return Pattern.matches(spaceErrorCheckPattern, userInput);
    }

    private static boolean availableRangeCheck(String userInput) {
        return (MINIMUM_CAR_NAME > userInput.length()
            || MAXIMUM_CAR_NAME < userInput.length());
    }

    private static boolean duplicationNameCheck(String[] userInputSet) {
        return (userInputSet.length != Arrays.stream(userInputSet).distinct().count());
    }

    private static boolean isRealIntegerCheck(String userInput) {
        return (!Pattern.matches(integerOnlyPossiblePattern, userInput));
    }

    public static void carNameErrorMessagePrint(String[] carNames){
        if (duplicationNameCheck(carNames)){ System.out.println(DUPLICATION_ERROR); }
        for(String carName:carNames){
            if (spaceErrorCheck(carName)){
                System.out.println(SPACE_ERROR);
                break;
            }
        }
        for(String carName:carNames){
            if (availableRangeCheck(carName)){
                System.out.println(NAME_RANGE_ERROR);
                break;
            }
        }
    }

    public static void matchTryNumberErrorPrint(String tryNumber){
            if (spaceErrorCheck(tryNumber)){
                System.out.println(SPACE_ERROR);

            }
            if (isRealIntegerCheck(tryNumber)){
                System.out.println(NOT_INTEGER_ERROR);

            }

    }
}
