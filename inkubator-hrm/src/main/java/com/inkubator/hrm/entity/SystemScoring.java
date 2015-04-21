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
@Table(name="system_scoring"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="code") 
)
public class SystemScoring  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String code;
     private String name;
     private String description;
     private String createdBy;
     private String updatedBy;
     private Date createdOn;
     private Date updatedOn;
     private Set<SystemScoringIndex> systemScoringIndexes = new HashSet<SystemScoringIndex>(0);

    public SystemScoring() {
    }

    public SystemScoring(long id) {
        this.id = id;
    }
	
    public SystemScoring(long id, String code) {
        this.id = id;
        this.code = code;
    }
    public SystemScoring(long id, String code, String name, String description, String createdBy, String updatedBy, Date createdOn, Date updatedOn, Set<SystemScoringIndex> systemScoringIndexes) {
       this.id = id;
       this.code = code;
       this.name = name;
       this.description = description;
       this.createdBy = createdBy;
       this.updatedBy = updatedBy;
       this.createdOn = createdOn;
       this.updatedOn = updatedOn;
       this.systemScoringIndexes = systemScoringIndexes;
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

    
    @Column(name="code", unique=true, nullable=false, length=8)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="system_scoring_name", length=100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="systemScoring")
    public Set<SystemScoringIndex> getSystemScoringIndexes() {
        return this.systemScoringIndexes;
    }
    
    public void setSystemScoringIndexes(Set<SystemScoringIndex> systemScoringIndexes) {
        this.systemScoringIndexes = systemScoringIndexes;
    }




}