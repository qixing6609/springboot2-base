package com.ts.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;


public class HttpUtil {
	public static String basePath() throws MalformedURLException,
			URISyntaxException {
		String protocol = ThreadContent.request().getProtocol();
		if(StringUtil.isEmpty(protocol)){
			protocol = "https";
		}else if(protocol.length()>4){
			protocol = protocol.substring(0, 4).toLowerCase().trim();
		}
		String basePath = protocol + "://" + ThreadContent.request().getServerName()
				+ ":" + ThreadContent.request().getServerPort()
				+ ThreadContent.request().getContextPath();
		URL urlObj = new URL(basePath);
		if (urlObj.getPort() == urlObj.getDefaultPort()) {
			urlObj = new URL(urlObj.getProtocol(), urlObj.getHost(), -1,
					urlObj.getPath());
		}
		return urlObj.toURI().toString();
	}
	
	/**
	 * 获取http body json数据<BR>
	 * 方法名：JsonReq<BR>
	 * 创建人：liuguangming <BR>
	 * 时间：2018年8月4日-下午5:51:49 <BR>
	 * @param request
	 * @return String<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static String getJsonOfBody(HttpServletRequest request) {
		BufferedReader br;
		StringBuilder sb = null;
		String reqBody = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					request.getInputStream()));
			String line = null;
			sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			reqBody = URLDecoder.decode(sb.toString(), "UTF-8");
			if(!StringUtil.isEmpty(reqBody)){
				reqBody = reqBody.substring(reqBody.indexOf("{"));
			}
			return reqBody;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}