package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
*
* @author rizkykojek
*/
public class ReportSalaryJounalModel implements Serializable{
	private Long periodId;
	private Date payrollPeriod;
	private Date payrollDate;
	private Double balance;	
	
	public Long getPeriodId() {
		return periodId;
	}
	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}
	public Date getPayrollPeriod() {
		return payrollPeriod;
	}
	public void setPayrollPeriod(Date payrollPeriod) {
		this.payrollPeriod = payrollPeriod;
	}
	public Date getPayrollDate() {
		return payrollDate;
	}
	public void setPayrollDate(Date payrollDate) {
		this.payrollDate = payrollDate;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	
}
