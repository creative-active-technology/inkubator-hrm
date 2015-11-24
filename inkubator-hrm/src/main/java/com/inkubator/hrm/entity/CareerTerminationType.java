/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

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
@Table(name="career_termination_type"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="code") 
)
public class CareerTerminationType implements java.io.Serializable {


     private long id;
     private Integer version;
     private SystemLetterReference systemLetterReference;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     private String code;
     private String name;
     private String description;
     private Set<CareerTransition> careerTransitions = new HashSet<CareerTransition>(0);

    public CareerTerminationType() {
    }

	
    public CareerTerminationType(long id) {
        this.id = id;
    }
    public CareerTerminationType(long id, SystemLetterReference systemLetterReference, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String code, String name, String description, Set<CareerTransition> careerTransitions) {
       this.id = id;
       this.systemLetterReference = systemLetterReference;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.code = code;
       this.name = name;
       this.description = description;
       this.careerTransitions = careerTransitions;
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
    @JoinColumn(name="letter_ref_id")
    public SystemLetterReference getSystemLetterReference() {
        return this.systemLetterReference;
    }
    
    public void setSystemLetterReference(SystemLetterReference systemLetterReference) {
        this.systemLetterReference = systemLetterReference;
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

    
    @Column(name="code", unique=true, length=8)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="name", length=60)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="description", length=65535, columnDefinition = "Text")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="careerTerminationType")
    public Set<CareerTransition> getCareerTransitions() {
        return this.careerTransitions;
    }
    
    public void setCareerTransitions(Set<CareerTransition> careerTransitions) {
        this.careerTransitions = careerTransitions;
    }




}


