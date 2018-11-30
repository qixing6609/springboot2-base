package com.ts.exception;

/**
 * 
 * WebBusinessException<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2017年12月13日-下午1:51:35 <BR>
 * @version 2.0
 *
 */
public class WebBusinessException extends RuntimeException {
	private static final long serialVersionUID = -2604653882050707909L;
	
	private String paramName;

	public WebBusinessException() {
	}

	public WebBusinessException(String message) {
		super(message);
	}
	
	public WebBusinessException(String paramName, String message) {
		super(message);
		this.paramName = paramName;
	}

	public WebBusinessException(Throwable cause) {
		super(cause);
	}

	public WebBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
}
