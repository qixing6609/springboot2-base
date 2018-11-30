package com.ts.constant;

public enum SortType {
    SALES(1, "销量优先"), PRICE(2, "价格优先");
    private int value;
    private String desc;

    private SortType(int value, String desc) {
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
