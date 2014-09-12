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
@Table(name = "reimbursment_schema", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames="code") )
public class ReimbursmentSchema implements java.io.Serializable{
    private Long id;
    private Integer version;
    private String code;
    private String name;
    private Integer effectiveDate;
    private CostCenter costCenter;
    private Integer measurement;
    private Integer basicValue;
    private BigDecimal nominalUnit;
    private Integer ratioSalary;
    private Integer timeRange;
    private Boolean payrollComponent;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private List<EmployeeType> employeeTypes = new ArrayList<>(0);
    private Set<ReimbursmentSchemaEmployeeType> reimbursmentSchemaEmployeeTypes = new HashSet<ReimbursmentSchemaEmployeeType>(0);
    private Set<Reimbursment> reimbursments = new HashSet<Reimbursment>(0);

    public ReimbursmentSchema() {
    }

    public ReimbursmentSchema(Long id) {
        this.id = id;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
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

    @Column(name="effective_date")
    public Integer getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Integer effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_id", nullable = false)
    public CostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
    }

    @Column(name="measurement", length = 1)
    public Integer getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Integer measurement) {
        this.measurement = measurement;
    }

    @Column(name="basic_value", length = 1)
    public Integer getBasicValue() {
        return basicValue;
    }

    public void setBasicValue(Integer basicValue) {
        this.basicValue = basicValue;
    }

    @Column(name = "nominal_unit", precision = 10, scale = 0)
    public BigDecimal getNominalUnit() {
        return nominalUnit;
    }

    public void setNominalUnit(BigDecimal nominalUnit) {
        this.nominalUnit = nominalUnit;
    }

    @Column(name="ratio_salary")
    public Integer getRatioSalary() {
        return ratioSalary;
    }

    public void setRatioSalary(Integer ratioSalary) {
        this.ratioSalary = ratioSalary;
    }

    @Column(name="time_range", length = 1)
    public Integer getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(Integer timeRange) {
        this.timeRange = timeRange;
    }

    @Column(name="payroll_component", length = 1)
    public Boolean getPayrollComponent() {
        return payrollComponent;
    }

    public void setPayrollComponent(Boolean payrollComponent) {
        this.payrollComponent = payrollComponent;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reimbursmentSchema", orphanRemoval = true)
    public Set<ReimbursmentSchemaEmployeeType> getReimbursmentSchemaEmployeeTypes() {
        return reimbursmentSchemaEmployeeTypes;
    }

    public void setReimbursmentSchemaEmployeeTypes(Set<ReimbursmentSchemaEmployeeType> reimbursmentSchemaEmployeeTypes) {
        this.reimbursmentSchemaEmployeeTypes = reimbursmentSchemaEmployeeTypes;
    }

    @Transient
    public List<EmployeeType> getEmployeeTypes() {
        return employeeTypes;
    }

    public void setEmployeeTypes(List<EmployeeType> employeeTypes) {
        this.employeeTypes = employeeTypes;
    }
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reimbursmentSchema", orphanRemoval = true)
    public Set<Reimbursment> getReimbursments() {
        return reimbursments;
    }

    public void setReimbursments(Set<Reimbursment> reimbursments) {
        this.reimbursments = reimbursments;
    }
    
    
    @Transient
    public String getMeasurementValueString(){
        String data = "";
        if(measurement == 0){
            data = "Unit";
        }else{
            data = "Nominal";
        }
        return data;
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
    public String getPayrollComponentString(){
        String data = "";
        if(payrollComponent == false){
            data = "No";
        }else{
            data = "Yes";
        }
        return data;
    }
    
    @Transient
    public String getTimeRangeString(){
        String data = "";
        if(timeRange == 0){
            data = "Monthly";
        }else if(timeRange == 1){
            data = "Yearly";
        }else{
            data = "Per Employee";
        }
        return data;
    }
}
