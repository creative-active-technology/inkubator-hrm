package com.inkubator.hrm.web.model;

import java.math.BigDecimal;
import java.util.Date;

/**
*
* @author rizkykojek
*/
public class ReportSalaryNoteModel {
	
	private Long empDataId;
	private Long periodId;
	private Date periodStart;
	private Date periodEnd;
	private String empName;
	private String empNik;
	private String empGolJab;
	private BigDecimal basicSalary;
	private BigDecimal income;
	private BigDecimal deduction;
	private BigDecimal tax;
	private BigDecimal takeHomePay;	
	
	public Long getEmpDataId() {
		return empDataId;
	}
	public void setEmpDataId(Long empDataId) {
		this.empDataId = empDataId;
	}
	public Long getPeriodId() {
		return periodId;
	}
	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}
	public Date getPeriodStart() {
		return periodStart;
	}
	public void setPeriodStart(Date periodStart) {
		this.periodStart = periodStart;
	}
	public Date getPeriodEnd() {
		return periodEnd;
	}
	public void setPeriodEnd(Date periodEnd) {
		this.periodEnd = periodEnd;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpNik() {
		return empNik;
	}
	public void setEmpNik(String empNik) {
		this.empNik = empNik;
	}
	public String getEmpGolJab() {
		return empGolJab;
	}
	public void setEmpGolJab(String empGolJab) {
		this.empGolJab = empGolJab;
	}
	public BigDecimal getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	public BigDecimal getDeduction() {
		return deduction;
	}
	public void setDeduction(BigDecimal deduction) {
		this.deduction = deduction;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getTakeHomePay() {
		return takeHomePay;
	}
	public void setTakeHomePay(BigDecimal takeHomePay) {
		this.takeHomePay = takeHomePay;
	}	
	
}
