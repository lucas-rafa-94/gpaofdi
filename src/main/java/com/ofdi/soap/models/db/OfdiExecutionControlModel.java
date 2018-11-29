package com.ofdi.soap.models.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="OFDI_EXECUTION_CONTROL")
public class OfdiExecutionControlModel implements Serializable {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="file_name")
    private String fileName;

    @Column(name="file_path")
    private String filePath;

    @Column(name="username")
    private String username;

    @Column(name="execution_status")
    private String executionStatus;

    @Column(name="step")
    private int step;

    @Column(name="last_update")
    private Date lastUpdate;

    public OfdiExecutionControlModel(String id, String fileName, String filePath, String username, String executionStatus, int step, Date lastUpdate) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.username = username;
        this.executionStatus = executionStatus;
        this.step = step;
        this.lastUpdate = lastUpdate;
    }

    public OfdiExecutionControlModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
