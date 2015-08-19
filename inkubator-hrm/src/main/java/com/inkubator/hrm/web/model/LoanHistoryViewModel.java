package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanHistoryViewModel implements Serializable {
	private Integer loanNewApplicationId;
    private Date tglPengajuan;
    private Integer loanStatus;
    private Long loanNewTypeId;
    private String loanNewTypeName;
    private Double loanNominal;
    private Double installmentNominal;
    private Integer totalNumberOfInstallment;
    private Integer totalAlreadyPaidInstallment;
    private BigDecimal loanInterestRate;
    private Integer typeOfInterest;
    private Integer buffer;
    private Date loanPaymentDate;
    private Date lastPaymentDate;
    
	public Date getTglPengajuan() {
		return tglPengajuan;
	}
	public void setTglPengajuan(Date tglPengajuan) {
		this.tglPengajuan = tglPengajuan;
	}
	public Integer getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(Integer loanStatus) {
		this.loanStatus = loanStatus;
	}
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
	public Double getLoanNominal() {
		return loanNominal;
	}
	public void setLoanNominal(Double loanNominal) {
		this.loanNominal = loanNominal;
	}
	public Double getInstallmentNominal() {
		return installmentNominal;
	}
	public void setInstallmentNominal(Double installmentNominal) {
		this.installmentNominal = installmentNominal;
	}
	public Integer getTotalNumberOfInstallment() {
		return totalNumberOfInstallment;
	}
	public void setTotalNumberOfInstallment(Integer totalNumberOfInstallment) {
		this.totalNumberOfInstallment = totalNumberOfInstallment;
	}
	public Integer getTotalAlreadyPaidInstallment() {
		return totalAlreadyPaidInstallment;
	}
	public void setTotalAlreadyPaidInstallment(Integer totalAlreadyPaidInstallment) {
		this.totalAlreadyPaidInstallment = totalAlreadyPaidInstallment;
	}
	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	public BigDecimal getLoanInterestRate() {
		return loanInterestRate;
	}
	public void setLoanInterestRate(BigDecimal loanInterestRate) {
		this.loanInterestRate = loanInterestRate;
	}
	public Integer getTypeOfInterest() {
		return typeOfInterest;
	}
	public void setTypeOfInterest(Integer typeOfInterest) {
		this.typeOfInterest = typeOfInterest;
	}
	public Date getLoanPaymentDate() {
		return loanPaymentDate;
	}
	public void setLoanPaymentDate(Date loanPaymentDate) {
		this.loanPaymentDate = loanPaymentDate;
	}
	public Integer getBuffer() {
		return buffer;
	}
	public void setBuffer(Integer buffer) {
		this.buffer = buffer;
	}
	public Integer getLoanNewApplicationId() {
		return loanNewApplicationId;
	}
	public void setLoanNewApplicationId(Integer loanNewApplicationId) {
		this.loanNewApplicationId = loanNewApplicationId;
	}
	
    
}
