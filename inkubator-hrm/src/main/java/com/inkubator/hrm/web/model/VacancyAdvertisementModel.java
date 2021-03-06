package com.inkubator.hrm.web.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inkubator.hrm.entity.ApprovalActivity;

/**
*
* @author rizkykojek
*/
public class VacancyAdvertisementModel implements java.io.Serializable {

	//for approval(revision) purpose
	private Boolean isRevised;
	private ApprovalActivity currentActivity;
    private ApprovalActivity askingRevisedActivity;
	
    //data model
    private Long id;
    private String vacancyAdvCode;
    private Date effectiveDate;
    private Long advertisementMediaId;
    private String advertisementMediaName;
    private String advertisementMediaAddress;
    private String advertisementMediaPhone;
    private String advertisementMediaContact;
    private List<VacancyAdvertisementDetailModel> listAdvertisementDetail =  new ArrayList<VacancyAdvertisementDetailModel>();
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVacancyAdvCode() {
		return vacancyAdvCode;
	}
	public void setVacancyAdvCode(String vacancyAdvCode) {
		this.vacancyAdvCode = vacancyAdvCode;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Long getAdvertisementMediaId() {
		return advertisementMediaId;
	}
	public void setAdvertisementMediaId(Long advertisementMediaId) {
		this.advertisementMediaId = advertisementMediaId;
	}	
	public String getAdvertisementMediaName() {
		return advertisementMediaName;
	}
	public void setAdvertisementMediaName(String advertisementMediaName) {
		this.advertisementMediaName = advertisementMediaName;
	}
	public String getAdvertisementMediaAddress() {
		return advertisementMediaAddress;
	}
	public void setAdvertisementMediaAddress(String advertisementMediaAddress) {
		this.advertisementMediaAddress = advertisementMediaAddress;
	}
	public String getAdvertisementMediaPhone() {
		return advertisementMediaPhone;
	}
	public void setAdvertisementMediaPhone(String advertisementMediaPhone) {
		this.advertisementMediaPhone = advertisementMediaPhone;
	}
	public String getAdvertisementMediaContact() {
		return advertisementMediaContact;
	}
	public void setAdvertisementMediaContact(String advertisementMediaContact) {
		this.advertisementMediaContact = advertisementMediaContact;
	}
	public List<VacancyAdvertisementDetailModel> getListAdvertisementDetail() {
		return listAdvertisementDetail;
	}
	public void setListAdvertisementDetail(List<VacancyAdvertisementDetailModel> listAdvertisementDetail) {
		this.listAdvertisementDetail = listAdvertisementDetail;
	}
	public Boolean getIsRevised() {
		return isRevised;
	}
	public void setIsRevised(Boolean isRevised) {
		this.isRevised = isRevised;
	}
	public ApprovalActivity getCurrentActivity() {
		return currentActivity;
	}
	public void setCurrentActivity(ApprovalActivity currentActivity) {
		this.currentActivity = currentActivity;
	}
	public ApprovalActivity getAskingRevisedActivity() {
		return askingRevisedActivity;
	}
	public void setAskingRevisedActivity(ApprovalActivity askingRevisedActivity) {
		this.askingRevisedActivity = askingRevisedActivity;
	}
	
	
}
