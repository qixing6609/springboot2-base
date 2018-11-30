package com.ts.constant;

public enum DeliveryType {
    DOOR( 0, "送药上门"), MAIL( 1, "邮寄");
    private int value;
    private String desc;

    private DeliveryType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }


    public String getDesc() {
        return desc;
    }


}
