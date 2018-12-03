/* OCS - Oracle Consulting Services - Middleware
 *
 * Data de criação 03/12/2018
 * Autor: lucas.dos@oracle.com
 *
 * Projeto: GPA OFDI
 *
 *
 */

package com.ofdi.soap.main.utils.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



@Component
@ConfigurationProperties("folder")
public class YAMLConfigFolder {

    private String path;
    private String csvChefe;
    private String csvVendedor;
    private String zipParticipantChefe;
    private String zipParticipantVendedor;
    private String zipGoalChefe;
    private String zipGoalVendedor;

    public YAMLConfigFolder(String path, String csvChefe, String csvVendedor, String zipParticipantChefe, String zipParticipantVendedor, String zipGoalChefe, String zipGoalVendedor) {
        this.path = path;
        this.csvChefe = csvChefe;
        this.csvVendedor = csvVendedor;
        this.zipParticipantChefe = zipParticipantChefe;
        this.zipParticipantVendedor = zipParticipantVendedor;
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

    public String getZipParticipantChefe() {
        return zipParticipantChefe;
    }

    public void setZipParticipantChefe(String zipParticipantChefe) {
        this.zipParticipantChefe = zipParticipantChefe;
    }

    public String getZipParticipantVendedor() {
        return zipParticipantVendedor;
    }

    public void setZipParticipantVendedor(String zipParticipantVendedor) {
        this.zipParticipantVendedor = zipParticipantVendedor;
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
