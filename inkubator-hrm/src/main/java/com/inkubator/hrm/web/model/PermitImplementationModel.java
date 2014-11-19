package com.inkubator.hrm.web.model;

import java.util.Date;

import javax.validation.constraints.Pattern;

import com.inkubator.hrm.entity.EmpData;

/**
 *
 * @author Taufik
 */
public class PermitImplementationModel {

	private Long id;
	private String numberFilling;
	private Long permitId;
	private EmpData empData;
	private Date latestPermitDate;
	private Double remainingPermit;
	private Date startDate;
	private Date endDate;
	private Date fillingDate;
	private String description;
	private Double actualPermitTaken;
        private String uploadFileName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getNumberFilling() {
		return numberFilling;
	}
	public void setNumberFilling(String numberFilling) {
		this.numberFilling = numberFilling;
	}
	public Long getPermitId() {
		return permitId;
	}
	public void setPermitId(Long permitId) {
		this.permitId = permitId;
	}
	public EmpData getEmpData() {
		return empData;
	}
	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}
	public Date getLatestPermitDate() {
		return latestPermitDate;
	}
	public void setLatestPermitDate(Date latestPermitDate) {
		this.latestPermitDate = latestPermitDate;
	}
	public Double getRemainingPermit() {
		return remainingPermit;
	}
	public void setRemainingPermit(Double remainingPermit) {
		this.remainingPermit = remainingPermit;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getFillingDate() {
		return fillingDate;
	}
	public void setFillingDate(Date fillingDate) {
		this.fillingDate = fillingDate;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getActualPermitTaken() {
		return actualPermitTaken;
	}
	public void setActualPermitTaken(Double actualPermitTaken) {
		this.actualPermitTaken = actualPermitTaken;
	}

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
        
        
}
