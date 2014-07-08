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
public class JabatanDeskripsiModel implements Serializable {

    private long id;
    private Integer categoryTugas;
    private Integer typeWaktu;
    private String deskripsi;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getCategoryTugas() {
        return categoryTugas;
    }

    public void setCategoryTugas(Integer categoryTugas) {
        this.categoryTugas = categoryTugas;
    }

    public Integer getTypeWaktu() {
        return typeWaktu;
    }

    public void setTypeWaktu(Integer typeWaktu) {
        this.typeWaktu = typeWaktu;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    
    
}
