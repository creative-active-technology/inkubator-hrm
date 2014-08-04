/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="people_interest"
    ,catalog="hrm"
)
public class PeopleInterest implements java.io.Serializable{
     private long id;
     private Integer version;
     private BioData biodata;
     private InterestType interestType;
     private String name;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;

    public PeopleInterest() {
    }

    public PeopleInterest(long id) {
        this.id = id;
    }

    public PeopleInterest(long id, Integer version, BioData biodata, InterestType interestType, String name, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
        this.id = id;
        this.version = version;
        this.biodata = biodata;
        this.interestType = interestType;
        this.name = name;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biodata_id")
    public BioData getBiodata() {
        return biodata;
    }

    public void setBiodata(BioData biodata) {
        this.biodata = biodata;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_type_id")
    public InterestType getInterestType() {
        return interestType;
    }

    public void setInterestType(InterestType interestType) {
        this.interestType = interestType;
    }

    @Column(name="interest_name", length=100)
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
    @Column(name = "created_on", length = 19)
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
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
     
     
}
