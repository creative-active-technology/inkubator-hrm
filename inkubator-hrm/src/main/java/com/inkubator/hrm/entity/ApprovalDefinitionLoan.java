/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="approval_definition_loan_schema"
    ,catalog="hrm"
)
public class ApprovalDefinitionLoan  implements java.io.Serializable {


     private ApprovalDefinitionLoanId id;
     private ApprovalDefinition approvalDefinition;
     private LoanNewSchema loanNewSchema;
     private String description;
     private List<ApprovalDefinition> listApprovalDefinition = new ArrayList<ApprovalDefinition>();

    public ApprovalDefinitionLoan() {
    }

	
    public ApprovalDefinitionLoan(ApprovalDefinitionLoanId id, ApprovalDefinition approvalDefinition, LoanNewSchema loanNewSchema) {
        this.id = id;
        this.approvalDefinition = approvalDefinition;
        this.loanNewSchema = loanNewSchema;
    }
    public ApprovalDefinitionLoan(ApprovalDefinitionLoanId id, ApprovalDefinition approvalDefinition, LoanNewSchema loanNewSchema, String description) {
       this.id = id;
       this.approvalDefinition = approvalDefinition;
       this.loanNewSchema = loanNewSchema;
       this.description = description;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="approvalDefinitionLoanId", column=@Column(name="approval_definition_loan_id", nullable=false) ), 
        @AttributeOverride(name="loanNewSchemaId", column=@Column(name="loan_new_schema_id", nullable=false) ) } )
    public ApprovalDefinitionLoanId getId() {
        return this.id;
    }
    
    public void setId(ApprovalDefinitionLoanId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="approval_definition_loan_id", nullable=false, insertable=false, updatable=false)
    public ApprovalDefinition getApprovalDefinition() {
        return this.approvalDefinition;
    }
    
    public void setApprovalDefinition(ApprovalDefinition approvalDefinition) {
        this.approvalDefinition = approvalDefinition;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="loan_new_schema_id", nullable=false, insertable=false, updatable=false)
    public LoanNewSchema getLoanNewSchema() {
        return this.loanNewSchema;
    }
    
    public void setLoanNewSchema(LoanNewSchema loanNewSchema) {
        this.loanNewSchema = loanNewSchema;
    }

    
    @Column(name="description", length=45)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public List<ApprovalDefinition> getListApprovalDefinition() {
        return listApprovalDefinition;
    }

    public void setListApprovalDefinition(List<ApprovalDefinition> listApprovalDefinition) {
        this.listApprovalDefinition = listApprovalDefinition;
    }




}