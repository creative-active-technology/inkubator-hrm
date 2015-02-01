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
public class SalaryJournalNativeModel implements Serializable{
    private String code_cost_center;
    private String nama_cost_center;
    private String code_jurnal;
    private String nama_jurnal;
    private BigDecimal debet;
    private BigDecimal kredit;

    public String getCode_cost_center() {
        return code_cost_center;
    }

    public void setCode_cost_center(String code_cost_center) {
        this.code_cost_center = code_cost_center;
    }

    public String getNama_cost_center() {
        return nama_cost_center;
    }

    public void setNama_cost_center(String nama_cost_center) {
        this.nama_cost_center = nama_cost_center;
    }

    public String getCode_jurnal() {
        return code_jurnal;
    }

    public void setCode_jurnal(String code_jurnal) {
        this.code_jurnal = code_jurnal;
    }

    public String getNama_jurnal() {
        return nama_jurnal;
    }

    public void setNama_jurnal(String nama_jurnal) {
        this.nama_jurnal = nama_jurnal;
    }

    public BigDecimal getDebet() {
        return debet;
    }

    public void setDebet(BigDecimal debet) {
        this.debet = debet;
    }

    public BigDecimal getKredit() {
        return kredit;
    }

    public void setKredit(BigDecimal kredit) {
        this.kredit = kredit;
    }
    
    
}
