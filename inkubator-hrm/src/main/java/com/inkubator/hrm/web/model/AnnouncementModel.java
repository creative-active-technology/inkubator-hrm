package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.UnitKerja;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    private Integer viewModel;
    private String subject;
    private Boolean isAlreadyShow;
    private String updatedBy;
    private Integer version;
    private Long id;
    private Date periodeEndDate;
    private Date periodeStartDate;
    private Boolean emailIsSend;
    private Integer timeModel;
    private Boolean internetPublish;
    private Date updatedOn;
    private List<Company> dropDownCompany;
    private Long companyId;
    private DualListModel<EmployeeType> dualListEmployeeType;
    private DualListModel<GolonganJabatan> dualListGolonganJabatan;
    private DualListModel<UnitKerja> dualListUnitKerja;
    private String attachmentFileName;
    private UploadedFile attachmentFile;
	public String getAnnouncementContent() {
		return announcementContent;
	}
	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
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
	public Boolean getIsAlreadyShow() {
		return isAlreadyShow;
	}
	public void setIsAlreadyShow(Boolean isAlreadyShow) {
		this.isAlreadyShow = isAlreadyShow;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
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
	public Boolean getEmailIsSend() {
		return emailIsSend;
	}
	public void setEmailIsSend(Boolean emailIsSend) {
		this.emailIsSend = emailIsSend;
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
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public List<Company> getDropDownCompany() {
		return dropDownCompany;
	}
	public void setDropDownCompany(List<Company> dropDownCompany) {
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
	public void setDualListEmployeeType(
			DualListModel<EmployeeType> dualListEmployeeType) {
		this.dualListEmployeeType = dualListEmployeeType;
	}
	public DualListModel<GolonganJabatan> getDualListGolonganJabatan() {
		return dualListGolonganJabatan;
	}
	public void setDualListGolonganJabatan(
			DualListModel<GolonganJabatan> dualListGolonganJabatan) {
		this.dualListGolonganJabatan = dualListGolonganJabatan;
	}
	public DualListModel<UnitKerja> getDualListUnitKerja() {
		return dualListUnitKerja;
	}
	public void setDualListUnitKerja(DualListModel<UnitKerja> dualListUnitKerja) {
		this.dualListUnitKerja = dualListUnitKerja;
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
