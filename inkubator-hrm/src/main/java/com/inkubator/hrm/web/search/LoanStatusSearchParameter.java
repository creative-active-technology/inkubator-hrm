package com.inkubator.hrm.web.search;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.webcore.util.SearchParameter;

public class LoanStatusSearchParameter extends SearchParameter {
	private List<Department> listDepartment;
	private List<GolonganJabatan> listGolonganJabatan;
	private Date startDate;
	private Date endDate;
	private BigDecimal startNominal;
	private BigDecimal endNominal;
	public List<Department> getListDepartment() {
		return listDepartment;
	}
	public void setListDepartment(List<Department> listDepartment) {
		this.listDepartment = listDepartment;
	}
	public List<GolonganJabatan> getListGolonganJabatan() {
		return listGolonganJabatan;
	}
	public void setListGolonganJabatan(List<GolonganJabatan> listGolonganJabatan) {
		this.listGolonganJabatan = listGolonganJabatan;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public BigDecimal getStartNominal() {
		return startNominal;
	}
	public void setStartNominal(BigDecimal startNominal) {
		this.startNominal = startNominal;
	}
	public BigDecimal getEndNominal() {
		return endNominal;
	}
	public void setEndNominal(BigDecimal endNominal) {
		this.endNominal = endNominal;
	}
	
	
	
}