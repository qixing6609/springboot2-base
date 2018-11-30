package com.ts.constant;

public enum JPushTag {
    ORDER(1,"订单变更"),WECHAT( 2, "待扩展"), ALIPAY( 3, "待扩展");
    private int value;
    private String desc;

    private JPushTag(int value, String desc) {
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
