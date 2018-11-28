package com.ofdi.soap.main.utils.configs;

import com.ofdi.soap.models.yaml.ConfigUtilsOthersServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mktImport")
public class YAMLConfigMktImport {

    private String defaultUri;
    private ConfigUtilsOthersServices.ServiceCustom submitImportActivity;
    private ConfigUtilsOthersServices.ServiceCustom getImportActivityStatus;

    public YAMLConfigMktImport(String defaultUri, ConfigUtilsOthersServices.ServiceCustom submitImportActivity, ConfigUtilsOthersServices.ServiceCustom getImportActivityStatus) {
        this.defaultUri = defaultUri;
        this.submitImportActivity = submitImportActivity;
        this.getImportActivityStatus = getImportActivityStatus;
    }

    public YAMLConfigMktImport() { }

    public String getDefaultUri() {
        return defaultUri;
    }

    public void setDefaultUri(String defaultUri) {
        this.defaultUri = defaultUri;
    }

    public ConfigUtilsOthersServices.ServiceCustom getSubmitImportActivity() {
        return submitImportActivity;
    }

    public void setSubmitImportActivity(ConfigUtilsOthersServices.ServiceCustom submitImportActivity) {
        this.submitImportActivity = submitImportActivity;
    }

    public ConfigUtilsOthersServices.ServiceCustom getGetImportActivityStatus() {
        return getImportActivityStatus;
    }

    public void setGetImportActivityStatus(ConfigUtilsOthersServices.ServiceCustom getImportActivityStatus) {
        this.getImportActivityStatus = getImportActivityStatus;
    }
}
