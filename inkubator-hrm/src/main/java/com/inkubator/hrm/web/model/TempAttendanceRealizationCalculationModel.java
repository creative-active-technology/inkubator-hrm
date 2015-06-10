/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class TempAttendanceRealizationCalculationModel implements Serializable {
    private Long id;
    private Long wtPeriodId;
    private Long wtGroupWorkingId;
    Long empId; 
    private Integer attendanceDaysSchedule;
    private Integer attendanceDaysPresent;
    private Float overTime;
    private Integer leaves;
    private Integer permit;
    private Integer sick;
    private Integer duty;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWtPeriodId() {
        return wtPeriodId;
    }

    public void setWtPeriodId(Long wtPeriodId) {
        this.wtPeriodId = wtPeriodId;
    }

    public Long getWtGroupWorkingId() {
        return wtGroupWorkingId;
    }

    public void setWtGroupWorkingId(Long wtGroupWorkingId) {
        this.wtGroupWorkingId = wtGroupWorkingId;
    }
   

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

   
    public Integer getAttendanceDaysSchedule() {
        return attendanceDaysSchedule;
    }

    public void setAttendanceDaysSchedule(Integer attendanceDaysSchedule) {
        this.attendanceDaysSchedule = attendanceDaysSchedule;
    }

    public Integer getAttendanceDaysPresent() {
        return attendanceDaysPresent;
    }

    public void setAttendanceDaysPresent(Integer attendanceDaysPresent) {
        this.attendanceDaysPresent = attendanceDaysPresent;
    }

    public Integer getLeaves() {
        return leaves;
    }

    public void setLeaves(Integer leaves) {
        this.leaves = leaves;
    }
   

    public Integer getPermit() {
        return permit;
    }

    public void setPermit(Integer permit) {
        this.permit = permit;
    }

    public Integer getSick() {
        return sick;
    }

    public void setSick(Integer sick) {
        this.sick = sick;
    }

    public Integer getDuty() {
        return duty;
    }

    public void setDuty(Integer duty) {
        this.duty = duty;
    }

    public Float getOverTime() {
        return overTime;
    }

    public void setOverTime(Float overTime) {
        this.overTime = overTime;
    }
    
    
    
    
}
