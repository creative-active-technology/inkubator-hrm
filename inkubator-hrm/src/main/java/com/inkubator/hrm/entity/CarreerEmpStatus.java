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
@Table(name="carreer_emp_status"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="code") 
)
public class CarreerEmpStatus implements java.io.Serializable {


     private long id;
     private Integer version;
     private EmployeeType employeeType;
     private SystemLetterReference systemLetterReference;
     private String code;
     private String name;
     private Integer setLimitTime;
     private Byte autoMove;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     private Set<CarreerTransition> carreerTransitions = new HashSet<CarreerTransition>(0);

    public CarreerEmpStatus() {
    }

	
    public CarreerEmpStatus(long id) {
        this.id = id;
    }
    public CarreerEmpStatus(long id, EmployeeType employeeType, SystemLetterReference systemLetterReference, String code, String name, Integer setLimitTime, Byte autoMove, String createdBy, Date createdOn, String updatedBy, Date updatedOn, Set<CarreerTransition> carreerTransitions) {
       this.id = id;
       this.employeeType = employeeType;
       this.systemLetterReference = systemLetterReference;
       this.code = code;
       this.name = name;
       this.setLimitTime = setLimitTime;
       this.autoMove = autoMove;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.carreerTransitions = carreerTransitions;
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
    @JoinColumn(name="employee_type_id")
    public EmployeeType getEmployeeType() {
        return this.employeeType;
    }
    
    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sys_letter_id")
    public SystemLetterReference getSystemLetterReference() {
        return this.systemLetterReference;
    }
    
    public void setSystemLetterReference(SystemLetterReference systemLetterReference) {
        this.systemLetterReference = systemLetterReference;
    }

    
    @Column(name="code", unique=true, length=12)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="name", length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="set_limit_time")
    public Integer getSetLimitTime() {
        return this.setLimitTime;
    }
    
    public void setSetLimitTime(Integer setLimitTime) {
        this.setLimitTime = setLimitTime;
    }

    
    @Column(name="auto_move")
    public Byte getAutoMove() {
        return this.autoMove;
    }
    
    public void setAutoMove(Byte autoMove) {
        this.autoMove = autoMove;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="carreerEmpStatus")
    public Set<CarreerTransition> getCarreerTransitions() {
        return this.carreerTransitions;
    }
    
    public void setCarreerTransitions(Set<CarreerTransition> carreerTransitions) {
        this.carreerTransitions = carreerTransitions;
    }




}


