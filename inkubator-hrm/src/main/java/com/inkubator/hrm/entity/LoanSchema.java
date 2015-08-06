/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name = "loan_schema", catalog="hrm_payroll_backup", uniqueConstraints = @UniqueConstraint(columnNames="code") )
public class LoanSchema implements java.io.Serializable {
    private Long id;
    private Integer version;
    private String code;
    private String name;
    private CostCenter costCenter;
    private Integer typeOfInterest;
    private Integer maxPeriode;
    private Integer basicValue;
    private BigDecimal maxNominal;
    private BigDecimal minPayment;
    private Double maxPaymentOfSalary;
    private Double penaltyOfNonComplance;
    private Integer payrollComponent;
    private Double interestRate;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private List<EmployeeType> employeeTypes = new ArrayList<>(0);
    private Set<LoanSchemaEmployeeType> loanSchemaEmployeeTypes = new HashSet<LoanSchemaEmployeeType>(0);
    private Set<LoanCanceled> loanCanceleds = new HashSet<LoanCanceled>(0);
    private Set<Loan> loans = new HashSet<Loan>(0);
    

    public LoanSchema() {
    }

    public LoanSchema(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "loan_schema_seq_gen")
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(name = "loan_schema_seq_gen", sequenceName = "LOAN_SCHEMA_SEQ")
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

    @Column(name = "code", unique = true, nullable = false, length = 10)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name="name", length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_id", nullable = false)
    public CostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
    }

    @Column(name="type_of_interest", length = 1)
    public Integer getTypeOfInterest() {
        return typeOfInterest;
    }

    @Column(name="basic_value", length = 1)
    public Integer getBasicValue() {
        return basicValue;
    }

    public void setBasicValue(Integer basicValue) {
        this.basicValue = basicValue;
    }
    
    public void setTypeOfInterest(Integer typeOfInterest) {
        this.typeOfInterest = typeOfInterest;
    }

    @Column(name="max_period", length = 2)
    public Integer getMaxPeriode() {
        return maxPeriode;
    }

    public void setMaxPeriode(Integer maxPeriode) {
        this.maxPeriode = maxPeriode;
    }

    @Column(name = "max_nominal", precision = 10, scale = 0)
    public BigDecimal getMaxNominal() {
        return maxNominal;
    }

    public void setMaxNominal(BigDecimal maxNominal) {
        this.maxNominal = maxNominal;
    }

    @Column(name = "min_payment", precision = 10, scale = 0)
    public BigDecimal getMinPayment() {
        return minPayment;
    }

    public void setMinPayment(BigDecimal minPayment) {
        this.minPayment = minPayment;
    }
    
    @Column(name="max_payment_of_salary", length = 20)
    public Double getMaxPaymentOfSalary() {
        return maxPaymentOfSalary;
    }

    public void setMaxPaymentOfSalary(Double maxPaymentOfSalary) {
        this.maxPaymentOfSalary = maxPaymentOfSalary;
    }

    @Column(name="penalty_of_non_complance", length = 20)
    public Double getPenaltyOfNonComplance() {
        return penaltyOfNonComplance;
    }

    public void setPenaltyOfNonComplance(Double penaltyOfNonComplance) {
        this.penaltyOfNonComplance = penaltyOfNonComplance;
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

    @Column(name = "payroll_component", length = 2)
    public Integer getPayrollComponent() {
        return payrollComponent;
    }

    public void setPayrollComponent(Integer payrollComponent) {
        this.payrollComponent = payrollComponent;
    }
    
    
    
    @Transient
    public List<EmployeeType> getEmployeeTypes() {
        return employeeTypes;
    }

    public void setEmployeeTypes(List<EmployeeType> employeeTypes) {
        this.employeeTypes = employeeTypes;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "loanSchema", orphanRemoval = true)
    public Set<LoanSchemaEmployeeType> getLoanSchemaEmployeeTypes() {
        return loanSchemaEmployeeTypes;
    }

    public void setLoanSchemaEmployeeTypes(Set<LoanSchemaEmployeeType> loanSchemaEmployeeTypes) {
        this.loanSchemaEmployeeTypes = loanSchemaEmployeeTypes;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="loanSchema")
    public Set<LoanCanceled> getLoanCanceleds() {
        return this.loanCanceleds;
    }
    
    public void setLoanCanceleds(Set<LoanCanceled> loanCanceleds) {
        this.loanCanceleds = loanCanceleds;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loanSchema")
    public Set<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Set<Loan> loans) {
		this.loans = loans;
	}

	@Transient
    public String getBasicValueString(){
        String data = "";
        if(basicValue == 0){
            data = "Nominal";
        }else{
            data = "Salary";
        }
        return data;
    }
    
    @Transient
    public String getTypeOfInterestString(){
        String data = "";
        if(typeOfInterest == 0){
            data = "Annuity";
        }else if(typeOfInterest == 1){
            data = "Flat";
        }else{
            data = "Floating";
        }
        return data;
    }
    
}
