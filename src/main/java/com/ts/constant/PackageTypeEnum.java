package com.ts.constant;

/**
 * PackageTypeEnum<BR>
 * 创建人:shixiaofei <BR>
 * 时间：2018年9月19日-下午2:28:56 <BR>
 * @version 2.0
 *
 */
public enum PackageTypeEnum {

	/**
     * 套餐类型： 1：组合套装、2：推荐套餐
     */
	RECOMMEND(1,"组合套餐"),
	COMBINATION(2,"推荐套餐");
	
	private int code;
	
	private String name;
	private PackageTypeEnum(int code, String name){
		this.code = code;
		this.name = name;
	}
	// 获取名称 
    public static String getName(int code) {  
        for (PackageTypeEnum c : PackageTypeEnum.values()) {  
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
