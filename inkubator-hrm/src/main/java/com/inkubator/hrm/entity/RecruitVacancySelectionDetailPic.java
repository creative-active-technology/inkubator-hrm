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
@Table(name="recruit_vacancy_selection_detail_pic"
    ,catalog="hrm"
)
public class RecruitVacancySelectionDetailPic  implements java.io.Serializable {


     private RecruitVacancySelectionDetailPicId id;
     private Integer version;
     private EmpData empData;
     private RecruitVacancySelectionDetail recruitVacancySelectionDetail;
     private Date createdOn;
     private String createdBy;

    public RecruitVacancySelectionDetailPic() {
    }

	
    public RecruitVacancySelectionDetailPic(RecruitVacancySelectionDetailPicId id, EmpData empData, RecruitVacancySelectionDetail recruitVacancySelectionDetail) {
        this.id = id;
        this.empData = empData;
        this.recruitVacancySelectionDetail = recruitVacancySelectionDetail;
    }
    public RecruitVacancySelectionDetailPic(RecruitVacancySelectionDetailPicId id, EmpData empData, RecruitVacancySelectionDetail recruitVacancySelectionDetail, Date createdOn, String createdBy) {
       this.id = id;
       this.empData = empData;
       this.recruitVacancySelectionDetail = recruitVacancySelectionDetail;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="recruitVacancySelectionDetailId", column=@Column(name="recruit_vacancy_selection_detail_id", nullable=false) ), 
        @AttributeOverride(name="empDataId", column=@Column(name="emp_data_id", nullable=false) ) } )
    public RecruitVacancySelectionDetailPicId getId() {
        return this.id;
    }
    
    public void setId(RecruitVacancySelectionDetailPicId id) {
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
    @JoinColumn(name="emp_data_id", nullable=false, insertable=false, updatable=false)
    public EmpData getEmpData() {
        return this.empData;
    }
    
    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="recruit_vacancy_selection_detail_id", nullable=false, insertable=false, updatable=false)
    public RecruitVacancySelectionDetail getRecruitVacancySelectionDetail() {
        return this.recruitVacancySelectionDetail;
    }
    
    public void setRecruitVacancySelectionDetail(RecruitVacancySelectionDetail recruitVacancySelectionDetail) {
        this.recruitVacancySelectionDetail = recruitVacancySelectionDetail;
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