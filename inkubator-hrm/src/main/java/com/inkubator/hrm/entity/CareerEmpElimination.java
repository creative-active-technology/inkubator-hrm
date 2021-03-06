package com.inkubator.hrm.entity;
// Generated Dec 10, 2015 10:52:10 AM by Hibernate Tools 4.3.1


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

/**
 * CareerEmpElimination generated by hbm2java
 */
@Entity
@Table(name="career_emp_elimination"
    ,catalog="hrm"
)
public class CareerEmpElimination  implements java.io.Serializable {


     private long id;
     private Integer version;
     private CareerTerminationType careerTerminationType;
     private WtPeriode wtPeriode;
     private EmpData empData;
     private Double pendingLoan;
     private Double separationPay;
     private String reason;
     private String approvalActivityNumber;
     private Integer eliminationStatus;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updatedBy;
     private Date effectiveDate;

    public CareerEmpElimination() {
    }

	
    public CareerEmpElimination(long id) {
        this.id = id;
    }
    public CareerEmpElimination(long id,  WtPeriode wtPeriode, CareerTerminationType careerTerminationType, Double pendingLoan, Double separationPay, Date createdOn, String createdBy, Date updatedOn, String updatedBy) {
       this.id = id;
       this.wtPeriode = wtPeriode;
       this.careerTerminationType = careerTerminationType;
       this.pendingLoan = pendingLoan;
       this.separationPay = separationPay;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedOn = updatedOn;
       this.updatedBy = updatedBy;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

   
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="career_termination_type_id")
    public CareerTerminationType getCareerTerminationType() {
		return careerTerminationType;
	}


	public void setCareerTerminationType(CareerTerminationType careerTerminationType) {
		this.careerTerminationType = careerTerminationType;
	}


	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="last_salary_periode_id")
    public WtPeriode getWtPeriode() {
        return this.wtPeriode;
    }
    
    public void setWtPeriode(WtPeriode wtPeriode) {
        this.wtPeriode = wtPeriode;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="emp_data_id")
    public EmpData getEmpData() {
		return empData;
	}
    
	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}


	@Column(name="pending_loan", precision=10, scale=0)
    public Double getPendingLoan() {
        return this.pendingLoan;
    }
    
    public void setPendingLoan(Double pendingLoan) {
        this.pendingLoan = pendingLoan;
    }

    
    @Column(name="separation_pay", precision=10, scale=0)
    public Double getSeparationPay() {
        return this.separationPay;
    }
    
    public void setSeparationPay(Double separationPay) {
        this.separationPay = separationPay;
    }
    
    
    @Column(name = "reason", length = 225)
    public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "approval_activity_number", length = 45, unique = true)
    public String getApprovalActivityNumber() {
        return approvalActivityNumber;
    }

    public void setApprovalActivityNumber(String approvalActivityNumber) {
        this.approvalActivityNumber = approvalActivityNumber;
    }
    
    @Column(name = "elimination_status")
	public Integer getEliminationStatus() {
		return eliminationStatus;
	}


	public void setEliminationStatus(Integer eliminationStatus) {
		this.eliminationStatus = eliminationStatus;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "effective_date", length = 10)
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

    


}


