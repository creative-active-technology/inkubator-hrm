package com.inkubator.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ApprovalDefinitionRmbsSchemaId  implements java.io.Serializable {


     private long rmbsSchemaId;
     private long approvalDefinitionId;

    public ApprovalDefinitionRmbsSchemaId() {
    }

    public ApprovalDefinitionRmbsSchemaId(long approvalDefinitionId, long rmbsSchemaId) {
       this.rmbsSchemaId = rmbsSchemaId;
       this.approvalDefinitionId = approvalDefinitionId;
    }
    
    @Column(name="rmbs_schema_id", nullable=false)
    public long getRmbsSchemaId() {
		return rmbsSchemaId;
	}

	public void setRmbsSchemaId(long rmbsSchemaId) {
		this.rmbsSchemaId = rmbsSchemaId;
	}    

	@Column(name="approval_definition_id", nullable=false)
	public long getApprovalDefinitionId() {
		return approvalDefinitionId;
	}

	public void setApprovalDefinitionId(long approvalDefinitionId) {
		this.approvalDefinitionId = approvalDefinitionId;
	}  

   public boolean equals(Object other) {
         if ((this == other )) return true;
		 if ((other == null )) return false;
		 if (!(other instanceof ApprovalDefinitionRmbsSchemaId)) return false;
		 ApprovalDefinitionRmbsSchemaId castOther = ( ApprovalDefinitionRmbsSchemaId ) other; 
         
		 return (this.getRmbsSchemaId()==castOther.getRmbsSchemaId()) && (this.getApprovalDefinitionId()==castOther.getApprovalDefinitionId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getRmbsSchemaId();
         result = 37 * result + (int) this.getApprovalDefinitionId();
         return result;
   }   


}


