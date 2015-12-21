package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanUnpaidForEmpTerminationViewModel implements Serializable {

    private Integer loanNewApplicationId;
    private String loanNewTypeName;
    private Double totalPrincipal;
    private Double totalOutstanding;
    private Integer totalNumberOfInstallment;
    private Integer lastPaidTermin;
    
	public Integer getLoanNewApplicationId() {
		return loanNewApplicationId;
	}
	public void setLoanNewApplicationId(Integer loanNewApplicationId) {
		this.loanNewApplicationId = loanNewApplicationId;
	}
	public String getLoanNewTypeName() {
		return loanNewTypeName;
	}
	public void setLoanNewTypeName(String loanNewTypeName) {
		this.loanNewTypeName = loanNewTypeName;
	}
	public Double getTotalPrincipal() {
		return totalPrincipal;
	}
	public void setTotalPrincipal(Double totalPrincipal) {
		this.totalPrincipal = totalPrincipal;
	}
	public Double getTotalOutstanding() {
		return totalOutstanding;
	}
	public void setTotalOutstanding(Double totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
	}
	public Integer getTotalNumberOfInstallment() {
		return totalNumberOfInstallment;
	}
	public void setTotalNumberOfInstallment(Integer totalNumberOfInstallment) {
		this.totalNumberOfInstallment = totalNumberOfInstallment;
	}
	public Integer getLastPaidTermin() {
		return lastPaidTermin;
	}
	public void setLastPaidTermin(Integer lastPaidTermin) {
		this.lastPaidTermin = lastPaidTermin;
	}
	
	
    
}
