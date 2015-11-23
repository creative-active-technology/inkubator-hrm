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
     private String const_;
     private String desc;
     private Byte isWork;
     private String name;
     private Set<CareerTransition> careerTransitions = new HashSet<CareerTransition>(0);

    public SystemCareerConst() {
    }

	
    public SystemCareerConst(Long id) {
        this.id = id;
    }
    public SystemCareerConst(Long id, String const_, String desc, Byte isWork, String name, Set<CareerTransition> careerTransitions) {
       this.id = id;
       this.const_ = const_;
       this.desc = desc;
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

    
    @Column(name="const", length=45)
    public String getConst_() {
        return this.const_;
    }
    
    public void setConst_(String const_) {
        this.const_ = const_;
    }

    
    @Column(name="desc")
    public String getDesc() {
        return this.desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }

    
    @Column(name="is_work")
    public Byte getIsWork() {
        return this.isWork;
    }
    
    public void setIsWork(Byte isWork) {
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


