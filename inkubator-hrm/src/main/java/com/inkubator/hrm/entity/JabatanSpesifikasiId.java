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
public class JabatanSpesifikasiId  implements java.io.Serializable {


     private long jabatanId;
     private long specificationId;

    public JabatanSpesifikasiId() {
    }

    public JabatanSpesifikasiId(long jabatanId, long specificationId) {
       this.jabatanId = jabatanId;
       this.specificationId = specificationId;
    }
   


    @Column(name="jabatan_id", nullable=false)
    public long getJabatanId() {
        return this.jabatanId;
    }
    
    public void setJabatanId(long jabatanId) {
        this.jabatanId = jabatanId;
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
		 if ( !(other instanceof JabatanSpesifikasiId) ) return false;
		 JabatanSpesifikasiId castOther = ( JabatanSpesifikasiId ) other; 
         
		 return (this.getJabatanId()==castOther.getJabatanId())
 && (this.getSpecificationId()==castOther.getSpecificationId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getJabatanId();
         result = 37 * result + (int) this.getSpecificationId();
         return result;
   }   


}


