package com.ts.constant;

/**
 * Created by shan on 16/7/25.
 */
public enum UserStatus {

	/**
     * 会员状态
     */
	NORMAL(1,"正常"),
	FREEZE(0,"冻结");
	
	private int code;
	
	private String name;
	private UserStatus(int code, String name){
		this.code = code;
		this.name = name;
	}
	// 获取名称 
    public static String getName(int code) {  
        for (UserStatus c : UserStatus.values()) {  
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
