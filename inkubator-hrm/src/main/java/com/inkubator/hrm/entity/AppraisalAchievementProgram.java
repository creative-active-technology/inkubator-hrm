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
@Table(name = "appraisal_achievement_program", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = {"appraisal_program_id", "award_type_id"}))
public class AppraisalAchievementProgram implements Serializable {

	private Long id;
    private Integer version;
    private AppraisalProgram appraisalProgram;
    private CareerAwardType careerAwardType;
    private Double score;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    
    public AppraisalAchievementProgram() {
    	
    }
    
    public AppraisalAchievementProgram(Long id) {
    	this.id = id;
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appraisal_program_id", nullable=false)
    public AppraisalProgram getAppraisalProgram() {
		return appraisalProgram;
	}

	public void setAppraisalProgram(AppraisalProgram appraisalProgram) {
		this.appraisalProgram = appraisalProgram;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "award_type_id", nullable=false)
	public CareerAwardType getCareerAwardType() {
		return careerAwardType;
	}

	public void setCareerAwardType(CareerAwardType careerAwardType) {
		this.careerAwardType = careerAwardType;
	}

	@Column(name = "score", nullable=false)
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
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
