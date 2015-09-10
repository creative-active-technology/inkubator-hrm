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
@Table(name="unreg_gender"
    ,catalog="hrm_payroll"
)
public class UnregGender  implements java.io.Serializable {


     private UnregGenderId id;
     private Integer version;
     private Gender gender;
     private UnregSalary unregSalary;
     private String descriptions;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;

    public UnregGender() {
    }

	
    public UnregGender(UnregGenderId id, Gender gender, UnregSalary unregSalary) {
        this.id = id;
        this.gender = gender;
        this.unregSalary = unregSalary;
    }
    public UnregGender(UnregGenderId id, Gender gender, UnregSalary unregSalary, String descriptions, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
       this.id = id;
       this.gender = gender;
       this.unregSalary = unregSalary;
       this.descriptions = descriptions;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="unregId", column=@Column(name="unreg_id", nullable=false) ), 
        @AttributeOverride(name="genderId", column=@Column(name="gender_id", nullable=false) ) } )
    public UnregGenderId getId() {
        return this.id;
    }
    
    public void setId(UnregGenderId id) {
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
    @JoinColumn(name="gender_id", nullable=false, insertable=false, updatable=false)
    public Gender getGender() {
        return this.gender;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="unreg_id", nullable=false, insertable=false, updatable=false)
    public UnregSalary getUnregSalary() {
        return this.unregSalary;
    }
    
    public void setUnregSalary(UnregSalary unregSalary) {
        this.unregSalary = unregSalary;
    }

    
    @Column(name="descriptions", length=65535, columnDefinition="Text")
    public String getDescriptions() {
        return this.descriptions;
    }
    
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
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




}
