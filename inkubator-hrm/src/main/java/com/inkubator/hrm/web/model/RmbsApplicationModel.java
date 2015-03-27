package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.inkubator.hrm.entity.EmpData;

/**
 *
 * @author rizkykojek
 */
public class RmbsApplicationModel implements Serializable {
	
	private Long id;
	private String code;
	private EmpData empData;
	private Long rmbsTypeId;
	private Long currencyId;
	private String purpose;
	private Date applicationDate;
	private Integer applicationStatus;
	private String description;
	private BigDecimal nominal;
	private String reimbursementFileName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public EmpData getEmpData() {
		return empData;
	}
	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}
	public Long getRmbsTypeId() {
		return rmbsTypeId;
	}
	public void setRmbsTypeId(Long rmbsTypeId) {
		this.rmbsTypeId = rmbsTypeId;
	}
	public Long getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public Integer getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(Integer applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getNominal() {
		return nominal;
	}
	public void setNominal(BigDecimal nominal) {
		this.nominal = nominal;
	}
	public String getReimbursementFileName() {
		return reimbursementFileName;
	}
	public void setReimbursementFileName(String reimbursementFileName) {
		this.reimbursementFileName = reimbursementFileName;
	}
	
}
