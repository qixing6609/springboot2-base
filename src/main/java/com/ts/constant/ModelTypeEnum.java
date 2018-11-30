package com.ts.constant;

/**
 * 
 * ModelTypeEnum<BR>
 * 创建人:shixiaofei <BR>
 * 时间：2018年9月27日-下午3:50:35 <BR>
 * @version 2.0
 *
 */
public enum ModelTypeEnum {
	/**
     * 产品详细类型  1产品型号  2产品附加项目'
     */
	PRODUCT_MODEL((byte)1,"产品型号"),
    PRODUCT_ADD_MODEL((byte)2,"产品附加项目");
	
	private byte code;
	
	private String name;
	private ModelTypeEnum(byte code, String name){
		this.code = code;
		this.name = name;
	}
	// 获取名称 
    public static String getName(byte code) {  
        for (ModelTypeEnum c : ModelTypeEnum.values()) {  
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
