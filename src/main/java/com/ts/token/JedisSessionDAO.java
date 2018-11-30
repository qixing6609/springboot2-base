package com.ts.token;

import java.io.Serializable;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ts.utils.StringUtil;

import redis.clients.jedis.Jedis;


/**
 * 
 * JedisSessionDAO自定义授权toke操作类<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2016年5月20日-下午6:16:37 <BR>
 * @version 1.0.0
 *
 */
public class JedisSessionDAO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	//token前缀
	private static String tokenKeyPrefix = "app_token:";
	//token过期时间(秒) 30天
	private static int TIME_OUT = 2592000;

	/**
	 * 
	 * 更新token<BR>
	 * 方法名：update<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月20日-下午6:23:00 <BR>
	 * @param token void<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public void update(Token token){
		if (token == null) {  
            return;
        }
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getResource();
			// 保存新的token
//			jedis.hset(sessionKeyPrefix, token, principalId + "|" + session.getTimeout() + "|" + session.getLastAccessTime().getTime());
			jedis.set(JedisUtils.getBytesKey(tokenKeyPrefix + token.getToken()), JedisUtils.toBytes(token));
			
			// 设置超期时间
			jedis.expire((tokenKeyPrefix + token), TIME_OUT);

			logger.debug("update {}", token);
		} catch (Exception e) {
			logger.error("update {}", token, e);
		} finally {
			JedisUtils.returnResource(jedis);
		}
	}
	
	/**
	 * 
	 * 删除token<BR>
	 * 方法名：delete<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月20日-下午6:23:24 <BR>
	 * @param token void<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public void delete(String token) {
		if (StringUtil.isBlank(token)) {
			return;
		}
		
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getResource();
			
			jedis.hdel(JedisUtils.getBytesKey(tokenKeyPrefix), JedisUtils.getBytesKey(token));
			jedis.del(JedisUtils.getBytesKey(tokenKeyPrefix + token));

			logger.debug("delete {} ", token);
		} catch (Exception e) {
			logger.error("delete {} ", token, e);
		} finally {
			JedisUtils.returnResource(jedis);
		}
	}
	
	/**
	 * 
	 * 生成token<BR>
	 * 方法名：doCreate<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月20日-下午6:24:04 <BR>
	 * @param tokenObj
	 * @return Serializable<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	protected Serializable doCreate(Token tokenObj) {
		String token = tokenObj.getToken();
		if (StringUtil.isBlank(token)) {
			token = this.generateTokenId().toString();
		}
		tokenObj.setToken(token);
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getResource();
			jedis.set(JedisUtils.getBytesKey(tokenKeyPrefix + token),JedisUtils.toBytes(tokenObj));
			// 设置超期时间
			jedis.expire((tokenKeyPrefix + token), TIME_OUT);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JedisUtils.returnResource(jedis);
		}
		return token;
	}
	
	/**
	 * 
	 * tokenID 生成<BR>
	 * 方法名：generateTokenId<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月20日-下午6:24:26 <BR>
	 * @return Serializable<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public Serializable generateTokenId() {
		// 自定义规则生成sessionidKey
		return UUID.randomUUID().toString().trim();
	}
	
	/**
	 * 
	 * 根据token获取token对象<BR>
	 * 方法名：doReadToken<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2016年5月20日-下午6:24:47 <BR>
	 * @param token
	 * @return Token<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	protected Token doReadToken(Serializable token) {
		Token tokenObj = null;
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getResource();
			if (jedis.exists(JedisUtils.getBytesKey(tokenKeyPrefix + token))){
				tokenObj = (Token)JedisUtils.toObject(jedis.get(
						JedisUtils.getBytesKey(tokenKeyPrefix + token)));
				// 设置超期时间
				// session设置过期时间
				jedis.expire((tokenKeyPrefix + token), TIME_OUT);
			}
			logger.debug("doReadSession {}", token);
		} catch (Exception e) {
			logger.error("doReadSession {}", token, e);
		} finally {
			JedisUtils.returnResource(jedis);
		}
		
		return tokenObj;
	}
	
	public String getSessionKeyPrefix() {
		return tokenKeyPrefix;
	}
}
