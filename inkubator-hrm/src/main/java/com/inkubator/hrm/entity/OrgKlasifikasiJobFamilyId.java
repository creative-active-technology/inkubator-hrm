package com.inkubator.hrm.entity;
// Generated Apr 7, 2015 3:50:55 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OrgKlasifikasiJobFamilyId generated by hbm2java
 */
@Embeddable
public class OrgKlasifikasiJobFamilyId  implements java.io.Serializable {


     private long golonganJabatanId;
     private long klasifikasiKerjaId;

    public OrgKlasifikasiJobFamilyId() {
    }

    public OrgKlasifikasiJobFamilyId(long golonganJabatanId, long klasifikasiKerjaId) {
       this.golonganJabatanId = golonganJabatanId;
       this.klasifikasiKerjaId = klasifikasiKerjaId;
    }
   


    @Column(name="golongan_jabatan_id", nullable=false)
    public long getGolonganJabatanId() {
        return this.golonganJabatanId;
    }
    
    public void setGolonganJabatanId(long golonganJabatanId) {
        this.golonganJabatanId = golonganJabatanId;
    }


    @Column(name="klasifikasi_kerja_id", nullable=false)
    public long getKlasifikasiKerjaId() {
        return this.klasifikasiKerjaId;
    }
    
    public void setKlasifikasiKerjaId(long klasifikasiKerjaId) {
        this.klasifikasiKerjaId = klasifikasiKerjaId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OrgKlasifikasiJobFamilyId) ) return false;
		 OrgKlasifikasiJobFamilyId castOther = ( OrgKlasifikasiJobFamilyId ) other; 
         
		 return (this.getGolonganJabatanId()==castOther.getGolonganJabatanId())
 && (this.getKlasifikasiKerjaId()==castOther.getKlasifikasiKerjaId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getGolonganJabatanId();
         result = 37 * result + (int) this.getKlasifikasiKerjaId();
         return result;
   }   


}


