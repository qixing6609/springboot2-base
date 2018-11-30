package com.ts.constant;

public enum PayType {
	/**
	 * 0：余额支付、 1：微信、 2：支付宝、 3：H5微信、4：线下支付
	 */
	BALANCE(0, "余额支付"), WECHAT(1, "微信"), ALIPAY(2, "支付宝"),H5WECHAT(3,"H5微信"),OFFLINE(4,"线下支付");
	private int value;
	private String desc;

	private PayType(int value, String desc) {
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
	public static String getDesc(int value) {
		for (PayType c : PayType.values()) {
			if (c.getValue() == value) {
				return c.desc;
			}
		}
		return null;
	}

}
