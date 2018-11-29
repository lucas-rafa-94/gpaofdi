package com.ofdi.soap.main.utils.configs;


import com.ofdi.soap.models.yaml.ConfigUtilsOthersServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("uploadfiletoucm")
public class YAMLConfigUploadToUcm {

    private String defaultUri;
    private ConfigUtilsOthersServices.ServiceCustom upload;

    public YAMLConfigUploadToUcm(String defaultUri, ConfigUtilsOthersServices.ServiceCustom upload) {
        this.defaultUri = defaultUri;
        this.upload = upload;
    }

    public YAMLConfigUploadToUcm() {
    }

    public String getDefaultUri() {
        return defaultUri;
    }

    public void setDefaultUri(String defaultUri) {
        this.defaultUri = defaultUri;
    }

    public ConfigUtilsOthersServices.ServiceCustom getUpload() {
        return upload;
    }

    public void setUpload(ConfigUtilsOthersServices.ServiceCustom upload) {
        this.upload = upload;
    }
}
