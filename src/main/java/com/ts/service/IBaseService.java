package com.ts.service;

import java.util.List;
import java.util.Map;


public interface IBaseService {
	
	/**
	 * 根据主键查询数据表<BR>
	 * 方法名：queryObjectById<BR>
	 * 创建人：liuguangming <BR>
	 * 时间：2018年8月1日-下午8:01:52 <BR>
	 * @param id
	 * @param clazz
	 * @return Object<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	Object queryObjectById(long id,Class clazz);

	/**
	 * 根据主键查询数据表 for update<BR>
	 * 方法名：queryObjectByIdForUpdate<BR>
	 * 创建人：liuguangming <BR>
	 * 时间：2018年8月3日-上午11:45:56 <BR>
	 * @param id
	 * @param clazz
	 * @return Object<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	Object queryObjectByIdForUpdate(long id, Class clazz);

	/**
	 * 统一根据条件查询相应实体<BR>
	 * 方法名：queryObjectByObj<BR>
	 * 创建人：liuguangming <BR>
	 * 时间：2018年8月3日-下午5:02:53 <BR>
	 * @param map
	 * @param clazz
	 * @return List<?><BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	List<?> queryObjectByObj(Map<String, Object> map, Class clazz);



	Object currentUser();
	
}
