/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="carreer_transition"
    ,catalog="hrm"
    , uniqueConstraints = {@UniqueConstraint(columnNames="transition_code"), @UniqueConstraint(columnNames="transition_name")} 
)
public class CarreerTransition implements java.io.Serializable {


     private int id;
     private Integer version;
     private CarreerEmpStatus carreerEmpStatus;
     private CarreerTerminationType carreerTerminationType;
     private SystemCarreerConst systemCarreerConst;
     private String transitionCode;
     private String transitionName;
     private String transitionRole;
     private Date createdOn;
     private String createdBy;
     private String updatedBy;
     private Date updatedOn;

    public CarreerTransition() {
    }

	
    public CarreerTransition(int id, String transitionCode, String transitionName, String transitionRole) {
        this.id = id;
        this.transitionCode = transitionCode;
        this.transitionName = transitionName;
        this.transitionRole = transitionRole;
    }
    public CarreerTransition(int id, CarreerEmpStatus carreerEmpStatus, CarreerTerminationType carreerTerminationType, SystemCarreerConst systemCarreerConst, String transitionCode, String transitionName, String transitionRole, Date createdOn, String createdBy, String updatedBy, Date updatedOn) {
       this.id = id;
       this.carreerEmpStatus = carreerEmpStatus;
       this.carreerTerminationType = carreerTerminationType;
       this.systemCarreerConst = systemCarreerConst;
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
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
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
    @JoinColumn(name="carreer_emp_status_id")
    public CarreerEmpStatus getCarreerEmpStatus() {
        return this.carreerEmpStatus;
    }
    
    public void setCarreerEmpStatus(CarreerEmpStatus carreerEmpStatus) {
        this.carreerEmpStatus = carreerEmpStatus;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="carreer_termination_type_id")
    public CarreerTerminationType getCarreerTerminationType() {
        return this.carreerTerminationType;
    }
    
    public void setCarreerTerminationType(CarreerTerminationType carreerTerminationType) {
        this.carreerTerminationType = carreerTerminationType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="system_carreer_const")
    public SystemCarreerConst getSystemCarreerConst() {
        return this.systemCarreerConst;
    }
    
    public void setSystemCarreerConst(SystemCarreerConst systemCarreerConst) {
        this.systemCarreerConst = systemCarreerConst;
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
    public String getTransitionRole() {
        return this.transitionRole;
    }
    
    public void setTransitionRole(String transitionRole) {
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




}


