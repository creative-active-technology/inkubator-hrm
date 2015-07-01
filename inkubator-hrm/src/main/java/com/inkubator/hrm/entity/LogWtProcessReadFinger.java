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
import javax.persistence.Version;

/**
 * LogAttendanceRealization generated by hbm2java
 */
@Entity
@Table(name="log_wt_process_read_finger", catalog="hrm_payroll_backup")
public class LogWtProcessReadFinger  implements java.io.Serializable {

    private Long id;
    private Integer version;
    private Long empDataId;
    private String empNik;
    private String empName;
    private Long empJabatanId;
    private String empJabatanCode;
    private String empJabatanName;
    private String empGolJab;
    private Long empDepartementId;
    private String empDepartementName;
    private Long empTypeId;
    private String empTypeName;
    private Long wtPeriodeId;
    private Date periodeDateStart;
    private Date periodeDateEnd;
    private String workingHourName;
    private Date scheduleDate;
    private Date scheduleIn;
    private Date scheduleOut;
    private Date fingerIn;
    private Date fingerOut;
    private Date webCheckIn;
    private Date webCheckOut;
    private Boolean isCorrectionIn;
    private Boolean isCorrectionOut;
    private Integer marginIn;
    private Integer marginOut;
    private Date createdOn;
    private String createdBy;

    public LogWtProcessReadFinger() {
    	
    }
	
    public LogWtProcessReadFinger(Long id) {
        this.id = id;
    }
   
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "log_wt_process_read_finger_seq_gen")
    @SequenceGenerator(name = "log_wt_process_read_finger_seq_gen", sequenceName = "LOG_WT_PROCESS_READ_FINGER_SEQ")
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
    
    @Column(name = "emp_type_id", nullable = false)
    public Long getEmpTypeId() {
		return empTypeId;
	}

	public void setEmpTypeId(Long empTypeId) {
		this.empTypeId = empTypeId;
	}

	@Column(name = "emp_type_name", nullable = false)
	public String getEmpTypeName() {
		return empTypeName;
	}

	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}

	@Column(name="wt_periode_id", nullable = false)
    public Long getWtPeriodeId() {
        return this.wtPeriodeId;
    }
    
    public void setWtPeriodeId(Long wtPeriodeId) {
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
    
    @Column(name = "working_hour_name", nullable = false)
	public String getWorkingHourName() {
		return workingHourName;
	}

	public void setWorkingHourName(String workingHourName) {
		this.workingHourName = workingHourName;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "schedule_date", length = 19, nullable = false)
	public Date getScheduleDate() {
		return scheduleDate;
	}
	
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	@Temporal(TemporalType.TIME)
    @Column(name = "schedule_in", length = 8, nullable = false)
	public Date getScheduleIn() {
		return scheduleIn;
	}

	public void setScheduleIn(Date scheduleIn) {
		this.scheduleIn = scheduleIn;
	}

	@Temporal(TemporalType.TIME)
    @Column(name = "schedule_out", length = 8, nullable = false)
	public Date getScheduleOut() {
		return scheduleOut;
	}

	public void setScheduleOut(Date scheduleOut) {
		this.scheduleOut = scheduleOut;
	}

	@Temporal(TemporalType.TIME)
    @Column(name = "finger_in", length = 8)
	public Date getFingerIn() {
		return fingerIn;
	}

	public void setFingerIn(Date fingerIn) {
		this.fingerIn = fingerIn;
	}

	@Temporal(TemporalType.TIME)
    @Column(name = "finger_out", length = 8)
	public Date getFingerOut() {
		return fingerOut;
	}

	public void setFingerOut(Date fingerOut) {
		this.fingerOut = fingerOut;
	}
		
	@Temporal(TemporalType.TIME)
    @Column(name = "web_check_in", length = 8)
	public Date getWebCheckIn() {
		return webCheckIn;
	}

	public void setWebCheckIn(Date webCheckIn) {
		this.webCheckIn = webCheckIn;
	}

	@Temporal(TemporalType.TIME)
    @Column(name = "web_check_out", length = 8)
	public Date getWebCheckOut() {
		return webCheckOut;
	}

	public void setWebCheckOut(Date webCheckOut) {
		this.webCheckOut = webCheckOut;
	}

	@Column(name = "is_correction_in")
	public Boolean getIsCorrectionIn() {
		return isCorrectionIn;
	}

	public void setIsCorrectionIn(Boolean isCorrectionIn) {
		this.isCorrectionIn = isCorrectionIn;
	}

	@Column(name = "is_correction_out")
	public Boolean getIsCorrectionOut() {
		return isCorrectionOut;
	}

	public void setIsCorrectionOut(Boolean isCorrectionOut) {
		this.isCorrectionOut = isCorrectionOut;
	}

	@Column(name = "margin_in")
	public Integer getMarginIn() {
		return marginIn;
	}
	
	public void setMarginIn(Integer marginIn) {
		this.marginIn = marginIn;
	}

	@Column(name = "margin_out")
	public Integer getMarginOut() {
		return marginOut;
	}

	public void setMarginOut(Integer marginOut) {
		this.marginOut = marginOut;
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