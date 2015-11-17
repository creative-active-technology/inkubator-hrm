package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Deni
 */
public class SystemLetterReferenceModel implements Serializable {

    private Long id;
    private String letterSumary;
    private String description;
    private String name;
    private Date effectiveDate;
    private byte[] uploadData;
    private String code;
    private String fileUploadName;
    private Boolean isActive;
    private UploadedFile fileUpload;
    private String content;
    private Integer groupReference;
    private Integer typeContent;

    public UploadedFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(UploadedFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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

    public String getFileUploadName() {
        return fileUploadName;
    }

    public void setFileUploadName(String fileUploadName) {
        this.fileUploadName = fileUploadName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getGroupReference() {
		return groupReference;
	}

	public void setGroupReference(Integer groupReference) {
		this.groupReference = groupReference;
	}

	public Integer getTypeContent() {
		return typeContent;
	}

	public void setTypeContent(Integer typeContent) {
		this.typeContent = typeContent;
	}
    
    
}
