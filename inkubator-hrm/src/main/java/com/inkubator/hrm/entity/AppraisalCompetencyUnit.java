package com.inkubator.hrm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.Version;

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "appraisal_competency_unit", catalog = "hrm"
)
public class AppraisalCompetencyUnit implements Serializable {

	private long id;
    private Integer version;
    private String name;
    private String description;
    private AppraisalCompetencyGroup competencyGroup;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Set<AppraisalCompetencyUnitIndicator> unitIndicators = new HashSet<AppraisalCompetencyUnitIndicator>(0);
    
    public AppraisalCompetencyUnit(){
    	
    }
    
    public AppraisalCompetencyUnit(Long id){
    	this.id = id;
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

    @Column(name="name", unique=true, nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="description", length = 65535, columnDefinition = "Text")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competency_group_id", nullable=false)
    public AppraisalCompetencyGroup getCompetencyGroup() {
		return competencyGroup;
	}

	public void setCompetencyGroup(AppraisalCompetencyGroup competencyGroup) {
		this.competencyGroup = competencyGroup;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appraisalCompetencyUnit", cascade = CascadeType.REMOVE)
	public Set<AppraisalCompetencyUnitIndicator> getUnitIndicators() {
		return unitIndicators;
	}

	public void setUnitIndicators(Set<AppraisalCompetencyUnitIndicator> unitIndicators) {
		this.unitIndicators = unitIndicators;
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
}
