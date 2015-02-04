/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author deni
 */
@Entity
@Table(name="loan_canceled"
    ,catalog="hrm_personalia"
    , uniqueConstraints = @UniqueConstraint(columnNames="approval_activity_number") 
)
public class LoanCanceled  implements java.io.Serializable {


     private Long id;
     private Integer version;
     private EmpData empData;
     private LoanSchema loanSchema;
     private String approvalActivityNumber;
     private String createdBy;
     private Date createdOn;
     private double interestRate;
     private Date loanDate;
     private double nominalPrincipal;
     private int termin;
     private Integer typeOfInterest;
     private String updatedBy;
     private Date updatedOn;
     private String description;
     private Date cancelationDate;

    public LoanCanceled() {
    }

	
    public LoanCanceled(EmpData empData, LoanSchema loanSchema, double interestRate, Date loanDate, double nominalPrincipal, int termin, String description) {
        this.empData = empData;
        this.loanSchema = loanSchema;
        this.interestRate = interestRate;
        this.loanDate = loanDate;
        this.nominalPrincipal = nominalPrincipal;
        this.termin = termin;
        this.description = description;
    }
    public LoanCanceled(EmpData empData, LoanSchema loanSchema, String approvalActivityNumber, String createdBy, Date createdOn, double interestRate, Date loanDate, double nominalPrincipal, int termin, Integer typeOfInterest, String updatedBy, Date updatedOn, String description, Date cancelationDate) {
       this.empData = empData;
       this.loanSchema = loanSchema;
       this.approvalActivityNumber = approvalActivityNumber;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.interestRate = interestRate;
       this.loanDate = loanDate;
       this.nominalPrincipal = nominalPrincipal;
       this.termin = termin;
       this.typeOfInterest = typeOfInterest;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.description = description;
       this.cancelationDate = cancelationDate;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
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
    @JoinColumn(name="emp_data_id", nullable=false)
    public EmpData getEmpData() {
        return this.empData;
    }
    
    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="loan_schema_id", nullable=false)
    public LoanSchema getLoanSchema() {
        return this.loanSchema;
    }
    
    public void setLoanSchema(LoanSchema loanSchema) {
        this.loanSchema = loanSchema;
    }

    
    @Column(name="approval_activity_number", unique=true, length=45)
    public String getApprovalActivityNumber() {
        return this.approvalActivityNumber;
    }
    
    public void setApprovalActivityNumber(String approvalActivityNumber) {
        this.approvalActivityNumber = approvalActivityNumber;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="interest_rate", nullable=false, precision=22, scale=0)
    public double getInterestRate() {
        return this.interestRate;
    }
    
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="loan_date", nullable=false, length=19)
    public Date getLoanDate() {
        return this.loanDate;
    }
    
    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    
    @Column(name="nominal_principal", nullable=false, precision=22, scale=0)
    public double getNominalPrincipal() {
        return this.nominalPrincipal;
    }
    
    public void setNominalPrincipal(double nominalPrincipal) {
        this.nominalPrincipal = nominalPrincipal;
    }

    
    @Column(name="termin", nullable=false)
    public int getTermin() {
        return this.termin;
    }
    
    public void setTermin(int termin) {
        this.termin = termin;
    }

    
    @Column(name="type_of_interest")
    public Integer getTypeOfInterest() {
        return this.typeOfInterest;
    }
    
    public void setTypeOfInterest(Integer typeOfInterest) {
        this.typeOfInterest = typeOfInterest;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
    @Column(name="description", nullable=false)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="cancelation_date", length=19)
    public Date getCancelationDate() {
        return this.cancelationDate;
    }
    
    public void setCancelationDate(Date cancelationDate) {
        this.cancelationDate = cancelationDate;
    }




}
