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
 * @author deni
 */
@Embeddable
public class BioSpesifikasiAbilityId  implements java.io.Serializable {


     private long biodataId;
     private long specificationId;

    public BioSpesifikasiAbilityId() {
    }

    public BioSpesifikasiAbilityId(long biodataId, long specificationId) {
       this.biodataId = biodataId;
       this.specificationId = specificationId;
    }
   


    @Column(name="biodata_id", nullable=false)
    public long getBiodataId() {
        return this.biodataId;
    }
    
    public void setBiodataId(long biodataId) {
        this.biodataId = biodataId;
    }


    @Column(name="specification_id", nullable=false)
    public long getSpecificationId() {
        return this.specificationId;
    }
    
    public void setSpecificationId(long specificationId) {
        this.specificationId = specificationId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BioSpesifikasiAbilityId) ) return false;
		 BioSpesifikasiAbilityId castOther = ( BioSpesifikasiAbilityId ) other; 
         
		 return (this.getBiodataId()==castOther.getBiodataId())
 && (this.getSpecificationId()==castOther.getSpecificationId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getBiodataId();
         result = 37 * result + (int) this.getSpecificationId();
         return result;
   }   


}
