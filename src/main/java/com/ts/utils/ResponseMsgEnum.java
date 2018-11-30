package com.ts.utils;

public enum ResponseMsgEnum {
	
	SUCCESS("ok", 200),
	FAILURE("网络异常，请稍后重试", -1),
//	FAILURE("操作失败", -1),
//	ERROR_PARAM("参数错误", 400),
	ERROR_PARAM("网络异常，请稍后重试", 400),
	UNLOGIN("未登录", 402),
	SESSION_OUT_DATE("登录过期", 401),
	NO_AUTHORIZED("无权限", 403),
	METHOD_DISABLE("方法已经停用", 405),
	BAN_DUPLICATE_SUBMIT("禁止重复提交", 406),
	NO_SPECIFIC_DATA("没有指定数据", 410),
	INNER_EXCEPTION("网络异常，请稍后重试", 500);
//	INNER_EXCEPTION("内部异常了", 500);
	
	private int code;
	private String msg;
	
	private ResponseMsgEnum(String msg, int code){
		this.code = code;
		this.msg = msg;
	}
	
	// 普通方法  
    public static String getName(int code) {  
        for (ResponseMsgEnum c : ResponseMsgEnum.values()) {  
            if (c.getCode() == code) {  
                return c.msg;  
            }  
        }  
        return null;  
    }  
    public String getMsg() {  
        return msg;  
    }  
    public int getCode() {  
        return code;  
    }  
}
