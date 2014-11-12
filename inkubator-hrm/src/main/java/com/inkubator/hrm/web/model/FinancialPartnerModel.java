package com.inkubator.hrm.web.model;

/**
 *
 * @author rizkykojek
 */
public class FinancialPartnerModel {

	private Long id;
	private Long companyId;
	private Long financialNonBankingId;
	private String financialService;
	private Integer accountNumber;
	private String accountName;
	private String productName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getFinancialNonBankingId() {
		return financialNonBankingId;
	}
	public void setFinancialNonBankingId(Long financialNonBankingId) {
		this.financialNonBankingId = financialNonBankingId;
	}
	public String getFinancialService() {
		return financialService;
	}
	public void setFinancialService(String financialService) {
		this.financialService = financialService;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
