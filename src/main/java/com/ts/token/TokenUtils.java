package com.ts.token;

import javax.servlet.http.HttpServletRequest;

import com.ts.utils.StringUtil;
import com.ts.utils.ThreadContent;


/**
 * 
 * SessionUtils工具类<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2016年5月20日-上午11:25:40 <BR>
 * @version 1.0.0
 *
 */
public class TokenUtils {
	
	private static final ThreadLocal<JedisSessionDAO> SESSIOND_DAO_THREAD_LOCAL = new ThreadLocal<JedisSessionDAO>();

	/**
	 * 
	 * 获取sessionDao实例<BR>
	 * 方法名：getSessionDao<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月20日-下午1:49:33 <BR>
	 * @return JedisSessionDAO<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	private static JedisSessionDAO getSessionDao(){
		JedisSessionDAO session = SESSIOND_DAO_THREAD_LOCAL.get();
		if (session == null) {
			session = new JedisSessionDAO();
			SESSIOND_DAO_THREAD_LOCAL.set(session);
		}
		return session;
	}
	/**
	 * 
	 * 验证token是否过期<BR>
	 * 方法名：validateToken<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月20日-下午1:18:56 <BR>
	 * @param token
	 * @return boolean<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static boolean validateToken(String token){
		JedisSessionDAO sessionDAO = getSessionDao();
		Token session = sessionDAO.doReadToken(token);
		if (session != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 根据token获取Token<BR>
	 * 方法名：getToken<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月20日-下午1:50:11 <BR>
	 * @param token
	 * @return Token<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static Token getToken(){
		String token = ThreadContent.request().getHeader("token");
		if (validateToken(token)) {
			return getSessionDao().doReadToken(token);
		}
		return null;
	}
	
	/**
	 * 
	 * 根据token获取Token<BR>
	 * 方法名：getToken<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月20日-下午1:50:11 <BR>
	 * @param token
	 * @return Token<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static Token getToken(String token){
		if (validateToken(token)) {
			return getSessionDao().doReadToken(token);
		}
		return null;
	}
	
	/**
	 * 
	 * 根据tokenId获取Token<BR>
	 * 方法名：getToken<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月23日-上午9:42:58 <BR>
	 * @param request
	 * @return Token<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static Token getToken(HttpServletRequest request){
		String token = request.getHeader("token");
		if (StringUtil.isBlank(token)) {
			return null;
		}
		if (validateToken(token)) {
			return getSessionDao().doReadToken(token);
		}
		return null;
	}
	
	/**
	 * 
	 * 设置Token<BR>
	 * 方法名：setToken<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月20日-下午1:55:38 <BR>
	 * @param Token
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static String setToken(Token tokenObj){
		String token = tokenObj.getToken();
		if (!validateToken(token)) {
			token = getSessionDao().doCreate(tokenObj).toString();
		}
		return token;
	}
	
	/**
	 * 
	 * 更新token<BR>
	 * 方法名：updateToken<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月20日-下午6:29:08 <BR>
	 * @param token void<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static void updateToken(Token token){
		if (validateToken(token.getToken())) {
			getSessionDao().update(token);
		}
	}
	
	/**
	 * 
	 * 删除Token<BR>
	 * 方法名：delToken<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月20日-下午1:58:52 <BR>
	 * @param token void<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static void delToken(String token){
		if (validateToken(token)) {
			getSessionDao().delete(token);
		}
	}
}
