package com.inkubator.hrm.entity;
// Generated Apr 22, 2015 3:42:31 PM by Hibernate Tools 4.3.1


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
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * RecruitMppApplyDetail generated by hbm2java
 */
@Entity
@Table(name="recruit_mpp_apply_detail"
    ,catalog="hrm"
)
public class RecruitMppApplyDetail  implements java.io.Serializable {


     private long id;
     private Integer version;
     private Jabatan jabatan;
     private RecruitMppApply recruitMppApply;
     private Date recruitMppMonth;
     private Integer recruitPlan;
     private Integer actualNumber;
     private Integer difference;
     private Date createdOn;
     private String createdBy;
     private String updatedBy;
     private Date updatedOn;
     
    public RecruitMppApplyDetail() {
    }

	
    public RecruitMppApplyDetail(long id) {
        this.id = id;
    }
    public RecruitMppApplyDetail(long id, Jabatan jabatan, RecruitMppApply recruitMppApply, Date recruitMppMonth, Integer recruitPlan, Date createdOn, String createdBy, String updatedBy, Date updatedOn) {
       this.id = id;
       this.jabatan = jabatan;
       this.recruitMppApply = recruitMppApply;
       this.recruitMppMonth = recruitMppMonth;
       this.recruitPlan = recruitPlan;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
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
    @JoinColumn(name="jabatan_id")
    public Jabatan getJabatan() {
        return this.jabatan;
    }
    
    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="recruit_mpp_apply_id")
    public RecruitMppApply getRecruitMppApply() {
        return this.recruitMppApply;
    }
    
    public void setRecruitMppApply(RecruitMppApply recruitMppApply) {
        this.recruitMppApply = recruitMppApply;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="recruit_mpp_month", length=10)
    public Date getRecruitMppMonth() {
        return this.recruitMppMonth;
    }
    
    public void setRecruitMppMonth(Date recruitMppMonth) {
        this.recruitMppMonth = recruitMppMonth;
    }

    
    @Column(name="recruit_plan")
    public Integer getRecruitPlan() {
        return this.recruitPlan;
    }
    
    public void setRecruitPlan(Integer recruitPlan) {
        this.recruitPlan = recruitPlan;
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
    
    @Transient
    public Integer getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(Integer actualNumber) {
        this.actualNumber = actualNumber;
    }
    
    @Transient
    public Integer getDifference() {
        return difference;
    }

    public void setDifference(Integer difference) {
        this.difference = difference;
    }

    


    

}


