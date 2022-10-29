package onboarding;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Problem6 {

    public static List<String> solution(List<List<String>> forms) {
        List<String> answer = List.of("answer");

        for (List<String> form : forms) {
            if (checkException(form.get(0), form.get(1))) {
                continue;
            }
        }

        return answer;
    }

    private static boolean checkException(String email, String nickname) {
        if (validateEmailLength(email)) {
            return true;
        }
        if (checkEmail(email)) {
            return true;
        }
        if (checkNicknameLength(nickname)) {
            return true;
        }
        return checkNicknameKorean(nickname);
    }

    private static boolean validateEmailLength(String email) {
        return email.length() < 11
                || email.length() >= 20;
    }

    private static boolean checkEmail(String email) {
        if (checkEmailFront(email.split("@")[0])) {
            return true;
        }
        return checkEmailFormat(email.split("@")[1]);
    }

    private static boolean checkEmailFront(String target) {
        return Pattern.matches("[^a-zA-Z0-9]*$", target);
    }

    private static boolean checkEmailFormat(String format) {
        return !Objects.equals(format, "email.com");
    }

    private static boolean checkNicknameLength(String nickname) {
        return nickname.length() < 1
                || nickname.length() >= 20;
    }

    private static boolean checkNicknameKorean(String nickname) {
        return !(nickname.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"));
    }

}
