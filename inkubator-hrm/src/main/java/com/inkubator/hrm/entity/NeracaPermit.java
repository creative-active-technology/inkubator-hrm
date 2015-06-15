package com.inkubator.hrm.entity;
// Generated Nov 6, 2014 2:42:30 PM by Hibernate Tools 3.6.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * NeracaPermit generated by hbm2java
 */
@Entity
@Table(name="neraca_permit"
    ,catalog="hrm_payroll_backup"
)
public class NeracaPermit  implements java.io.Serializable {


     private long id;
     private Integer version;
     private PermitDistribution permitDistribution;
     private String createdBy;
     private Date createdOn;
     private Double debet;
     private Double kredit;
     private String updatedBy;
     private Date updatedOn;

    public NeracaPermit() {
    }

	
    public NeracaPermit(long id) {
        this.id = id;
    }
    public NeracaPermit(long id, PermitDistribution permitDistribution, String createdBy, Date createdOn, Double debet, Double kredit, String updatedBy, Date updatedOn) {
       this.id = id;
       this.permitDistribution = permitDistribution;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.debet = debet;
       this.kredit = kredit;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="permit_distribusi_id")
    public PermitDistribution getPermitDistribution() {
        return this.permitDistribution;
    }
    
    public void setPermitDistribution(PermitDistribution permitDistribution) {
        this.permitDistribution = permitDistribution;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="debet", precision=22, scale=0)
    public Double getDebet() {
        return this.debet;
    }
    
    public void setDebet(Double debet) {
        this.debet = debet;
    }

    
    @Column(name="kredit", precision=22, scale=0)
    public Double getKredit() {
        return this.kredit;
    }
    
    public void setKredit(Double kredit) {
        this.kredit = kredit;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }




}


