package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author rizkykojek
 */
public class LogMonthEndPayrollViewModel implements Serializable {

	private Long id;
	private String empNik;
	private String empName;
	private BigDecimal income;
	private BigDecimal deduction;
	private BigDecimal subsidy;
	private BigDecimal takeHomePay;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpNik() {
		return empNik;
	}
	public void setEmpNik(String empNik) {
		this.empNik = empNik;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
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
	public BigDecimal getSubsidy() {
		return subsidy;
	}
	public void setSubsidy(BigDecimal subsidy) {
		this.subsidy = subsidy;
	}
	public BigDecimal getTakeHomePay() {
		return takeHomePay;
	}
	public void setTakeHomePay(BigDecimal takeHomePay) {
		this.takeHomePay = takeHomePay;
	}
	
	
	
}
