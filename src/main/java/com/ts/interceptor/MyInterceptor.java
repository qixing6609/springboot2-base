package com.ts.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ts.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ts.service.BaseService;
import com.ts.utils.StringUtil;

/**
 * 拦截器
 * 
 * MyInterceptor<BR>
 * 创建人:liuguangming <BR>
 * 时间：2018年7月26日-下午2:26:18 <BR>
 * @version 2.0
 *
 */
@Component
public class MyInterceptor extends BaseService implements HandlerInterceptor {
	static Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
	@Autowired
	private Environment env;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURL().toString();
		logger.info("#拦截sessionId:{}", request.getSession().getId());
		String appVersion = request.getHeader("appVersion");
		String deviceGroupId = request.getHeader("deviceGroupId");
		String channelId = request.getHeader("channelId");
		String mac = request.getHeader("mac");
//		String ip = IpUtil.getIp(request);
		String ip = "";
		logger.info("访问URL:{},appVersion:{},deviceGroupId:{},channelId:{},mac:{},ip:{}", url, appVersion, deviceGroupId, channelId, mac,ip);
		if(url.contains(".session")){
			if(url.contains("getProductModelDetailByIdNew")){
				return true;
			}
			String token = request.getHeader("token");
			if(StringUtil.isEmpty(token) || "undefined".equals(token)){
				token = UUID.randomUUID().toString().trim();
			}
			Object user = null;
//			if(url.contains("salseman")){
//				 user = (Salesman) currentSaleman();
//			}else if (url.contains("partner")) {
//				user = currentPartner();
//			}else{
//				 user = (User) currentUser(token);
//			}
			
			if(user == null){
				logger.info("#用户token已过期，请求的url={}", url);
				String profile = env.getProperty("spring.profiles.active");
				if("prod".equals(profile)){
					response.sendRedirect("https://tsmj.ts-aomei.com/ydj/user/noAuth");
				}else{
					response.sendRedirect(HttpUtil.basePath() + "/ydj/user/noAuth");
				}
				return false;
			}
			logger.info("用户userId:{},phone:{},访问URL:{},appVersion:{},deviceGroupId:{},channelId:{},mac:{},ip:{}", url, appVersion, deviceGroupId, channelId, mac,ip);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	 }

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	 }
	
}
