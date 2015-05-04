package com.inkubator.hrm.entity;
// Generated Jun 10, 2015 1:00:44 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * WtHitungLembur generated by hbm2java
 */
@Entity
@Table(name="wt_hitung_lembur"
    ,catalog="hrm"
)
public class WtHitungLembur  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String metodeHitung;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updatedBy;
     private Set<WtOverTime> wtOverTimes = new HashSet<WtOverTime>(0);
     private Set<WtHitungLemburJam> wtHitungLemburJams = new HashSet<WtHitungLemburJam>(0);

    public WtHitungLembur() {
    }

	
    public WtHitungLembur(long id) {
        this.id = id;
    }
    public WtHitungLembur(long id, String metodeHitung, Date createdOn, String createdBy, Date updatedOn, String updatedBy, Set<WtOverTime> wtOverTimes, Set<WtHitungLemburJam> wtHitungLemburJams) {
       this.id = id;
       this.metodeHitung = metodeHitung;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedOn = updatedOn;
       this.updatedBy = updatedBy;
       this.wtOverTimes = wtOverTimes;
       this.wtHitungLemburJams = wtHitungLemburJams;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
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

    
    @Column(name="metode_hitung", length=45)
    public String getMetodeHitung() {
        return this.metodeHitung;
    }
    
    public void setMetodeHitung(String metodeHitung) {
        this.metodeHitung = metodeHitung;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="wtHitungLembur")
    public Set<WtOverTime> getWtOverTimes() {
        return this.wtOverTimes;
    }
    
    public void setWtOverTimes(Set<WtOverTime> wtOverTimes) {
        this.wtOverTimes = wtOverTimes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="wtHitungLembur")
    public Set<WtHitungLemburJam> getWtHitungLemburJams() {
        return this.wtHitungLemburJams;
    }
    
    public void setWtHitungLemburJams(Set<WtHitungLemburJam> wtHitungLemburJams) {
        this.wtHitungLemburJams = wtHitungLemburJams;
    }




}


