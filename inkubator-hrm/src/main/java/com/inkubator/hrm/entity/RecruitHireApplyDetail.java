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
import javax.persistence.Version;

/**
 * RecruitHireApplyDetail generated by hbm2java
 */
@Entity
@Table(name="recruit_hire_apply_detail"
    ,catalog="hrm"
)
public class RecruitHireApplyDetail  implements java.io.Serializable {


     private long id;
     private Integer version;
     private OrgTypeOfSpecList orgTypeOfSpecList;
     private RecruitHireApply recruitHireApply;
     private Date createdOn;
     private String createdBy;

    public RecruitHireApplyDetail() {
    }

	
    public RecruitHireApplyDetail(long id) {
        this.id = id;
    }
    public RecruitHireApplyDetail(long id, OrgTypeOfSpecList orgTypeOfSpecList, RecruitHireApply recruitHireApply, Date createdOn, String createdBy) {
       this.id = id;
       this.orgTypeOfSpecList = orgTypeOfSpecList;
       this.recruitHireApply = recruitHireApply;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
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
    @JoinColumn(name="org_jabatan_spec_list_id")
    public OrgTypeOfSpecList getOrgTypeOfSpecList() {
        return this.orgTypeOfSpecList;
    }
    
    public void setOrgTypeOfSpecList(OrgTypeOfSpecList orgTypeOfSpecList) {
        this.orgTypeOfSpecList = orgTypeOfSpecList;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="hire_apply_id")
    public RecruitHireApply getRecruitHireApply() {
        return this.recruitHireApply;
    }
    
    public void setRecruitHireApply(RecruitHireApply recruitHireApply) {
        this.recruitHireApply = recruitHireApply;
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


