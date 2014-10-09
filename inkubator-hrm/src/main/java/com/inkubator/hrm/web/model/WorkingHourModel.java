package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public class WorkingHourModel implements Serializable {

    private Long id;
    private String name;
    private String code;
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
    private Long attendanceStatusId;
    private Boolean isManageBreakTime;
    private Date breakHourBegin;
    private Date breakHourEnd;
    private Integer breakStartLimitBegin;
    private Integer breakStartLimitEnd;
    private Integer breakFinishLimitBegin;
    private Integer breakFinishLimitEnd;
    private Boolean isPenaltyBreakStartEarly;
    private Boolean isPenaltyBreakFinishLate; 
    private Boolean isManageOvertime;
    private Date startOvertime;
    private Date endOvertime;
    private Long overTimeId;
    private Long exchangeWorkingHourId;

    public WorkingHourModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getWorkingHourBegin() {
        return workingHourBegin;
    }

    public void setWorkingHourBegin(Date workingHourBegin) {
        this.workingHourBegin = workingHourBegin;
    }

    public Date getWorkingHourEnd() {
        return workingHourEnd;
    }

    public void setWorkingHourEnd(Date workingHourEnd) {
        this.workingHourEnd = workingHourEnd;
    }

    public Date getMaxHour() {
        return maxHour;
    }

    public void setMaxHour(Date maxHour) {
        this.maxHour = maxHour;
    }

    public Integer getArriveLimitBegin() {
        return arriveLimitBegin;
    }

    public void setArriveLimitBegin(Integer arriveLimitBegin) {
        this.arriveLimitBegin = arriveLimitBegin;
    }

    public Integer getArriveLimitEnd() {
        return arriveLimitEnd;
    }

    public void setArriveLimitEnd(Integer arriveLimitEnd) {
        this.arriveLimitEnd = arriveLimitEnd;
    }

    public Integer getGoHomeLimitBegin() {
        return goHomeLimitBegin;
    }

    public void setGoHomeLimitBegin(Integer goHomeLimitBegin) {
        this.goHomeLimitBegin = goHomeLimitBegin;
    }

    public Integer getGoHomeLimitEnd() {
        return goHomeLimitEnd;
    }

    public void setGoHomeLimitEnd(Integer goHomeLimitEnd) {
        this.goHomeLimitEnd = goHomeLimitEnd;
    }

    public Boolean getIsPenaltyArriveLate() {
        return isPenaltyArriveLate;
    }

    public void setIsPenaltyArriveLate(Boolean isPenaltyArriveLate) {
        this.isPenaltyArriveLate = isPenaltyArriveLate;
    }

    public Boolean getIsPenaltyGoHomeEarly() {
        return isPenaltyGoHomeEarly;
    }

    public void setIsPenaltyGoHomeEarly(Boolean isPenaltyGoHomeEarly) {
        this.isPenaltyGoHomeEarly = isPenaltyGoHomeEarly;
    }

    public Boolean getIsManageBreakTime() {
        return isManageBreakTime;
    }

    public void setIsManageBreakTime(Boolean isManageBreakTime) {
        this.isManageBreakTime = isManageBreakTime;
    }

    public Date getBreakHourBegin() {
        return breakHourBegin;
    }

    public void setBreakHourBegin(Date breakHourBegin) {
        this.breakHourBegin = breakHourBegin;
    }

    public Date getBreakHourEnd() {
        return breakHourEnd;
    }

    public void setBreakHourEnd(Date breakHourEnd) {
        this.breakHourEnd = breakHourEnd;
    }

    public Integer getBreakStartLimitBegin() {
        return breakStartLimitBegin;
    }

    public void setBreakStartLimitBegin(Integer breakStartLimitBegin) {
        this.breakStartLimitBegin = breakStartLimitBegin;
    }

    public Integer getBreakStartLimitEnd() {
        return breakStartLimitEnd;
    }

    public void setBreakStartLimitEnd(Integer breakStartLimitEnd) {
        this.breakStartLimitEnd = breakStartLimitEnd;
    }

    public Integer getBreakFinishLimitBegin() {
        return breakFinishLimitBegin;
    }

    public void setBreakFinishLimitBegin(Integer breakFinishLimitBegin) {
        this.breakFinishLimitBegin = breakFinishLimitBegin;
    }

    public Integer getBreakFinishLimitEnd() {
        return breakFinishLimitEnd;
    }

    public void setBreakFinishLimitEnd(Integer breakFinishLimitEnd) {
        this.breakFinishLimitEnd = breakFinishLimitEnd;
    }

    public Boolean getIsPenaltyBreakStartEarly() {
        return isPenaltyBreakStartEarly;
    }

    public void setIsPenaltyBreakStartEarly(Boolean isPenaltyBreakStartEarly) {
        this.isPenaltyBreakStartEarly = isPenaltyBreakStartEarly;
    }

    public Boolean getIsPenaltyBreakFinishLate() {
        return isPenaltyBreakFinishLate;
    }

    public void setIsPenaltyBreakFinishLate(Boolean isPenaltyBreakFinishLate) {
        this.isPenaltyBreakFinishLate = isPenaltyBreakFinishLate;
    }

	public Long getAttendanceStatusId() {
		return attendanceStatusId;
	}

	public void setAttendanceStatusId(Long attendanceStatusId) {
		this.attendanceStatusId = attendanceStatusId;
	}

	public Boolean getIsManageOvertime() {
		return isManageOvertime;
	}

	public void setIsManageOvertime(Boolean isManageOvertime) {
		this.isManageOvertime = isManageOvertime;
	}

	public Date getStartOvertime() {
		return startOvertime;
	}

	public void setStartOvertime(Date startOvertime) {
		this.startOvertime = startOvertime;
	}

	public Date getEndOvertime() {
		return endOvertime;
	}

	public void setEndOvertime(Date endOvertime) {
		this.endOvertime = endOvertime;
	}

	public Long getOverTimeId() {
		return overTimeId;
	}

	public void setOverTimeId(Long overTimeId) {
		this.overTimeId = overTimeId;
	}

	public Long getExchangeWorkingHourId() {
		return exchangeWorkingHourId;
	}

	public void setExchangeWorkingHourId(Long exchangeWorkingHourId) {
		this.exchangeWorkingHourId = exchangeWorkingHourId;
	}

}
