/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="system_career_const"
    ,catalog="hrm"
)
public class SystemCareerConst implements java.io.Serializable {


     private Long id;
     private String constant;
     private String description;
     private Boolean isWork;
     private String name;
     private Set<CareerTransition> careerTransitions = new HashSet<CareerTransition>(0);

    public SystemCareerConst() {
    }

	
    public SystemCareerConst(Long id) {
        this.id = id;
    }
    public SystemCareerConst(Long id, String constant, String description, Boolean isWork, String name, Set<CareerTransition> careerTransitions) {
       this.id = id;
       this.constant = constant;
       this.description = description;
       this.isWork = isWork;
       this.name = name;
       this.careerTransitions = careerTransitions;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    
    @Column(name="constant", length=45)
    public String getConstant() {
		return constant;
	}


	public void setConstant(String constant) {
		this.constant = constant;
	}

    
	@Column(name = "description", length = 65535, columnDefinition = "Text")
    public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name="is_work")
    public Boolean getIsWork() {
        return this.isWork;
    }
    
    public void setIsWork(Boolean isWork) {
        this.isWork = isWork;
    }

    
    @Column(name="name", length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="systemCareerConst")
    public Set<CareerTransition> getCareerTransitions() {
        return this.careerTransitions;
    }
    
    public void setCareerTransitions(Set<CareerTransition> careerTransitions) {
        this.careerTransitions = careerTransitions;
    }




}


