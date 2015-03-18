package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class RmbsSchemaModel implements Serializable {

	private Long id;
	private String name;
	private String code;
    private String description;
    private String nomorSk;
    private Double maxTotalReimburst;
    private Double maxWithReceiptPerType;
    private Double maxWithoutReceiptPerType;
    private Integer noticeForLimit;
    private Integer submissionDeadline;
    private Boolean isActive;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNomorSk() {
		return nomorSk;
	}
	public void setNomorSk(String nomorSk) {
		this.nomorSk = nomorSk;
	}
	public Double getMaxTotalReimburst() {
		return maxTotalReimburst;
	}
	public void setMaxTotalReimburst(Double maxTotalReimburst) {
		this.maxTotalReimburst = maxTotalReimburst;
	}
	public Double getMaxWithReceiptPerType() {
		return maxWithReceiptPerType;
	}
	public void setMaxWithReceiptPerType(Double maxWithReceiptPerType) {
		this.maxWithReceiptPerType = maxWithReceiptPerType;
	}
	public Double getMaxWithoutReceiptPerType() {
		return maxWithoutReceiptPerType;
	}
	public void setMaxWithoutReceiptPerType(Double maxWithoutReceiptPerType) {
		this.maxWithoutReceiptPerType = maxWithoutReceiptPerType;
	}
	public Integer getNoticeForLimit() {
		return noticeForLimit;
	}
	public void setNoticeForLimit(Integer noticeForLimit) {
		this.noticeForLimit = noticeForLimit;
	}	
	public Integer getSubmissionDeadline() {
		return submissionDeadline;
	}
	public void setSubmissionDeadline(Integer submissionDeadline) {
		this.submissionDeadline = submissionDeadline;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
}
