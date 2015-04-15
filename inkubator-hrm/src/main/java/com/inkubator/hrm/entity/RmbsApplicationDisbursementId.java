package com.inkubator.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RmbsApplicationDisbursementId  implements java.io.Serializable {

     private long rmbsApplicationId;
     private long rmbsDisbursementId;

    public RmbsApplicationDisbursementId() {
    }

    public RmbsApplicationDisbursementId(long rmbsDisbursementId, long rmbsApplicationId) {
       this.rmbsApplicationId = rmbsApplicationId;
       this.rmbsDisbursementId = rmbsDisbursementId;
    }    
    
    @Column(name="rmbs_application_id", nullable=false)
    public long getRmbsApplicationId() {
		return rmbsApplicationId;
	}

	public void setRmbsApplicationId(long rmbsApplicationId) {
		this.rmbsApplicationId = rmbsApplicationId;
	}

	@Column(name="rmbs_disbursement_id", nullable=false)
	public long getRmbsDisbursementId() {
		return rmbsDisbursementId;
	}

	public void setRmbsDisbursementId(long rmbsDisbursementId) {
		this.rmbsDisbursementId = rmbsDisbursementId;
	}
	
	public boolean equals(Object other) {
        if ((this == other )) return true;
		 if ((other == null )) return false;
		 if (!(other instanceof RmbsApplicationDisbursementId)) return false;
		 RmbsApplicationDisbursementId castOther = ( RmbsApplicationDisbursementId ) other; 
        
		 return (this.getRmbsApplicationId()==castOther.getRmbsApplicationId()) && (this.getRmbsDisbursementId()==castOther.getRmbsDisbursementId());
  }
  
  public int hashCode() {
        int result = 17;
        
        result = 37 * result + (int) this.getRmbsApplicationId();
        result = 37 * result + (int) this.getRmbsDisbursementId();
        return result;
  }   

	

}


