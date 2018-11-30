package com.ts.constant;

public enum DelFlgEnum {
	/**
	 * 0 未删除1已删除
	 */
	UNDELETE(0, "未删除"), DELETE(1, "已删除");
	private int value;
	private String desc;

	private DelFlgEnum(int value, String desc) {
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
		for (DelFlgEnum c : DelFlgEnum.values()) {
			if (c.getValue() == value) {
				return c.desc;
			}
		}
		return null;
	}

}
