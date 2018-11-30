package com.ts.constant;

/**
 * SalesManTypeEnum<BR>
 * 创建人:shixiaofei <BR>
 * 时间：2018年9月12日-下午2:28:56 <BR>
 * @version 2.0
 *
 */
public enum SalesManTypeEnum {

	/**
     * 业务员类型
     */
	CITYMANAGE(1,"城市经理"),
	SALESMAN(2,"业务员");
	
	private int code;
	
	private String name;
	private SalesManTypeEnum(int code, String name){
		this.code = code;
		this.name = name;
	}
	// 获取名称 
    public static String getName(int code) {  
        for (SalesManTypeEnum c : SalesManTypeEnum.values()) {  
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
