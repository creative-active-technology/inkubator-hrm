/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.beans.Transient;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="career_transition"
    ,catalog="hrm"
    , uniqueConstraints = {@UniqueConstraint(columnNames="transition_code"), @UniqueConstraint(columnNames="transition_name")} 
)
public class CareerTransition implements java.io.Serializable {


     private Long id;
     private Integer version;
     private CareerEmpStatus careerEmpStatus;
     private CareerTerminationType careerTerminationType;
     private SystemCareerConst systemCareerConst;
     private String transitionCode;
     private String transitionName;
     private Integer transitionRole;
     private Date createdOn;
     private String createdBy;
     private String updatedBy;
     private Date updatedOn;
     private String description;
     private SystemLetterReference systemLetterReference;

    public CareerTransition() {
    }

	
    public CareerTransition(Long id, String transitionCode, String transitionName, Integer transitionRole) {
        this.id = id;
        this.transitionCode = transitionCode;
        this.transitionName = transitionName;
        this.transitionRole = transitionRole;
    }
    public CareerTransition(Long id, CareerEmpStatus careerEmpStatus, CareerTerminationType careerTerminationType, SystemCareerConst systemCareerConst, String transitionCode, String transitionName, Integer transitionRole, Date createdOn, String createdBy, String updatedBy, Date updatedOn) {
       this.id = id;
       this.careerEmpStatus = careerEmpStatus;
       this.careerTerminationType = careerTerminationType;
       this.systemCareerConst = systemCareerConst;
       this.transitionCode = transitionCode;
       this.transitionName = transitionName;
       this.transitionRole = transitionRole;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
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
    @JoinColumn(name="career_emp_status_id")
    public CareerEmpStatus getCareerEmpStatus() {
        return this.careerEmpStatus;
    }
    
    public void setCareerEmpStatus(CareerEmpStatus careerEmpStatus) {
        this.careerEmpStatus = careerEmpStatus;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="career_termination_type_id")
   	public CareerTerminationType getCareerTerminationType() {
        return this.careerTerminationType;
    }
    
    public void setCareerTerminationType(CareerTerminationType careerTerminationType) {
        this.careerTerminationType = careerTerminationType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="system_career_const")
    public SystemCareerConst getSystemCareerConst() {
        return this.systemCareerConst;
    }
    
    public void setSystemCareerConst(SystemCareerConst systemCareerConst) {
        this.systemCareerConst = systemCareerConst;
    }

    
    @Column(name="transition_code", unique=true, nullable=false, length=45)
    public String getTransitionCode() {
        return this.transitionCode;
    }
    
    public void setTransitionCode(String transitionCode) {
        this.transitionCode = transitionCode;
    }

    
    @Column(name="transition_name", unique=true, nullable=false, length=45)
    public String getTransitionName() {
        return this.transitionName;
    }
    
    public void setTransitionName(String transitionName) {
        this.transitionName = transitionName;
    }

    
    @Column(name="transition_role", nullable=false, length=11)
    public Integer getTransitionRole() {
        return this.transitionRole;
    }
    
    public void setTransitionRole(Integer transitionRole) {
        this.transitionRole = transitionRole;
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

	@Column(name = "description", length = 65535, columnDefinition = "Text")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="system_letter_reference_id")
	public SystemLetterReference getSystemLetterReference() {
		return systemLetterReference;
	}


	public void setSystemLetterReference(SystemLetterReference systemLetterReference) {
		this.systemLetterReference = systemLetterReference;
	}


}


