package com.ts.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import com.ts.utils.ThreadContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 
 * ThreadLocalFilter<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2017年12月20日-上午10:31:48 <BR>
 * @version 2.0
 *
 */
@CrossOrigin(methods = { RequestMethod.DELETE, RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT })
@Component
@ServletComponentScan
@WebFilter(filterName = "ThreadLocalFilter", urlPatterns = "/*")
public class ThreadLocalFilter implements Filter {
	static Logger logger = LoggerFactory.getLogger(ThreadLocalFilter.class);

	public void destroy() {
		logger.info(getClass() + " destroy");
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (!(servletRequest instanceof HttpServletRequest)) {
			ThreadContent.set(servletRequest, servletResponse);
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE");
		httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, UserName,Authorization");//响应头 请按照自己需求添加。
		if (httpRequest.isRequestedSessionIdFromURL()) {
			HttpSession session = httpRequest.getSession();
			if (session != null) {
				session.invalidate();
			}
		}
		HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(
				httpResponse) {
			public String encodeRedirectUrl(String url) {
				return url;
			}

			public String encodeRedirectURL(String url) {
				return url;
			}

			public String encodeUrl(String url) {
				return url;
			}

			public String encodeURL(String url) {
				return url;
			}
		};
		ThreadContent.set(servletRequest, wrappedResponse);
		filterChain.doFilter(servletRequest, wrappedResponse);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info(getClass() + " init");
	}
}
