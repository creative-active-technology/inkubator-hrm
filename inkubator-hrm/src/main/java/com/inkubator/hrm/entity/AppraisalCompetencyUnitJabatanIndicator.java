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
@Table(name = "appraisal_competency_unit_jabatan_indicator", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = {"jabatan_id", "competency_unit_indicator_id"}))
public class AppraisalCompetencyUnitJabatanIndicator implements Serializable {

	private long id;
    private Integer version;
    private AppraisalCompetencyUnitIndicator appraisalCompetencyUnitIndicator;
    private Jabatan jabatan;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    
    public AppraisalCompetencyUnitJabatanIndicator(){
    	
    }
    
    public AppraisalCompetencyUnitJabatanIndicator(Long id){
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
    @JoinColumn(name = "competency_unit_indicator_id", nullable=false)
	public AppraisalCompetencyUnitIndicator getAppraisalCompetencyUnitIndicator() {
		return appraisalCompetencyUnitIndicator;
	}

	public void setAppraisalCompetencyUnitIndicator(AppraisalCompetencyUnitIndicator appraisalCompetencyUnitIndicator) {
		this.appraisalCompetencyUnitIndicator = appraisalCompetencyUnitIndicator;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jabatan_id", nullable=false)
	public Jabatan getJabatan() {
		return jabatan;
	}

	public void setJabatan(Jabatan jabatan) {
		this.jabatan = jabatan;
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
