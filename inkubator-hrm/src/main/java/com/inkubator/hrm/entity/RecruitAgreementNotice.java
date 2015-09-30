/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.math.BigDecimal;
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
@Table(name="recruit_agreement_notice"
    ,catalog="hrm"
)
public class RecruitAgreementNotice  implements java.io.Serializable {


     private Long id;
     private Integer version;
     private BioData bioData;
     private String resourceType;
     private String uploadedCv;
     private BigDecimal lastSalary;
     private BigDecimal expectedSalary;
     private String description;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updatedBy;

    public RecruitAgreementNotice() {
    }

	
    public RecruitAgreementNotice(Long id) {
        this.id = id;
    }
    public RecruitAgreementNotice(Long id, BioData bioData, String resourceType, String uploadedCv, BigDecimal lastSalary, BigDecimal expectedSalary, String description, Date createdOn, String createdBy, Date updatedOn, String updatedBy) {
       this.id = id;
       this.bioData = bioData;
       this.resourceType = resourceType;
       this.uploadedCv = uploadedCv;
       this.lastSalary = lastSalary;
       this.expectedSalary = expectedSalary;
       this.description = description;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedOn = updatedOn;
       this.updatedBy = updatedBy;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
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
    @JoinColumn(name="bio_data_id")
    public BioData getBioData() {
        return this.bioData;
    }
    
    public void setBioData(BioData bioData) {
        this.bioData = bioData;
    }

    
    @Column(name="resource_type", length=2)
    public String getResourceType() {
        return this.resourceType;
    }
    
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    
    @Column(name="uploaded_cv", length=45)
    public String getUploadedCv() {
        return this.uploadedCv;
    }
    
    public void setUploadedCv(String uploadedCv) {
        this.uploadedCv = uploadedCv;
    }

    
    @Column(name="last_salary", precision=10, scale=0)
    public BigDecimal getLastSalary() {
        return this.lastSalary;
    }
    
    public void setLastSalary(BigDecimal lastSalary) {
        this.lastSalary = lastSalary;
    }

    
    @Column(name="expected_salary", precision=10, scale=0)
    public BigDecimal getExpectedSalary() {
        return this.expectedSalary;
    }
    
    public void setExpectedSalary(BigDecimal expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }




}
