/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni Husni FR
 */
public class JabatanModel implements Serializable {

    private Long id;
    private Long jabatanAtasanId;
    private String kodeJabatan;
    private String namaJabatan;
    private Long golonganJabatanId;
    private Long unitKerjaId;
    private Long departementId;
    private Long posBiayaId;
    private String tujuanJabatan;
//    private Integer levelJabatan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJabatanAtasanId() {
        return jabatanAtasanId;
    }

    public void setJabatanAtasanId(Long jabatanAtasanId) {
        this.jabatanAtasanId = jabatanAtasanId;
    }

    public String getKodeJabatan() {
        return kodeJabatan;
    }

    public void setKodeJabatan(String kodeJabatan) {
        this.kodeJabatan = kodeJabatan;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public Long getGolonganJabatanId() {
        return golonganJabatanId;
    }

    public void setGolonganJabatanId(Long golonganJabatanId) {
        this.golonganJabatanId = golonganJabatanId;
    }

    public Long getUnitKerjaId() {
        return unitKerjaId;
    }

    public void setUnitKerjaId(Long unitKerjaId) {
        this.unitKerjaId = unitKerjaId;
    }

    public Long getDepartementId() {
        return departementId;
    }

    public void setDepartementId(Long departementId) {
        this.departementId = departementId;
    }

    public Long getPosBiayaId() {
        return posBiayaId;
    }

    public void setPosBiayaId(Long posBiayaId) {
        this.posBiayaId = posBiayaId;
    }

    public String getTujuanJabatan() {
        return tujuanJabatan;
    }

    public void setTujuanJabatan(String tujuanJabatan) {
        this.tujuanJabatan = tujuanJabatan;
    }

//    public Integer getLevelJabatan() {
//        return levelJabatan;
//    }
//
//    public void setLevelJabatan(Integer levelJabatan) {
//        this.levelJabatan = levelJabatan;
//    }
    
    
   
   

   
}
