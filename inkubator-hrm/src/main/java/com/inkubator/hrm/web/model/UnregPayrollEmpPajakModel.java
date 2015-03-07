package com.inkubator.hrm.web.model;
/**
 *
 * @author rizkykojek
 */
public class UnregPayrollEmpPajakModel {
	
	private Long unregSalaryId;
	private Long taxComponentId;
	private String taxComponentName;
	private Double nominal;
	
	public Long getUnregSalaryId() {
		return unregSalaryId;
	}
	public void setUnregSalaryId(Long unregSalaryId) {
		this.unregSalaryId = unregSalaryId;
	}
	public Long getTaxComponentId() {
		return taxComponentId;
	}
	public void setTaxComponentId(Long taxComponentId) {
		this.taxComponentId = taxComponentId;
	}
	public String getTaxComponentName() {
		return taxComponentName;
	}
	public void setTaxComponentName(String taxComponentName) {
		this.taxComponentName = taxComponentName;
	}
	public Double getNominal() {
		return nominal;
	}
	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}
	
}
