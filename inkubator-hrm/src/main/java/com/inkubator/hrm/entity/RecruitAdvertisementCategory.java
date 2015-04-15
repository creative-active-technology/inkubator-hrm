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
@Table(name="recruit_advertisement_category"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="category_code") 
)
public class RecruitAdvertisementCategory  implements java.io.Serializable {


     private Long id;
     private Integer version;
     private String code;
     private String name;
     private String description;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updatedBy;
     private Boolean isOnline;
     private Set<RecruitAdvertisementMedia> recruitAdvertisementMedias = new HashSet<RecruitAdvertisementMedia>(0);

    public RecruitAdvertisementCategory() {
    }

	
    public RecruitAdvertisementCategory(Long id, String categoryCode, String categoryName) {
        this.id = id;
        this.code = categoryCode;
        this.name = categoryName;
    }
    public RecruitAdvertisementCategory(Long id, String categoryCode, String categoryName, String description, Date createdOn, String createdBy, Date updatedOn, String updatedBy, Set<RecruitAdvertisementMedia> recruitAdvertisementMedias) {
       this.id = id;
       this.code = categoryCode;
       this.name = categoryName;
       this.description = description;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedOn = updatedOn;
       this.updatedBy = updatedBy;
       this.recruitAdvertisementMedias = recruitAdvertisementMedias;
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

    
    @Column(name="category_code", unique=true, nullable=false, length=8)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="category_name", nullable=false, length=45)
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="recruitAdvertisementCategory")
    public Set<RecruitAdvertisementMedia> getRecruitAdvertisementMedias() {
        return this.recruitAdvertisementMedias;
    }
    
    public void setRecruitAdvertisementMedias(Set<RecruitAdvertisementMedia> recruitAdvertisementMedias) {
        this.recruitAdvertisementMedias = recruitAdvertisementMedias;
    }

    @Column(name = "is_online")
    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }




}
