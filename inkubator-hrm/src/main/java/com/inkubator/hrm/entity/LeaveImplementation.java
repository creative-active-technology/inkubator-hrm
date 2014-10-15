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
@Table(name = "leave_implementation", catalog = "hrm"
)
public class LeaveImplementation implements java.io.Serializable {

    private Long id;
    private Integer version;
    private String numberFilling;
    private Leave leave;
    private EmpData empData;
    private Date startDate;
    private Date endDate;
    private Date fillingDate;
    private String address;
    private String mobilePhone;
    private String materialJobsAbandoned;
    private EmpData temporaryActing;
    private String description;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;

    public LeaveImplementation() {
    	
    }

    public LeaveImplementation(long id) {
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

    @Column(name = "number_filling", unique = true, nullable = false, length = 60)
    public String getNumberFilling() {
		return numberFilling;
	}

	public void setNumberFilling(String numberFilling) {
		this.numberFilling = numberFilling;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_id", nullable = false)
	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
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
    @Column(name = "start_date", length = 19, nullable =  false)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", length = 19, nullable =  false)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "filling_date", length = 19, nullable =  false)
	public Date getFillingDate() {
		return fillingDate;
	}

	public void setFillingDate(Date fillingDate) {
		this.fillingDate = fillingDate;
	}

	@Column(name = "address", length = 65535, columnDefinition = "Text", nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "mobile_phone", length = 25, nullable = false)
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "material_jobs_abandoned", length = 65535, columnDefinition = "Text", nullable = false)
	public String getMaterialJobsAbandoned() {
		return materialJobsAbandoned;
	}

	public void setMaterialJobsAbandoned(String materialJobsAbandoned) {
		this.materialJobsAbandoned = materialJobsAbandoned;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temporary_acting_id")
	public EmpData getTemporaryActing() {
		return temporaryActing;
	}

	public void setTemporaryActing(EmpData temporaryActing) {
		this.temporaryActing = temporaryActing;
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
