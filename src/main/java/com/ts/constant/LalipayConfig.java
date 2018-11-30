package com.ts.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by shan on 16/11/02.
 */
@ConfigurationProperties()
@PropertySource(value = {"classpath:config/lalipay-config.properties"})
//@ImportResource({"classpath:config/lalipay-config.properties"}) 
public class LalipayConfig {
    private String lAliPayPublicKey;
    private String lAliPayAppId;
    private String lAliPayPrivateKey;
    private String lPublicKey;
    private String lAliNotifyUrl;

    public String getlAliPayPublicKey() {
        return lAliPayPublicKey;
    }

    public void setlAliPayPublicKey(String lAliPayPublicKey) {
        this.lAliPayPublicKey = lAliPayPublicKey;
    }

    public String getlAliPayAppId() {
        return lAliPayAppId;
    }

    public void setlAliPayAppId(String lAliPayAppId) {
        this.lAliPayAppId = lAliPayAppId;
    }

    public String getlAliPayPrivateKey() {
        return lAliPayPrivateKey;
    }

    public void setlAliPayPrivateKey(String lAliPayPrivateKey) {
        this.lAliPayPrivateKey = lAliPayPrivateKey;
    }

    public String getlPublicKey() {
        return lPublicKey;
    }

    public void setlPublicKey(String lPublicKey) {
        this.lPublicKey = lPublicKey;
    }

    public String getlAliNotifyUrl() {
        return lAliNotifyUrl;
    }

    public void setlAliNotifyUrl(String lAliNotifyUrl) {
        this.lAliNotifyUrl = lAliNotifyUrl;
    }
}
