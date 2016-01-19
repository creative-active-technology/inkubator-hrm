package com.inkubator.hrm.entity;

import java.io.Serializable;
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
 * @author rizkykojek
 */
@Entity
@Table(name = "appraisal_performance_indicator", catalog = "hrm"
)
public class AppraisalPerformanceIndicator implements Serializable {

	private long id;
    private Integer version;
    private String indicatorCode;
    private String indicatorLabel;
    private String description;
    private AppraisalPerformanceGroup performanceGroup;
    private SystemScoring systemScoring;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    
    public AppraisalPerformanceIndicator(){
    	
    }
    
    public AppraisalPerformanceIndicator(Long id){
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
    
    @Column(name="indicator_code", unique=true, nullable=false, length=12)
    public String getIndicatorCode() {
		return indicatorCode;
	}

	public void setIndicatorCode(String indicatorCode) {
		this.indicatorCode = indicatorCode;
	}

	@Column(name="indicator_label", unique=true, nullable=false, length=100)
	public String getIndicatorLabel() {
		return indicatorLabel;
	}

	public void setIndicatorLabel(String indicatorLabel) {
		this.indicatorLabel = indicatorLabel;
	}

    @Column(name="description", length = 65535, columnDefinition = "Text")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_group_id", nullable = false)
    public AppraisalPerformanceGroup getPerformanceGroup() {
		return performanceGroup;
	}

	public void setPerformanceGroup(AppraisalPerformanceGroup performanceGroup) {
		this.performanceGroup = performanceGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_scoring_id", nullable = false)
	public SystemScoring getSystemScoring() {
		return systemScoring;
	}

	public void setSystemScoring(SystemScoring systemScoring) {
		this.systemScoring = systemScoring;
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
