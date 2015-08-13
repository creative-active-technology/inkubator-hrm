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

    private Date tglPengajuan;
    private Integer loanStatus;
    private Long loanNewTypeId;
    private String loanNewTypeName;
    private BigDecimal loanNominal;
    private BigDecimal installmentNominal;
    private Integer totalNumberOfInstallment;
    private Integer totalAlreadyPaidInstallment;
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
	public BigDecimal getLoanNominal() {
		return loanNominal;
	}
	public void setLoanNominal(BigDecimal loanNominal) {
		this.loanNominal = loanNominal;
	}
	public BigDecimal getInstallmentNominal() {
		return installmentNominal;
	}
	public void setInstallmentNominal(BigDecimal installmentNominal) {
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
    
    
    
}
