package onboarding;

import static java.lang.Math.max;
import static java.util.Objects.isNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Problem1 {

    private static final int DRAW = 0;
    private static final int POBI_WIN = 1;
    private static final int CRONG_WIN = 2;
    private static final int INVALID = -1;
    private static final int LEFT_PAGE = 0;
    private static final int RIGHT_PAGE = 1;
    private static final int MINIMUM_PAGE = 1;
    private static final int MAXIMUM_PAGE = 400;
    private static final int PAGE_SIZE = 2;

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        if (validate(pobi) && validate(crong)) {
            int pobiResult = receiveScore(pobi);
            int crongResult = receiveScore(crong);
            return receiveResult(pobiResult, crongResult);
        }
        return INVALID;
    }

    private static int receiveResult(int pobiResult, int crongResult) {
        if (pobiResult > crongResult) {
            return POBI_WIN;
        }
        if (pobiResult < crongResult) {
            return CRONG_WIN;
        }
        return DRAW;
    }

    private static int receiveScore(List<Integer> pages) {
        int left = receiveLeft(pages);
        int right = receiveRight(pages);
        return max(calculateDigit(left), calculateDigit(right));
    }

    private static int calculateDigit(int page) {
        int[] numbers = splitDigits(page);
        return max(calculateSum(numbers), calculateMultiplication(numbers));
    }

    private static int calculateMultiplication(int[] numbers) {
        int result = 1;
        for (int number : numbers) {
            result *= number;
        }
        return result;
    }

    private static int[] splitDigits(int page) {
        return Stream.of(receiveSplit(page))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static String[] receiveSplit(int page) {
        return String.valueOf(page).split("");
    }

    private static int calculateSum(int[] numbers) {
        return Arrays.stream(numbers).sum();
    }

    private static boolean validate(List<Integer> pages) {
        if (isNull(pages)) {
            return false;
        }
        if (!validateInput(pages)) {
            return false;
        }
        if (!validateRange(pages)) {
            return false;
        }
        return validateLeftRight(pages);
    }

    private static boolean validateLeftRight(List<Integer> pages) {
        return receiveRight(pages) == receiveLeft(pages) + RIGHT_PAGE;
    }

    private static boolean validateRange(List<Integer> pages) {
        return validatePageRange(receiveLeft(pages)) && validatePageRange(receiveRight(pages));
    }

    private static boolean validatePageRange(int page) {
        return MINIMUM_PAGE <= page && page <= MAXIMUM_PAGE;
    }

    private static boolean validateInput(List<Integer> pages) {
        return pages != null && pages.size() == PAGE_SIZE;
    }

    private static int receiveRight(List<Integer> pages) {
        return pages.get(RIGHT_PAGE);
    }

    private static int receiveLeft(List<Integer> pages) {
        return pages.get(LEFT_PAGE);
    }
}