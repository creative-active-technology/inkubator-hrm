package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.inkubator.hrm.entity.CareerTransition;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Jabatan;

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
    private String notes;
    
    //additional
    private String companyName;
    private String currentCompany;
    private String currentDepartment;
    private String currentJabatan;
    private String currentEmployeeType;
    private String currentGolJab;
    private Date currentJoinDate;
    
    private List<CareerTransition> listCareerTransition;
    private List<Department> listDepartment;
    private List<Jabatan> listJabatan;
    private List<GolonganJabatan> listGolonganJabatan;
    private List<EmployeeType> listEmployeeType;
	
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
	public List<CareerTransition> getListCareerTransition() {
		return listCareerTransition;
	}
	public void setListCareerTransition(List<CareerTransition> listCareerTransition) {
		this.listCareerTransition = listCareerTransition;
	}
	public List<Department> getListDepartment() {
		return listDepartment;
	}
	public void setListDepartment(List<Department> listDepartment) {
		this.listDepartment = listDepartment;
	}
	public List<Jabatan> getListJabatan() {
		return listJabatan;
	}
	public void setListJabatan(List<Jabatan> listJabatan) {
		this.listJabatan = listJabatan;
	}
	public List<GolonganJabatan> getListGolonganJabatan() {
		return listGolonganJabatan;
	}
	public void setListGolonganJabatan(List<GolonganJabatan> listGolonganJabatan) {
		this.listGolonganJabatan = listGolonganJabatan;
	}
	public List<EmployeeType> getListEmployeeType() {
		return listEmployeeType;
	}
	public void setListEmployeeType(List<EmployeeType> listEmployeeType) {
		this.listEmployeeType = listEmployeeType;
	}
	public String getCurrentCompany() {
		return currentCompany;
	}
	public void setCurrentCompany(String currentCompany) {
		this.currentCompany = currentCompany;
	}
	public String getCurrentDepartment() {
		return currentDepartment;
	}
	public void setCurrentDepartment(String currentDepartment) {
		this.currentDepartment = currentDepartment;
	}
	public String getCurrentJabatan() {
		return currentJabatan;
	}
	public void setCurrentJabatan(String currentJabatan) {
		this.currentJabatan = currentJabatan;
	}
	public String getCurrentEmployeeType() {
		return currentEmployeeType;
	}
	public void setCurrentEmployeeType(String currentEmployeeType) {
		this.currentEmployeeType = currentEmployeeType;
	}
	public String getCurrentGolJab() {
		return currentGolJab;
	}
	public void setCurrentGolJab(String currentGolJab) {
		this.currentGolJab = currentGolJab;
	}
	public Date getCurrentJoinDate() {
		return currentJoinDate;
	}
	public void setCurrentJoinDate(Date currentJoinDate) {
		this.currentJoinDate = currentJoinDate;
	}
    
}
