/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

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
 *
 * @author Deni
 */
@Entity
@Table(name="recruit_selection_applicant_initial"
    ,catalog="hrm"
)
public class RecruitSelectionApplicantInitial  implements java.io.Serializable {


     private RecruitSelectionApplicantInitialId id;
     private Integer version;
     private RecruitApplicant recruitApplicant;
     private RecruitHireApply recruitHireApply;
     private Date createdOn;
     private String createdBy;

    public RecruitSelectionApplicantInitial() {
    }

	
    public RecruitSelectionApplicantInitial(RecruitSelectionApplicantInitialId id, RecruitApplicant recruitApplicant, RecruitHireApply recruitHireApply) {
        this.id = id;
        this.recruitApplicant = recruitApplicant;
        this.recruitHireApply = recruitHireApply;
    }
    public RecruitSelectionApplicantInitial(RecruitSelectionApplicantInitialId id, RecruitApplicant recruitApplicant, RecruitHireApply recruitHireApply, Date createdOn, String createdBy) {
       this.id = id;
       this.recruitApplicant = recruitApplicant;
       this.recruitHireApply = recruitHireApply;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="applicantId", column=@Column(name="applicant_id", nullable=false) ), 
        @AttributeOverride(name="hireApplyId", column=@Column(name="hire_apply_id", nullable=false) ) } )
    public RecruitSelectionApplicantInitialId getId() {
        return this.id;
    }
    
    public void setId(RecruitSelectionApplicantInitialId id) {
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
    @JoinColumn(name="applicant_id", nullable=false, insertable=false, updatable=false)
    public RecruitApplicant getRecruitApplicant() {
        return this.recruitApplicant;
    }
    
    public void setRecruitApplicant(RecruitApplicant recruitApplicant) {
        this.recruitApplicant = recruitApplicant;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="hire_apply_id", nullable=false, insertable=false, updatable=false)
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