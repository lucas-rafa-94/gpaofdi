package com.ofdi.soap.main.utils.configs;

import com.ofdi.soap.models.yaml.ConfigUtilsClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("client")
public class YAMLConfigClient {

    private String defaultUriIntegrationService;
    private String defaultUriMktImport;
    private ConfigUtilsClient.User user = new ConfigUtilsClient.User();

    public YAMLConfigClient(String defaultUriIntegrationService, String defaultUriMktImport, ConfigUtilsClient.User user) {
        this.defaultUriIntegrationService = defaultUriIntegrationService;
        this.defaultUriMktImport = defaultUriMktImport;
        this.user = user;
    }

    public YAMLConfigClient() { }

    public String getDefaultUriIntegrationService() {
        return defaultUriIntegrationService;
    }

    public void setDefaultUriIntegrationService(String defaultUriIntegrationService) {
        this.defaultUriIntegrationService = defaultUriIntegrationService;
    }

    public String getDefaultUriMktImport() {
        return defaultUriMktImport;
    }

    public void setDefaultUriMktImport(String defaultUriMktImport) {
        this.defaultUriMktImport = defaultUriMktImport;
    }

    public ConfigUtilsClient.User getUser() {
        return user;
    }

    public void setUser(ConfigUtilsClient.User user) {
        this.user = user;
    }
}
