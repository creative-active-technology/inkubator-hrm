package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author WebGenX
 */
public class AnnouncementModel implements Serializable {

    private String announcementContent;
    private String attachmentPath;
    private int viewModel;
    private String subject;
    private Boolean isAlreadyShow;
    private String updatedBy;
    private Integer version;
    private Long id;
    private Date periodeEndDate;
    private Date periodeStartDate;
    private Boolean emailIsSend;
    private Integer timeModel;
    private boolean internetPublish;
    private Date updatedOn;

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public int getViewModel() {
        return viewModel;
    }

    public String getSubject() {
        return subject;
    }

    public Boolean getIsAlreadyShow() {
        return isAlreadyShow;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public Long getId() {
        return id;
    }

    public Date getPeriodeEndDate() {
        return periodeEndDate;
    }

    public Date getPeriodeStartDate() {
        return periodeStartDate;
    }

    public Boolean getEmailIsSend() {
        return emailIsSend;
    }

    public Integer getTimeModel() {
        return timeModel;
    }

    public boolean getInternetPublish() {
        return internetPublish;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public void setViewModel(int viewModel) {
        this.viewModel = viewModel;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setIsAlreadyShow(Boolean isAlreadyShow) {
        this.isAlreadyShow = isAlreadyShow;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPeriodeEndDate(Date periodeEndDate) {
        this.periodeEndDate = periodeEndDate;
    }

    public void setPeriodeStartDate(Date periodeStartDate) {
        this.periodeStartDate = periodeStartDate;
    }

    public void setEmailIsSend(Boolean emailIsSend) {
        this.emailIsSend = emailIsSend;
    }

    public void setTimeModel(Integer timeModel) {
        this.timeModel = timeModel;
    }

    public void setInternetPublish(boolean internetPublish) {
        this.internetPublish = internetPublish;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
