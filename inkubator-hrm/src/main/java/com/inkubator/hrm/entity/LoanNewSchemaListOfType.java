/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="loan_new_schema_list_of_type"
    ,catalog="hrm"
)
public class LoanNewSchemaListOfType  implements java.io.Serializable {


     private LoanNewSchemaListOfTypeId id;
     private LoanNewSchema loanNewSchema;
     private LoanNewType loanNewType;
     private Double maximumApproval;
     private Double maximumAllocation;
     private Double minimumApproval;
     private Double minimumAllocation;
     private Double minimumMonthlyInstallment;

    public LoanNewSchemaListOfType() {
    }

	
    public LoanNewSchemaListOfType(LoanNewSchemaListOfTypeId id, LoanNewSchema loanNewSchema, LoanNewType loanNewType) {
        this.id = id;
        this.loanNewSchema = loanNewSchema;
        this.loanNewType = loanNewType;
    }
    public LoanNewSchemaListOfType(LoanNewSchemaListOfTypeId id, LoanNewSchema loanNewSchema, LoanNewType loanNewType, Double maximumApproval, Double maximumAllocation, Double minimumApproval, Double minimumAllocation, Double minimumMonthlyInstallment) {
       this.id = id;
       this.loanNewSchema = loanNewSchema;
       this.loanNewType = loanNewType;
       this.maximumApproval = maximumApproval;
       this.maximumAllocation = maximumAllocation;
       this.minimumApproval = minimumApproval;
       this.minimumAllocation = minimumAllocation;
       this.minimumMonthlyInstallment = minimumMonthlyInstallment;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="loanNewTypeId", column=@Column(name="loan_new_type_id", nullable=false) ), 
        @AttributeOverride(name="loanNewSchemaId", column=@Column(name="loan_new_schema_id", nullable=false) ) } )
    public LoanNewSchemaListOfTypeId getId() {
        return this.id;
    }
    
    public void setId(LoanNewSchemaListOfTypeId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="loan_new_schema_id", nullable=false, insertable=false, updatable=false)
    public LoanNewSchema getLoanNewSchema() {
        return this.loanNewSchema;
    }
    
    public void setLoanNewSchema(LoanNewSchema loanNewSchema) {
        this.loanNewSchema = loanNewSchema;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="loan_new_type_id", nullable=false, insertable=false, updatable=false)
    public LoanNewType getLoanNewType() {
        return this.loanNewType;
    }
    
    public void setLoanNewType(LoanNewType loanNewType) {
        this.loanNewType = loanNewType;
    }

    
    @Column(name="maximum_approval", precision=22, scale=0)
    public Double getMaximumApproval() {
        return this.maximumApproval;
    }
    
    public void setMaximumApproval(Double maximumApproval) {
        this.maximumApproval = maximumApproval;
    }

    
    @Column(name="maximum_allocation", precision=22, scale=0)
    public Double getMaximumAllocation() {
        return this.maximumAllocation;
    }
    
    public void setMaximumAllocation(Double maximumAllocation) {
        this.maximumAllocation = maximumAllocation;
    }

    
    @Column(name="minimum_approval", precision=22, scale=0)
    public Double getMinimumApproval() {
        return this.minimumApproval;
    }
    
    public void setMinimumApproval(Double minimumApproval) {
        this.minimumApproval = minimumApproval;
    }

    
    @Column(name="minimum_allocation", precision=22, scale=0)
    public Double getMinimumAllocation() {
        return this.minimumAllocation;
    }
    
    public void setMinimumAllocation(Double minimumAllocation) {
        this.minimumAllocation = minimumAllocation;
    }

    
    @Column(name="minimum_monthly_installment", precision=22, scale=0)
    public Double getMinimumMonthlyInstallment() {
        return this.minimumMonthlyInstallment;
    }
    
    public void setMinimumMonthlyInstallment(Double minimumMonthlyInstallment) {
        this.minimumMonthlyInstallment = minimumMonthlyInstallment;
    }




}
