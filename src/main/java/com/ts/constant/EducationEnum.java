package com.ts.constant;

/**
 * SalesManTypeEnum<BR>
 * 创建人:shixiaofei <BR>
 * 时间：2018年9月12日-下午2:28:56 <BR>
 * @version 2.0
 *
 */
public enum EducationEnum {

	/**
     * 业务员类型
     */
	PRIMARY_SCHOOL(0,"小学"),
	MIDDLE_SCHOOL(1,"初中"),
	HIGH_SCHOOL(2,"高中"),
	COLLEGE(3,"大专"),
	BACHELOR(4,"本科"),
	MASTERS_DEGREE(5,"硕士"),
	DOCTOR(6,"博士");
	
	private int code;
	
	private String name;
	private EducationEnum(int code, String name){
		this.code = code;
		this.name = name;
	}
	// 获取名称 
    public static String getName(int code) {  
        for (EducationEnum c : EducationEnum.values()) {  
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
