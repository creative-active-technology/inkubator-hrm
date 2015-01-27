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
import javax.persistence.Version;

@Entity
@Table(name = "finger_swap_captured", catalog = "hrm")
public class FingerSwapCaptured implements Serializable {

    private Long id;
    private Integer version;
    
    private Integer dataSource;
    private String fingerIndexId;
    private Date swapDatetimeLog;
    private Date datetimeAdded;
    private MecineFinger mecineFinger;
    private Boolean isAlreadyProcessed;
    
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;

    public FingerSwapCaptured() {

    }

    public FingerSwapCaptured(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "finger_swap_captured_seq_gen")
    @SequenceGenerator(name = "finger_swap_captured_seq_gen", sequenceName = "FingerSwapCaptured_SEQ")
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

    @Column(name = "data_source", nullable = false)
    public Integer getDataSource() {
		return dataSource;
	}

	public void setDataSource(Integer dataSource) {
		this.dataSource = dataSource;
	}

	@Column(name = "finger_index_id", nullable = false, length = 45)
	public String getFingerIndexId() {
		return fingerIndexId;
	}

	public void setFingerIndexId(String fingerIndexId) {
		this.fingerIndexId = fingerIndexId;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "swap_datetime_log", length = 19, nullable = false)
	public Date getSwapDatetimeLog() {
		return swapDatetimeLog;
	}

	public void setSwapDatetimeLog(Date swapDatetimeLog) {
		this.swapDatetimeLog = swapDatetimeLog;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datetime_added", length = 19, nullable = false)
	public Date getDatetimeAdded() {
		return datetimeAdded;
	}

	public void setDatetimeAdded(Date datetimeAdded) {
		this.datetimeAdded = datetimeAdded;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mecine_finger_id", nullable = false)
	public MecineFinger getMecineFinger() {
		return mecineFinger;
	}

	public void setMecineFinger(MecineFinger mecineFinger) {
		this.mecineFinger = mecineFinger;
	}

	@Column(name = "is_already_processed")
	public Boolean getIsAlreadyProcessed() {
		return isAlreadyProcessed;
	}

	public void setIsAlreadyProcessed(Boolean isAlreadyProcessed) {
		this.isAlreadyProcessed = isAlreadyProcessed;
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
