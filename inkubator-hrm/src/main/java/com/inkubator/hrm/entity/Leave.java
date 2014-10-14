package com.inkubator.hrm.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;

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
    private String dayType;
    private String calculation;
    private AttendanceStatus attendanceStatus;
    private String periodBase;
    private String availability;
    private Date availabilityAtSpecificDate;
    private Boolean isTakingLeaveToNextYear;
    private Integer maxTakingLeaveToNextYear;
    private Integer backwardPeriodLimit;
    private Boolean isAllowedMinus;
    private Integer maxAllowedMinus;
    private Integer effectiveFrom;
    private Integer submittedLimit;
    private Boolean isQuotaReduction;
    private String endOfPeriod;
    private Integer endOfPeriodMonth;
    private Integer quotaPerPeriod;
    private Boolean isOnlyOncePerEmployee;
    private Boolean isActive;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<LeaveScheme> leaveSchemes = new HashSet<LeaveScheme>(0);
    private Set<ApprovalDefinitionLeave> approvalDefinitionLeaves = new HashSet<ApprovalDefinitionLeave>(0);
    private Set<LeaveDistribution> leaveDistributions = new HashSet<>(0);
    private Set<LeaveImplementation> leaveImplementations = new HashSet<LeaveImplementation>(0);
    
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
	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	@Column(name = "calculation", nullable = false, length = 1)
	public String getCalculation() {
		return calculation;
	}

	public void setCalculation(String calculation) {
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
	public String getPeriodBase() {
		return periodBase;
	}

	public void setPeriodBase(String periodBase) {
		this.periodBase = periodBase;
	}

	@Column(name = "availability", nullable = false, length = 1)
	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "availabity_at_specific_date", length = 19)
	public Date getAvailabilityAtSpecificDate() {
		return availabilityAtSpecificDate;
	}

	public void setAvailabilityAtSpecificDate(Date availabilityAtSpecificDate) {
		this.availabilityAtSpecificDate = availabilityAtSpecificDate;
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

	@Column(name = "is_quota_reduction", nullable = false)
	public Boolean getIsQuotaReduction() {
		return isQuotaReduction;
	}

	public void setIsQuotaReduction(Boolean isQuotaReduction) {
		this.isQuotaReduction = isQuotaReduction;
	}

	@Column(name = "end_of_period", nullable = false, length = 1)
	public String getEndOfPeriod() {
		return endOfPeriod;
	}

	public void setEndOfPeriod(String endOfPeriod) {
		this.endOfPeriod = endOfPeriod;
	}

	@Column(name = "end_of_period_month", length = 2)
	public Integer getEndOfPeriodMonth() {
		return endOfPeriodMonth;
	}

	public void setEndOfPeriodMonth(Integer endOfPeriodMonth) {
		this.endOfPeriodMonth = endOfPeriodMonth;
	}	

	@Column(name = "quota_per_period", nullable=false, length = 2)
	public Integer getQuotaPerPeriod() {
		return quotaPerPeriod;
	}

	public void setQuotaPerPeriod(Integer quotaPerPeriod) {
		this.quotaPerPeriod = quotaPerPeriod;
	}

	@Column(name = "is_only_once_per_employee", nullable = false)
	public Boolean getIsOnlyOncePerEmployee() {
		return isOnlyOncePerEmployee;
	}

	public void setIsOnlyOncePerEmployee(Boolean isOnlyOncePerEmployee) {
		this.isOnlyOncePerEmployee = isOnlyOncePerEmployee;
	}

	@Column(name = "is_active", nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "leave")
    public Set<LeaveScheme> getLeaveSchemes() {
		return leaveSchemes;
	}

	public void setLeaveSchemes(Set<LeaveScheme> leaveSchemes) {
		this.leaveSchemes = leaveSchemes;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "leave")
	public Set<ApprovalDefinitionLeave> getApprovalDefinitionLeaves() {
		return approvalDefinitionLeaves;
	}

	public void setApprovalDefinitionLeaves(Set<ApprovalDefinitionLeave> approvalDefinitionLeaves) {
		this.approvalDefinitionLeaves = approvalDefinitionLeaves;
	}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "leave")
    public Set<LeaveDistribution> getLeaveDistributions() {
        return leaveDistributions;
    }

    public void setLeaveDistributions(Set<LeaveDistribution> leaveDistributions) {
        this.leaveDistributions = leaveDistributions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "leave")
	public Set<LeaveImplementation> getLeaveImplementations() {
		return leaveImplementations;
	}

	public void setLeaveImplementations(
			Set<LeaveImplementation> leaveImplementations) {
		this.leaveImplementations = leaveImplementations;
	}
    
	@Transient
    public String getDayTypeAsLabel(){
		String dayTypeAsLabel = StringUtils.EMPTY;
		ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
		if(StringUtils.equals(dayType, HRMConstant.LEAVE_DAY_TYPE_CALENDAR)){
			dayTypeAsLabel = messages.getString("leave.calendar_days");
		} else if(StringUtils.equals(dayType, HRMConstant.LEAVE_DAY_TYPE_WORKING)) {
			dayTypeAsLabel = messages.getString("leave.working_days");
		}
		return dayTypeAsLabel;
	}
    
    @Transient
	public String getCalculationAsLabel(){
		String calculationAsLabel = StringUtils.EMPTY;
		ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
		if(StringUtils.equals(calculation, HRMConstant.LEAVE_CALCULATION_FULL_DAY)){
			calculationAsLabel = messages.getString("leave.full_day");
		} else if(StringUtils.equals(calculation, HRMConstant.LEAVE_CALCULATION_PART_DAY)) {
			calculationAsLabel = messages.getString("leave.part_of_day");
		}
		return calculationAsLabel;
	}
    
    @Transient
    public String getPeriodBaseAsLabel(){
    	String periodBaseAsLabel = StringUtils.EMPTY;
    	ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
    	if(StringUtils.equals(periodBase, HRMConstant.LEAVE_PERIOD_BASE_TMB)){
    		periodBaseAsLabel = "T.M.B";
    	} else  if(StringUtils.equals(periodBase, HRMConstant.LEAVE_PERIOD_BASE_0101)){
    		periodBaseAsLabel = "0101";
    	} else if(StringUtils.equals(periodBase, HRMConstant.LEAVE_PERIOD_BASE_TMB_TO_0101)){
    		periodBaseAsLabel = "T.M.B ke 0101";
    	}
    	return periodBaseAsLabel;
    }
    
    @Transient
    public String getAvailabilityAslabel(){
    	String availabilityAsLabel = StringUtils.EMPTY;
    	ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
    	if(StringUtils.equals(availability, HRMConstant.LEAVE_AVAILABILITY_FULL)){
    		availabilityAsLabel = messages.getString("leave.full");
		} else if(StringUtils.equals(availability, HRMConstant.LEAVE_AVAILABILITY_INCREASES_MONTH)) {
			availabilityAsLabel = messages.getString("leave.increases_each_month");
		} else if(StringUtils.equals(availability, HRMConstant.LEAVE_AVAILABILITY_INCREASES_SPECIFIC_DATE)) {
			SimpleDateFormat parser = new SimpleDateFormat("dd MMMM", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
			availabilityAsLabel = messages.getString("leave.increases_on_certain_date") + ". " + messages.getString("global.date") + " = " + parser.format(availabilityAtSpecificDate);		
		}
    	return availabilityAsLabel;
    }
    
    @Transient
    public String getEndOfPeriodAsLabel(){
    	String endOfPeriodAsLabel = StringUtils.EMPTY;
    	ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
    	if(StringUtils.equals(endOfPeriod, HRMConstant.LEAVE_END_OF_PERIOD_MONTH)){
    		endOfPeriodAsLabel = messages.getString("leave.monthly")+ ". " + messages.getString("leave.total_month") + " = " + endOfPeriodMonth;
		} else if(StringUtils.equals(availability, HRMConstant.LEAVE_END_OF_PERIOD_REST_OF_LEAVE)) {
			endOfPeriodAsLabel = messages.getString("leave.rest_of_leave");
		}
    	return endOfPeriodAsLabel;
    }
    
    
}
