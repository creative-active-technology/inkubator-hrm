package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public class CompanyPolicyModel implements Serializable {

	private Long id;
	private String subjectTitle;
    private Date effectiveDate;
    private String contentPolicy;
    private Long departmentId;
    private String departmentName;
    private String attachmentFileName;
    private Boolean isBroadcast;
    private String repeatOn;
    private Boolean isUseAttachment;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getContentPolicy() {
		return contentPolicy;
	}
	public void setContentPolicy(String contentPolicy) {
		this.contentPolicy = contentPolicy;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getAttachmentFileName() {
		return attachmentFileName;
	}
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	public Boolean getIsBroadcast() {
		return isBroadcast;
	}
	public void setIsBroadcast(Boolean isBroadcast) {
		this.isBroadcast = isBroadcast;
	}
	public String getRepeatOn() {
		return repeatOn;
	}
	public void setRepeatOn(String repeatOn) {
		this.repeatOn = repeatOn;
	}
	public Boolean getIsUseAttachment() {
		return isUseAttachment;
	}
	public void setIsUseAttachment(Boolean isUseAttachment) {
		this.isUseAttachment = isUseAttachment;
	}
	
}
