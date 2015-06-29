/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="recruit_vacancy_selection_detail"
    ,catalog="hrm"
)
public class RecruitVacancySelectionDetail  implements java.io.Serializable {


     private long id;
     private Integer version;
     private RecruitSelectionType recruitSelectionType;
     private RecruitVacancySelection recruitVacancySelection;
     private Date startDate;
     private Date endDate;
     private Date time;
     private String place;
     private BigDecimal basicCost;
     private BigDecimal individualCost;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updatedBy;
     private Set<RecruitVacancySelectionDetailPic> recruitVacancySelectionDetailPics = new HashSet<RecruitVacancySelectionDetailPic>(0);
     private List<EmpData> listEmpData;
     
    public RecruitVacancySelectionDetail() {
    }

	
    public RecruitVacancySelectionDetail(long id, RecruitSelectionType recruitSelectionType, RecruitVacancySelection recruitVacancySelection) {
        this.id = id;
        this.recruitSelectionType = recruitSelectionType;
        this.recruitVacancySelection = recruitVacancySelection;
    }
    public RecruitVacancySelectionDetail(long id, RecruitSelectionType recruitSelectionType, RecruitVacancySelection recruitVacancySelection, Date startDate, Date endDate, Date time, String place, BigDecimal basicCost, BigDecimal individualCost, Date createdOn, String createdBy, Date updatedOn, String updatedBy, Set<RecruitVacancySelectionDetailPic> recruitVacancySelectionDetailPics) {
       this.id = id;
       this.recruitSelectionType = recruitSelectionType;
       this.recruitVacancySelection = recruitVacancySelection;
       this.startDate = startDate;
       this.endDate = endDate;
       this.time = time;
       this.place = place;
       this.basicCost = basicCost;
       this.individualCost = individualCost;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedOn = updatedOn;
       this.updatedBy = updatedBy;
       this.recruitVacancySelectionDetailPics = recruitVacancySelectionDetailPics;
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
    @JoinColumn(name="selection_type_id", nullable=false)
    public RecruitSelectionType getRecruitSelectionType() {
        return this.recruitSelectionType;
    }
    
    public void setRecruitSelectionType(RecruitSelectionType recruitSelectionType) {
        this.recruitSelectionType = recruitSelectionType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="vacancy_selection_id", nullable=false)
    public RecruitVacancySelection getRecruitVacancySelection() {
        return this.recruitVacancySelection;
    }
    
    public void setRecruitVacancySelection(RecruitVacancySelection recruitVacancySelection) {
        this.recruitVacancySelection = recruitVacancySelection;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="start_date", length=10)
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="end_date", length=10)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Temporal(TemporalType.TIME)
    @Column(name="time", length=8)
    public Date getTime() {
        return this.time;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }

    
    @Column(name="place", length=80)
    public String getPlace() {
        return this.place;
    }
    
    public void setPlace(String place) {
        this.place = place;
    }

    
    @Column(name="basic_cost", precision=10, scale=0)
    public BigDecimal getBasicCost() {
		return basicCost;
	}


	public void setBasicCost(BigDecimal basicCost) {
		this.basicCost = basicCost;
	}

    
    @Column(name="individual_cost", precision=10, scale=0)
    public BigDecimal getIndividualCost() {
		return individualCost;
	}


	public void setIndividualCost(BigDecimal individualCost) {
		this.individualCost = individualCost;
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

@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="recruitVacancySelectionDetail", orphanRemoval = true)
    public Set<RecruitVacancySelectionDetailPic> getRecruitVacancySelectionDetailPics() {
        return this.recruitVacancySelectionDetailPics;
    }
    
    public void setRecruitVacancySelectionDetailPics(Set<RecruitVacancySelectionDetailPic> recruitVacancySelectionDetailPics) {
        this.recruitVacancySelectionDetailPics = recruitVacancySelectionDetailPics;
    }

    @Transient
	public List<EmpData> getListEmpData() {
		return listEmpData;
	}


	public void setListEmpData(List<EmpData> listEmpData) {
		this.listEmpData = listEmpData;
	}




}