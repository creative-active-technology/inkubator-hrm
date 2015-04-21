package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.UploadedFile;

/**
 *
 * @author WebGenX
 */
public class AnnouncementModel implements Serializable {

    private String announcementContent;
    private Integer viewModel;
    private String subject;
    private Long id;
    private Date periodeEndDate;
    private Date periodeStartDate;
    private Integer timeModel;
    private Boolean internetPublish;
    private Long companyId;
    private String attachmentFileName;
    private UploadedFile attachmentFile;
    
	public String getAnnouncementContent() {
		return announcementContent;
	}
	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}
	public Integer getViewModel() {
		return viewModel;
	}
	public void setViewModel(Integer viewModel) {
		this.viewModel = viewModel;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getPeriodeEndDate() {
		return periodeEndDate;
	}
	public void setPeriodeEndDate(Date periodeEndDate) {
		this.periodeEndDate = periodeEndDate;
	}
	public Date getPeriodeStartDate() {
		return periodeStartDate;
	}
	public void setPeriodeStartDate(Date periodeStartDate) {
		this.periodeStartDate = periodeStartDate;
	}
	public Integer getTimeModel() {
		return timeModel;
	}
	public void setTimeModel(Integer timeModel) {
		this.timeModel = timeModel;
	}
	public Boolean getInternetPublish() {
		return internetPublish;
	}
	public void setInternetPublish(Boolean internetPublish) {
		this.internetPublish = internetPublish;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getAttachmentFileName() {
		return attachmentFileName;
	}
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	public UploadedFile getAttachmentFile() {
		return attachmentFile;
	}
	public void setAttachmentFile(UploadedFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}
	
}
