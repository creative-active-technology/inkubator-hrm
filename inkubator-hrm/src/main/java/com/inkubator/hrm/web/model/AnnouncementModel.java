package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.UnitKerja;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

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
    private Map<String, Long> dropDownCompany;
    private Long companyId;
    private DualListModel<EmployeeType> dualListEmployeeType;
    private DualListModel<GolonganJabatan> dualListGolonganJabatan;
    private DualListModel<UnitKerja> dualListUnitKerja;
    private Boolean isUpdate;
    private String fotoFileName;
    private UploadedFile attachmentFile;

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

    public Map<String, Long> getDropDownCompany() {
        return dropDownCompany;
    }

    public void setDropDownCompany(Map<String, Long> dropDownCompany) {
        this.dropDownCompany = dropDownCompany;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public DualListModel<EmployeeType> getDualListEmployeeType() {
        return dualListEmployeeType;
    }

    public void setDualListEmployeeType(DualListModel<EmployeeType> dualListEmployeeType) {
        this.dualListEmployeeType = dualListEmployeeType;
    }

    public DualListModel<GolonganJabatan> getDualListGolonganJabatan() {
        return dualListGolonganJabatan;
    }

    public void setDualListGolonganJabatan(DualListModel<GolonganJabatan> dualListGolonganJabatan) {
        this.dualListGolonganJabatan = dualListGolonganJabatan;
    }

    public DualListModel<UnitKerja> getDualListUnitKerja() {
        return dualListUnitKerja;
    }

    public void setDualListUnitKerja(DualListModel<UnitKerja> dualListUnitKerja) {
        this.dualListUnitKerja = dualListUnitKerja;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getFotoFileName() {
        return fotoFileName;
    }

    public void setFotoFileName(String fotoFileName) {
        this.fotoFileName = fotoFileName;
    }

    public UploadedFile getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(UploadedFile attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    
    
}
