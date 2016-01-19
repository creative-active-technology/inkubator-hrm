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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "appraisal_performance_indicator_jabatan", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = {"performance_indicator_id", "jabatan_id"}))
public class AppraisalPerformanceIndicatorJabatan implements Serializable {

	private long id;
    private Integer version;
    private AppraisalPerformanceIndicator performanceIndicator;
    private Jabatan jabatan;
    private SystemScoringIndex systemScoringIndex;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    
    public AppraisalPerformanceIndicatorJabatan(){
    	
    }
    
    public AppraisalPerformanceIndicatorJabatan(Long id){
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_indicator_id", nullable = false)
    public AppraisalPerformanceIndicator getPerformanceIndicator() {
		return performanceIndicator;
	}

	public void setPerformanceIndicator(AppraisalPerformanceIndicator performanceIndicator) {
		this.performanceIndicator = performanceIndicator;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jabatan_id", nullable = false)
	public Jabatan getJabatan() {
		return jabatan;
	}

	public void setJabatan(Jabatan jabatan) {
		this.jabatan = jabatan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_scoring_index_id")
	public SystemScoringIndex getSystemScoringIndex() {
		return systemScoringIndex;
	}

	public void setSystemScoringIndex(SystemScoringIndex systemScoringIndex) {
		this.systemScoringIndex = systemScoringIndex;
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
