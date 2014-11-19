package com.inkubator.hrm.entity;

import java.io.Serializable;
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
import javax.persistence.Version;

@Entity
@Table(name = "temp_process_read_finger", catalog = "hrm")
public class TempProcessReadFinger implements Serializable {

    private Long id;
    private Integer version;
    
    private MecineFinger mecineFinger;
    private EmpData empData;
    private Date scheduleDate;
    private Date scheduleIn;
    private Date scheduleOut;
    private Date fingerIn;
    private Date fingerOut;
    private Boolean correctionIn;
    private Boolean correctionOut;
    private Double marginIn;
    private Double marginOut;
    
    
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;

    public TempProcessReadFinger() {

    }

    public TempProcessReadFinger(Long id) {
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

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mecine_finger_id", nullable = false)
	public MecineFinger getMecineFinger() {
		return mecineFinger;
	}

	public void setMecineFinger(MecineFinger mecineFinger) {
		this.mecineFinger = mecineFinger;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_data_id", nullable = false)
	public EmpData getEmpData() {
		return empData;
	}

	public void setEmpData(EmpData empData) {
		this.empData = empData;
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
    @Column(name = "finger_in", length = 8, nullable = false)
	public Date getFingerIn() {
		return fingerIn;
	}

	public void setFingerIn(Date fingerIn) {
		this.fingerIn = fingerIn;
	}

	@Temporal(TemporalType.TIME)
    @Column(name = "finger_out", length = 8, nullable = false)
	public Date getFingerOut() {
		return fingerOut;
	}

	public void setFingerOut(Date fingerOut) {
		this.fingerOut = fingerOut;
	}

	@Column(name = "correction_in")
	public Boolean getCorrectionIn() {
		return correctionIn;
	}
	
	public void setCorrectionIn(Boolean correctionIn) {
		this.correctionIn = correctionIn;
	}

	@Column(name = "correction_out")
	public Boolean getCorrectionOut() {
		return correctionOut;
	}

	public void setCorrectionOut(Boolean correctionOut) {
		this.correctionOut = correctionOut;
	}

	@Column(name = "margin_in")
	public Double getMarginIn() {
		return marginIn;
	}
	
	public void setMarginIn(Double marginIn) {
		this.marginIn = marginIn;
	}

	@Column(name = "margin_out")
	public Double getMarginOut() {
		return marginOut;
	}

	public void setMarginOut(Double marginOut) {
		this.marginOut = marginOut;
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