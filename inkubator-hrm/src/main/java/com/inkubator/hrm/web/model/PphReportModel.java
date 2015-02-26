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
public class PphReportModel implements Serializable{
    private Long id;
    private String empName;
    private String empNik;
    private String empGolJabatan;
    private Double biayaJabatan;
    private Double pph;
    private Double ptkp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNik() {
        return empNik;
    }

    public void setEmpNik(String empNik) {
        this.empNik = empNik;
    }

    public String getEmpGolJabatan() {
        return empGolJabatan;
    }

    public void setEmpGolJabatan(String empGolJabatan) {
        this.empGolJabatan = empGolJabatan;
    }

    public Double getBiayaJabatan() {
        return biayaJabatan;
    }

    public void setBiayaJabatan(Double biayaJabatan) {
        this.biayaJabatan = biayaJabatan;
    }

    public Double getPph() {
        return pph;
    }

    public void setPph(Double pph) {
        this.pph = pph;
    }

    public Double getPtkp() {
        return ptkp;
    }

    public void setPtkp(Double ptkp) {
        this.ptkp = ptkp;
    }
    
    
}
