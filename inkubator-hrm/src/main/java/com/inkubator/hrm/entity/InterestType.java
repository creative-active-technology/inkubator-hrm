/*
 * To change this template, choose Tools | Templates
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
@Table(name="interest_type"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="interest_type_name") 
)
public class InterestType implements java.io.Serializable {
    private long id;
    private Integer version;
    private String name;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private String description;
    private Set<PeopleInterest> peopleInterests = new HashSet<PeopleInterest>(0);

    public InterestType() {
    }

    public InterestType(long id) {
        this.id = id;
    }

    public InterestType(long id, Integer version, String name, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String description, Set<PeopleInterest> peopleInterests) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.description = description;
        this.peopleInterests = peopleInterests;
    }

    @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

     @Version
    @Column(name="version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name="interest_type_name", length=100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

     @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Column(name="description", length=65535)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "interestType")
    public Set<PeopleInterest> getPeopleInterests() {
        return peopleInterests;
    }

    public void setPeopleInterests(Set<PeopleInterest> peopleInterests) {
        this.peopleInterests = peopleInterests;
    }
     
     
}
