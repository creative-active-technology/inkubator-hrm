package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class EmpEliminationViewModel implements Serializable {
	
	private Long id;
    private Long empCareerHistoryId;
    private Long empDataId;
    private Long  bioDataId;
    private Long lastJabatanId;
    private Long lastWtPeriodId;
    private String nik;
    private String empName;
    private String jabatanName;
    private Date joinDate;
    private Date terminationDate;
    private Date startDateLastWtPeriod;
    private String reason;
    private Integer status;
    private String empCareerHistoryStatus;
    
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmpCareerHistoryId() {
		return empCareerHistoryId;
	}
	public void setEmpCareerHistoryId(Long empCareerHistoryId) {
		this.empCareerHistoryId = empCareerHistoryId;
	}
	public Long getEmpDataId() {
		return empDataId;
	}
	public void setEmpDataId(Long empDataId) {
		this.empDataId = empDataId;
	}
	
	public Long getLastJabatanId() {
		return lastJabatanId;
	}
	public void setLastJabatanId(Long lastJabatanId) {
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
	public Long getLastWtPeriodId() {
		return lastWtPeriodId;
	}
	public void setLastWtPeriodId(Long lastWtPeriodId) {
		this.lastWtPeriodId = lastWtPeriodId;
	}
	public Date getStartDateLastWtPeriod() {
		return startDateLastWtPeriod;
	}
	public void setStartDateLastWtPeriod(Date startDateLastWtPeriod) {
		this.startDateLastWtPeriod = startDateLastWtPeriod;
	}
	public Long getBioDataId() {
		return bioDataId;
	}
	public void setBioDataId(Long bioDataId) {
		this.bioDataId = bioDataId;
	}
	public String getEmpCareerHistoryStatus() {
		return empCareerHistoryStatus;
	}
	public void setEmpCareerHistoryStatus(String empCareerHistoryStatus) {
		this.empCareerHistoryStatus = empCareerHistoryStatus;
	}
	
	
}
