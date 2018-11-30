package com.ts.constant;

/**
 * SalesManStatusEnum<BR>
 * 创建人:shixiaofei <BR>
 * 时间：2018年9月12日-下午2:28:56 <BR>
 * @version 2.0
 *
 */
public enum SalesManStatusEnum {

	/**
     * 业务员状态
     */
	NORMAL(1,"正常"),
	REST(2,"休息"),
	RESIGNATION(3,"离职"),
	WORKING(4,"正在工作");
	
	private int code;
	
	private String name;
	private SalesManStatusEnum(int code, String name){
		this.code = code;
		this.name = name;
	}
	// 获取名称 
    public static String getName(int code) {  
        for (SalesManStatusEnum c : SalesManStatusEnum.values()) {  
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
