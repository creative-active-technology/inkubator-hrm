package com.inkubator.hrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "tax_rate", catalog="hrm_payroll_backup")
public class TaxRate implements java.io.Serializable {

    private Long id;
    private Integer version;
    private Double lowRate;
    private Double topRate;
    private Double percentRate;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;

    public TaxRate() {
    }

    public TaxRate(Long id) {
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

    @Column(name = "low_rate", nullable = false)
    public Double getLowRate() {
		return lowRate;
	}

	public void setLowRate(Double lowRate) {
		this.lowRate = lowRate;
	}

	@Column(name = "top_rate", nullable = false)
	public Double getTopRate() {
		return topRate;
	}

	public void setTopRate(Double topRate) {
		this.topRate = topRate;
	}

	@Column(name = "percent_rate", nullable = false)
	public Double getPercentRate() {
		return percentRate;
	}

	public void setPercentRate(Double percentRate) {
		this.percentRate = percentRate;
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
