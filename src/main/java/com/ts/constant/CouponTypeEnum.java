package com.ts.constant;

public enum CouponTypeEnum {
	COUPON( 0, "优惠券"), RED_ENVELOPE(1, "红包"), CASH(2, "现金");
	private int code;
	private String name;

	private CouponTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	
	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	// 获取名称 
    public static String getName(int code) {  
        for (CouponTypeEnum c : CouponTypeEnum.values()) {  
            if (c.getCode() == code) {  
                return c.name;  
            }  
        }  
        return null;  
    }


}
