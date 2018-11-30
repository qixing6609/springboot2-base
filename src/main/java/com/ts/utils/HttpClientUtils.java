package com.ts.utils;

import com.ts.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by shan on 16/7/19.
 */
@Component
public class HttpClientUtils implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    private static CloseableHttpClient httpClient = null;

    @Override
    public void run(String... strings) throws Exception {
        PoolingHttpClientConnectionManager manager =
                new PoolingHttpClientConnectionManager();
        manager.setDefaultMaxPerRoute(20);
        manager.setMaxTotal(100);
        httpClient = HttpClients.custom().setConnectionManager(manager).build();

    }

    public static String get(String url) {
        if (httpClient == null) {
            throw new BusinessException("HttpClient init fail");
        }
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .build();
        httpGet.setConfig(requestConfig);
        try {
            return httpClient.execute(httpGet, new BasicResponseHandler());
        } catch (IOException e) {
            logger.error("httpClient post execute error",e);
            return "";
        } finally {
            httpGet.releaseConnection();
        }
    }

    public static String post(String url, Map<String, String> params) {
        if (httpClient == null) {
            throw new BusinessException("HttpClient init fail");
        }
        List<NameValuePair> param = new ArrayList<>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            param.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .build();
        httpPost.setConfig(requestConfig);
        try {
            return httpClient.execute(httpPost, new BasicResponseHandler());
        } catch (IOException e) {
            logger.error("httpClient post execute error", e);
            return "";
        } finally {
            httpPost.releaseConnection();
        }
    }

    /**
     * 获取所有request请求参数key-value
     *
     * @param request
     * @return
     */
    public static Map<String, String> getRequestParams(ServletRequest request) {

        Map<String, String> params = new HashMap<>();
        if (null != request) {
            Set<String> paramsKey = request.getParameterMap().keySet();
            for (String key : paramsKey) {
                params.put(key, request.getParameter(key));
            }
        }
        return params;
    }

    /**
     * 获取ip
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("PRoxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (null == ip) {
            ip = "";
        }
        if (StringUtils.isNotEmpty(ip)) {
            String[] ipArr = ip.split(",");
            if (ipArr.length > 1) {
                ip = ipArr[0];
            }
        }
        return ip;
    }

}
