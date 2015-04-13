package com.inkubator.hrm.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "rmbs_disbursement", catalog = "hrm")
public class RmbsDisbursement implements java.io.Serializable {

    private Long id;
    private Integer version;
    private String code;
    private Date disbursementDate;
    private Date payrollPeriodDate;
    private String description;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<RmbsApplicationDisbursement> rmbsApplicationDisbursements = new HashSet<RmbsApplicationDisbursement>(0);

    public RmbsDisbursement() {    	
    }

    public RmbsDisbursement(long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "rmbs_disbursement_seq_gen")
    @SequenceGenerator(name = "rmbs_disbursement_seq_gen", sequenceName = "RMBS_DISBURSEMENT_SEQ")
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
    
    @Column(name = "code", unique = true, nullable = false, length = 45)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}		

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "disbursement_date", length = 19, nullable = false)
	public Date getDisbursementDate() {
		return disbursementDate;
	}

	public void setDisbursementDate(Date disbursementDate) {
		this.disbursementDate = disbursementDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "payroll_period_date", length = 19, nullable = false)
	public Date getPayrollPeriodDate() {
		return payrollPeriodDate;
	}

	public void setPayrollPeriodDate(Date payrollPeriodDate) {
		this.payrollPeriodDate = payrollPeriodDate;
	}

	@Column(name = "description", length = 65535, columnDefinition = "Text", nullable = false)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rmbsDisbursement")
	public Set<RmbsApplicationDisbursement> getRmbsApplicationDisbursements() {
		return rmbsApplicationDisbursements;
	}

	public void setRmbsApplicationDisbursements(Set<RmbsApplicationDisbursement> rmbsApplicationDisbursements) {
		this.rmbsApplicationDisbursements = rmbsApplicationDisbursements;
	}
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RmbsDisbursement other = (RmbsDisbursement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
        
}
