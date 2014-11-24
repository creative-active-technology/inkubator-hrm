/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni
 */
public class PaySalaryComponentModel implements Serializable {

    private Long id;
    private Integer version;
    private Long modelComponentId;
    private Long paySalaryJurnalId;
    private Long taxComponentId;
    private String code;
    private String name;
    private Boolean renumeration;
    private String formula;
    private Integer componentCategory;
    private Boolean resetData;
    private Boolean taxableCheck;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getModelComponentId() {
        return modelComponentId;
    }

    public void setModelComponentId(Long modelComponentId) {
        this.modelComponentId = modelComponentId;
    }

    public Long getPaySalaryJurnalId() {
        return paySalaryJurnalId;
    }

    public void setPaySalaryJurnalId(Long paySalaryJurnalId) {
        this.paySalaryJurnalId = paySalaryJurnalId;
    }

    public Long getTaxComponentId() {
        return taxComponentId;
    }

    public void setTaxComponentId(Long taxComponentId) {
        this.taxComponentId = taxComponentId;
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

    public Boolean getRenumeration() {
        return renumeration;
    }

    public void setRenumeration(Boolean renumeration) {
        this.renumeration = renumeration;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Integer getComponentCategory() {
        return componentCategory;
    }

    public void setComponentCategory(Integer componentCategory) {
        this.componentCategory = componentCategory;
    }

    public Boolean getResetData() {
        return resetData;
    }

    public void setResetData(Boolean resetData) {
        this.resetData = resetData;
    }

    public Boolean getTaxableCheck() {
        return taxableCheck;
    }

    public void setTaxableCheck(Boolean taxableCheck) {
        this.taxableCheck = taxableCheck;
    }
    
    
}
