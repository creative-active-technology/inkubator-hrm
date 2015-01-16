package com.inkubator.hrm.entity;
// Generated Dec 30, 2014 11:22:24 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UnregPayComponentsExceptionId generated by hbm2java
 */
@Embeddable
public class UnregPayComponentsExceptionId  implements java.io.Serializable {


     private long unregPayComponentsId;
     private long empDataId;

    public UnregPayComponentsExceptionId() {
    }

    public UnregPayComponentsExceptionId(long unregPayComponentsId, long empDataId) {
       this.unregPayComponentsId = unregPayComponentsId;
       this.empDataId = empDataId;
    }

    @Column(name="unreg_pay_components_id", nullable=false)
    public long getUnregPayComponentsId() {
		return unregPayComponentsId;
	}

	public void setUnregPayComponentsId(long unregPayComponentsId) {
		this.unregPayComponentsId = unregPayComponentsId;
	}

    @Column(name="emp_data_id", nullable=false)
    public long getEmpDataId() {
        return this.empDataId;
    }
    
    public void setEmpDataId(long empDataId) {
        this.empDataId = empDataId;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UnregPayComponentsExceptionId) ) return false;
		 UnregPayComponentsExceptionId castOther = ( UnregPayComponentsExceptionId ) other; 
         
		 return (this.getUnregPayComponentsId()==castOther.getUnregPayComponentsId()) && (this.getEmpDataId()==castOther.getEmpDataId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getUnregPayComponentsId();
         result = 37 * result + (int) this.getEmpDataId();
         return result;
   }   


}

