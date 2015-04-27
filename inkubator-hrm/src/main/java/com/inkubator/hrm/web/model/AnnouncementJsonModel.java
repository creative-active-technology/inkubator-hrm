package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public class AnnouncementJsonModel implements Serializable {

	private Boolean isGeneratingAnnouncementLog;
	private Boolean isSendingAllEmailNotSent;
	private Long announcementId;
	private Integer viewModel;
	private Date planExecutionDate;
	
	public Boolean getIsGeneratingAnnouncementLog() {
		return isGeneratingAnnouncementLog;
	}
	public void setIsGeneratingAnnouncementLog(Boolean isGeneratingAnnouncementLog) {
		this.isGeneratingAnnouncementLog = isGeneratingAnnouncementLog;
	}
	public Boolean getIsSendingAllEmailNotSent() {
		return isSendingAllEmailNotSent;
	}
	public void setIsSendingAllEmailNotSent(Boolean isSendingAllEmailNotSent) {
		this.isSendingAllEmailNotSent = isSendingAllEmailNotSent;
	}
	public Long getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
	}
	public Integer getViewModel() {
		return viewModel;
	}
	public void setViewModel(Integer viewModel) {
		this.viewModel = viewModel;
	}
	public Date getPlanExecutionDate() {
		return planExecutionDate;
	}
	public void setPlanExecutionDate(Date planExecutionDate) {
		this.planExecutionDate = planExecutionDate;
	}
	
}
