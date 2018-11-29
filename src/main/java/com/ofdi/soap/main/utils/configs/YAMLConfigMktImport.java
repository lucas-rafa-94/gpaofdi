package com.ofdi.soap.main.utils.configs;

import com.ofdi.soap.models.yaml.ConfigUtilsOthersServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mktimport")
public class YAMLConfigMktImport {

    private String defaultUri;
    private ConfigUtilsOthersServices.ServiceCustom submitImportActivityChefe;
    private ConfigUtilsOthersServices.ServiceCustom submitImportActivityVendedor;
    private ConfigUtilsOthersServices.ServiceCustom getImportActivityStatus;

    public YAMLConfigMktImport(String defaultUri, ConfigUtilsOthersServices.ServiceCustom submitImportActivityChefe, ConfigUtilsOthersServices.ServiceCustom submitImportActivityVendedor, ConfigUtilsOthersServices.ServiceCustom getImportActivityStatus) {
        this.defaultUri = defaultUri;
        this.submitImportActivityChefe = submitImportActivityChefe;
        this.submitImportActivityVendedor = submitImportActivityVendedor;
        this.getImportActivityStatus = getImportActivityStatus;
    }

    public YAMLConfigMktImport() {
    }

    public String getDefaultUri() {
        return defaultUri;
    }

    public void setDefaultUri(String defaultUri) {
        this.defaultUri = defaultUri;
    }

    public ConfigUtilsOthersServices.ServiceCustom getSubmitImportActivityChefe() {
        return submitImportActivityChefe;
    }

    public void setSubmitImportActivityChefe(ConfigUtilsOthersServices.ServiceCustom submitImportActivityChefe) {
        this.submitImportActivityChefe = submitImportActivityChefe;
    }

    public ConfigUtilsOthersServices.ServiceCustom getSubmitImportActivityVendedor() {
        return submitImportActivityVendedor;
    }

    public void setSubmitImportActivityVendedor(ConfigUtilsOthersServices.ServiceCustom submitImportActivityVendedor) {
        this.submitImportActivityVendedor = submitImportActivityVendedor;
    }

    public ConfigUtilsOthersServices.ServiceCustom getGetImportActivityStatus() {
        return getImportActivityStatus;
    }

    public void setGetImportActivityStatus(ConfigUtilsOthersServices.ServiceCustom getImportActivityStatus) {
        this.getImportActivityStatus = getImportActivityStatus;
    }
}
