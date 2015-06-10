/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author deni.fahri
 */
public class WorkingTimeDeviation implements Serializable {

    private Long empId;
    private String empRealName;
    private String attendaceRealization;
    private Long extraHour;
    private Long extraMinute;
    private Long hourDefect;
    private Long minuteDefect;
    private Long overTimeHour;
    private Long overTimeMinute;
    private Date extraDate;
    private Date dateDefect;
    private Date overTime;
    private Long totalDeviation;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpRealName() {
        return empRealName;
    }

    public void setEmpRealName(String empRealName) {
        this.empRealName = empRealName;
    }

    public String getAttendaceRealization() {
        return attendaceRealization;
    }

    public void setAttendaceRealization(String attendaceRealization) {
        this.attendaceRealization = attendaceRealization;
    }

    public Long getExtraHour() {
        return extraHour;
    }

    public void setExtraHour(Long extraHour) {
        this.extraHour = extraHour;
    }

    public Long getExtraMinute() {
        return extraMinute;
    }

    public void setExtraMinute(Long extraMinute) {
        this.extraMinute = extraMinute;
    }

    public Long getHourDefect() {
        return hourDefect;
    }

    public void setHourDefect(Long hourDefect) {
        this.hourDefect = hourDefect;
    }

    public Long getMinuteDefect() {
        return minuteDefect;
    }

    public void setMinuteDefect(Long minuteDefect) {
        this.minuteDefect = minuteDefect;
    }

    public Long getOverTimeHour() {
        return overTimeHour;
    }

    public void setOverTimeHour(Long overTimeHour) {
        this.overTimeHour = overTimeHour;
    }

    public Long getOverTimeMinute() {
        return overTimeMinute;
    }

    public void setOverTimeMinute(Long overTimeMinute) {
        this.overTimeMinute = overTimeMinute;
    }

    public Date getExtraDate() {
        return extraDate;
    }

    public void setExtraDate(Date extraDate) {
        this.extraDate = extraDate;
    }

    public Date getDateDefect() {
        return dateDefect;
    }

    public void setDateDefect(Date dateDefect) {
        this.dateDefect = dateDefect;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    @Override
    public String toString() {
     
        return "WorkingTimeDeviation{" + "empId=" + empId + ", empRealName=" + empRealName + ", attendaceRealization=" + attendaceRealization + ", extraHour=" + extraHour + ", extraMinute=" + extraMinute + ", hourDefect=" + hourDefect + ", minuteDefect=" + minuteDefect + ", overTimeHour=" + overTimeHour + ", overTimeMinute=" + overTimeMinute + ", extraDate=" + extraDate + ", dateDefect=" + dateDefect + ", overTime=" + overTime + '}';
    }

    public Long getTotalDeviation() {
        return totalDeviation;
    }

    public void setTotalDeviation(Long totalDeviation) {
        this.totalDeviation = totalDeviation;
    }

    
    
}
