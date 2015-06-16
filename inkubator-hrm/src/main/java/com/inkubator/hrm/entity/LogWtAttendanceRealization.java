package com.inkubator.hrm.entity;
// Generated Jun 3, 2015 4:03:54 PM by Hibernate Tools 4.3.1


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LogAttendanceRealization generated by hbm2java
 */
@Entity
@Table(name="log_wt_attendance_realization"
    ,catalog="hrm_payroll"
)
public class LogWtAttendanceRealization  implements java.io.Serializable {

    private int id;
    private Long empDataId;
    private String empNik;
    private String empName;
    private Long empJabatanId;
    private String empJabatanCode;
    private String empJabatanName;
    private String empGolJab;
    private Long empDepartementId;
    private String empDepartementName;
    private Integer wtPeriodeId;
    private Date periodeDateStart;
    private Date periodeDateEnd;
    private Long wtGroupWorkingId;
    private String wtGroupWorkingName;
    private Integer attendanceDaysSchedule;
    private Integer attendanceDaysPresent;
    private Integer leave;
    private Float overtime;
    private Integer permit;
    private Integer sick;
    private Integer duty;
    private Date createdOn;
    private String createdBy;

    public LogWtAttendanceRealization() {
    	
    }
	
    public LogWtAttendanceRealization(int id) {
        this.id = id;
    }
   
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "log_wt_attendance_realization_seq_gen")
    @SequenceGenerator(name = "log_wt_attendance_realization_seq_gen", sequenceName = "LOG_WT_ATTENDANCE_REALIZATION_SEQ")
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name="emp_data_id", nullable = false)
    public Long getEmpDataId() {
        return this.empDataId;
    }
    
    public void setEmpDataId(Long empDataId) {
        this.empDataId = empDataId;
    }
    
    @Column(name="emp_nik", length=45, nullable = false)
    public String getEmpNik() {
        return this.empNik;
    }
    
    public void setEmpNik(String empNik) {
        this.empNik = empNik;
    }
    
    @Column(name="emp_name", length=45, nullable = false)
    public String getEmpName() {
        return this.empName;
    }
    
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    @Column(name="emp_jabatan_id", nullable = false)
    public Long getEmpJabatanId() {
		return empJabatanId;
	}

	public void setEmpJabatanId(Long empJabatanId) {
		this.empJabatanId = empJabatanId;
	}

	@Column(name="emp_jabatan_code", length=45, nullable = false)
    public String getEmpJabatanCode() {
        return this.empJabatanCode;
    }
    
    public void setEmpJabatanCode(String empJabatanCode) {
        this.empJabatanCode = empJabatanCode;
    }
    
    @Column(name="emp_jabatan_name", length=45, nullable = false)
    public String getEmpJabatanName() {
        return this.empJabatanName;
    }
    
    public void setEmpJabatanName(String empJabatanName) {
        this.empJabatanName = empJabatanName;
    }
    
    @Column(name="emp_gol_jab", length=45, nullable = false)
    public String getEmpGolJab() {
        return this.empGolJab;
    }
    
    public void setEmpGolJab(String empGolJab) {
        this.empGolJab = empGolJab;
    }
    
    @Column(name="emp_departement_id", nullable = false)
    public Long getEmpDepartementId() {
		return empDepartementId;
	}

	public void setEmpDepartementId(Long empDepartementId) {
		this.empDepartementId = empDepartementId;
	}

	@Column(name="emp_departement_name", length=45, nullable = false)
    public String getEmpDepartementName() {
        return this.empDepartementName;
    }
    
    public void setEmpDepartementName(String empDepartementName) {
        this.empDepartementName = empDepartementName;
    }
    
    @Column(name="wt_periode_id", nullable = false)
    public Integer getWtPeriodeId() {
        return this.wtPeriodeId;
    }
    
    public void setWtPeriodeId(Integer wtPeriodeId) {
        this.wtPeriodeId = wtPeriodeId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="periode_date_start", length=10, nullable = false)
    public Date getPeriodeDateStart() {
        return this.periodeDateStart;
    }
    
    public void setPeriodeDateStart(Date periodeDateStart) {
        this.periodeDateStart = periodeDateStart;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="periode_date_end", length=10, nullable = false)
    public Date getPeriodeDateEnd() {
        return this.periodeDateEnd;
    }
    
    public void setPeriodeDateEnd(Date periodeDateEnd) {
        this.periodeDateEnd = periodeDateEnd;
    }
    
    @Column(name="wt_group_working_id", nullable = false)
    public Long getWtGroupWorkingId() {
        return this.wtGroupWorkingId;
    }
    
    public void setWtGroupWorkingId(Long wtGroupWorkingId) {
        this.wtGroupWorkingId = wtGroupWorkingId;
    }
    
    @Column(name="wt_group_working_name", length=45, nullable = false)
    public String getWtGroupWorkingName() {
        return this.wtGroupWorkingName;
    }
    
    public void setWtGroupWorkingName(String wtGroupWorkingName) {
        this.wtGroupWorkingName = wtGroupWorkingName;
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
    
    @Column(name="overtime", precision=12, scale=0)
    public Float getOvertime() {
        return this.overtime;
    }
    
    public void setOvertime(Float overtime) {
        this.overtime = overtime;
    }
    
    @Column(name="permit", precision=12, scale=0)
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