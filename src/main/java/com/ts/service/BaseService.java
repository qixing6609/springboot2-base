package com.ts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ts.mapper.ext.CommonMapper;
import com.ts.token.Token;
import com.ts.token.TokenUtils;
import com.ts.utils.CollectionUtil;
import com.ts.utils.StringUtil;
import com.ts.utils.ThreadContent;



/**
 * service基类，处理token获取，设置
 * 
 * BaseService<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2017年1月23日-下午2:11:57 <BR>
 * @version 2.0
 *
 */

@Service
public class BaseService implements IBaseService{

	@Autowired
	protected CommonMapper commonMapper;
	
	static String SESSION_USER = "userObj";
	
	static String SESSION_SALSEMAN = "salesmanObj";
	
	static String SESSION_PARTNER = "partnerObj";

	public Object currentUser(String token) {
		Token tokenObj = TokenUtils.getToken(token);
		if (tokenObj != null) {
			return tokenObj.getAttribute(SESSION_USER);
		}
		return null;
	}
	
	/**
	 * 
	 * 获取当前用户<BR>
	 * 方法名：currentUser<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年5月24日-下午2:54:33 <BR>
	 * @return Object<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	@Override
	public Object currentUser() {
		String token = ThreadContent.request().getHeader("token");
		if (!StringUtil.isEmpty(token) && !"undefined".equals(token)) {
			Token tokenObj = TokenUtils.getToken(token);
			if (tokenObj != null) {
				return tokenObj.getAttribute(SESSION_USER);
			}
		}
		return null;
	}
	
	
	/**
	 * 
	 * 保存用户信息到session<BR>
	 * 方法名：loadUser2Session<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年5月24日-下午2:54:51 <BR>
	 * @param user
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public String loadUser2Session(Object user) {
		String tokenStr = ThreadContent.request().getHeader("token");
		if(StringUtil.isEmpty(tokenStr) || "undefined".equals(tokenStr)){
			tokenStr = UUID.randomUUID().toString().trim();
		}
		Token token = new Token();
		token.setToken(tokenStr);
		token.setAttribute(SESSION_USER, user);
		return TokenUtils.setToken(token);
	}
	
	
	public String loadSaleman2Session(Object user) {
		String tokenStr = ThreadContent.request().getHeader("token");
		if(StringUtil.isEmpty(tokenStr) || "undefined".equals(tokenStr)){
			tokenStr = UUID.randomUUID().toString().trim();
		}
		Token token = new Token();
		token.setToken(tokenStr);
		token.setAttribute(SESSION_SALSEMAN, user);
		return TokenUtils.setToken(token);
	}
	
	public String loadPartner2Session(Object user) {
		String tokenStr = ThreadContent.request().getHeader("token");
		if(StringUtil.isEmpty(tokenStr) || "undefined".equals(tokenStr)){
			tokenStr = UUID.randomUUID().toString().trim();
		}
		Token token = new Token();
		token.setToken(tokenStr);
		token.setAttribute(SESSION_PARTNER, user);
		return TokenUtils.setToken(token);
	}
	
	/**
	 * 
	 * 设置属性<BR>
	 * 方法名：setAttribute<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年5月31日-下午7:08:35 <BR>
	 * @param key
	 * @param value
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public String setAttribute(String key, String value) {
		String token = ThreadContent.request().getHeader("token");
		if (StringUtil.isNotBlank(token)) {
			Token tokenObj = TokenUtils.getToken(token);
			if (tokenObj != null) {
				tokenObj.setAttribute(key, value);
				return TokenUtils.setToken(tokenObj);
			}
		}
		return null;
	}
	
	/**
	 * 
	 * 获取属性<BR>
	 * 方法名：getAttribute<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年5月31日-下午7:10:50 <BR>
	 * @param key
	 * @return Object<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public Object getAttribute(String key) {
		String token = ThreadContent.request().getHeader("token");
		if (StringUtil.isNotBlank(token)) {
			Token tokenObj = TokenUtils.getToken(token);
			if (tokenObj != null) {
				return tokenObj.getAttribute(key);
			}
		}
		return null;
	}
	
	
	/**
	 * 
	 * 获取头信息<BR>
	 * 方法名：getHeader<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年5月24日-下午2:55:16 <BR>
	 * @param key
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public String getHeader(String key){
		return ThreadContent.request().getHeader(key);
	}
	
	/**
	 * 获取session属性值
	 * (这里用一句话描述这个方法的作用)<BR>
	 * 方法名：getSessionAttribute<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年7月30日-下午2:05:17 <BR>
	 * @param key
	 * @param removeFlg
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static String getSessionAttribute(String key, boolean removeFlg){
		String value = (String) ThreadContent.request().getSession().getAttribute(key);
		if (removeFlg) {
			ThreadContent.request().getSession().removeAttribute(key);
		}
		return value;
	}
	
	public String getIpAddr(){
//		return IpUtil.getIp(ThreadContent.request());
		return "";
	}
	
	/**
	 * 根据主键id查询任意指定对象实体
	 * 方法名：queryObjectById<BR>
	 * 创建人：liuguangming <BR>
	 * 时间：2018年7月30日-下午2:05:17 <BR>
	 * @param id     表主键
	 * @param clazz  实体类 如User.class
	 * @return Object<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	@Override
	public Object queryObjectById(long id,Class clazz){
		//类名
		String className  = clazz.getName();
		//实体名称
		String Objectname = className.substring(className.lastIndexOf(".")+1, className.length());
		//表名
		String tableName = "ydj_" + StringUtil.underscoreName(Objectname).toLowerCase();
		String sql = "select * from " + tableName  + " where id = " + id;
		List<Map<String,Object>> list = commonMapper.execSql(sql);
		if(!CollectionUtil.isEmpty(list)){
			Map<String,Object> map =  list.get(0);
			@SuppressWarnings("unchecked")
			Object obj =  JSONObject.parseObject(JSON.toJSONString(map), clazz);
			return obj;
		}
		return null;
	}
	
	
	/**
	 * 根据主键id行级锁查询任意指定对象实体
	 * 方法名：queryObjectByIdForUpdate<BR>
	 * 创建人：liuguangming <BR>
	 * 时间：2018年7月30日-下午2:05:17 <BR>
	 * @param id     表主键
	 * @param clazz  实体类 如User.class
	 * @return Object<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	@Override
	public Object queryObjectByIdForUpdate(long id,Class clazz){
		//类名
		String className  = clazz.getName();
		//实体名称
		String Objectname = className.substring(className.lastIndexOf(".")+1, className.length());
		//表名
		String tableName = "ydj_" + StringUtil.underscoreName(Objectname).toLowerCase();
		String sql = "select * from " + tableName  + " where id = " + id + " for UPDATE ";
		List<Map<String,Object>> list = commonMapper.execSql(sql);
		if(!CollectionUtil.isEmpty(list)){
			Map<String,Object> map =  list.get(0);
			@SuppressWarnings("unchecked")
			Object obj =  JSONObject.parseObject(JSON.toJSONString(map), clazz);
			return obj;
		}
		return null;
	}
	
	
	/**
	 * 根据条件map查询任意指定对象实体集合
	 * 方法名：queryObjectByObj<BR>
	 * 创建人：liuguangming <BR>
	 * 时间：2018年7月30日-下午2:05:17 <BR>
	 * @param map    条件map key=表字段 +（&=、>=、<=、<、>、like、in...）  value=条件值  ，默认是“=”条件 ，
	 *               如 {"token":"111111"},指定表达式符号  如{"token&>=":"111111"}
	 * @param clazz  实体类 如User.class
	 * @return List<?><BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	@Override
	public List<?> queryObjectByObj(Map<String,Object> map,Class clazz){
		//类名
		String className  = clazz.getName();
		//实体名称
		String Objectname = className.substring(className.lastIndexOf(".")+1, className.length());
		//表名
		String tableName = "ydj_" + StringUtil.underscoreName(Objectname).toLowerCase();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ").append(tableName).append(" where 1=1 ");
		if(map!=null && !map.isEmpty()){
			Set<Entry<String, Object>> sets = map.entrySet();
			for(Entry<String, Object> set : sets){
				String condition = "=";
				String key = set.getKey();
				Object value = set.getValue();
				String[] keys = key.split("&");
				if(keys.length>1){
					key = keys[0];
					condition = keys[1];
				}
				sql.append(" and ").append(key).append(condition);
				if(value instanceof String ){
					if("like".contains(condition)){
						sql.append(" '%").append(value).append("%' ");
					}else{
						sql.append(" '").append(value).append("' ");
					}
				}else{
					sql.append(value);
				}
			}
		}
		
		List<Map<String,Object>> list = commonMapper.execSql(sql.toString());
		List<Object> result = new ArrayList<Object>();
		if(!CollectionUtil.isEmpty(list)){
			for(Map<String,Object> objMap : list){
				@SuppressWarnings("unchecked")
				Object obj =  JSONObject.parseObject(JSON.toJSONString(objMap), clazz);
				result.add(obj);
			}
			
			return result;
		}
		return null;
	}
	
}
