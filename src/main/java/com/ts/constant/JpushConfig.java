package com.ts.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by shan on 16/11/02.
 */
@ConfigurationProperties()
@PropertySource(value = {"classpath:config/jpush-config.properties"})
//@ImportResource({"classpath:config/jpush-config.properties"}) 
public class JpushConfig {
    private String appKey;
    private String masterSecret;
    private String platformAppKey;
    private String platformMasterSecret;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    public String getPlatformAppKey() {
        return platformAppKey;
    }

    public void setPlatformAppKey(String platformAppKey) {
        this.platformAppKey = platformAppKey;
    }

    public String getPlatformMasterSecret() {
        return platformMasterSecret;
    }

    public void setPlatformMasterSecret(String platformMasterSecret) {
        this.platformMasterSecret = platformMasterSecret;
    }
}
