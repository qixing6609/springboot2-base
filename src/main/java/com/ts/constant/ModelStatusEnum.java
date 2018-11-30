package com.ts.constant;

/**
 * 
 * ModelTypeEnum<BR>
 * 创建人:shixiaofei <BR>
 * 时间：2018年9月27日-下午3:50:35 <BR>
 * @version 2.0
 *
 */
public enum ModelStatusEnum {
	/**
     * 产品型号状态'1:上架  2：下架'
     */
	ON_THE_SHELF((byte)1,"上架"),
	LOWER_FRAME((byte)2,"下架");
	
	private byte code;
	
	private String name;
	private ModelStatusEnum(byte code, String name){
		this.code = code;
		this.name = name;
	}
	// 获取名称 
    public static String getName(byte code) {  
        for (ModelStatusEnum c : ModelStatusEnum.values()) {  
            if (c.getCode() == code) {  
                return c.name;  
            }  
        }  
        return null;  
    }

	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
