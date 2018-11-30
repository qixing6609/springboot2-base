package com.ts.constant;

/**
 * 枚举类案例
 * WhetherEnum<BR>
 * 创建人:SHIXIAOFEI <BR>
 * 时间：2018年9月3日-下午3:50:35 <BR>
 * @version 2.0
 *
 */
public enum WhetherEnum {
	/**
     * 是否
     */
	YES(1,"是"),
    NO(0,"否");
	
	private int code;
	
	private String name;
	private WhetherEnum(int code, String name){
		this.code = code;
		this.name = name;
	}
	// 获取名称 
    public static String getName(int code) {  
        for (WhetherEnum c : WhetherEnum.values()) {  
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
