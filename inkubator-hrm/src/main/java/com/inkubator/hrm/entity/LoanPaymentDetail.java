/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

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
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "loan_payment_detail", catalog="hrm_payroll")
public class LoanPaymentDetail implements java.io.Serializable {
    private Long id;
    private Integer version;
    private Loan loan;
    private Date paymentDate;
    private Date dueDate;
    private Double principal;
    private Double interest;
    private Double totalPayment;
    private Double penalty;
    private Double remainingPrincipal;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Long bayarKe;
    

    public LoanPaymentDetail() {
    }

    public LoanPaymentDetail(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "loan_payment_detail_seq_gen")
    @SequenceGenerator(name = "loan_payment_detail_seq_gen", sequenceName = "LOAN_PAYMENT_DETAIL_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "payment_date", length = 19)
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "due_date", length = 19, nullable=false)
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name="principal", nullable = false)
	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	@Column(name="interest", nullable = false)
	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	@Column(name="total_payment", nullable = false)
	public Double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(Double totalPayment) {
		this.totalPayment = totalPayment;
	}

	@Column(name="penalty")
	public Double getPenalty() {
		return penalty;
	}

	public void setPenalty(Double penalty) {
		this.penalty = penalty;
	}

	@Column(name="remaining_principal", nullable = false)
	public Double getRemainingPrincipal() {
		return remainingPrincipal;
	}

	public void setRemainingPrincipal(Double remainingPrincipal) {
		this.remainingPrincipal = remainingPrincipal;
	}

	@Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }    

    @Transient
    public Long getBayarKe() {
        return bayarKe;
    }

    public void setBayarKe(Long bayarKe) {
        this.bayarKe = bayarKe;
    }
    
    
}
