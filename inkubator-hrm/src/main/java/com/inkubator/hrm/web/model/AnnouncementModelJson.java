/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.util.ArrayList;
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
    private List<String> listEmpTypeName;
    private List<String> listGolJabName;
    private List<String> listUnitKerjaName;
    private String announcementSubject;
    private String announcementContent;
    private String attachmentFileName;
    private Integer publishTimeModel;
    private Date periodStartDate;
    private Date periodEndDate;
    private Integer viewModel;
    private Boolean isInternetPublish;
    private UploadedFile attachmentFile;
    private String nomor;
    private Date createdOn;
    private String createdBy;

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

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<String> getListEmpTypeName() {
        return listEmpTypeName;
    }

    public void setListEmpTypeName(List<String> listEmpTypeName) {
        this.listEmpTypeName = listEmpTypeName;
    }

    public List<String> getListGolJabName() {
        return listGolJabName;
    }

    public void setListGolJabName(List<String> listGolJabName) {
        this.listGolJabName = listGolJabName;
    }

    public List<String> getListUnitKerjaName() {
        return listUnitKerjaName;
    }

    public void setListUnitKerjaName(List<String> listUnitKerjaName) {
        this.listUnitKerjaName = listUnitKerjaName;
    }

    
}
