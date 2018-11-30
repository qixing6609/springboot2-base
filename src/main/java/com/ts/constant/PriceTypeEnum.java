package com.ts.constant;

/**
 * PriceTypeEnum<BR>
 * 创建人:shixiaofei <BR>
 * 时间：2018年9月12日-下午2:28:56 <BR>
 * @version 2.0
 *
 */
public enum PriceTypeEnum {

	/**
     * 价格类型
     */
	ORIGINAL_PRICE(1,"原价"),
	MEMBERSHIP_PRICE(2,"会员价"),
	ACTIVITY_PRICE(3,"活动价");
	
	private int code;
	
	private String name;
	private PriceTypeEnum(int code, String name){
		this.code = code;
		this.name = name;
	}
	// 获取名称 
    public static String getName(int code) {  
        for (PriceTypeEnum c : PriceTypeEnum.values()) {  
            if (c.getCode() == code) {  
                return c.name;  
            }  
        }  
        return null;  
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

}
