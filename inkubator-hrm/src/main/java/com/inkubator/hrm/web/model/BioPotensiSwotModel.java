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
public class BioPotensiSwotModel implements Serializable {

    private Long id;
    private Long bioDataId;
    private Integer klasifikasi;
    private String labelPotensi;
    private Double potensiPoint;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBioDataId() {
        return bioDataId;
    }

    public void setBioDataId(Long bioDataId) {
        this.bioDataId = bioDataId;
    }

    public Integer getKlasifikasi() {
        return klasifikasi;
    }

    public void setKlasifikasi(Integer klasifikasi) {
        this.klasifikasi = klasifikasi;
    }

    public String getLabelPotensi() {
        return labelPotensi;
    }

    public void setLabelPotensi(String labelPotensi) {
        this.labelPotensi = labelPotensi;
    }

    public Double getPotensiPoint() {
        return potensiPoint;
    }

    public void setPotensiPoint(Double potensiPoint) {
        this.potensiPoint = potensiPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
