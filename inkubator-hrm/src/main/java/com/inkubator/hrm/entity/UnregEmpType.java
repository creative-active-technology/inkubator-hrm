package com.inkubator.hrm.entity;
// Generated Dec 24, 2014 8:49:26 AM by Hibernate Tools 4.3.1


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
 * UnregEmpType generated by hbm2java
 */
@Entity
@Table(name="unreg_emp_type"
    ,catalog="hrm"
)
public class UnregEmpType  implements java.io.Serializable {


     private UnregEmpTypeId id;
     private Integer version;
     private EmployeeType employeeType;
     private UnregSalary unregSalary;
     private String descriptions;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;

    public UnregEmpType() {
    }

	
    public UnregEmpType(UnregEmpTypeId id, EmployeeType employeeType, UnregSalary unregSalary) {
        this.id = id;
        this.employeeType = employeeType;
        this.unregSalary = unregSalary;
    }
    public UnregEmpType(UnregEmpTypeId id, EmployeeType employeeType, UnregSalary unregSalary, String descriptions, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
       this.id = id;
       this.employeeType = employeeType;
       this.unregSalary = unregSalary;
       this.descriptions = descriptions;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="unregId", column=@Column(name="unreg_id", nullable=false) ), 
        @AttributeOverride(name="unregEmpTypeId", column=@Column(name="unreg_emp_type_id", nullable=false) ) } )
    public UnregEmpTypeId getId() {
        return this.id;
    }
    
    public void setId(UnregEmpTypeId id) {
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
    @JoinColumn(name="unreg_emp_type_id", nullable=false, insertable=false, updatable=false)
    public EmployeeType getEmployeeType() {
        return this.employeeType;
    }
    
    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="unreg_id", nullable=false, insertable=false, updatable=false)
    public UnregSalary getUnregSalary() {
        return this.unregSalary;
    }
    
    public void setUnregSalary(UnregSalary unregSalary) {
        this.unregSalary = unregSalary;
    }

    
    @Column(name="descriptions", length=65535, columnDefinition="Text")
    public String getDescriptions() {
        return this.descriptions;
    }
    
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
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

}


