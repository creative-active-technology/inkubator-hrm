package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author WebGenX
 */
public class RecruitHireApplyModel implements Serializable {
    
    private Long recruitHireApplyId;
    private Long salaryMax;
    private String reason;
    private Date proposeDate;
    private String reqHireCode;
    private Integer ageMax;
    private Integer yearExperience;
    private String maritalStatus;
    private Double gpaMin;
    private Double gpaMax;
    private String updatedBy;
    private Integer version;
//private Currency currency;
    private Date createdOn;
    private Long id;
//private Jabatan jabatan;
    private Integer ageMin;
    private Date efectiveDate;
    private String createdBy;
    private int candidateCountRequest;
    private String gender;
    private Long salaryMin;
    private Long recruitMppId;
    private Long recruitMppPeriodId;
    private Long currencyId;
    private Long empStatus;
    private Long jabatanId;
    private Long actual;
    private Long mpp;   
    private EmpData empDataApplier;     
    private Date updatedOn;

    public Long getSalaryMax() {
        return salaryMax;
    }

    public String getReason() {
        return reason;
    }

    public Date getProposeDate() {
        return proposeDate;
    }

    public String getReqHireCode() {
        return reqHireCode;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public Double getGpaMin() {
        return gpaMin;
    }

    public Double getGpaMax() {
        return gpaMax;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Integer getVersion() {
        return version;
    }

//    public Currency getCurrency() {
//        return currency;
//    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Long getId() {
        return id;
    }

//    public Jabatan getJabatan() {
//        return jabatan;
//    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public Date getEfectiveDate() {
        return efectiveDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public int getCandidateCountRequest() {
        return candidateCountRequest;
    }

    public String getGender() {
        return gender;
    }

    public Long getSalaryMin() {
        return salaryMin;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setSalaryMax(Long salaryMax) {
        this.salaryMax = salaryMax;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setProposeDate(Date proposeDate) {
        this.proposeDate = proposeDate;
    }

    public void setReqHireCode(String reqHireCode) {
        this.reqHireCode = reqHireCode;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setGpaMin(Double gpaMin) {
        this.gpaMin = gpaMin;
    }

    public void setGpaMax(Double gpaMax) {
        this.gpaMax = gpaMax;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

//    public void setCurrency(Currency currency) {
//        this.currency = currency;
//    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public void setJabatan(Jabatan jabatan) {
//        this.jabatan = jabatan;
//    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public void setEfectiveDate(Date efectiveDate) {
        this.efectiveDate = efectiveDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCandidateCountRequest(int candidateCountRequest) {
        this.candidateCountRequest = candidateCountRequest;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSalaryMin(Long salaryMin) {
        this.salaryMin = salaryMin;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getRecruitMppId() {
        return recruitMppId;
    }

    public void setRecruitMppId(Long recruitMppId) {
        this.recruitMppId = recruitMppId;
    }

    public Long getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(Long jabatanId) {
        this.jabatanId = jabatanId;
    }

    public Long getActual() {
        return actual;
    }

    public void setActual(Long actual) {
        this.actual = actual;
    }

    public Long getMpp() {
        return mpp;
    }

    public void setMpp(Long mpp) {
        this.mpp = mpp;
    }

    public Long getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(Long empStatus) {
        this.empStatus = empStatus;
    }

    public Integer getYearExperience() {
        return yearExperience;
    }

    public void setYearExperience(Integer yearExperience) {
        this.yearExperience = yearExperience;
    }    

    public Long getRecruitHireApplyId() {
        return recruitHireApplyId;
    }

    public void setRecruitHireApplyId(Long recruitHireApplyId) {
        this.recruitHireApplyId = recruitHireApplyId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }   

    public EmpData getEmpDataApplier() {
        return empDataApplier;
    }

    public void setEmpDataApplier(EmpData empDataApplier) {
        this.empDataApplier = empDataApplier;
    }

	public Long getRecruitMppPeriodId() {
		return recruitMppPeriodId;
	}

	public void setRecruitMppPeriodId(Long recruitMppPeriodId) {
		this.recruitMppPeriodId = recruitMppPeriodId;
	}

    
}
