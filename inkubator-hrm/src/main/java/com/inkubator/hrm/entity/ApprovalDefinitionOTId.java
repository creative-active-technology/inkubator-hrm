/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;
// Generated Oct 7, 2014 2:27:51 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ApprovalDefinitionOverTimeId generated by hbm2java
 */
@Embeddable
public class ApprovalDefinitionOTId  implements java.io.Serializable {


     private long approvalDefinitionId;
     private long overTimeId;

    public ApprovalDefinitionOTId() {
    }

    public ApprovalDefinitionOTId(long approvalDefinitionId, long overTimeId) {
       this.approvalDefinitionId = approvalDefinitionId;
       this.overTimeId = overTimeId;
    }
   

    @Column(name="approval_definition_id", nullable=false)
    public long getApprovalDefinitionId() {
        return this.approvalDefinitionId;
    }
    
    public void setApprovalDefinitionId(long approvalDefinitionId) {
        this.approvalDefinitionId = approvalDefinitionId;
    }

    @Column(name="over_time_id", nullable=false)
    public long getOverTimeId() {
        return this.overTimeId;
    }
    
    public void setOverTimeId(long overTimeId) {
        this.overTimeId = overTimeId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ApprovalDefinitionOTId) ) return false;
		 ApprovalDefinitionOTId castOther = ( ApprovalDefinitionOTId ) other; 
         
		 return (this.getApprovalDefinitionId()==castOther.getApprovalDefinitionId())
 && (this.getOverTimeId()==castOther.getOverTimeId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getApprovalDefinitionId();
         result = 37 * result + (int) this.getOverTimeId();
         return result;
   }   


}


