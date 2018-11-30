package com.ts.constant;

public enum PackageStatusEnum {
	/**
	 * 1上架 2下架
	 */
	ON_THE_SHELF(1, "上架"), LOWER_FRAME(2, "下架");
	private int value;
	private String desc;

	private PackageStatusEnum(int value, String desc) {
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
		for (PackageStatusEnum c : PackageStatusEnum.values()) {
			if (c.getValue() == value) {
				return c.desc;
			}
		}
		return null;
	}

}
