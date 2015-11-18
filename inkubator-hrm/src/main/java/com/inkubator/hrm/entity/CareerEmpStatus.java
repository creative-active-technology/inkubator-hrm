package com.inkubator.hrm.entity;

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
@Table(name = "career_emp_status", catalog = "hrm")
public class CareerEmpStatus implements java.io.Serializable {

    private Long id;
    private Integer version;
    private String code;
    private String name;
    private Integer limitTime;
    private Boolean isAutoMove;
    private EmployeeType empType;
    private SystemLetterReference systemLetterReference;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    

    public CareerEmpStatus() {
    }

    public CareerEmpStatus(Long id) {
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

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name = "code", unique = true, nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name = "limit_time", nullable = false)
    public Integer getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Integer limitTime) {
		this.limitTime = limitTime;
	}

	@Column(name = "is_auto_move", nullable = false)
	public Boolean getIsAutoMove() {
		return isAutoMove;
	}

	public void setIsAutoMove(Boolean isAutoMove) {
		this.isAutoMove = isAutoMove;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_type_id", nullable = false)
	public EmployeeType getEmpType() {
		return empType;
	}

	public void setEmpType(EmployeeType empType) {
		this.empType = empType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sys_letter_ref_id", nullable = false)
	public SystemLetterReference getSystemLetterReference() {
		return systemLetterReference;
	}

	public void setSystemLetterReference(SystemLetterReference systemLetterReference) {
		this.systemLetterReference = systemLetterReference;
	}

	@Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    
    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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
