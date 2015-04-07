/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.util.Date;
import java.util.List;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Deni
 */
public class AnnouncementModelJson {
    private Long companyId;
    private List<Long> listEmployeeType;
    private List<Long> listGolonganJabatan;
    private List<Long> listUnitKerja;
    private String announcementSubject;
    private String announcementContent;
    private String attachmentFileName;
    private Integer publishTimeModel;
    private Date periodStartDate;
    private Date periodEndDate;
    private Integer viewModel;
    private Boolean isInternetPublish;
    private UploadedFile attachmentFile;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<Long> getListEmployeeType() {
        return listEmployeeType;
    }

    public void setListEmployeeType(List<Long> listEmployeeType) {
        this.listEmployeeType = listEmployeeType;
    }

    public List<Long> getListGolonganJabatan() {
        return listGolonganJabatan;
    }

    public void setListGolonganJabatan(List<Long> listGolonganJabatan) {
        this.listGolonganJabatan = listGolonganJabatan;
    }

    public List<Long> getListUnitKerja() {
        return listUnitKerja;
    }

    public void setListUnitKerja(List<Long> listUnitKerja) {
        this.listUnitKerja = listUnitKerja;
    }

    public String getAnnouncementSubject() {
        return announcementSubject;
    }

    public void setAnnouncementSubject(String announcementSubject) {
        this.announcementSubject = announcementSubject;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    public Integer getPublishTimeModel() {
        return publishTimeModel;
    }

    public void setPublishTimeModel(Integer publishTimeModel) {
        this.publishTimeModel = publishTimeModel;
    }

    public Date getPeriodStartDate() {
        return periodStartDate;
    }

    public void setPeriodStartDate(Date periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public Date getPeriodEndDate() {
        return periodEndDate;
    }

    public void setPeriodEndDate(Date periodEndDate) {
        this.periodEndDate = periodEndDate;
    }

    public Integer getViewModel() {
        return viewModel;
    }

    public void setViewModel(Integer viewModel) {
        this.viewModel = viewModel;
    }

    public Boolean getIsInternetPublish() {
        return isInternetPublish;
    }

    public void setIsInternetPublish(Boolean isInternetPublish) {
        this.isInternetPublish = isInternetPublish;
    }

    public UploadedFile getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(UploadedFile attachmentFile) {
        this.attachmentFile = attachmentFile;
    }
    
    
}
