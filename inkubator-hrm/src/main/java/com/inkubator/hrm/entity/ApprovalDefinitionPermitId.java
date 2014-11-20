/*
 * To change this template, choose Tools | Templates
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
public class ApprovalDefinitionPermitId implements java.io.Serializable {

    private long approvalDefinitionId;
    private long permitClassificationId;

    public ApprovalDefinitionPermitId() {
    }

    public ApprovalDefinitionPermitId(long approvalDefinitionId, long permitClassificationId) {
        this.approvalDefinitionId = approvalDefinitionId;
        this.permitClassificationId = permitClassificationId;
    }
    
    @Column(name="approval_definition_id", nullable=false)
    public long getApprovalDefinitionId() {
        return this.approvalDefinitionId;
    }
    
    public void setApprovalDefinitionId(long approvalDefinitionId) {
        this.approvalDefinitionId = approvalDefinitionId;
    }

    @Column(name="permit_classification_id", nullable=false)
    public long getPermitClassificationId() {
        return this.permitClassificationId;
    }
    
    public void setPermitClassificationId(long permitClassificationId) {
        this.permitClassificationId = permitClassificationId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ApprovalDefinitionPermitId) ) return false;
		 ApprovalDefinitionPermitId castOther = ( ApprovalDefinitionPermitId ) other; 
         
		 return (this.getApprovalDefinitionId()==castOther.getApprovalDefinitionId())
 && (this.getPermitClassificationId()==castOther.getPermitClassificationId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getApprovalDefinitionId();
         result = 37 * result + (int) this.getPermitClassificationId();
         return result;
   }   
}
