package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class RecruitAgreementNoticeViewModel implements Serializable{
	private BigInteger employeeId;
	private String firstName;
	private String lastName;
	private String jabatanDituju;
	private Integer level;
	private Integer levelDituju;
	private BigInteger bioDataId;
	private String jabatanName;
	private String pangkatName;
	private Date birthOfDate;
	private String lastEducationLevel;
	
	
	public String getLastEducationLevel() {
		return lastEducationLevel;
	}
	public void setLastEducationLevel(String lastEducationLevel) {
		this.lastEducationLevel = lastEducationLevel;
	}
	public Integer getLevelDituju() {
		return levelDituju;
	}
	public void setLevelDituju(Integer levelDituju) {
		this.levelDituju = levelDituju;
	}
	public BigInteger getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(BigInteger employeeId) {
		this.employeeId = employeeId;
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
	public String getJabatanDituju() {
		return jabatanDituju;
	}
	public void setJabatanDituju(String jabatanDituju) {
		this.jabatanDituju = jabatanDituju;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public BigInteger getBioDataId() {
		return bioDataId;
	}
	public void setBioDataId(BigInteger bioDataId) {
		this.bioDataId = bioDataId;
	}
	public String getJabatanName() {
		return jabatanName;
	}
	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}
	public String getPangkatName() {
		return pangkatName;
	}
	public void setPangkatName(String pangkatName) {
		this.pangkatName = pangkatName;
	}
	public Date getBirthOfDate() {
		return birthOfDate;
	}
	public void setBirthOfDate(Date birthOfDate) {
		this.birthOfDate = birthOfDate;
	}
	
	
	
}