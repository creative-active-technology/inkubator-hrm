package com.inkubator.hrm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(name = "wt_emp_correction_attendance_detail", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames= {"emp_correction_attendance_id", "attendance_date"}))
public class WtEmpCorrectionAttendanceDetail implements Serializable {

    private Long id;
    private Integer version;
    
    private WtEmpCorrectionAttendance wtEmpCorrectionAttendance;
    private Long workingHourId;
    private String workingHourName;
    private Date scheduleIn;
    private Date scheduleOut;
    private Date attendanceDate;
    private Date attendanceIn;
    private Date attendanceOut;
    private Date correctionIn;
    private Date correctionOut;
    private String description;    
    
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;

    public WtEmpCorrectionAttendanceDetail() {

    }

    public WtEmpCorrectionAttendanceDetail(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "wt_emp_correction_attendance_detail_seq_gen")
    @SequenceGenerator(name = "wt_emp_correction_attendance_detail_seq_gen", sequenceName = "WT_EMP_CORRECTION_ATTENDANCE_DETAIL_SEQ")
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_correction_attendance_id", nullable = false)
	public WtEmpCorrectionAttendance getWtEmpCorrectionAttendance() {
		return wtEmpCorrectionAttendance;
	}

	public void setWtEmpCorrectionAttendance(
			WtEmpCorrectionAttendance wtEmpCorrectionAttendance) {
		this.wtEmpCorrectionAttendance = wtEmpCorrectionAttendance;
	}

	@Column(name = "working_hour_id", nullable = false)
	public Long getWorkingHourId() {
		return workingHourId;
	}

	public void setWorkingHourId(Long workingHourId) {
		this.workingHourId = workingHourId;
	}

	@Column(name = "working_hour_name", nullable = false)
	public String getWorkingHourName() {
		return workingHourName;
	}

	public void setWorkingHourName(String workingHourName) {
		this.workingHourName = workingHourName;
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

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "attendance_date", length = 19, nullable = false)
	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
		
	@Temporal(TemporalType.TIME)
    @Column(name = "attendance_in", length = 19, nullable = false)
	public Date getAttendanceIn() {
		return attendanceIn;
	}

	public void setAttendanceIn(Date attendanceIn) {
		this.attendanceIn = attendanceIn;
	}

	@Temporal(TemporalType.TIME)
    @Column(name = "attendance_out", length = 19, nullable = false)
	public Date getAttendanceOut() {
		return attendanceOut;
	}

	public void setAttendanceOut(Date attendanceOut) {
		this.attendanceOut = attendanceOut;
	}

	@Temporal(TemporalType.TIME)
    @Column(name = "correction_in", length = 19)
	public Date getCorrectionIn() {
		return correctionIn;
	}

	public void setCorrectionIn(Date correctionIn) {
		this.correctionIn = correctionIn;
	}

	@Temporal(TemporalType.TIME)
    @Column(name = "correction_out", length = 19)
	public Date getCorrectionOut() {
		return correctionOut;
	}

	public void setCorrectionOut(Date correctionOut) {
		this.correctionOut = correctionOut;
	}	
	
	@Column(name = "description", length = 65535, columnDefinition = "Text")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

	public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
}
