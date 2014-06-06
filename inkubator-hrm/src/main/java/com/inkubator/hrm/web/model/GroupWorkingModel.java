/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public class GroupWorkingModel implements Serializable{
    private Long id;
    private String code;
    private String name;
    private Date beginTime;
    private Date endTime;
    private Boolean isPeriodic;
    private Double workingTimePerday;
    private Double workingTimePerweek;
    private Boolean overtimeBasedOnAttendance;
    private Boolean overtimeBasedOnRequest;
    private List<ScheduleShiftModel> dataShiftModels=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getIsPeriodic() {
        return isPeriodic;
    }

    public void setIsPeriodic(Boolean isPeriodic) {
        this.isPeriodic = isPeriodic;
    }

    public Double getWorkingTimePerday() {
        return workingTimePerday;
    }

    public void setWorkingTimePerday(Double workingTimePerday) {
        this.workingTimePerday = workingTimePerday;
    }

    public Double getWorkingTimePerweek() {
        return workingTimePerweek;
    }

    public void setWorkingTimePerweek(Double workingTimePerweek) {
        this.workingTimePerweek = workingTimePerweek;
    }

    public Boolean getOvertimeBasedOnAttendance() {
        return overtimeBasedOnAttendance;
    }

    public void setOvertimeBasedOnAttendance(Boolean overtimeBasedOnAttendance) {
        this.overtimeBasedOnAttendance = overtimeBasedOnAttendance;
    }

    public Boolean getOvertimeBasedOnRequest() {
        return overtimeBasedOnRequest;
    }

    public void setOvertimeBasedOnRequest(Boolean overtimeBasedOnRequest) {
        this.overtimeBasedOnRequest = overtimeBasedOnRequest;
    }

    public List<ScheduleShiftModel> getDataShiftModels() {
        return dataShiftModels;
    }

    public void setDataShiftModels(List<ScheduleShiftModel> dataShiftModels) {
        this.dataShiftModels = dataShiftModels;
    }
    
    
}
