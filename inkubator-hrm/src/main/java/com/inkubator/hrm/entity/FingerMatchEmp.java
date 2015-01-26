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
@Table(name = "finger_match_emp", catalog = "hrm")
public class FingerMatchEmp implements Serializable {

    private Long id;
    private Integer version;
    private String fingerIndexId;
    private EmpData empData;    
    private MecineFinger mecineFinger;
    private String nik;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;

    public FingerMatchEmp() {

    }

    public FingerMatchEmp(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "finger_match_emp_seq_gen")
    @SequenceGenerator(name = "finger_match_emp_seq_gen", sequenceName = "FingerMatchEmp_SEQ")
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
    
    @Column(name = "finger_index_id", nullable = false)
	public String getFingerIndexId() {
		return fingerIndexId;
	}

	public void setFingerIndexId(String fingerIndexId) {
		this.fingerIndexId = fingerIndexId;
	}

	@Column(name = "nik", nullable = false, length = 45)
	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_data_id", nullable = false)
	public EmpData getEmpData() {
		return empData;
	}

	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mecine_finger_id", nullable = false)
	public MecineFinger getMecineFinger() {
		return mecineFinger;
	}

	public void setMecineFinger(MecineFinger mecineFinger) {
		this.mecineFinger = mecineFinger;
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
