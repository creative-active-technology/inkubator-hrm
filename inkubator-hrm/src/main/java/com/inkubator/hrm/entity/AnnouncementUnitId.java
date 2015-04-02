package com.inkubator.hrm.entity;
// Generated Apr 2, 2015 2:03:18 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AnnouncementUnitId generated by hbm2java
 */
@Embeddable
public class AnnouncementUnitId  implements java.io.Serializable {


     private long announcementJd;
     private long unitId;

    public AnnouncementUnitId() {
    }

    public AnnouncementUnitId(long announcementJd, long unitId) {
       this.announcementJd = announcementJd;
       this.unitId = unitId;
    }
   


    @Column(name="announcement_jd", nullable=false)
    public long getAnnouncementJd() {
        return this.announcementJd;
    }
    
    public void setAnnouncementJd(long announcementJd) {
        this.announcementJd = announcementJd;
    }


    @Column(name="unit_id", nullable=false)
    public long getUnitId() {
        return this.unitId;
    }
    
    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AnnouncementUnitId) ) return false;
		 AnnouncementUnitId castOther = ( AnnouncementUnitId ) other; 
         
		 return (this.getAnnouncementJd()==castOther.getAnnouncementJd())
 && (this.getUnitId()==castOther.getUnitId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getAnnouncementJd();
         result = 37 * result + (int) this.getUnitId();
         return result;
   }   


}


