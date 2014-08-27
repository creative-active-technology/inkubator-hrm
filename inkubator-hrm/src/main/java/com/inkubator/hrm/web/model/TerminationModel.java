/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.util.Date;

/**
 *
 * @author Deni
 */
public class TerminationModel {
    private Long id;
    private String empDataWithNik;
    private Long terminationTypeId;
    private String description;
    private String code;
    private Date effectiveDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpDataWithNik() {
        return empDataWithNik;
    }

    public void setEmpDataWithNik(String empDataWithNik) {
        this.empDataWithNik = empDataWithNik;
    }

    public Long getTerminationTypeId() {
        return terminationTypeId;
    }

    public void setTerminationTypeId(Long terminationTypeId) {
        this.terminationTypeId = terminationTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
}
