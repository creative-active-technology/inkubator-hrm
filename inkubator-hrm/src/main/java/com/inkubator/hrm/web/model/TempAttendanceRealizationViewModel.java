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
public class TempAttendanceRealizationViewModel implements Serializable {
    private Long id;
    private Long wtPeriodId;
    private Long wtGroupWorkingId;
    private String wtGroupWorkingName;
    private Long empId;
    private String nik;
    private String empName;   
    private Integer attendanceDaysSchedule;
    private Integer attendanceDaysPresent;
    private Float overTime;
    private Integer leaves;
    private Integer permit;
    private Integer sick;
    private Integer duty;
    private String absenStatus;

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

    public String getWtGroupWorkingName() {
        return wtGroupWorkingName;
    }

    public void setWtGroupWorkingName(String wtGroupWorkingName) {
        this.wtGroupWorkingName = wtGroupWorkingName;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
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

    public String getAbsenStatus() {
        return absenStatus;
    }

    public void setAbsenStatus(String absenStatus) {
        this.absenStatus = absenStatus;
    }

    public Float getOverTime() {
        return overTime;
    }

    public void setOverTime(Float overTime) {
        this.overTime = overTime;
    }
    
    
    
    
}
