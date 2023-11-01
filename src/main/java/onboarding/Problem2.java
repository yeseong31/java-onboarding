package onboarding;

import java.util.Stack;

public class Problem2 {
    public static String solution(String cryptogram) {
        Stack<Character> stack = configureStack(cryptogram);
        return convertToString(stack);
    }

    private static Stack<Character> configureStack(String cryptogram) {
        Stack<Character> stack = new Stack<>();

        for (char currentCharacter : cryptogram.toCharArray()) {
            Character peekCharacter = stack.peek();
            process(stack, currentCharacter, peekCharacter);
        }

        return stack;
    }

    private static void process(Stack<Character> stack, char currentCharacter, Character peekCharacter) {
        if (notEmpty(stack) && isEquals(peekCharacter, currentCharacter)) {
            stack.pop();
            return;
        }
        stack.push(currentCharacter);
    }

    private static boolean isEquals(Character peekCharacter, char currentCharacter) {
        return peekCharacter.equals(currentCharacter);
    }

    private static boolean notEmpty(Stack<Character> stack) {
        return !stack.isEmpty();
    }

    private static String convertToString(Stack<Character> stack) {
        StringBuilder sb = new StringBuilder();

        for (Character targetCharacter : stack) {
            sb.append(targetCharacter);
        }

        return sb.toString();
    }
}
