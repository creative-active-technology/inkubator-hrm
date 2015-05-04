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
public class OrgTypeOfSpecJabatanId  implements java.io.Serializable {


     private long typeOfSpecListId;
     private long jabatanId;

    public OrgTypeOfSpecJabatanId() {
    }

    public OrgTypeOfSpecJabatanId(long typeOfSpecListId, long jabatanId) {
       this.typeOfSpecListId = typeOfSpecListId;
       this.jabatanId = jabatanId;
    }
   


    @Column(name="type_of_spec_list_id", nullable=false)
    public long getTypeOfSpecListId() {
        return this.typeOfSpecListId;
    }
    
    public void setTypeOfSpecListId(long typeOfSpecListId) {
        this.typeOfSpecListId = typeOfSpecListId;
    }


    @Column(name="jabatan_id", nullable=false)
    public long getJabatanId() {
        return this.jabatanId;
    }
    
    public void setJabatanId(long jabatanId) {
        this.jabatanId = jabatanId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OrgTypeOfSpecJabatanId) ) return false;
		 OrgTypeOfSpecJabatanId castOther = ( OrgTypeOfSpecJabatanId ) other; 
         
		 return (this.getTypeOfSpecListId()==castOther.getTypeOfSpecListId())
 && (this.getJabatanId()==castOther.getJabatanId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getTypeOfSpecListId();
         result = 37 * result + (int) this.getJabatanId();
         return result;
   }   


}