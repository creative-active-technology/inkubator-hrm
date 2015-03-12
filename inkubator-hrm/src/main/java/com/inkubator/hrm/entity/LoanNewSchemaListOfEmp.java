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
@Table(name="loan_new_schema_list_of_emp"
    ,catalog="hrm"
)
public class LoanNewSchemaListOfEmp  implements java.io.Serializable {


     private LoanNewSchemaListOfEmpId id;
     private Integer version;
     private EmpData empData;
     private LoanNewSchema loanNewSchema;
     private String nomorSk;
     private String description;
     private Date createdOn;
     private String createdBy;

    public LoanNewSchemaListOfEmp() {
    }

	
    public LoanNewSchemaListOfEmp(LoanNewSchemaListOfEmpId id, EmpData empData, LoanNewSchema loanNewSchema) {
        this.id = id;
        this.empData = empData;
        this.loanNewSchema = loanNewSchema;
    }
    public LoanNewSchemaListOfEmp(LoanNewSchemaListOfEmpId id, EmpData empData, LoanNewSchema loanNewSchema, String nomorSk, String description, Date createdOn, String createdBy) {
       this.id = id;
       this.empData = empData;
       this.loanNewSchema = loanNewSchema;
       this.nomorSk = nomorSk;
       this.description = description;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="empId", column=@Column(name="emp_id", nullable=false) ), 
        @AttributeOverride(name="loanNewSchemaId", column=@Column(name="loan_new_schema_id", nullable=false) ) } )
    public LoanNewSchemaListOfEmpId getId() {
        return this.id;
    }
    
    public void setId(LoanNewSchemaListOfEmpId id) {
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
    @JoinColumn(name="emp_id", nullable=false, insertable=false, updatable=false)
    public EmpData getEmpData() {
        return this.empData;
    }
    
    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="loan_new_schema_id", nullable=false, insertable=false, updatable=false)
    public LoanNewSchema getLoanNewSchema() {
        return this.loanNewSchema;
    }
    
    public void setLoanNewSchema(LoanNewSchema loanNewSchema) {
        this.loanNewSchema = loanNewSchema;
    }

    
    @Column(name="nomor_sk", length=45)
    public String getNomorSk() {
        return this.nomorSk;
    }
    
    public void setNomorSk(String nomorSk) {
        this.nomorSk = nomorSk;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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




}