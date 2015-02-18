package com.inkubator.hrm.entity;
// Generated Jun 16, 2014 10:20:21 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * Bank generated by hbm2java
 */
@Entity
@Table(name="bank"
    ,catalog="hrm_personalia"
    , uniqueConstraints ={ @UniqueConstraint(columnNames="bank_code") 
    , @UniqueConstraint(columnNames="swift_code") 
    , @UniqueConstraint(columnNames="bank_identification_no") }
)
public class Bank  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     private String bankCode;
     private String bankName;
     private String description;
     private String swiftCcode; 
     private String iban;
     private String bankIdentificationNo;
     private Set<CompanyBankAccount> companyBankAccounts = new HashSet<CompanyBankAccount>(0);
     private String branchCode;
     private String branchName;
     private String address;
     private String bankPhone;
     private String bankFax;
     private Long bankGroup;

    public Bank() {
    }

	
    public Bank(long id) {
        this.id = id;
    }
    public Bank(long id, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String bankCode, String bankName, String description,String swiftCcode,String iban,String bankIdentificationNo, String branchCode, String branchName, String address, String bankPhone, String bankFax, Long bankGroup) {
       this.id = id;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.bankCode = bankCode;
       this.bankName = bankName;
       this.description = description;
       this.swiftCcode = swiftCcode;
       this.iban = iban;
       this.bankIdentificationNo = bankIdentificationNo;
       this.branchCode = branchCode;
       this.branchName = branchName;
       this.address = address;
       this.bankPhone = bankPhone;
       this.bankFax = bankFax;
       this.bankGroup = bankGroup;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
    @Column(name="bank_code", unique=true, length=8)
    public String getBankCode() {
        return this.bankCode;
    }
    
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    
    @Column(name="bank_name", length=60)
    public String getBankName() {
        return this.bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    
    @Column(name="description", length=65535, columnDefinition="Text")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="swift_code", unique=true, length=30)
    public String getSwiftCcode() {
        return swiftCcode;
    }

    public void setSwiftCcode(String swiftCcode) {
        this.swiftCcode = swiftCcode;
    }

    @Column(name="iban", length=20)
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Column(name="bank_identification_no", unique=true, length=60)
    public String getBankIdentificationNo() {
        return bankIdentificationNo;
    }

    public void setBankIdentificationNo(String bankIdentificationNo) {
        this.bankIdentificationNo = bankIdentificationNo;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bank")
	public Set<CompanyBankAccount> getCompanyBankAccounts() {
		return companyBankAccounts;
	}

	public void setCompanyBankAccounts(Set<CompanyBankAccount> companyBankAccounts) {
		this.companyBankAccounts = companyBankAccounts;
	}

    @Column(name="branch_code", unique=true, length=8)    
    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Column(name="branch_name", length=60)
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Column(name="address", length=225)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="bank_phone", length=20)
    public String getBankPhone() {
        return bankPhone;
    }

    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone;
    }

    @Column(name="bank_fax", length=20)
    public String getBankFax() {
        return bankFax;
    }

    public void setBankFax(String bankFax) {
        this.bankFax = bankFax;
    }

    @Column(name="bank_group", length=30)
    public Long getBankGroup() {
        return bankGroup;
    }

    public void setBankGroup(Long bankGroup) {
        this.bankGroup = bankGroup;
    }
}


