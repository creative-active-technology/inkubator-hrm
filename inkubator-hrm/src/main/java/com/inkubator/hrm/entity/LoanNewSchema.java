/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

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
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="loan_new_schema"
    ,catalog="hrm"
)
public class LoanNewSchema  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String loanSchemaCode;
     private String loanSchemaName;
     private String description;
     private Long totalMaximumLoan;
     private Long totalMaximumInstallment;
     private String nomorSk;
     private Date createdOn;
     private String createdBy;
     private String updatedBy;
     private Date updatedOn;
     private Set<LoanNewSchemaListOfType> loanNewSchemaListOfTypes = new HashSet<LoanNewSchemaListOfType>(0);
     private Set<LoanNewSchemaListOfEmp> loanNewSchemaListOfEmps = new HashSet<LoanNewSchemaListOfEmp>(0);

    public LoanNewSchema() {
    }

	
    public LoanNewSchema(long id, String loanSchemaCode, String loanSchemaName) {
        this.id = id;
        this.loanSchemaCode = loanSchemaCode;
        this.loanSchemaName = loanSchemaName;
    }
    public LoanNewSchema(long id, String loanSchemaCode, String loanSchemaName, String description, Long totalMaximumLoan, Long totalMaximumInstallment, Date createdOn, String createdBy, String updatedBy, Date updatedOn, Set<LoanNewSchemaListOfType> loanNewSchemaListOfTypes, Set<LoanNewSchemaListOfEmp> loanNewSchemaListOfEmps) {
       this.id = id;
       this.loanSchemaCode = loanSchemaCode;
       this.loanSchemaName = loanSchemaName;
       this.description = description;
       this.totalMaximumLoan = totalMaximumLoan;
       this.totalMaximumInstallment = totalMaximumInstallment;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.loanNewSchemaListOfTypes = loanNewSchemaListOfTypes;
       this.loanNewSchemaListOfEmps = loanNewSchemaListOfEmps;
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

    
    @Column(name="loan_schema_code", nullable=false, length=8)
    public String getLoanSchemaCode() {
        return this.loanSchemaCode;
    }
    
    public void setLoanSchemaCode(String loanSchemaCode) {
        this.loanSchemaCode = loanSchemaCode;
    }

    
    @Column(name="loan_schema_name", nullable=false, length=60)
    public String getLoanSchemaName() {
        return this.loanSchemaName;
    }
    
    public void setLoanSchemaName(String loanSchemaName) {
        this.loanSchemaName = loanSchemaName;
    }

    
    @Column(name="description", length=65535,columnDefinition="Text" )
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="nomor_sk", length=45)
    public String getNomorSk() {
        return this.nomorSk;
    }
    
    public void setNomorSk(String nomorSk) {
        this.nomorSk = nomorSk;
    }
    
    @Column(name="total_maximum_loan", precision=10, scale=0)
    public Long getTotalMaximumLoan() {
        return this.totalMaximumLoan;
    }
    
    public void setTotalMaximumLoan(Long totalMaximumLoan) {
        this.totalMaximumLoan = totalMaximumLoan;
    }

    
    @Column(name="total_maximum_installment", precision=10, scale=0)
    public Long getTotalMaximumInstallment() {
        return this.totalMaximumInstallment;
    }
    
    public void setTotalMaximumInstallment(Long totalMaximumInstallment) {
        this.totalMaximumInstallment = totalMaximumInstallment;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="loanNewSchema")
    public Set<LoanNewSchemaListOfType> getLoanNewSchemaListOfTypes() {
        return this.loanNewSchemaListOfTypes;
    }
    
    public void setLoanNewSchemaListOfTypes(Set<LoanNewSchemaListOfType> loanNewSchemaListOfTypes) {
        this.loanNewSchemaListOfTypes = loanNewSchemaListOfTypes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="loanNewSchema")
    public Set<LoanNewSchemaListOfEmp> getLoanNewSchemaListOfEmps() {
        return this.loanNewSchemaListOfEmps;
    }
    
    public void setLoanNewSchemaListOfEmps(Set<LoanNewSchemaListOfEmp> loanNewSchemaListOfEmps) {
        this.loanNewSchemaListOfEmps = loanNewSchemaListOfEmps;
    }




}
