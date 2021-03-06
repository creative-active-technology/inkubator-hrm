package com.inkubator.hrm.entity;
// Generated Apr 2, 2015 2:03:18 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * AnnouncementUnit generated by hbm2java
 */
@Entity
@Table(name="announcement_unit"
    ,catalog="hrm"
)
public class AnnouncementUnit  implements java.io.Serializable {


     private AnnouncementUnitId id;
     private Integer version;
     private Announcement announcement;
     private UnitKerja unitKerja;
     private Date createdOn;
     private String createdBy;

    public AnnouncementUnit() {
    }

	
    public AnnouncementUnit(AnnouncementUnitId id, Announcement announcement, UnitKerja unitKerja) {
        this.id = id;
        this.announcement = announcement;
        this.unitKerja = unitKerja;
    }
    public AnnouncementUnit(AnnouncementUnitId id, Announcement announcement, UnitKerja unitKerja, Date createdOn, String createdBy) {
       this.id = id;
       this.announcement = announcement;
       this.unitKerja = unitKerja;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="announcementId", column=@Column(name="announcement_id", nullable=false) ), 
        @AttributeOverride(name="unitId", column=@Column(name="unit_id", nullable=false) ) } )
    public AnnouncementUnitId getId() {
        return this.id;
    }
    
    public void setId(AnnouncementUnitId id) {
        this.id = id;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="announcement_id", nullable=false, insertable=false, updatable=false)
    public Announcement getAnnouncement() {
        return this.announcement;
    }
    
    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="unit_id", nullable=false, insertable=false, updatable=false)
    public UnitKerja getUnitKerja() {
        return this.unitKerja;
    }
    
    public void setUnitKerja(UnitKerja unitKerja) {
        this.unitKerja = unitKerja;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }




}


