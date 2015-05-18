/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.math.BigDecimal;
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
 *
 * @author Deni
 */
@Entity
@Table(name="recruit_vacancy_selection"
    ,catalog="hrm"
)
public class RecruitVacancySelection  implements java.io.Serializable {


     private long id;
     private Integer version;
     private RecruitHireApply recruitHireApply;
     private RecruitmenSelectionSeries recruitmenSelectionSeries;
     private String code;
     private Date recruitVacancySelectionDate;
     private BigDecimal extraBudget;
     private Date createdOn;
     private String createdBy;
     private String updatedBy;
     private Date updatedOn;

    public RecruitVacancySelection() {
    }

	
    public RecruitVacancySelection(long id, RecruitHireApply recruitHireApply, RecruitmenSelectionSeries recruitmenSelectionSeries) {
        this.id = id;
        this.recruitHireApply = recruitHireApply;
        this.recruitmenSelectionSeries = recruitmenSelectionSeries;
    }
    public RecruitVacancySelection(long id, RecruitHireApply recruitHireApply, RecruitmenSelectionSeries recruitmenSelectionSeries, String code, Date recruitVacancySelectionDate, BigDecimal extraBudget, Date createdOn, String createdBy, String updatedBy, Date updatedOn) {
       this.id = id;
       this.recruitHireApply = recruitHireApply;
       this.recruitmenSelectionSeries = recruitmenSelectionSeries;
       this.code = code;
       this.recruitVacancySelectionDate = recruitVacancySelectionDate;
       this.extraBudget = extraBudget;
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
    @JoinColumn(name="recruit_hire_apply_id", nullable=false)
    public RecruitHireApply getRecruitHireApply() {
        return this.recruitHireApply;
    }
    
    public void setRecruitHireApply(RecruitHireApply recruitHireApply) {
        this.recruitHireApply = recruitHireApply;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="selection_series_id", nullable=false)
    public RecruitmenSelectionSeries getRecruitmenSelectionSeries() {
        return this.recruitmenSelectionSeries;
    }
    
    public void setRecruitmenSelectionSeries(RecruitmenSelectionSeries recruitmenSelectionSeries) {
        this.recruitmenSelectionSeries = recruitmenSelectionSeries;
    }

    
    @Column(name="recruit_vacancy_selection_code", length=12)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="recruit_vacancy_selection_date", length=10)
    public Date getRecruitVacancySelectionDate() {
        return this.recruitVacancySelectionDate;
    }
    
    public void setRecruitVacancySelectionDate(Date recruitVacancySelectionDate) {
        this.recruitVacancySelectionDate = recruitVacancySelectionDate;
    }

    
    @Column(name="extra_budget", precision=10, scale=0)
    public BigDecimal getExtraBudget() {
        return this.extraBudget;
    }
    
    public void setExtraBudget(BigDecimal extraBudget) {
        this.extraBudget = extraBudget;
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




}