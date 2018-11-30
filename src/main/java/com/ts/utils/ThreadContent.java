package com.ts.utils;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * ThreadContent<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2017年12月20日-上午10:32:22 <BR>
 * @version 2.0
 *
 */
public class ThreadContent {
	private static Logger logger = LoggerFactory.getLogger(ThreadContent.class);

	private static final ThreadLocal<HttpServletRequest> request = new ThreadLocal();

	private static final ThreadLocal<HttpServletResponse> response = new ThreadLocal();

	private static final ThreadLocal<Pagination> pagination = new ThreadLocal();

	private static final ThreadLocal<Map<String, Object>> data = new ThreadLocal();

	public static void set(ServletRequest servletRequest,
			ServletResponse servletResponse) {
		if ((servletRequest instanceof HttpServletRequest)) {
			request.set((HttpServletRequest) servletRequest);
		}
		if ((servletResponse instanceof HttpServletResponse)) {
			//((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Origin", "*");
			response.set((HttpServletResponse) servletResponse);
		}
		pagination.set(null);
		data.set(new HashMap());
	}

	public static HttpServletRequest request() {
		return (HttpServletRequest) request.get();
	}

	public static HttpServletResponse response() {
		return (HttpServletResponse) response.get();
	}

	public static void pagination(Pagination page) {
		pagination.set(page);
	}

	public static Pagination pagination() {
		return (Pagination) pagination.get();
	}

	public static <T> T getData(String key) {
		return (T) ((Map) data.get()).get(key);
	}

	public static void addData(String key, Object obj) {
		Object oldValue = ((Map) data.get()).put(key, obj);
		if (oldValue != null)
			throw new IllegalArgumentException("数据已存在不能重复设置");
	}

	public static <T> T removeData(String key) {
		return (T) ((Map) data.get()).remove(key);
	}
}