package onboarding;

import static java.lang.Math.max;

import java.util.List;

class Problem1 {
    public static final int DRAW = 0;
    public static final int POBI_WIN = 1;
    public static final int CRONG_WIN = 2;
    public static final int EXCEPTION = -1;

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        int answer = Integer.MAX_VALUE;
        if (checkException(pobi, crong)) {
            return EXCEPTION;
        }
        return answer;
    }

    private static boolean checkException(List<Integer> pobi, List<Integer> crong) {
        if (validatePageListLength(pobi) || validatePageListLength(crong)) {
            return true;
        }
        if (validatePageNumber(pobi) || validatePageNumber(crong)) {
            return true;
        }
        if (validatePageRange(pobi) || validatePageRange(crong)) {
            return true;
        }
        return validatePageDifference(pobi) && !validatePageDifference(crong);
    }

    private static boolean validatePageListLength(List<Integer> list) {
        return list.size() != 2;
    }

    private static boolean validatePageNumber(List<Integer> list) {
        return list.get(0) % 2 != 1 || list.get(1) % 2 != 0;
    }

    private static boolean validatePageRange(List<Integer> list) {
        return list.get(0) <= 1 || list.get(1) >= 400;
    }

    private static boolean validatePageDifference(List<Integer> list) {
        return list.get(1) - list.get(0) != 1;
    }

    private static int calculateNumber(int page) {
        int sum = 0, mul = 1;
        while (page != 0) {
            sum += page % 10;
            mul *= page % 10;
            page /= 10;
        }
        return max(sum, mul);
    }

}