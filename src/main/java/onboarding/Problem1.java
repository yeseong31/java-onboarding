package onboarding;

import java.util.List;

class Problem1 {
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

}