package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper(){}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static OperationInfo[] getOperations() {
        OperationInfo operation1 = new OperationInfo("5559 0000 0000 0002",
                "5559 0000 0000 0001", 100);

        OperationInfo operation2 = new OperationInfo("5559 0000 0000 0001",
                "5559 0000 0000 0002", 100);

        return new OperationInfo[]{operation1, operation2};
    }

    public static String getLastCardNumbers(String cardText) {
        String[] targetCardArray = cardText.split(" ");
        return targetCardArray[targetCardArray.length - 1];
    }

    @Value
    public static class OperationInfo {
        private String sourceCard;
        private String targetCard;
        private int amount;
    }
}
