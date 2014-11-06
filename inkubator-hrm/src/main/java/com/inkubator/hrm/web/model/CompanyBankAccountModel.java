package com.inkubator.hrm.web.model;

/**
 *
 * @author rizkykojek
 */
public class CompanyBankAccountModel {

	private Long id;
	private Long companyId;
	private Long bankId;
	private Long savingTypeId;
	private Integer accountNumber;
	private String accountName;
	private Boolean isDefault;
    
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
	public Long getBankId() {
		return bankId;
	}
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}
	public Long getSavingTypeId() {
		return savingTypeId;
	}
	public void setSavingTypeId(Long savingTypeId) {
		this.savingTypeId = savingTypeId;
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
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
}
