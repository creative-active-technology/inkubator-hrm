package com.inkubator.hrm.web.model;
import java.util.Date;

/**
*
* @author rizkykojek
*/
public class VacancyAdvertisementDetailModel implements java.io.Serializable {

    private Long id;
    private Integer indexList; //digunakan untuk menandai index berapa dalam list(untuk kasus belum punya ID atao masih belum persist ke DB)
    private Date publishStart;
    private Date publishEnd;
    private Long vacancyAdvertisementId;
    private Long hireApplyId;
    private String hireApplyCode;
    private String jabatanName;
    private Integer staffCount;
    private Double cost;
    private String description;
    private Boolean isUpdate;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getIndexList() {
		return indexList;
	}
	public void setIndexList(Integer indexList) {
		this.indexList = indexList;
	}
	public Date getPublishStart() {
		return publishStart;
	}
	public void setPublishStart(Date publishStart) {
		this.publishStart = publishStart;
	}
	public Date getPublishEnd() {
		return publishEnd;
	}
	public void setPublishEnd(Date publishEnd) {
		this.publishEnd = publishEnd;
	}
	public Long getVacancyAdvertisementId() {
		return vacancyAdvertisementId;
	}
	public void setVacancyAdvertisementId(Long vacancyAdvertisementId) {
		this.vacancyAdvertisementId = vacancyAdvertisementId;
	}
	public Long getHireApplyId() {
		return hireApplyId;
	}
	public void setHireApplyId(Long hireApplyId) {
		this.hireApplyId = hireApplyId;
	}
	public String getHireApplyCode() {
		return hireApplyCode;
	}
	public void setHireApplyCode(String hireApplyCode) {
		this.hireApplyCode = hireApplyCode;
	}
	public String getJabatanName() {
		return jabatanName;
	}
	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}
	public Integer getStaffCount() {
		return staffCount;
	}
	public void setStaffCount(Integer staffCount) {
		this.staffCount = staffCount;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsUpdate() {
		return indexList != null;
	}
	public void setIsUpdate(Boolean isUpdate) {
		
	}
	

}
