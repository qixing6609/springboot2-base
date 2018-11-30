package com.ts.constant;

/**
 * 枚举类案例
 * PayEnum<BR>
 * 创建人:liuguangming <BR>
 * 时间：2018年4月3日-下午3:50:35 <BR>
 * @version 2.0
 *
 */
public enum MemberEnum {
	/**
     * 会员等级 0不是会员 10 一级 20 二级  30 三级
     */
	BINDCARD_STATUS_0((byte)0,"普通用户"),
    BINDCARD_STATUS_10((byte)10,"黄金会员"),
    BINDCARD_STATUS_20((byte)20,"白金会员"),
    BINDCARD_STATUS_30((byte)30,"钻石会员"),
	BINDCARD_STATUS_40((byte)40,"私人订制");
	
	private byte code;
	
	private String name;
	private MemberEnum(byte code, String name){
		this.code = code;
		this.name = name;
	}
	// 获取名称 
    public static String getName(byte code) {  
        for (MemberEnum c : MemberEnum.values()) {  
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
