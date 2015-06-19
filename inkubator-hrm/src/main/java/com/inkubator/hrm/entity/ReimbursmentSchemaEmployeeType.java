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
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name = "reimbursment_schema_employee_type", catalog="hrm_payroll_backup"
)
public class ReimbursmentSchemaEmployeeType implements java.io.Serializable {
    private Long id;
    private Integer version;
    private ReimbursmentSchema reimbursmentSchema;
    private EmployeeType employeeType;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;

    public ReimbursmentSchemaEmployeeType() {
    }

    public ReimbursmentSchemaEmployeeType(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "reimbursment_schema_employee_type_seq_gen")
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(name = "reimbursment_schema_employee_type_seq_gen", sequenceName = "REIMBURSMENT_SCHEMA_EMPLOYEE_TYPE_SEQ")
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
    @JoinColumn(name = "reimbursment_schema_id", nullable = false)
    public ReimbursmentSchema getReimbursmentSchema() {
        return reimbursmentSchema;
    }

    public void setReimbursmentSchema(ReimbursmentSchema reimbursmentSchema) {
        this.reimbursmentSchema = reimbursmentSchema;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_type_id", nullable = false)
    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
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
    
    
}
