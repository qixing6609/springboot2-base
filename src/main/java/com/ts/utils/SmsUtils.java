package com.ts.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shan on 16/7/19.
 */
public class SmsUtils {
	private static final Logger logger = LoggerFactory.getLogger(SmsUtils.class);
    private static final String SMS_URL = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=%s&pwd=%s&mobile=%s&content=%s&ext=&stime=&rrid=&msgfmt=";
    private static final String ACCOUNT = "SDK-WSS-010-29353";
    private static final String PASSWORD = "6e17c63Fwd";
    
    public static boolean sendMsg(String mobile, String message) {
        String password = DigestUtils.md5Hex(ACCOUNT + PASSWORD).toUpperCase();
        String url = String.format(SMS_URL, ACCOUNT, password, mobile, message);
        String response = HttpClientUtils.get(url);
        try {
            Document doc = DocumentHelper.parseText(response);
            Element root = doc.getRootElement();
            String success = root.getStringValue();
            if (Long.parseLong(success) < 0) {
                logger.error("短信验证码发送失败,失败原因:" + success);
                return false;
            }
        } catch (DocumentException e) {
            logger.error("短信验证码发送失败,失败原因:", e);
            return false;
        }
        return true;
    }
    
	
}
