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
import javax.persistence.Version;

/**
 * AppraisalPerformanceGroup generated by hbm2java
 */
@Entity
@Table(name="appraisal_performance_group"
    ,catalog="hrm"
)
public class AppraisalPerformanceGroup  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String code;
     private String name;
     private String orientation;
     private String appraiser;
     private String description;
     private Date createdOn;
     private String createdBy;
     private String updatedBy;
     private Date updatedOn;
     private Set<AppraisalPerformanceIndicator> appraisalPerformanceIndicators = new HashSet<AppraisalPerformanceIndicator>(0);

    public AppraisalPerformanceGroup() {
    }

	
    public AppraisalPerformanceGroup(long id) {
		this.id = id;
	}


	public AppraisalPerformanceGroup(long id, String code, String orientation, String appraiser) {
        this.id = id;
        this.code = code;
        this.orientation = orientation;
        this.appraiser = appraiser;
    }
    public AppraisalPerformanceGroup(long id, String code, String name, String orientation, String appraiser, String description, Date createdOn, String createdBy, String updatedBy, Date updatedOn) {
       this.id = id;
       this.code = code;
       this.name = name;
       this.orientation = orientation;
       this.appraiser = appraiser;
       this.description = description;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
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

    
    @Column(name="performance_group_code", nullable=false, length=10)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="performance_group_name", length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="orientation", nullable=false, length=6)
    public String getOrientation() {
        return this.orientation;
    }
    
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    
    @Column(name="appraiser", nullable=false, length=16)
    public String getAppraiser() {
        return this.appraiser;
    }
    
    public void setAppraiser(String appraiser) {
        this.appraiser = appraiser;
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

    @OneToMany(fetch=FetchType.LAZY, mappedBy="performanceGroup")
	public Set<AppraisalPerformanceIndicator> getAppraisalPerformanceIndicators() {
		return appraisalPerformanceIndicators;
	}

	public void setAppraisalPerformanceIndicators(Set<AppraisalPerformanceIndicator> appraisalPerformanceIndicators) {
		this.appraisalPerformanceIndicators = appraisalPerformanceIndicators;
	}
    
}