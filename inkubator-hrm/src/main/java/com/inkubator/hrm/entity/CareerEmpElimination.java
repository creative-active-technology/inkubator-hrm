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
     private CareerTransition careerTransition;
     private WtPeriode wtPeriode;
     private EmpData empData;
     private Long pendingLoan;
     private Long separationPay;
     private String reason;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updatedBy;

    public CareerEmpElimination() {
    }

	
    public CareerEmpElimination(long id) {
        this.id = id;
    }
    public CareerEmpElimination(long id, CareerTransition careerTransition, WtPeriode wtPeriode, Long pendingLoan, Long separationPay, Date createdOn, String createdBy, Date updatedOn, String updatedBy) {
       this.id = id;
       this.careerTransition = careerTransition;
       this.wtPeriode = wtPeriode;
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
    @JoinColumn(name="career_transition_id")
    public CareerTransition getCareerTransition() {
        return this.careerTransition;
    }
    
    public void setCareerTransition(CareerTransition careerTransition) {
        this.careerTransition = careerTransition;
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
    public Long getPendingLoan() {
        return this.pendingLoan;
    }
    
    public void setPendingLoan(Long pendingLoan) {
        this.pendingLoan = pendingLoan;
    }

    
    @Column(name="separation_pay", precision=10, scale=0)
    public Long getSeparationPay() {
        return this.separationPay;
    }
    
    public void setSeparationPay(Long separationPay) {
        this.separationPay = separationPay;
    }
    
    
    @Column(name = "reason", length = 225)
    public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
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




}


