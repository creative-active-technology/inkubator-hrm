package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

public class LoanNewApplicationStatusViewModel implements Serializable{
	private Integer id;
	private String firstName;
	private String lastName;
	private String loanSchemaName;
	private Double nominalPrincipal;
	private String approvalName;
	private String purposeNote;
	private Integer termin;
	private Integer totalTermin;
	private String activityNumber;
	private Integer approvalStatus;
	private String jsonData;
	private String approvalMessage;
	private Integer loanStatus;
	private Date dibursmentDate;
	private String approvalActivityNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoanSchemaName() {
		return loanSchemaName;
	}

	public void setLoanSchemaName(String loanSchemaName) {
		this.loanSchemaName = loanSchemaName;
	}

	public Double getNominalPrincipal() {
		return nominalPrincipal;
	}

	public void setNominalPrincipal(Double nominalPrincipal) {
		this.nominalPrincipal = nominalPrincipal;
	}

	public String getApprovalName() {
		return approvalName;
	}

	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}

	public String getPurposeNote() {
		return purposeNote;
	}

	public void setPurposeNote(String purposeNote) {
		this.purposeNote = purposeNote;
	}

	public Integer getTermin() {
		if(termin == null){
			termin = 0;
		}
		return termin;
	}

	public void setTermin(Integer termin) {
		this.termin = termin;
	}

	public String getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
	}

	public Integer getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Integer getTotalTermin() {
		return totalTermin;
	}

	public void setTotalTermin(Integer totalTermin) {
		this.totalTermin = totalTermin;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getApprovalMessage() {
		return approvalMessage;
	}

	public void setApprovalMessage(String approvalMessage) {
		this.approvalMessage = approvalMessage;
	}

	public Integer getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(Integer loanStatus) {
		this.loanStatus = loanStatus;
	}

	public Date getDibursmentDate() {
		return dibursmentDate;
	}

	public void setDibursmentDate(Date dibursmentDate) {
		this.dibursmentDate = dibursmentDate;
	}

	public String getApprovalActivityNumber() {
		return approvalActivityNumber;
	}

	public void setApprovalActivityNumber(String approvalActivityNumber) {
		this.approvalActivityNumber = approvalActivityNumber;
	}
	
	
}