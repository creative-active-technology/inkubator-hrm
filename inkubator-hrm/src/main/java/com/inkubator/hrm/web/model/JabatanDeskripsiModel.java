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

    private Long id;
    private Integer categoryTugas;
    private Integer typeWaktu;
    private String deskripsi;
    private Long jabatanId;
    private Boolean isUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(Long jabatanId) {
        this.jabatanId = jabatanId;
    }

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
    
    
    
}
