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
@Table(name = "appraisal_program", catalog = "hrm")
public class AppraisalProgram implements Serializable {

	private Long id;
    private Integer version;
    private String code;
    private String name;
    private String description;
    private Date evalStartDate;
    private Date evalEndDate;
    private Boolean isGapCompetency;
    private Boolean isPerformanceScoring;
    private Boolean isAchievement;
    private Boolean isIndiscipline;
    private AppraisalPerformanceGroup appraisalPerformanceGroup;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Set<AppraisalAchievementProgram> appraisalAchievementPrograms = new HashSet<AppraisalAchievementProgram>(0);
    private Set<AppraisalIndisciplineProgram> appraisalIndisciplinePrograms = new HashSet<AppraisalIndisciplineProgram>(0);
    private Set<AppraisalProgramEmp> appraisalProgramEmps = new HashSet<AppraisalProgramEmp>(0);
    
    public AppraisalProgram() {
    	
    }
    
    public AppraisalProgram(Long id){
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
    
    @Column(name="code", unique=true, nullable=false, length=12)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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
    
    @Temporal(TemporalType.DATE)
    @Column(name="eval_start_date", length=19, nullable = false)
    public Date getEvalStartDate() {
		return evalStartDate;
	}

	public void setEvalStartDate(Date evalStartDate) {
		this.evalStartDate = evalStartDate;
	}

	@Temporal(TemporalType.DATE)
    @Column(name="eval_end_date", length=19, nullable = false)
	public Date getEvalEndDate() {
		return evalEndDate;
	}

	public void setEvalEndDate(Date evalEndDate) {
		this.evalEndDate = evalEndDate;
	}
	
	@Column(name="is_gap_competency", nullable = false)
	public Boolean getIsGapCompetency() {
		return isGapCompetency;
	}

	public void setIsGapCompetency(Boolean isGapCompetency) {
		this.isGapCompetency = isGapCompetency;
	}

	@Column(name="is_performance_scoring", nullable = false)
	public Boolean getIsPerformanceScoring() {
		return isPerformanceScoring;
	}

	public void setIsPerformanceScoring(Boolean isPerformanceScoring) {
		this.isPerformanceScoring = isPerformanceScoring;
	}

	@Column(name="is_achievement", nullable = false)
	public Boolean getIsAchievement() {
		return isAchievement;
	}

	public void setIsAchievement(Boolean isAchievement) {
		this.isAchievement = isAchievement;
	}

	@Column(name="is_indiscipline", nullable = false)
	public Boolean getIsIndiscipline() {
		return isIndiscipline;
	}

	public void setIsIndiscipline(Boolean isIndiscipline) {
		this.isIndiscipline = isIndiscipline;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appraisal_performance_group_id", nullable=false)
	public AppraisalPerformanceGroup getAppraisalPerformanceGroup() {
		return appraisalPerformanceGroup;
	}

	public void setAppraisalPerformanceGroup(AppraisalPerformanceGroup appraisalPerformanceGroup) {
		this.appraisalPerformanceGroup = appraisalPerformanceGroup;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appraisalProgram", cascade = CascadeType.REMOVE)
	public Set<AppraisalAchievementProgram> getAppraisalAchievementPrograms() {
		return appraisalAchievementPrograms;
	}

	public void setAppraisalAchievementPrograms(Set<AppraisalAchievementProgram> appraisalAchievementPrograms) {
		this.appraisalAchievementPrograms = appraisalAchievementPrograms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appraisalProgram", cascade = CascadeType.REMOVE)
	public Set<AppraisalIndisciplineProgram> getAppraisalIndisciplinePrograms() {
		return appraisalIndisciplinePrograms;
	}

	public void setAppraisalIndisciplinePrograms(Set<AppraisalIndisciplineProgram> appraisalIndisciplinePrograms) {
		this.appraisalIndisciplinePrograms = appraisalIndisciplinePrograms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appraisalProgram")
	public Set<AppraisalProgramEmp> getAppraisalProgramEmps() {
		return appraisalProgramEmps;
	}

	public void setAppraisalProgramEmps(Set<AppraisalProgramEmp> appraisalProgramEmps) {
		this.appraisalProgramEmps = appraisalProgramEmps;
	}
    
    
    
}
