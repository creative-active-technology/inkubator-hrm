package com.inkubator.hrm.entity;
// Generated Jun 1, 2015 12:34:00 PM by Hibernate Tools 4.3.1


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

/**
 * TempAttendanceRealization generated by hbm2java
 */
@Entity
@Table(name="temp_attendance_realization"
    ,catalog="hrm"
)
public class TempAttendanceRealization  implements java.io.Serializable {


     private long id;
     private EmpData empData;
     private WtGroupWorking wtGroupWorking;
     private Integer attendanceDaysSchedule;
     private Integer attendanceDaysPresent;
     private Integer leave;
     private Integer permit;
     private Integer sick;
     private Integer duty;
     private Date createdOn;
     private String createdBy;

    public TempAttendanceRealization() {
    }

	
    public TempAttendanceRealization(long id) {
        this.id = id;
    }
    public TempAttendanceRealization(long id, EmpData empData, WtGroupWorking wtGroupWorking, Integer attendanceDaysSchedule, Integer attendanceDaysPresent, Integer leave, Integer permit, Integer sick, Integer duty, Date createdOn, String createdBy) {
       this.id = id;
       this.empData = empData;
       this.wtGroupWorking = wtGroupWorking;
       this.attendanceDaysSchedule = attendanceDaysSchedule;
       this.attendanceDaysPresent = attendanceDaysPresent;
       this.leave = leave;
       this.permit = permit;
       this.sick = sick;
       this.duty = duty;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="emp_data_id")
    public EmpData getEmpData() {
        return this.empData;
    }
    
    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="wt_group_working_id")
    public WtGroupWorking getWtGroupWorking() {
        return this.wtGroupWorking;
    }
    
    public void setWtGroupWorking(WtGroupWorking wtGroupWorking) {
        this.wtGroupWorking = wtGroupWorking;
    }

    
    @Column(name="attendance_days_schedule")
    public Integer getAttendanceDaysSchedule() {
        return this.attendanceDaysSchedule;
    }
    
    public void setAttendanceDaysSchedule(Integer attendanceDaysSchedule) {
        this.attendanceDaysSchedule = attendanceDaysSchedule;
    }

    
    @Column(name="attendance_days_present")
    public Integer getAttendanceDaysPresent() {
        return this.attendanceDaysPresent;
    }
    
    public void setAttendanceDaysPresent(Integer attendanceDaysPresent) {
        this.attendanceDaysPresent = attendanceDaysPresent;
    }

    
    @Column(name="leave")
    public Integer getLeave() {
        return this.leave;
    }
    
    public void setLeave(Integer leave) {
        this.leave = leave;
    }

    
    @Column(name="permit")
    public Integer getPermit() {
        return this.permit;
    }
    
    public void setPermit(Integer permit) {
        this.permit = permit;
    }

    
    @Column(name="sick")
    public Integer getSick() {
        return this.sick;
    }
    
    public void setSick(Integer sick) {
        this.sick = sick;
    }

    
    @Column(name="duty")
    public Integer getDuty() {
        return this.duty;
    }
    
    public void setDuty(Integer duty) {
        this.duty = duty;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }




}


