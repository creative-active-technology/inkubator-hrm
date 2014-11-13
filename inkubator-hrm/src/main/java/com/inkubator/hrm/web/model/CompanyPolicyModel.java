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
    private String attachFilePath;
    private Boolean isBroadcast;
    private String repeatOn;
    private Boolean useAttachment;
    
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
	public String getAttachFilePath() {
		return attachFilePath;
	}
	public void setAttachFilePath(String attachFilePath) {
		this.attachFilePath = attachFilePath;
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
	public Boolean getUseAttachment() {
		return useAttachment;
	}
	public void setUseAttachment(Boolean useAttachment) {
		this.useAttachment = useAttachment;
	}	
}
