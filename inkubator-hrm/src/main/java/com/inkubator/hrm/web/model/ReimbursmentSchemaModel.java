/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Deni
 */
public class ReimbursmentSchemaModel implements Serializable{
    private Long id;
    private String code;
    private String name;
    private Integer effectiveDate;
    private Long costCenter;
    private Integer measurement;
    private Integer basicValue;
    private BigDecimal nominalUnit;
    private Integer ratioSalary;
    private Integer timeRange;
    private Integer payrollComponent;
    private Boolean payrolComponent;
    private Boolean isAttachDocument;
    private Integer Quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Integer effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Long getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(Long costCenter) {
        this.costCenter = costCenter;
    }

    public Integer getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Integer measurement) {
        this.measurement = measurement;
    }

    public Integer getBasicValue() {
        return basicValue;
    }

    public void setBasicValue(Integer basicValue) {
        this.basicValue = basicValue;
    }

    public BigDecimal getNominalUnit() {
        return nominalUnit;
    }

    public void setNominalUnit(BigDecimal nominalUnit) {
        this.nominalUnit = nominalUnit;
    }

    public Integer getRatioSalary() {
        return ratioSalary;
    }

    public void setRatioSalary(Integer ratioSalary) {
        this.ratioSalary = ratioSalary;
    }

    public Integer getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(Integer timeRange) {
        this.timeRange = timeRange;
    }

    public Integer getPayrollComponent() {
        return payrollComponent;
    }

    public void setPayrollComponent(Integer payrollComponent) {
        this.payrollComponent = payrollComponent;
    }

    public Boolean getPayrolComponent() {
        return payrolComponent;
    }

    public void setPayrolComponent(Boolean payrolComponent) {
        this.payrolComponent = payrolComponent;
    }

    public Boolean getIsAttachDocument() {
        return isAttachDocument;
    }

    public void setIsAttachDocument(Boolean isAttachDocument) {
        this.isAttachDocument = isAttachDocument;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }
    
    
}
