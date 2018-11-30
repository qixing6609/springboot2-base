package com.ts.constant;

public enum CouponStatus {
	NORMAL( 0, "正常"), OVERDUE(2, "已过期"), USED(1, "已使用");
	private int value;
	private String desc;

	private CouponStatus(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}


	public String getDesc() {
		return desc;
	}
	// 获取名称 
    public static String getName(int code) {  
        for (CouponStatus c : CouponStatus.values()) {  
            if (c.getValue() == code) {  
                return c.desc;  
            }  
        }  
        return null;  
    }


}
