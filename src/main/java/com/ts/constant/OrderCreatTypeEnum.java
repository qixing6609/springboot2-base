package com.ts.constant;

public enum OrderCreatTypeEnum {
	USER(1, "用户"), SALESMAN(2, "业务员");

	private int value;
	private String desc;

	public static String getName(int status) {
		for (OrderCreatTypeEnum sta : OrderCreatTypeEnum.values()) {
			if (sta.getValue() == status) {
				return sta.getDesc();
			}
		}
		return null;
	}

	private OrderCreatTypeEnum(int value, String desc) {
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
