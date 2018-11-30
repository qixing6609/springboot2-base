package com.ts.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by shan on 16/11/02.
 */
@ConfigurationProperties()
@PropertySource(value = {"classpath:config/wechat-config.properties"})
//@ImportResource({"classpath:config/wechat-config.properties"}) 
public class WeiXinConfig {
	
    private String weChatUrl;
    private String weChatAppId;
    private String weChatPartnerId;
    private String weChatMchId;
    private String weChatKey;
    private String weChatNotifyUrl;

    public String getWeChatNotifyUrl() {
        return weChatNotifyUrl;
    }

    public void setWeChatNotifyUrl(String weChatNotifyUrl) {
        this.weChatNotifyUrl = weChatNotifyUrl;
    }

    public String getWeChatKey() {
        return weChatKey;
    }

    public void setWeChatKey(String weChatKey) {
        this.weChatKey = weChatKey;
    }

    public String getWeChatUrl() {
        return weChatUrl;
    }

    public void setWeChatUrl(String weChatUrl) {
        this.weChatUrl = weChatUrl;
    }

    public String getWeChatAppId() {
        return weChatAppId;
    }

    public void setWeChatAppId(String weChatAppId) {
        this.weChatAppId = weChatAppId;
    }

    public String getWeChatPartnerId() {
        return weChatPartnerId;
    }

    public void setWeChatPartnerId(String weChatPartnerId) {
        this.weChatPartnerId = weChatPartnerId;
    }

    public String getWeChatMchId() {
        return weChatMchId;
    }

    public void setWeChatMchId(String weChatMchId) {
        this.weChatMchId = weChatMchId;
    }

}
