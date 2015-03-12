/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="loan_new_type"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="loan_type_name") 
)
public class LoanNewType  implements java.io.Serializable {


     private long id;
     private Integer version;
     private Currency currency;
     private String loanTypeName;
     private String loanTypeCode;
     private String description;
     private Integer roundingStatus;
     private Integer interestMethod;
     private BigDecimal interest;
     private Date createdOn;
     private String createdBy;
     private String updatedBy;
     private Date updatedOn;

    public LoanNewType() {
    }

	
    public LoanNewType(long id, Currency currency, String loanTypeName, String loanTypeCode, Integer roundingStatus, Integer interestMethod, BigDecimal interest) {
        this.id = id;
        this.currency = currency;
        this.loanTypeName = loanTypeName;
        this.loanTypeCode = loanTypeCode;
        this.roundingStatus = roundingStatus;
        this.interestMethod = interestMethod;
        this.interest = interest;
    }
    public LoanNewType(long id, Currency currency, String loanTypeName, String loanTypeCode, String description, Integer roundingStatus, Integer interestMethod, BigDecimal interest, Date createdOn, String createdBy, String updatedBy, Date updatedOn) {
       this.id = id;
       this.currency = currency;
       this.loanTypeName = loanTypeName;
       this.loanTypeCode = loanTypeCode;
       this.description = description;
       this.roundingStatus = roundingStatus;
       this.interestMethod = interestMethod;
       this.interest = interest;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="currency_id", nullable=false)
    public Currency getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    
    @Column(name="loan_type_name", unique=true, nullable=false, length=45)
    public String getLoanTypeName() {
        return this.loanTypeName;
    }
    
    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    
    @Column(name="loan_type_code", nullable=false, length=45)
    public String getLoanTypeCode() {
        return this.loanTypeCode;
    }
    
    public void setLoanTypeCode(String loanTypeCode) {
        this.loanTypeCode = loanTypeCode;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="rounding_status", nullable=false)
    public Integer getRoundingStatus() {
        return roundingStatus;
    }

    public void setRoundingStatus(Integer roundingStatus) {
        this.roundingStatus = roundingStatus;
    }

    
    @Column(name="interest_method", nullable=false)
    public Integer getInterestMethod() {
        return interestMethod;
    }

    public void setInterestMethod(Integer interestMethod) {
        this.interestMethod = interestMethod;
    }

    
    @Column(name="interest", nullable=false, precision=10, scale=0)
    public BigDecimal getInterest() {
        return interest;
    }

    

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
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




}