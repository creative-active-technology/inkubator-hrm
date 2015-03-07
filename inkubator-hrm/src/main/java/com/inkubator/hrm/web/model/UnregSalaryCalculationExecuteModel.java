package com.inkubator.hrm.web.model;

import java.math.BigDecimal;


/**
*
* @author rizkykojek
*/
public class UnregSalaryCalculationExecuteModel {
	private Long unregSalaryId;
	private Long paySalaryCompId;
	private String paySalaryCompName;
	private Long totalEmployee;
	private BigDecimal totalNominal;
	
	public Long getUnregSalaryId() {
		return unregSalaryId;
	}
	public void setUnregSalaryId(Long unregSalaryId) {
		this.unregSalaryId = unregSalaryId;
	}
	public Long getPaySalaryCompId() {
		return paySalaryCompId;
	}
	public void setPaySalaryCompId(Long paySalaryCompId) {
		this.paySalaryCompId = paySalaryCompId;
	}
	public String getPaySalaryCompName() {
		return paySalaryCompName;
	}
	public void setPaySalaryCompName(String paySalaryCompName) {
		this.paySalaryCompName = paySalaryCompName;
	}
	public Long getTotalEmployee() {
		return totalEmployee;
	}
	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}
	public BigDecimal getTotalNominal() {
		return totalNominal;
	}
	public void setTotalNominal(BigDecimal totalNominal) {
		this.totalNominal = totalNominal;
	}
	
}
