package com.ts.token;

import java.io.Serializable;  
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * TokenBean<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2016年5月20日-下午6:30:30 <BR>
 * @version 1.0.0
 *
 */
public class Token implements Serializable  
{  
    private static final long serialVersionUID = -8766321739625153631L;  
    // tokenId
    private String token;
    //token中存放数据的map容器
	private Map<String, Object> dataMap = new HashMap<String, Object>();
      
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Object getAttribute(String key) {
		return dataMap.get(key);
	}

	public void setAttribute(String key, Object o) {
		this.dataMap.put(key, o);
		TokenUtils.updateToken(this);
	}  
}  