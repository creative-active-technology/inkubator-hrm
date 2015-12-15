package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LogMonthEndPayroll;

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
    private Double basicSalary;
    private List<LogMonthEndPayroll> listMonthEndPayroll = new ArrayList<LogMonthEndPayroll>();
    private List<LogMonthEndPayroll> listMonthEndPayrollTunjangan = new ArrayList<LogMonthEndPayroll>();
    private List<LogMonthEndPayroll> listMonthEndPayrollPotongan = new ArrayList<LogMonthEndPayroll>();
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
	public Double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}
	public List<LogMonthEndPayroll> getListMonthEndPayroll() {
		return listMonthEndPayroll;
	}
	public void setListMonthEndPayroll(List<LogMonthEndPayroll> listMonthEndPayroll) {
		this.listMonthEndPayroll = listMonthEndPayroll;
	}
	public List<LogMonthEndPayroll> getListMonthEndPayrollTunjangan() {
		return listMonthEndPayrollTunjangan;
	}
	public void setListMonthEndPayrollTunjangan(List<LogMonthEndPayroll> listMonthEndPayrollTunjangan) {
		this.listMonthEndPayrollTunjangan = listMonthEndPayrollTunjangan;
	}
	public List<LogMonthEndPayroll> getListMonthEndPayrollPotongan() {
		return listMonthEndPayrollPotongan;
	}
	public void setListMonthEndPayrollPotongan(List<LogMonthEndPayroll> listMonthEndPayrollPotongan) {
		this.listMonthEndPayrollPotongan = listMonthEndPayrollPotongan;
	}
    
	
}
