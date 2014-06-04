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
@Table(name = "leave", catalog = "hrm")
public class Leave implements Serializable {

	private Long id;
    private Integer version;
    
    private String code;
    private String name;
    private String description;
    private Integer dayType;
    private Integer calculation;
    private AttendanceStatus attendanceStatus;
    private Integer periodBase;
    private Integer leaveAvailability;
    private Date increasedLeaveDate;
    private Boolean isTakingLeaveToNextYear;
    private Integer maxTakingLeaveToNextYear;
    private Integer backwardPeriodLimit;
    private Boolean isAllowedMinus;
    private Integer maxAllowedMinus;
    private Integer effectiveFrom;
    private Integer submittedLimit;
    private Integer approvalLevel;
    private Integer leaveQuotaReduction;
    private Integer endOfPeriodLeave;
    private Integer endOfPeriodLeaveMonth;
    private Boolean isOnlyOncePerEmployee;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    
    public Leave(){
    	
    }
    
    public Leave(Long id){
    	this.id = id;
    }
    
    @Id
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    } 
    
    @Column(name = "code", unique = true, nullable = false, length = 4)
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "day_type", nullable = false, length = 1)
	public Integer getDayType() {
		return dayType;
	}

	public void setDayType(Integer dayType) {
		this.dayType = dayType;
	}

	@Column(name = "calculation", nullable = false, length = 1)
	public Integer getCalculation() {
		return calculation;
	}

	public void setCalculation(Integer calculation) {
		this.calculation = calculation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_status_id", nullable = false)
	public AttendanceStatus getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	@Column(name = "period_base", nullable = false, length = 1)
	public Integer getPeriodBase() {
		return periodBase;
	}

	public void setPeriodBase(Integer periodBase) {
		this.periodBase = periodBase;
	}

	@Column(name = "leave_availability", nullable = false, length = 1)
	public Integer getLeaveAvailability() {
		return leaveAvailability;
	}

	public void setLeaveAvailability(Integer leaveAvailability) {
		this.leaveAvailability = leaveAvailability;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "increased_leave_date", length = 19)
	public Date getIncreasedLeaveDate() {
		return increasedLeaveDate;
	}

	public void setIncreasedLeaveDate(Date increasedLeaveDate) {
		this.increasedLeaveDate = increasedLeaveDate;
	}
	
	
	@Column(name = "is_taking_leave_to_next_year", nullable = false)
	public Boolean getIsTakingLeaveToNextYear() {
		return isTakingLeaveToNextYear;
	}

	public void setIsTakingLeaveToNextYear(Boolean isTakingLeaveToNextYear) {
		this.isTakingLeaveToNextYear = isTakingLeaveToNextYear;
	}

	@Column(name = "max_taking_leave_to_next_year", length = 3)
	public Integer getMaxTakingLeaveToNextYear() {
		return maxTakingLeaveToNextYear;
	}

	public void setMaxTakingLeaveToNextYear(Integer maxTakingLeaveToNextYear) {
		this.maxTakingLeaveToNextYear = maxTakingLeaveToNextYear;
	}

	@Column(name = "backward_period_limit", length = 2)
	public Integer getBackwardPeriodLimit() {
		return backwardPeriodLimit;
	}

	public void setBackwardPeriodLimit(Integer backwardPeriodLimit) {
		this.backwardPeriodLimit = backwardPeriodLimit;
	}

	@Column(name = "is_allowed_minus", nullable = false)
	public Boolean getIsAllowedMinus() {
		return isAllowedMinus;
	}

	public void setIsAllowedMinus(Boolean isAllowedMinus) {
		this.isAllowedMinus = isAllowedMinus;
	}

	@Column(name = "max_allowed_minus", length = 3)
	public Integer getMaxAllowedMinus() {
		return maxAllowedMinus;
	}

	public void setMaxAllowedMinus(Integer maxAllowedMinus) {
		this.maxAllowedMinus = maxAllowedMinus;
	}

	@Column(name = "effective_from", nullable = false, length = 3)
	public Integer getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Integer effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	@Column(name = "submitted_limit", nullable = false, length = 3)
	public Integer getSubmittedLimit() {
		return submittedLimit;
	}

	public void setSubmittedLimit(Integer submittedLimit) {
		this.submittedLimit = submittedLimit;
	}

	@Column(name = "aproval_level", nullable = false, length = 2)
	public Integer getApprovalLevel() {
		return approvalLevel;
	}

	public void setApprovalLevel(Integer approvalLevel) {
		this.approvalLevel = approvalLevel;
	}

	@Column(name = "leave_quota_reduction", nullable = false)
	public Integer getLeaveQuotaReduction() {
		return leaveQuotaReduction;
	}

	public void setLeaveQuotaReduction(Integer leaveQuotaReduction) {
		this.leaveQuotaReduction = leaveQuotaReduction;
	}

	@Column(name = "end_of_period_leave", nullable = false, length = 1)
	public Integer getEndOfPeriodLeave() {
		return endOfPeriodLeave;
	}

	public void setEndOfPeriodLeave(Integer endOfPeriodLeave) {
		this.endOfPeriodLeave = endOfPeriodLeave;
	}

	@Column(name = "end_of_period_leave_month", length = 2)
	public Integer getEndOfPeriodLeaveMonth() {
		return endOfPeriodLeaveMonth;
	}

	public void setEndOfPeriodLeaveMonth(Integer endOfPeriodLeaveMonth) {
		this.endOfPeriodLeaveMonth = endOfPeriodLeaveMonth;
	}

	@Column(name = "is_only_once_per_employee", nullable = false)
	public Boolean getIsOnlyOncePerEmployee() {
		return isOnlyOncePerEmployee;
	}

	public void setIsOnlyOncePerEmployee(Boolean isOnlyOncePerEmployee) {
		this.isOnlyOncePerEmployee = isOnlyOncePerEmployee;
	}

	@Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
