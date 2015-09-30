/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.primefaces.model.UploadedFile;

import com.inkubator.hrm.entity.BioData;

public class RecruitAgreementNoticeModel implements Serializable{
	
    private Long id;
    private Long bioData;
    private String resourceType;
    private String uploadedCv;
    private BigDecimal lastSalary;
    private BigDecimal expectedSalary;
    private String description;
    private String pathUpload;
    private UploadedFile uploadFile;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBioData() {
		return bioData;
	}
	public void setBioData(Long bioData) {
		this.bioData = bioData;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getUploadedCv() {
		return uploadedCv;
	}
	public void setUploadedCv(String uploadedCv) {
		this.uploadedCv = uploadedCv;
	}
	public BigDecimal getLastSalary() {
		return lastSalary;
	}
	public void setLastSalary(BigDecimal lastSalary) {
		this.lastSalary = lastSalary;
	}
	public BigDecimal getExpectedSalary() {
		return expectedSalary;
	}
	public void setExpectedSalary(BigDecimal expectedSalary) {
		this.expectedSalary = expectedSalary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPathUpload() {
		return pathUpload;
	}
	public void setPathUpload(String pathUpload) {
		this.pathUpload = pathUpload;
	}
	public UploadedFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}
    
    
}