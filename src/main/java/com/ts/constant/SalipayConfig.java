package com.ts.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by shan on 16/11/02.
 */
@ConfigurationProperties()
@PropertySource(value = {"classpath:config/salipay-config.properties"})
//@ImportResource({"classpath:config/salipay-config.properties"}) 
public class SalipayConfig {
    private String sAliPayPublicKey;
    private String sAliPayAppId;
    private String sAliPayPrivateKey;
    private String sPublicKey;
    private String sAliNotifyUrl;

    public String getsAliPayPublicKey() {
        return sAliPayPublicKey;
    }

    public void setsAliPayPublicKey(String sAliPayPublicKey) {
        this.sAliPayPublicKey = sAliPayPublicKey;
    }

    public String getsAliPayAppId() {
        return sAliPayAppId;
    }

    public void setsAliPayAppId(String sAliPayAppId) {
        this.sAliPayAppId = sAliPayAppId;
    }

    public String getsAliPayPrivateKey() {
        return sAliPayPrivateKey;
    }

    public void setsAliPayPrivateKey(String sAliPayPrivateKey) {
        this.sAliPayPrivateKey = sAliPayPrivateKey;
    }

    public String getsPublicKey() {
        return sPublicKey;
    }

    public void setsPublicKey(String sPublicKey) {
        this.sPublicKey = sPublicKey;
    }

    public String getsAliNotifyUrl() {
        return sAliNotifyUrl;
    }

    public void setsAliNotifyUrl(String sAliNotifyUrl) {
        this.sAliNotifyUrl = sAliNotifyUrl;
    }
}
