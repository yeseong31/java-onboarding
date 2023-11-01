package onboarding;

import java.util.stream.IntStream;

public class Problem3 {

    public static int solution(int number) {
        long count = IntStream.range(1, number + 1)
                .mapToObj(Problem3::convertToCharacterArray)
                .mapToInt(Problem3::clapHands)
                .sum();
        return Math.toIntExact(count);
    }

    private static char[] convertToCharacterArray(int n) {
        return String.valueOf(n)
                .toCharArray();
    }

    private static int clapHands(char[] characterNumbers) {
        int result = 0;
        for (Character c : characterNumbers) {
            if (satisfyCondition(c)) {
                ++result;
            }
        }
        System.out.println(result);
        return result;
    }

    private static boolean satisfyCondition(Character c) {
        int n = Character.getNumericValue(c);
        return n != 0 && n % 3 == 0;
    }
}
