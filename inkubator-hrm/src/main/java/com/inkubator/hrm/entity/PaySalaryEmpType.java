package com.inkubator.hrm.entity;
// Generated Nov 24, 2014 11:16:03 AM by Hibernate Tools 4.3.1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PaySalaryEmpType generated by hbm2java
 */
@Entity
@Table(name="pay_salary_emp_type"
    ,catalog="hrm"
)
public class PaySalaryEmpType  implements java.io.Serializable {


     private PaySalaryEmpTypeId id;
     private EmployeeType employeeType;
     private PaySalaryComponent paySalaryComponent;
     private String description;

    public PaySalaryEmpType() {
    }

	
    public PaySalaryEmpType(PaySalaryEmpTypeId id, EmployeeType employeeType, PaySalaryComponent paySalaryComponent) {
        this.id = id;
        this.employeeType = employeeType;
        this.paySalaryComponent = paySalaryComponent;
    }
    public PaySalaryEmpType(PaySalaryEmpTypeId id, EmployeeType employeeType, PaySalaryComponent paySalaryComponent, String description) {
       this.id = id;
       this.employeeType = employeeType;
       this.paySalaryComponent = paySalaryComponent;
       this.description = description;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="paySalaryComId", column=@Column(name="pay_salary_com_id", nullable=false) ), 
        @AttributeOverride(name="empTypeId", column=@Column(name="emp_type_id", nullable=false) ) } )
    public PaySalaryEmpTypeId getId() {
        return this.id;
    }
    
    public void setId(PaySalaryEmpTypeId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="emp_type_id", nullable=false, insertable=false, updatable=false)
    public EmployeeType getEmployeeType() {
        return this.employeeType;
    }
    
    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pay_salary_com_id", nullable=false, insertable=false, updatable=false)
    public PaySalaryComponent getPaySalaryComponent() {
        return this.paySalaryComponent;
    }
    
    public void setPaySalaryComponent(PaySalaryComponent paySalaryComponent) {
        this.paySalaryComponent = paySalaryComponent;
    }

    
    @Column(name="description", length=65535)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


