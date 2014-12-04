/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PayComponentDataException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Deni
 */
public class PayComponentDataExceptionModel implements Serializable {
    private Long id;
    private Date startDate;
    private Date endDate;
    private EmpData empData;
    private BigDecimal nominal;
    private Boolean resetAfterMonth;
    private String ComponentName;
    private Integer jumlahKaryawan;
    private BigDecimal jmlNominalComponentException;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    public Boolean getResetAfterMonth() {
        return resetAfterMonth;
    }

    public void setResetAfterMonth(Boolean resetAfterMonth) {
        this.resetAfterMonth = resetAfterMonth;
    }

    public String getComponentName() {
        return ComponentName;
    }

    public void setComponentName(String ComponentName) {
        this.ComponentName = ComponentName;
    }

    public Integer getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(Integer jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }

    public BigDecimal getJmlNominalComponentException() {
        return jmlNominalComponentException;
    }

    public void setJmlNominalComponentException(BigDecimal jmlNominalComponentException) {
        this.jmlNominalComponentException = jmlNominalComponentException;
    }
    
    
}
