package com.ts.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ts.utils.ValidationUtil;



/**
 * 异常统一处理
 * 
 * ExceptionHandleAdvice<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2018年5月17日-上午10:47:23 <BR>
 * @version 2.0
 *
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandleAdvice {
	Logger logger = LoggerFactory.getLogger(ExceptionHandleAdvice.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
    public String handleException(Exception e) {
        if (e instanceof WebBusinessException){
        	WebBusinessException webBusinessException = (WebBusinessException) e;
            return ValidationUtil.respParamErrMsg(webBusinessException.getParamName(), webBusinessException.getMessage());
        }else {
        	e.printStackTrace();
        	//将系统异常以打印出来
            logger.error("[系统异常]{}",e);
            return ValidationUtil.failureReturn();
        }
    }
}
