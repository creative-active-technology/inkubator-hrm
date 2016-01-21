package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class EmpEliminationViewModel implements Serializable {
	
	private BigInteger careerEmpEliminationId;
    private Long empCareerHistoryId;
    private BigInteger empDataId;
    private BigInteger  bioDataId;
    private BigInteger lastJabatanId;
    private BigInteger lastWtPeriodId;
    private String nik;
    private String empName;
    private String jabatanName;
    private Date joinDate;
    private Date terminationDate;
    private Date startDateLastWtPeriod;
    private String reason;
    private Integer status;
    private String empCareerHistoryStatus;
    private BigInteger approvalActivityId;
    private String activityNumber;
    private String jsonData;
    
	public BigInteger getCareerEmpEliminationId() {
		return careerEmpEliminationId;
	}
	public void setCareerEmpEliminationId(BigInteger careerEmpEliminationId) {
		this.careerEmpEliminationId = careerEmpEliminationId;
	}
	
	public Long getEmpCareerHistoryId() {
		return empCareerHistoryId;
	}
	public void setEmpCareerHistoryId(Long empCareerHistoryId) {
		this.empCareerHistoryId = empCareerHistoryId;
	}
	public BigInteger getEmpDataId() {
		return empDataId;
	}
	public void setEmpDataId(BigInteger empDataId) {
		this.empDataId = empDataId;
	}
	
	public BigInteger getLastJabatanId() {
		return lastJabatanId;
	}
	public void setLastJabatanId(BigInteger lastJabatanId) {
		this.lastJabatanId = lastJabatanId;
	}
	
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getJabatanName() {
		return jabatanName;
	}
	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Date getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigInteger getLastWtPeriodId() {
		return lastWtPeriodId;
	}
	public void setLastWtPeriodId(BigInteger lastWtPeriodId) {
		this.lastWtPeriodId = lastWtPeriodId;
	}
	public Date getStartDateLastWtPeriod() {
		return startDateLastWtPeriod;
	}
	public void setStartDateLastWtPeriod(Date startDateLastWtPeriod) {
		this.startDateLastWtPeriod = startDateLastWtPeriod;
	}
	public BigInteger getBioDataId() {
		return bioDataId;
	}
	public void setBioDataId(BigInteger bioDataId) {
		this.bioDataId = bioDataId;
	}
	public String getEmpCareerHistoryStatus() {
		return empCareerHistoryStatus;
	}
	public void setEmpCareerHistoryStatus(String empCareerHistoryStatus) {
		this.empCareerHistoryStatus = empCareerHistoryStatus;
	}
	public BigInteger getApprovalActivityId() {
		return approvalActivityId;
	}
	public void setApprovalActivityId(BigInteger approvalActivityId) {
		this.approvalActivityId = approvalActivityId;
	}
	public String getActivityNumber() {
		return activityNumber;
	}
	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
}
