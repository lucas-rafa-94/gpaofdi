package com.ofdi.soap.main.utils.configs;

import com.ofdi.soap.models.yaml.ConfigUtilsEss;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("essJobs")
public class YAMLConfigEssJobs {

    private String defaultUri;
    private ConfigUtilsEss.Xmlns xmlns;
    private ConfigUtilsEss.LocalPart localPart;
    private ConfigUtilsEss.Services services;

    public YAMLConfigEssJobs(String defaultUri, ConfigUtilsEss.Xmlns xmlns, ConfigUtilsEss.LocalPart localPart, ConfigUtilsEss.Services services) {
        this.defaultUri = defaultUri;
        this.xmlns = xmlns;
        this.localPart = localPart;
        this.services = services;
    }

    public YAMLConfigEssJobs() { }

    public String getDefaultUri() {
        return defaultUri;
    }

    public void setDefaultUri(String defaultUri) {
        this.defaultUri = defaultUri;
    }

    public ConfigUtilsEss.Xmlns getXmlns() {
        return xmlns;
    }

    public void setXmlns(ConfigUtilsEss.Xmlns xmlns) {
        this.xmlns = xmlns;
    }

    public ConfigUtilsEss.LocalPart getLocalPart() {
        return localPart;
    }

    public void setLocalPart(ConfigUtilsEss.LocalPart localPart) {
        this.localPart = localPart;
    }

    public ConfigUtilsEss.Services getServices() {
        return services;
    }

    public void setServices(ConfigUtilsEss.Services services) {
        this.services = services;
    }
}
