package com.ts.constant;

public enum RefundStatus {
    REFUND( 20, "退款"),PAY_REFUND( 10, "申请退款");
	
    private int value;
    private String desc;

    public static String getRefundStatusDes(int status) {
        for (RefundStatus sta : RefundStatus.values()) {
            if (sta.getValue()== status) {
                return sta.getDesc();
            }
        }
        return null;
    }
    
    private RefundStatus(int value, String desc) {
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
