package com.inkubator.hrm.entity;

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

@Entity
@Table(name = "appraisal_system_scoring_index", catalog = "hrm")
public class AppraisalSystemScoringIndex implements java.io.Serializable {
	private Long id;
	private Integer version;
	private AppraisalSystemScoring appraisalSystemScoring;
	private Integer value;
	private String labelMask;
	private String description;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="appraisal_system_scoring_id", nullable=false)
	public AppraisalSystemScoring getAppraisalSystemScoring() {
		return appraisalSystemScoring;
	}

	public void setAppraisalSystemScoring(AppraisalSystemScoring appraisalSystemScoring) {
		this.appraisalSystemScoring = appraisalSystemScoring;
	}

	@Column(name="value")
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Column(name="label_mask", length=60)
	public String getLabelMask() {
		return labelMask;
	}

	public void setLabelMask(String labelMask) {
		this.labelMask = labelMask; 
	}

	@Column(name="created_by", length=45)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name="updated_by", length=45)
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Column(name = "description", length = 65535, columnDefinition = "Text")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
