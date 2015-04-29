package com.inkubator.hrm.entity;
// Generated Apr 15, 2015 4:13:35 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
 * RecruitmenSelectionSeries generated by hbm2java
 */
@Entity
@Table(name="recruitmen_selection_series"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="code") 
)
public class RecruitmenSelectionSeries  implements java.io.Serializable {


     private Long id;
     private Integer version;
     private String code;
     private String name;
     private String description;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updatedBy;
     private Set<RecruitmenSelectionSeriesDetail> recruitmenSelectionSeriesDetails = new HashSet<RecruitmenSelectionSeriesDetail>(0);

    public RecruitmenSelectionSeries() {
    }

    public RecruitmenSelectionSeries(Long id) {
        this.id = id;
    }
    
    public RecruitmenSelectionSeries(Long id, String code) {
        this.id = id;
        this.code = code;
    }
    public RecruitmenSelectionSeries(Long id, String code, String name, String description, Date createdOn, String createdBy, Date updatedOn, String updatedBy, Set<RecruitmenSelectionSeriesDetail> recruitmenSelectionSeriesDetails) {
       this.id = id;
       this.code = code;
       this.name = name;
       this.description = description;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedOn = updatedOn;
       this.updatedBy = updatedBy;
       this.recruitmenSelectionSeriesDetails = recruitmenSelectionSeriesDetails;
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

    
    @Column(name="code", unique=true, nullable=false, length=12)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="name", length=60)
    public String getName() {
        return this.name;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="recruitmenSelectionSeries", cascade=CascadeType.REMOVE)
    public Set<RecruitmenSelectionSeriesDetail> getRecruitmenSelectionSeriesDetails() {
        return this.recruitmenSelectionSeriesDetails;
    }
    
    public void setRecruitmenSelectionSeriesDetails(Set<RecruitmenSelectionSeriesDetail> recruitmenSelectionSeriesDetails) {
        this.recruitmenSelectionSeriesDetails = recruitmenSelectionSeriesDetails;
    }




}


