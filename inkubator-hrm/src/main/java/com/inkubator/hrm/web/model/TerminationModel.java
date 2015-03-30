/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Deni
 */
public class TerminationModel implements Serializable{
    private Long id;
    private String empDataWithNik;
    private Long terminationTypeId;
    private String description;
    private String code;
    private Date effectiveDate;
    private EmpData empData;

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

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }
    
    
}
