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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "appraisal_competency_unit_indicator", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = {"level_index", "competency_unit_id"}))
public class AppraisalCompetencyUnitIndicator implements Serializable {

	private long id;
    private Integer version;
    private Integer levelIndex;
    private String indicator;
    private AppraisalCompetencyUnit appraisalCompetencyUnit;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    
    public AppraisalCompetencyUnitIndicator(){
    	
    }
    
    public AppraisalCompetencyUnitIndicator(Long id){
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
    
    @Column(name="level_index", nullable=false)
    public Integer getLevelIndex() {
		return levelIndex;
	}

	public void setLevelIndex(Integer levelIndex) {
		this.levelIndex = levelIndex;
	}

	@Column(name="indicator", length = 65535, columnDefinition = "Text")
	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competency_unit_id", nullable=false)
	public AppraisalCompetencyUnit getAppraisalCompetencyUnit() {
		return appraisalCompetencyUnit;
	}

	public void setAppraisalCompetencyUnit(AppraisalCompetencyUnit appraisalCompetencyUnit) {
		this.appraisalCompetencyUnit = appraisalCompetencyUnit;
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
