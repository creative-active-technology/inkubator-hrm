package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;

public class LogWtAttendanceRealizationModel implements Serializable {
	private Date startPeriod;
	private Date endPeriod;
	private List<Department> listSelectedDepartment;
	private List<GolonganJabatan> listSelectedGolJab;
	List<String> listDeptName;
	List<String> listGolJabName;
	
	public Date getStartPeriod() {
		return startPeriod;
	}
	public void setStartPeriod(Date startPeriod) {
		this.startPeriod = startPeriod;
	}
	public Date getEndPeriod() {
		return endPeriod;
	}
	public void setEndPeriod(Date endPeriod) {
		this.endPeriod = endPeriod;
	}
	public List<Department> getListSelectedDepartment() {
		return listSelectedDepartment;
	}
	public void setListSelectedDepartment(List<Department> listSelectedDepartment) {
		this.listSelectedDepartment = listSelectedDepartment;
	}
	public List<GolonganJabatan> getListSelectedGolJab() {
		return listSelectedGolJab;
	}
	public void setListSelectedGolJab(List<GolonganJabatan> listSelectedGolJab) {
		this.listSelectedGolJab = listSelectedGolJab;
	}
	public List<String> getListDeptName() {
		return listDeptName;
	}
	public void setListDeptName(List<String> listDeptName) {
		this.listDeptName = listDeptName;
	}
	public List<String> getListGolJabName() {
		return listGolJabName;
	}
	public void setListGolJabName(List<String> listGolJabName) {
		this.listGolJabName = listGolJabName;
	}
	
	
}
