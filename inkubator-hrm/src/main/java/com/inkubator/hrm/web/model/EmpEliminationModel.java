package com.inkubator.hrm.web.model;

import java.io.Serializable;

import com.inkubator.hrm.entity.EmpData;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class EmpEliminationModel implements Serializable {

    private Long id;
    private EmpData empData;
    private Long jabatanId;
    private Long jabatanAtasanId;
    private Long departmentId;
    private Long terminationTypeId;
    private String jabatanName;
    private String jabatanAtasanName;
    private String atasanName;
    private String departmentName;
    private String reason;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public EmpData getEmpData() {
		return empData;
	}
	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}
	public Long getJabatanId() {
		return jabatanId;
	}
	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
	}
	public Long getJabatanAtasanId() {
		return jabatanAtasanId;
	}
	public void setJabatanAtasanId(Long jabatanAtasanId) {
		this.jabatanAtasanId = jabatanAtasanId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getJabatanName() {
		return jabatanName;
	}
	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}
	public String getJabatanAtasanName() {
		return jabatanAtasanName;
	}
	public void setJabatanAtasanName(String jabatanAtasanName) {
		this.jabatanAtasanName = jabatanAtasanName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAtasanName() {
		return atasanName;
	}
	public void setAtasanName(String atasanName) {
		this.atasanName = atasanName;
	}
	public Long getTerminationTypeId() {
		return terminationTypeId;
	}
	public void setTerminationTypeId(Long terminationTypeId) {
		this.terminationTypeId = terminationTypeId;
	}
    
}
