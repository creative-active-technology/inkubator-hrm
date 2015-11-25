package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

import com.inkubator.hrm.entity.EmpData;

/**
 *
 * @author rizkykojek
 */
public class EmpCareerHistoryModel implements Serializable {

	private EmpData empData;
	private Long careerTransitionId;
	private Date effectiveDate;
	private EmpData copyOfLetterTo;
	private Long departmentId;
    private Long jabatanId;
    private Long golonganJabatanId;
    private Long employeeTypeId;
    private Date joinDate;
    private String salaryChangesType;
    private Double salaryChangesPercent;
    private String noSk;
    private String approvalActivityNumber;
    private String companyName;
    private String notes;
	
	public Long getCareerTransitionId() {
		return careerTransitionId;
	}
	public void setCareerTransitionId(Long careerTransitionId) {
		this.careerTransitionId = careerTransitionId;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public EmpData getEmpData() {
		return empData;
	}
	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}
	public EmpData getCopyOfLetterTo() {
		return copyOfLetterTo;
	}
	public void setCopyOfLetterTo(EmpData copyOfLetterTo) {
		this.copyOfLetterTo = copyOfLetterTo;
	}
	public Long getJabatanId() {
		return jabatanId;
	}
	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
	}
	public Long getGolonganJabatanId() {
		return golonganJabatanId;
	}
	public void setGolonganJabatanId(Long golonganJabatanId) {
		this.golonganJabatanId = golonganJabatanId;
	}
	public Long getEmployeeTypeId() {
		return employeeTypeId;
	}
	public void setEmployeeTypeId(Long employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getSalaryChangesType() {
		return salaryChangesType;
	}
	public void setSalaryChangesType(String salaryChangesType) {
		this.salaryChangesType = salaryChangesType;
	}
	public Double getSalaryChangesPercent() {
		return salaryChangesPercent;
	}
	public void setSalaryChangesPercent(Double salaryChangesPercent) {
		this.salaryChangesPercent = salaryChangesPercent;
	}
	public String getNoSk() {
		return noSk;
	}
	public void setNoSk(String noSk) {
		this.noSk = noSk;
	}
	public String getApprovalActivityNumber() {
		return approvalActivityNumber;
	}
	public void setApprovalActivityNumber(String approvalActivityNumber) {
		this.approvalActivityNumber = approvalActivityNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
    
}
