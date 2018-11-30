package com.ts.constant;

/**
 * 
 * 订单分类 10拉新单  20业务员订单 30自然单
 * OrderKindEnum<BR>
 * 创建人:shixiaofei <BR>
 * 时间：2018年9月17日-下午6:45:23 <BR>
 * @version 2.0
 *
 */
public enum OrderKindEnum {
	NEW(10, "拉新单"), SALESMAN(20, "业务员订单"),NATURAL(30, "自然单");

	private int value;
	private String desc;

	public static String getName(int status) {
		for (OrderKindEnum sta : OrderKindEnum.values()) {
			if (sta.getValue() == status) {
				return sta.getDesc();
			}
		}
		return null;
	}

	private OrderKindEnum(int value, String desc) {
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
