package com.ts.constant;

public enum BannerTypeEnum {
	HOME(1, "首页banner"), INFORMATION(2, "资讯轮播图片");
	private int code;
	private String name;

	private BannerTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
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


	// 获取名称 
    public static String getName(int code) {  
        for (BannerTypeEnum c : BannerTypeEnum.values()) {  
            if (c.getCode() == code) {  
                return c.name;  
            }  
        }  
        return null;  
    }


}
