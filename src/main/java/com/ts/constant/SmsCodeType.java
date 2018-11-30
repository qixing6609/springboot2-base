package com.ts.constant;

public enum SmsCodeType {

    REGISTER(1), RESETPWD(2), VERIFYCODE(3), PAYSMS(4);

    private final int value;

    private SmsCodeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SmsCodeType findByValue(int value) {
        switch (value) {
            case 1:
                return REGISTER;
            case 2:
                return RESETPWD;
            case 3:
                return VERIFYCODE;
            case 4:
            	return PAYSMS;
            default:
                return null;
        }
    }
}
