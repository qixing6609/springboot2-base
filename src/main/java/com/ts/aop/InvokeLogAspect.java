package com.ts.aop;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ts.token.Token;
import com.ts.utils.IpUtil;
import com.ts.utils.ThreadContent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.ts.token.TokenUtils;
import com.ts.utils.StringUtil;

@Aspect
@Component
public class InvokeLogAspect {
    public static final Logger logger = LoggerFactory.getLogger(InvokeLogAspect.class);
    
    // 执行最大时间 超过该时间则警告
    private static final int DEFAULT_TIME_LIMIT = 3000;
//    private static final String MSG = "--请求--\n --URL:{}\t --IP:{}\t --method:{}\n --方法:{}\t --描述：{}\t --位置：{}\t --参数：{}\n --返回：{}\n --耗时：{} ms";
    private static final String MSG = "--请求--\n --URL:{}\t --IP:{}\t --method:{}\n --请求头:{}\n --方法:{}\t --参数:{}\n --返回:{}\n --耗时:{} ms";
	static String SESSION_USER = "userObj";
	
	static String SESSION_SALSEMAN = "salesmanObj";
	
    // 切点
//    @Pointcut("@annotation(com.ydj.service.aop.InvokeLog)")
    @Pointcut("execution(* com.ts.*.controller.*.*(..))")
    public void executePointCut() {

    }


    // around 切面强化
    @Around("executePointCut()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (logger.isDebugEnabled() || logger.isWarnEnabled()) {
            StopWatch clock = new StopWatch();
            clock.start();
            Object retrunobj = null;
            try {
                // 注意和finally中的执行顺序 finally是在return中的计算结束返回前执行
                return retrunobj = joinPoint.proceed(args);
            } catch (Exception e) {
                throw e;
            } finally {
                clock.stop();
                long consumTime = clock.getTotalTimeMillis();
                // 打印日志
                handleLog(joinPoint, args, retrunobj, consumTime);
            }
        } else {
            return joinPoint.proceed(args);
        }

    }



    /**
     * 日志处理
     *
     * @param joinPoint 位置
     * @param args      参数
     * @param retrunobj 响应
     * @param consumTime  耗时ms
     */
    private void handleLog(ProceedingJoinPoint joinPoint, Object[] args, Object returnObj, long consumTime) {
    	// 获取请求
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =  attributes.getRequest();
        Enumeration<String> headerEnums = request.getHeaderNames();
        // 请求头参数
        StringBuilder headers = new StringBuilder();
		while(headerEnums.hasMoreElements()){
			String name = headerEnums.nextElement();
			headers.append(name).append("=").append(request.getHeader(name)).append(",");
		}
		
		String url = request.getRequestURI();
		String token = request.getHeader("token");
		
//		if(url.contains("salseman")){
//			Salesman user = (Salesman) currentSaleman();
//			if (user != null) {
//				logger.info("业务员：id:{},phone:{},name:{},token:{}", user.getId(), user.getPhone(), user.getRealName(), token);
//			}
//		}else{
//			User user = (User) currentUser();
//			if (user != null) {
//				logger.info("用户：id:{},phone:{},token:{},openId:{}", user.getId(), user.getPhone(), token, user.getOpenId());
//			}
//		}
        
        // 获取访问java方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取方法上InvokeLog注解
//        InvokeLog invokeLog = method.getAnnotation(InvokeLog.class);
        // 分解请求参数
        Object[] params = argsDemote(args);
        // 访问方法
        String function = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 调用方法名称
        String logName = "";
        // 调用方法描述
        String logDesc = "";
        // 是否打印返回数据
        boolean printReturn = true;
        // 如果invokeLog 不为空，则获取注解上的描述
//        if (invokeLog != null) {
//			logName = invokeLog.name();
//			logDesc = invokeLog.description();
//			printReturn = invokeLog.printReturn();
//		}
        // 打印日志
//        if (consumTime < DEFAULT_TIME_LIMIT)
//            logger.info(MSG, new Object[]{request.getRequestURI(), request.getRemoteAddr(), request.getMethod(), logName, logDesc, function, params, getReturnMsg(printReturn, returnObj), consumTime});
//        else
//            logger.warn(MSG, new Object[]{request.getRequestURI(), request.getRemoteAddr(), request.getMethod(), logName, logDesc, function, params, getReturnMsg(printReturn, returnObj), consumTime});
        if (consumTime < DEFAULT_TIME_LIMIT)
        	logger.info(MSG, new Object[]{request.getRequestURI(), IpUtil.getIp(request), request.getMethod(), headers, function, params, getReturnMsg(printReturn, returnObj), consumTime});
        else
        	logger.warn(MSG, new Object[]{request.getRequestURI(), IpUtil.getIp(request), request.getMethod(), headers, function, params, getReturnMsg(printReturn, returnObj), consumTime});
    }

    /**
     * @param name            操作名称
     * @param description     描述
     * @param printReturn     是否打印响应
     * @param joinPoint       位置
     * @param args            参数
     * @param returnObj       响应
     * @param totalTimeMillis 耗时ms
     */
    protected void printLogMsg(String url, String ip, String method, String name, String description, boolean printReturn, JoinPoint joinPoint, Object[] args, Object returnObj, long totalTimeMillis) {
        Object[] params = argsDemote(args);
        String function = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (totalTimeMillis < DEFAULT_TIME_LIMIT)
            logger.info(MSG, new Object[]{url, ip, method, name, description, function, params, getReturnMsg(printReturn, returnObj), totalTimeMillis});
        else
            logger.warn(MSG, new Object[]{url, ip, method, name, description, function, params, getReturnMsg(printReturn, returnObj), totalTimeMillis});
    }


    /**
     * 
     * 获取返回数据<BR>
     * 方法名：getReturnMsg<BR>
     * 创建人：wbd <BR>
     * 时间：2018年8月24日-上午11:47:02 <BR>
     * @param printReturn
     * @param returnObj
     * @return String<BR>
     * @exception <BR>
     * @since  2.0
     */
    private String getReturnMsg(boolean printReturn, Object returnObj) {
    	if (returnObj instanceof String){
    		return String.valueOf(returnObj);
    	}
        return printReturn ? ((returnObj != null) ? JSONObject.toJSONString(returnObj) : "null") : "[printReturn = false]";
    }

    /**
     * 
     * (这里用一句话描述这个方法的作用)<BR>
     * 方法名：argsDemote<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年8月24日-下午2:55:24 <BR>
     * @param args
     * @return Object[]<BR>
     * @exception <BR>
     * @since  2.0
     */
    private Object[] argsDemote(Object[] args) {
        if (args == null || args.length == 0) {
            return new Object[]{};
        }
        Object[] params = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof ModelMap
                    || arg instanceof Model || arg instanceof InputStreamSource ||
                    arg instanceof File) {
                params[i] = args.toString();
            } else {
                params[i] = args[i];
            }
        }
        return params;
    }
    
	public Object currentUser() {
		String token = ThreadContent.request().getHeader("token");
		if (StringUtil.isNotBlank(token)) {
			Token tokenObj = TokenUtils.getToken(token);
			if (tokenObj != null) {
				return tokenObj.getAttribute(SESSION_USER);
			}
		}
		return null;
	}
	
	
//	public Salesman currentSaleman() {
//		String token = ThreadContent.request().getHeader("token");
//		if (StringUtil.isNotBlank(token)) {
//			Token tokenObj = TokenUtils.getToken(token);
//			if (tokenObj != null) {
//				return (Salesman)tokenObj.getAttribute(SESSION_SALSEMAN);
//			}
//		}
//		return null;
//	}

}