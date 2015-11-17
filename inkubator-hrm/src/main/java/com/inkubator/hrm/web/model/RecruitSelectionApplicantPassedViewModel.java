/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class RecruitSelectionApplicantPassedViewModel implements Serializable{
	
	private Long id;
    private Long applicantId;
    private String applicantName;
    private Long hireApplyId;
    private Long jabatanId;
    private String jabatanName;
    private Long departmentId;
    private String departmentName;
    private Date applyDate;
    private Long employeeTypeId;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public Long getHireApplyId() {
		return hireApplyId;
	}
	public void setHireApplyId(Long hireApplyId) {
		this.hireApplyId = hireApplyId;
	}
	public Long getJabatanId() {
		return jabatanId;
	}
	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
	}
	public String getJabatanName() {
		return jabatanName;
	}
	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Long getEmployeeTypeId() {
		return employeeTypeId;
	}
	public void setEmployeeTypeId(Long employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
    
}
