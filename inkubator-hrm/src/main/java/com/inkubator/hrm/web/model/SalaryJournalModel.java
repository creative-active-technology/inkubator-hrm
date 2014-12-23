/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author deni
 */
public class SalaryJournalModel implements Serializable {

    private Long id;
    private String costCenterName;
    private String costCenterCode;
    private String jurnalCode;
    private String jurnalName;
    private Double debet;
    private BigDecimal debet2;
    private Double kredit;
    private BigDecimal kredit2;
    private Integer jumlahRows;
    private String costCenterCodeDebet;
    private String costCenterNameDebet;
    private String costCenterCodeKredit;
    private String costCenterNameKredit;
    private BigDecimal jumlahKredit;
    private BigDecimal jumlahDebet;
    private BigDecimal totalKredit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }

    public String getCostCenterCode() {
        return costCenterCode;
    }

    public void setCostCenterCode(String costCenterCode) {
        this.costCenterCode = costCenterCode;
    }

    public String getJurnalCode() {
        return jurnalCode;
    }

    public void setJurnalCode(String jurnalCode) {
        this.jurnalCode = jurnalCode;
    }

    public String getJurnalName() {
        return jurnalName;
    }

    public void setJurnalName(String jurnalName) {
        this.jurnalName = jurnalName;
    }

    public Double getDebet() {
        return debet = debet2.doubleValue();
    }

    public void setDebet(Double debet) {
        this.debet = debet;
    }

    public Double getKredit() {
        return kredit;
    }

    public void setKredit(Double kredit) {
        this.kredit = kredit;
    }

    public BigDecimal getDebet2() {
        return debet2;
    }

    public void setDebet2(BigDecimal debet2) {
        this.debet2 = debet2;
    }

    public BigDecimal getKredit2() {
        return kredit2;
    }

    public void setKredit2(BigDecimal kredit2) {
        this.kredit2 = kredit2;
    }

    public Integer getJumlahRows() {
        return jumlahRows;
    }

    public void setJumlahRows(Integer jumlahRows) {
        this.jumlahRows = jumlahRows;
    }

    public String getCostCenterCodeDebet() {
        return costCenterCodeDebet;
    }

    public void setCostCenterCodeDebet(String costCenterCodeDebet) {
        this.costCenterCodeDebet = costCenterCodeDebet;
    }

    public String getCostCenterNameDebet() {
        return costCenterNameDebet;
    }

    public void setCostCenterNameDebet(String costCenterNameDebet) {
        this.costCenterNameDebet = costCenterNameDebet;
    }

    public String getCostCenterCodeKredit() {
        return costCenterCodeKredit;
    }

    public void setCostCenterCodeKredit(String costCenterCodeKredit) {
        this.costCenterCodeKredit = costCenterCodeKredit;
    }

    public String getCostCenterNameKredit() {
        return costCenterNameKredit;
    }

    public void setCostCenterNameKredit(String costCenterNameKredit) {
        this.costCenterNameKredit = costCenterNameKredit;
    }

    public BigDecimal getJumlahKredit() {
        return jumlahKredit;
    }

    public void setJumlahKredit(BigDecimal jumlahKredit) {
        this.jumlahKredit = jumlahKredit;
    }

    public BigDecimal getJumlahDebet() {
        return jumlahDebet;
    }

    public void setJumlahDebet(BigDecimal jumlahDebet) {
        this.jumlahDebet = jumlahDebet;
    }

    public BigDecimal getTotalKredit() {
        return totalKredit;
    }

    public void setTotalKredit(BigDecimal totalKredit) {
        this.totalKredit = totalKredit;
    }

    

    
}
