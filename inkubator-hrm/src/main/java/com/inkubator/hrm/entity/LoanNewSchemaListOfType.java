/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name="loan_new_schema_list_of_type"
    ,catalog="hrm"
)
public class LoanNewSchemaListOfType  implements java.io.Serializable {


     private LoanNewSchemaListOfTypeId id;
     private Integer version;
     private LoanNewSchema loanNewSchema;
     private LoanNewType loanNewType;
     private Double maximumApproval;
     private Double maximumAllocation;
     private Double minimumApproval;
     private Double minimumAllocation;
     private Double minimumMonthlyInstallment;
     private Integer maxPeriode;
     private Integer maksimumHariTersedia;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updateBy;

    public LoanNewSchemaListOfType() {
    }

	
    public LoanNewSchemaListOfType(LoanNewSchemaListOfTypeId id, LoanNewSchema loanNewSchema, LoanNewType loanNewType, Integer maxPeriode, Integer maksimumHariTersedia) {
        this.id = id;
        this.loanNewSchema = loanNewSchema;
        this.loanNewType = loanNewType;
        this.maxPeriode = maxPeriode;
        this.maksimumHariTersedia = maksimumHariTersedia;
    }
    public LoanNewSchemaListOfType(LoanNewSchemaListOfTypeId id, LoanNewSchema loanNewSchema, LoanNewType loanNewType, Double maximumApproval, Double maximumAllocation, Double minimumApproval, Double minimumAllocation, Double minimumMonthlyInstallment, Integer maxPeriode, Integer maksimumHariTersedia, Date createdOn, String createdBy, Date updatedOn, String updateBy) {
       this.id = id;
       this.loanNewSchema = loanNewSchema;
       this.loanNewType = loanNewType;
       this.maximumApproval = maximumApproval;
       this.maximumAllocation = maximumAllocation;
       this.minimumApproval = minimumApproval;
       this.minimumAllocation = minimumAllocation;
       this.minimumMonthlyInstallment = minimumMonthlyInstallment;
       this.maxPeriode = maxPeriode;
       this.maksimumHariTersedia = maksimumHariTersedia;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedOn = updatedOn;
       this.updateBy = updateBy;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="loanNewTypeId", column=@Column(name="loan_new_type_id", nullable=false) ), 
        @AttributeOverride(name="loanNewSchemaId", column=@Column(name="loan_new_schema_id", nullable=false) ) } )
    public LoanNewSchemaListOfTypeId getId() {
        return this.id;
    }
    
    public void setId(LoanNewSchemaListOfTypeId id) {
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
    @JoinColumn(name="loan_new_schema_id", nullable=false, insertable=false, updatable=false)
    public LoanNewSchema getLoanNewSchema() {
        return this.loanNewSchema;
    }
    
    public void setLoanNewSchema(LoanNewSchema loanNewSchema) {
        this.loanNewSchema = loanNewSchema;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="loan_new_type_id", nullable=false, insertable=false, updatable=false)
    public LoanNewType getLoanNewType() {
        return this.loanNewType;
    }
    
    public void setLoanNewType(LoanNewType loanNewType) {
        this.loanNewType = loanNewType;
    }

    
    @Column(name="maximum_approval", precision=22, scale=0)
    public Double getMaximumApproval() {
        return this.maximumApproval;
    }
    
    public void setMaximumApproval(Double maximumApproval) {
        this.maximumApproval = maximumApproval;
    }

    
    @Column(name="maximum_allocation", precision=22, scale=0)
    public Double getMaximumAllocation() {
        return this.maximumAllocation;
    }
    
    public void setMaximumAllocation(Double maximumAllocation) {
        this.maximumAllocation = maximumAllocation;
    }

    
    @Column(name="minimum_approval", precision=22, scale=0)
    public Double getMinimumApproval() {
        return this.minimumApproval;
    }
    
    public void setMinimumApproval(Double minimumApproval) {
        this.minimumApproval = minimumApproval;
    }

    
    @Column(name="minimum_allocation", precision=22, scale=0)
    public Double getMinimumAllocation() {
        return this.minimumAllocation;
    }
    
    public void setMinimumAllocation(Double minimumAllocation) {
        this.minimumAllocation = minimumAllocation;
    }

    
    @Column(name="minimum_monthly_installment", precision=22, scale=0)
    public Double getMinimumMonthlyInstallment() {
        return this.minimumMonthlyInstallment;
    }
    
    public void setMinimumMonthlyInstallment(Double minimumMonthlyInstallment) {
        this.minimumMonthlyInstallment = minimumMonthlyInstallment;
    }

    
    @Column(name="max_periode", nullable=false)
    public Integer getMaxPeriode() {
        return this.maxPeriode;
    }
    
    public void setMaxPeriode(Integer maxPeriode) {
        this.maxPeriode = maxPeriode;
    }

    
    @Column(name="maksimum_hari_tersedia", nullable=false)
    public Integer getMaksimumHariTersedia() {
        return this.maksimumHariTersedia;
    }
    
    public void setMaksimumHariTersedia(Integer maksimumHariTersedia) {
        this.maksimumHariTersedia = maksimumHariTersedia;
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

    
    @Column(name="update_by", length=45)
    public String getUpdateBy() {
        return this.updateBy;
    }
    
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }




}


