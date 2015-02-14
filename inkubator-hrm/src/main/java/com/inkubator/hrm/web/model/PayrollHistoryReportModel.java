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
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class PayrollHistoryReportModel implements Serializable {
    private Long id;
    private Long periodeId;
    private Date periode;
    private String tahunPeriode;
    private Integer bulanPeriode;
    private Date tglAwalPeriode;
    private Date tglAkhirPeriode;
    private BigDecimal nominal;
    private Date createdOn;
    private String createdBy;
    private String updatedBy;
    private Date updatedOn;
    private String code;
    private String name;
    private Long jumlahKaryawan;
    private Long jumlahKaryawanKeseluruhan;
    private Date startDate;
    private Date endDate;

    public Long getPeriodeId() {
        return periodeId;
    }

    public void setPeriodeId(Long periodeId) {
        this.periodeId = periodeId;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTahunPeriode() {
        return tahunPeriode;
    }

    public void setTahunPeriode(String tahunPeriode) {
        this.tahunPeriode = tahunPeriode;
    }
    
    public Integer getBulanPeriode() {
        return bulanPeriode;
    }

    public void setBulanPeriode(Integer bulanPeriode) {
        this.bulanPeriode = bulanPeriode;
    }

    public Date getTglAwalPeriode() {
        return tglAwalPeriode;
    }

    public void setTglAwalPeriode(Date tglAwalPeriode) {
        this.tglAwalPeriode = tglAwalPeriode;
    }

    public Date getTglAkhirPeriode() {
        return tglAkhirPeriode;
    }

    public void setTglAkhirPeriode(Date tglAkhirPeriode) {
        this.tglAkhirPeriode = tglAkhirPeriode;
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

    public Long getJumlahKaryawanKeseluruhan() {
        return jumlahKaryawanKeseluruhan;
    }

    public void setJumlahKaryawanKeseluruhan(Long jumlahKaryawanKeseluruhan) {
        this.jumlahKaryawanKeseluruhan = jumlahKaryawanKeseluruhan;
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

    public Date getPeriode() {
        return periode;
    }

    public void setPeriode(Date periode) {
        this.periode = periode;
    }

   
}
