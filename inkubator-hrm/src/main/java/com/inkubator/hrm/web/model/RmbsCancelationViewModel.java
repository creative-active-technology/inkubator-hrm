package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public class RmbsCancelationViewModel implements Serializable{

	private BigInteger cancelationId;
	private String cancelationCode;
	private String applicationCode;
	private String rmbsTypeName;
	private BigDecimal nominal;
	private Date applicationDate;
	private Date cancelationDate;
	private String reason;
	private String employeeName;
	
	public BigInteger getCancelationId() {
		return cancelationId;
	}
	public void setCancelationId(BigInteger cancelationId) {
		this.cancelationId = cancelationId;
	}
	public String getCancelationCode() {
		return cancelationCode;
	}
	public void setCancelationCode(String cancelationCode) {
		this.cancelationCode = cancelationCode;
	}
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getRmbsTypeName() {
		return rmbsTypeName;
	}
	public void setRmbsTypeName(String rmbsTypeName) {
		this.rmbsTypeName = rmbsTypeName;
	}
	public BigDecimal getNominal() {
		return nominal;
	}
	public void setNominal(BigDecimal nominal) {
		this.nominal = nominal;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public Date getCancelationDate() {
		return cancelationDate;
	}
	public void setCancelationDate(Date cancelationDate) {
		this.cancelationDate = cancelationDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}	
	
}
