/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Deni
 */
@Embeddable
public class ApprovalDefinitionLoanId  implements java.io.Serializable {


     private long approvalDefinitionLoanId;
     private long loanNewSchemaId;

    public ApprovalDefinitionLoanId() {
    }

    public ApprovalDefinitionLoanId(long approvalDefinitionLoanId, long loanNewSchemaId) {
       this.approvalDefinitionLoanId = approvalDefinitionLoanId;
       this.loanNewSchemaId = loanNewSchemaId;
    }
   


    @Column(name="approval_definition_loan_id", nullable=false)
    public long getApprovalDefinitionLoanId() {
        return this.approvalDefinitionLoanId;
    }
    
    public void setApprovalDefinitionLoanId(long approvalDefinitionLoanId) {
        this.approvalDefinitionLoanId = approvalDefinitionLoanId;
    }


    @Column(name="loan_new_schema_id", nullable=false)
    public long getLoanNewSchemaId() {
        return this.loanNewSchemaId;
    }
    
    public void setLoanNewSchemaId(long loanNewSchemaId) {
        this.loanNewSchemaId = loanNewSchemaId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ApprovalDefinitionLoanId) ) return false;
		 ApprovalDefinitionLoanId castOther = ( ApprovalDefinitionLoanId ) other; 
         
		 return (this.getApprovalDefinitionLoanId()==castOther.getApprovalDefinitionLoanId())
 && (this.getLoanNewSchemaId()==castOther.getLoanNewSchemaId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getApprovalDefinitionLoanId();
         result = 37 * result + (int) this.getLoanNewSchemaId();
         return result;
   }   


}