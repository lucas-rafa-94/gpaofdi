package com.ofdi.soap.main.utils.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



@Component
@ConfigurationProperties("folder")
public class YAMLConfigFolder {

    private String path;
    private String csvChefe;
    private String csvVendedor;
    private String zipChefe;
    private String zipVendedor;
    private String zipGoalChefe;
    private String zipGoalVendedor;

    public YAMLConfigFolder(String path, String csvChefe, String csvVendedor, String zipChefe, String zipVendedor, String zipGoalChefe, String zipGoalVendedor) {
        this.path = path;
        this.csvChefe = csvChefe;
        this.csvVendedor = csvVendedor;
        this.zipChefe = zipChefe;
        this.zipVendedor = zipVendedor;
        this.zipGoalChefe = zipGoalChefe;
        this.zipGoalVendedor = zipGoalVendedor;
    }

    public YAMLConfigFolder() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCsvChefe() {
        return csvChefe;
    }

    public void setCsvChefe(String csvChefe) {
        this.csvChefe = csvChefe;
    }

    public String getCsvVendedor() {
        return csvVendedor;
    }

    public void setCsvVendedor(String csvVendedor) {
        this.csvVendedor = csvVendedor;
    }

    public String getZipChefe() {
        return zipChefe;
    }

    public void setZipChefe(String zipChefe) {
        this.zipChefe = zipChefe;
    }

    public String getZipVendedor() {
        return zipVendedor;
    }

    public void setZipVendedor(String zipVendedor) {
        this.zipVendedor = zipVendedor;
    }

    public String getZipGoalChefe() {
        return zipGoalChefe;
    }

    public void setZipGoalChefe(String zipGoalChefe) {
        this.zipGoalChefe = zipGoalChefe;
    }

    public String getZipGoalVendedor() {
        return zipGoalVendedor;
    }

    public void setZipGoalVendedor(String zipGoalVendedor) {
        this.zipGoalVendedor = zipGoalVendedor;
    }
}
