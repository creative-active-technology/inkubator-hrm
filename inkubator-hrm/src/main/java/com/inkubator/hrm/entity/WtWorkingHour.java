package com.inkubator.hrm.entity;
// Generated May 21, 2014 4:59:40 PM by Hibernate Tools 3.6.0

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Version;

/**
 * WtHoliday generated by hbm2java
 */
@Entity
@Table(name = "wt_working_hour", catalog="hrm_payroll")
public class WtWorkingHour implements java.io.Serializable {

    private Long id;
    private Integer version;

    private String code;
    private String name;
    private String description;
    private Date workingHourBegin;
    private Date workingHourEnd;
    private Date maxHour;
    private Integer arriveLimitBegin;
    private Integer arriveLimitEnd;
    private Integer goHomeLimitBegin;
    private Integer goHomeLimitEnd;
    private Boolean isPenaltyArriveLate;
    private Boolean isPenaltyGoHomeEarly;
    private Boolean isManageBreakTime;
    private Date breakHourBegin;
    private Date breakHourEnd;
    private Integer breakStartLimitBegin;
    private Integer breakStartLimitEnd;
    private Integer breakFinishLimitBegin;
    private Integer breakFinishLimitEnd;
    private Boolean isPenaltyBreakStartEarly;
    private Boolean isPenaltyBreakFinishLate;
    private AttendanceStatus attendanceStatus;
    private Boolean isManageOvertime;
    private Date startOvertime;
    private Date endOvertime;
    private WtOverTime wtOverTime;
    private WtWorkingHour exchangeWorkingHour;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<WtScheduleShift> wtScheduleShifts = new HashSet<WtScheduleShift>(0);
    private Set<WtWorkingHour> exchangeWorkingHours = new HashSet<WtWorkingHour>(0);

    public WtWorkingHour() {
    }

    public WtWorkingHour(Long id) {
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

    @Column(name = "code", unique = true, nullable = false, length = 10)
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

    @Temporal(TemporalType.TIME)
    @Column(name = "working_hour_begin", nullable = false, length = 10)
    public Date getWorkingHourBegin() {
        return workingHourBegin;
    }

    public void setWorkingHourBegin(Date workingHourBegin) {
        this.workingHourBegin = workingHourBegin;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "working_hour_end", nullable = false, length = 10)
    public Date getWorkingHourEnd() {
        return workingHourEnd;
    }

    public void setWorkingHourEnd(Date workingHourEnd) {
        this.workingHourEnd = workingHourEnd;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "max_hour", nullable = false, length = 10)
    public Date getMaxHour() {
        return maxHour;
    }

    public void setMaxHour(Date maxHour) {
        this.maxHour = maxHour;
    }

    @Column(name = "arrive_limit_begin", nullable = false, length = 3)
    public Integer getArriveLimitBegin() {
        return arriveLimitBegin;
    }

    public void setArriveLimitBegin(Integer arriveLimitBegin) {
        this.arriveLimitBegin = arriveLimitBegin;
    }

    @Column(name = "arrive_limit_end", nullable = false, length = 3)
    public Integer getArriveLimitEnd() {
        return arriveLimitEnd;
    }

    public void setArriveLimitEnd(Integer arriveLimitEnd) {
        this.arriveLimitEnd = arriveLimitEnd;
    }

    @Column(name = "go_home_limit_begin", nullable = false, length = 3)
    public Integer getGoHomeLimitBegin() {
        return goHomeLimitBegin;
    }

    public void setGoHomeLimitBegin(Integer goHomeLimitBegin) {
        this.goHomeLimitBegin = goHomeLimitBegin;
    }

    @Column(name = "go_home_limit_end", nullable = false, length = 3)
    public Integer getGoHomeLimitEnd() {
        return goHomeLimitEnd;
    }

    public void setGoHomeLimitEnd(Integer goHomeLimitEnd) {
        this.goHomeLimitEnd = goHomeLimitEnd;
    }

    @Column(name = "is_penalty_arrive_late", nullable = false)
    public Boolean getIsPenaltyArriveLate() {
        return isPenaltyArriveLate;
    }

    public void setIsPenaltyArriveLate(Boolean isPenaltyArriveLate) {
        this.isPenaltyArriveLate = isPenaltyArriveLate;
    }

    @Column(name = "is_penalty_go_home_early", nullable = false)
    public Boolean getIsPenaltyGoHomeEarly() {
        return isPenaltyGoHomeEarly;
    }

    public void setIsPenaltyGoHomeEarly(Boolean isPenaltyGoHomeEarly) {
        this.isPenaltyGoHomeEarly = isPenaltyGoHomeEarly;
    }

    @Column(name = "is_manage_break_time", nullable = false)
    public Boolean getIsManageBreakTime() {
        return isManageBreakTime;
    }

    public void setIsManageBreakTime(Boolean isManageBreakTime) {
        this.isManageBreakTime = isManageBreakTime;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "break_hour_begin", length = 3)
    public Date getBreakHourBegin() {
        return breakHourBegin;
    }

    public void setBreakHourBegin(Date breakHourBegin) {
        this.breakHourBegin = breakHourBegin;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "break_hour_end", length = 3)
    public Date getBreakHourEnd() {
        return breakHourEnd;
    }

    public void setBreakHourEnd(Date breakHourEnd) {
        this.breakHourEnd = breakHourEnd;
    }

    @Column(name = "break_start_limit_begin", length = 3)
    public Integer getBreakStartLimitBegin() {
        return breakStartLimitBegin;
    }

    public void setBreakStartLimitBegin(Integer breakStartLimitBegin) {
        this.breakStartLimitBegin = breakStartLimitBegin;
    }

    @Column(name = "break_start_limit_end", length = 3)
    public Integer getBreakStartLimitEnd() {
        return breakStartLimitEnd;
    }

    public void setBreakStartLimitEnd(Integer breakStartLimitEnd) {
        this.breakStartLimitEnd = breakStartLimitEnd;
    }

    @Column(name = "break_finish_limit_begin", length = 3)
    public Integer getBreakFinishLimitBegin() {
        return breakFinishLimitBegin;
    }

    public void setBreakFinishLimitBegin(Integer breakFinishLimitBegin) {
        this.breakFinishLimitBegin = breakFinishLimitBegin;
    }

    @Column(name = "break_finish_limit_end", length = 3)
    public Integer getBreakFinishLimitEnd() {
        return breakFinishLimitEnd;
    }

    public void setBreakFinishLimitEnd(Integer breakFinishLimitEnd) {
        this.breakFinishLimitEnd = breakFinishLimitEnd;
    }

    @Column(name = "is_penalty_break_start_early")
    public Boolean getIsPenaltyBreakStartEarly() {
        return isPenaltyBreakStartEarly;
    }

    public void setIsPenaltyBreakStartEarly(Boolean isPenaltyBreakStartEarly) {
        this.isPenaltyBreakStartEarly = isPenaltyBreakStartEarly;
    }

    @Column(name = "is_penalty_break_finish_late")
    public Boolean getIsPenaltyBreakFinishLate() {
        return isPenaltyBreakFinishLate;
    }

    public void setIsPenaltyBreakFinishLate(Boolean isPenaltyBreakFinishLate) {
        this.isPenaltyBreakFinishLate = isPenaltyBreakFinishLate;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_status_id", nullable = false)
	public AttendanceStatus getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
		
	@Column(name = "is_manage_overtime", nullable = false)
    public Boolean getIsManageOvertime() {
		return isManageOvertime;
	}

	public void setIsManageOvertime(Boolean isManageOvertime) {
		this.isManageOvertime = isManageOvertime;
	}

	@Temporal(TemporalType.TIME)
    @Column(name = "start_overtime", length = 10)
	public Date getStartOvertime() {
		return startOvertime;
	}

	public void setStartOvertime(Date startOvertime) {
		this.startOvertime = startOvertime;
	}
	
	@Temporal(TemporalType.TIME)
    @Column(name = "end_overtime", length = 10)
	public Date getEndOvertime() {
		return endOvertime;
	}

	public void setEndOvertime(Date endOvertime) {
		this.endOvertime = endOvertime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wt_over_time_id")
	public WtOverTime getWtOverTime() {
		return wtOverTime;
	}

	public void setWtOverTime(WtOverTime wtOverTime) {
		this.wtOverTime = wtOverTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_working_hour_id")
	public WtWorkingHour getExchangeWorkingHour() {
		return exchangeWorkingHour;
	}

	public void setExchangeWorkingHour(WtWorkingHour exchangeWorkingHour) {
		this.exchangeWorkingHour = exchangeWorkingHour;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wtWorkingHour")
    public Set<WtScheduleShift> getWtScheduleShifts() {
        return this.wtScheduleShifts;
    }

    public void setWtScheduleShifts(Set<WtScheduleShift> wtScheduleShifts) {
        this.wtScheduleShifts = wtScheduleShifts;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exchangeWorkingHour")
	public Set<WtWorkingHour> getExchangeWorkingHours() {
		return exchangeWorkingHours;
	}

	public void setExchangeWorkingHours(Set<WtWorkingHour> exchangeWorkingHours) {
		this.exchangeWorkingHours = exchangeWorkingHours;
	}
    
    

}
