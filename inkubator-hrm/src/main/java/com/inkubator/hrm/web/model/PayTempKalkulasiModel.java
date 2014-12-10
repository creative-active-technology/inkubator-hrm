/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PaySalaryComponent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Deni
 */
public class PayTempKalkulasiModel implements Serializable {
    private long id;
    private Integer version;
    private EmpData empData;
    private PaySalaryComponent paySalaryComponent;
    private BigDecimal nominal;
    private Date createdOn;
    private String createdBy;
    private String updatedBy;
    private Date updatedOn;
    private String code;
    private String name;
    private Long jumlahKaryawan;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    public PaySalaryComponent getPaySalaryComponent() {
        return paySalaryComponent;
    }

    public void setPaySalaryComponent(PaySalaryComponent paySalaryComponent) {
        this.paySalaryComponent = paySalaryComponent;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
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

    public Long getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(Long jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }
    
    
}
