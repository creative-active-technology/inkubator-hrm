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
@Table(name="system_carreer_const"
    ,catalog="hrm"
)
public class SystemCarreerConst implements java.io.Serializable {


     private int id;
     private String const_;
     private String desc;
     private Byte isWork;
     private String name;
     private Set<CarreerTransition> carreerTransitions = new HashSet<CarreerTransition>(0);

    public SystemCarreerConst() {
    }

	
    public SystemCarreerConst(int id) {
        this.id = id;
    }
    public SystemCarreerConst(int id, String const_, String desc, Byte isWork, String name, Set<CarreerTransition> carreerTransitions) {
       this.id = id;
       this.const_ = const_;
       this.desc = desc;
       this.isWork = isWork;
       this.name = name;
       this.carreerTransitions = carreerTransitions;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="systemCarreerConst")
    public Set<CarreerTransition> getCarreerTransitions() {
        return this.carreerTransitions;
    }
    
    public void setCarreerTransitions(Set<CarreerTransition> carreerTransitions) {
        this.carreerTransitions = carreerTransitions;
    }




}


