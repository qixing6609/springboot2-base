package com.ts.utils;

import com.ts.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class WinXinUtils {
    private static final Logger logger = LoggerFactory.getLogger(WinXinUtils.class);
    private static String charset = "UTF-8";
    private WinXinUtils() {
    }

    /**
     * 随机16为数值
     *
     * @return
     */
    public static String buildRandom() {
        String currTime = DateUtils.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < 4; i++) {
            num = num * 10;
        }
        return (int) (random * num) + strTime;
    }


    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。 sign
     */
    public static String createSign(Map<String, Object> map, String partnerkey) {
        SortedMap<String, String> packageParams = new TreeMap<>();
        for (Map.Entry<String, Object> m : map.entrySet()) {
            packageParams.put(m.getKey(), m.getValue().toString());
        }
        StringBuffer sb = new StringBuffer();
        Set<?> es = packageParams.entrySet();
        Iterator<?> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!StringUtils.isEmpty(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + partnerkey);

        return MD5Util.mD5Encode(sb.toString(), charset).toUpperCase();
    }


    // 无需安全证书
    public static String unifiedorder(String url, String data) throws IOException {
        CloseableHttpClient httpclient = HttpClients.custom().build();
        try {
            HttpPost httpost = new HttpPost(url); // 设置响应头信息
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = toStringInfo(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    // 解析xml报文
    public static Element xmlToElement(String sbxml) {
        // 对xml进行解析
        StringReader read = new StringReader(sbxml);
        InputSource source = new InputSource(read);
        SAXBuilder sb = new SAXBuilder();
        try {
            Document doc = sb.build(source);
            // 获取post来的 xml参数 在返给微信时需要 回传
            return  doc.getRootElement();
        } catch (JDOMException e) {
            logger.info("解析xml报文错误", e);
        } catch (IOException e) {
            throw new BusinessException(e);
        }
        return null;
    }

    private static String toStringInfo(HttpEntity entity, String defaultCharset) throws IOException {
        final InputStream instream = entity.getContent();
        if (instream == null) {
            return null;
        }
        try {
            Args.check(entity.getContentLength() <= Integer.MAX_VALUE,
                    "HTTP entity too large to be buffered in memory");
            int i = (int) entity.getContentLength();
            if (i < 0) {
                i = 4096;
            }
            Charset charset = Charset.forName(defaultCharset);
            if (charset == null) {
                charset = HTTP.DEF_CONTENT_CHARSET;
            }
            final Reader reader = new InputStreamReader(instream, charset);
            final CharArrayBuffer buffer = new CharArrayBuffer(i);
            final char[] tmp = new char[1024];
            int l;
            while ((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            return buffer.toString();
        } catch (Exception e) {
            throw new BusinessException(e);
        } finally {
            instream.close();
        }
    }

    public static String createPayXML(Map<String, Object> map) {
        String xml = "<xml>";
        Set<String> set = map.keySet();
        Iterator<String> i = set.iterator();
        while (i.hasNext()) {
            String str = i.next();
            xml += "<" + str + ">" + map.get(str) + "</" + str + ">";
        }
        xml += "</xml>";
        return xml;
    }

}
