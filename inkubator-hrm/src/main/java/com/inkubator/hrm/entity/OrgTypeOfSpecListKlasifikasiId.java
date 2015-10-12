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
public class OrgTypeOfSpecListKlasifikasiId  implements java.io.Serializable {


     private long orgTypeOfSpecListId;
     private long klasifikasiId;

    public OrgTypeOfSpecListKlasifikasiId() {
    }

    public OrgTypeOfSpecListKlasifikasiId(long orgTypeOfSpecListId, long klasifikasiId) {
       this.orgTypeOfSpecListId = orgTypeOfSpecListId;
       this.klasifikasiId = klasifikasiId;
    }
   


    @Column(name="org_type_of_spec_list_id", nullable=false)
    public long getOrgTypeOfSpecListId() {
        return this.orgTypeOfSpecListId;
    }
    
    public void setOrgTypeOfSpecListId(long orgTypeOfSpecListId) {
        this.orgTypeOfSpecListId = orgTypeOfSpecListId;
    }


    @Column(name="klasifikasi_id", nullable=false)
    public long getKlasifikasiId() {
        return this.klasifikasiId;
    }
    
    public void setKlasifikasiId(long klasifikasiId) {
        this.klasifikasiId = klasifikasiId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OrgTypeOfSpecListKlasifikasiId) ) return false;
		 OrgTypeOfSpecListKlasifikasiId castOther = ( OrgTypeOfSpecListKlasifikasiId ) other; 
         
		 return (this.getOrgTypeOfSpecListId()==castOther.getOrgTypeOfSpecListId())
 && (this.getKlasifikasiId()==castOther.getKlasifikasiId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getOrgTypeOfSpecListId();
         result = 37 * result + (int) this.getKlasifikasiId();
         return result;
   }   


}

