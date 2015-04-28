package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author WebGenX
 */
public class SystemLetterReferenceModel implements Serializable {

    private Date createdOn;
    private Long id;
    private String createdBy;
    private String letterSumary;
    private String description;
    private String name;
    private byte[] uploadData;
    private String code;
    private Date updatedOn;
    private String updatedBy;
    private Integer version;

    public Date getCreatedOn() {
        return createdOn;
    }

    public Long getId() {
        return id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLetterSumary() {
        return letterSumary;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public byte[] getUploadData() {
        return uploadData;
    }

    public String getCode() {
        return code;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setLetterSumary(String letterSumary) {
        this.letterSumary = letterSumary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUploadData(byte[] uploadData) {
        this.uploadData = uploadData;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
