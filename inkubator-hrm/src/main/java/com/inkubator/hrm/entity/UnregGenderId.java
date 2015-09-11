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
public class UnregGenderId  implements java.io.Serializable {


     private long unregId;
     private long genderId;

    public UnregGenderId() {
    }

    public UnregGenderId(long unregId, long genderId) {
       this.unregId = unregId;
       this.genderId = genderId;
    }
   


    @Column(name="unreg_id", nullable=false)
    public long getUnregId() {
        return this.unregId;
    }
    
    public void setUnregId(long unregId) {
        this.unregId = unregId;
    }


    @Column(name="gender_id", nullable=false)
    public long getGenderId() {
        return this.genderId;
    }
    
    public void setGenderId(long genderId) {
        this.genderId = genderId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UnregGenderId) ) return false;
		 UnregGenderId castOther = ( UnregGenderId ) other; 
         
		 return (this.getUnregId()==castOther.getUnregId())
 && (this.getGenderId()==castOther.getGenderId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getUnregId();
         result = 37 * result + (int) this.getGenderId();
         return result;
   }   


}
