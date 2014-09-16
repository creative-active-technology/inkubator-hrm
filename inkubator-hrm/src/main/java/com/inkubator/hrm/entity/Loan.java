/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "loan", catalog = "hrm")
public class Loan implements java.io.Serializable {
    private Long id;
    private Integer version;
    private EmpData empData;
    private LoanSchema loanSchema;
    private Double nominalPrincipal;
    private Date loanDate;
    private Date loanPaymentDate;
    private Double interestRate;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<LoanPaymentDetail> loanPaymentDetails = new HashSet<LoanPaymentDetail>(0);
    

    public Loan() {
    }

    public Loan(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "loan_seq_gen")
    @SequenceGenerator(name = "loan_seq_gen", sequenceName = "LOAN_SEQ")
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
    @JoinColumn(name = "emp_data_id", nullable = false)
    public EmpData getEmpData() {
		return empData;
	}

	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_schema_id", nullable = false)
	public LoanSchema getLoanSchema() {
		return loanSchema;
	}

	public void setLoanSchema(LoanSchema loanSchema) {
		this.loanSchema = loanSchema;
	}

	@Column(name="nominal_principal", nullable = false)
	public Double getNominalPrincipal() {
		return nominalPrincipal;
	}

	public void setNominalPrincipal(Double nominalPrincipal) {
		this.nominalPrincipal = nominalPrincipal;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "loan_date", length = 19, nullable = false)
	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "loan_payment_date", length = 19, nullable = false)
	public Date getLoanPaymentDate() {
		return loanPaymentDate;
	}

	public void setLoanPaymentDate(Date loanPaymentDate) {
		this.loanPaymentDate = loanPaymentDate;
	}

	@Column(name="interest_rate", nullable=false)
	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "loan", orphanRemoval = true)
	public Set<LoanPaymentDetail> getLoanPaymentDetails() {
		return loanPaymentDetails;
	}

	public void setLoanPaymentDetails(Set<LoanPaymentDetail> loanPaymentDetails) {
		this.loanPaymentDetails = loanPaymentDetails;
	}   
    
    
}
