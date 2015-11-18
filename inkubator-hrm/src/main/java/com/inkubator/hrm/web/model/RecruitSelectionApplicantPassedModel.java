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
public class RecruitSelectionApplicantPassedModel implements Serializable{
	
	private String nik;
    private Long applicantId;
    private String applicantName;
    private Date dateOfBirth;
    private Long departmentId;
    private String departmentName;
    private Long employeeTypeId;
    private Long golonganJabatanId;
    private Long jabatanId;
    private String jabatanName;
    private Date tmbDate;
    private Date rotationDate;
    private Long paySalaryGradeId;
    private Double gajiPokok;
    private Long hireApplyId;
    
	public Long getHireApplyId() {
		return hireApplyId;
	}
	public void setHireApplyId(Long hireApplyId) {
		this.hireApplyId = hireApplyId;
	}
	public Double getGajiPokok() {
		return gajiPokok;
	}
	public void setGajiPokok(Double gajiPokok) {
		this.gajiPokok = gajiPokok;
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
	public Long getJabatanId() {
		return jabatanId;
	}
	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
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
	public Long getEmployeeTypeId() {
		return employeeTypeId;
	}
	public void setEmployeeTypeId(Long employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Long getGolonganJabatanId() {
		return golonganJabatanId;
	}
	public void setGolonganJabatanId(Long golonganJabatanId) {
		this.golonganJabatanId = golonganJabatanId;
	}
	public Date getTmbDate() {
		return tmbDate;
	}
	public void setTmbDate(Date tmbDate) {
		this.tmbDate = tmbDate;
	}
	public Date getRotationDate() {
		return rotationDate;
	}
	public void setRotationDate(Date rotationDate) {
		this.rotationDate = rotationDate;
	}
	public Long getPaySalaryGradeId() {
		return paySalaryGradeId;
	}
	public void setPaySalaryGradeId(Long paySalaryGradeId) {
		this.paySalaryGradeId = paySalaryGradeId;
	}
	public String getJabatanName() {
		return jabatanName;
	}
	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}
    
	
}
