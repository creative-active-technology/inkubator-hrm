package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanUsageHistoryViewModel implements Serializable {

    private Long loanNewTypeId;
    private String loanNewTypeName;
    private Double maximumAllocation;
    private Double totalNominalUsed;
    private Integer chartPersentation;
	public Long getLoanNewTypeId() {
		return loanNewTypeId;
	}
	public void setLoanNewTypeId(Long loanNewTypeId) {
		this.loanNewTypeId = loanNewTypeId;
	}
	public String getLoanNewTypeName() {
		return loanNewTypeName;
	}
	public void setLoanNewTypeName(String loanNewTypeName) {
		this.loanNewTypeName = loanNewTypeName;
	}
	public Double getMaximumAllocation() {
		return maximumAllocation;
	}
	public void setMaximumAllocation(Double maximumAllocation) {
		this.maximumAllocation = maximumAllocation;
	}
	public Double getTotalNominalUsed() {
		return totalNominalUsed;
	}
	public void setTotalNominalUsed(Double totalNominalUsed) {
		this.totalNominalUsed = totalNominalUsed;
	}
	public Integer getChartPersentation() {
		return chartPersentation;
	}
	public void setChartPersentation(Integer chartPersentation) {
		this.chartPersentation = chartPersentation;
	}
	
    
    
   
}
