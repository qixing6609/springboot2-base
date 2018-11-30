package com.ts.service;

import java.util.Map;


public interface ICommonService {
    void delCategoryCache();
    
    /**
     * 
     * 获取配置信息<BR>
     * 方法名：configInfo<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年8月18日-下午6:17:52 <BR>
     * @param appType
     * @param terminal
     * @return Map<String,Object><BR>
     * @exception <BR>
     * @since  2.0
     */
	Map<String, Object> configInfo(int appType, int terminal);


}
