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

@Entity
@Table(name="appraisal_system_scoring"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="appraisal_system_scoring_name") 
)
public class AppraisalSystemScoring implements java.io.Serializable {
	
	private long id;
    private Integer version;
    private String name;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Set<AppraisalSystemScoringIndex> appraisalSystemScoringIndexes = new HashSet<AppraisalSystemScoringIndex>(0);
    
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
	
	@Column(name="appraisal_system_scoring_name", length=100)
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
	
	@Column(name="updated_by", length=45)
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="appraisalSystemScoring")
	public Set<AppraisalSystemScoringIndex> getAppraisalSystemScoringIndexes() {
		return appraisalSystemScoringIndexes;
	}
	public void setAppraisalSystemScoringIndexes(Set<AppraisalSystemScoringIndex> appraisalSystemScoringIndexes) {
		this.appraisalSystemScoringIndexes = appraisalSystemScoringIndexes;
	}
	

    
    
}
