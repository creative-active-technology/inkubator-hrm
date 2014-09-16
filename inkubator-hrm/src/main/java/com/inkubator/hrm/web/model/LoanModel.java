package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanPaymentDetail;

/**
 *
 * @author rizkykojek
 */
public class LoanModel implements Serializable {

	private Long id;
	private EmpData empData;
	private Long loanSchemaId;
	private Double nominalPrincipal;
	private Double maxNominalPrincipal;
	private Integer termin;
	private Integer maxTermin;
	private Double interestRate;
	private Date loanPaymentDate;
	private Date loanDate;
	private List<LoanPaymentDetail> loanPaymentDetails;
	
	public LoanModel(){
		loanPaymentDetails = new ArrayList<LoanPaymentDetail>();
	}
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
	public Long getLoanSchemaId() {
		return loanSchemaId;
	}
	public void setLoanSchemaId(Long loanSchemaId) {
		this.loanSchemaId = loanSchemaId;
	}
	public Double getNominalPrincipal() {
		return nominalPrincipal;
	}
	public void setNominalPrincipal(Double nominalPrincipal) {
		this.nominalPrincipal = nominalPrincipal;
	}
	public Integer getTermin() {
		return termin;
	}
	public void setTermin(Integer termin) {
		this.termin = termin;
	}
	public Date getLoanPaymentDate() {
		return loanPaymentDate;
	}
	public void setLoanPaymentDate(Date loanPaymentDate) {
		this.loanPaymentDate = loanPaymentDate;
	}
	public Date getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public List<LoanPaymentDetail> getLoanPaymentDetails() {
		return loanPaymentDetails;
	}
	public void setLoanPaymentDetails(List<LoanPaymentDetail> loanPaymentDetails) {
		this.loanPaymentDetails = loanPaymentDetails;
	}
	public Double getMaxNominalPrincipal() {
		return maxNominalPrincipal;
	}
	public void setMaxNominalPrincipal(Double maxNominalPrincipal) {
		this.maxNominalPrincipal = maxNominalPrincipal;
	}
	public Double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	public Integer getMaxTermin() {
		return maxTermin;
	}
	public void setMaxTermin(Integer maxTermin) {
		this.maxTermin = maxTermin;
	}	
	
}
