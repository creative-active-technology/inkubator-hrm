/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author EKA
 */
public class KlasifikasiKerjaLevelModel implements Serializable{
    private Long id;
    private String code;
    private String name;
    private String description;
    private Long klasifikasiKerjaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getKlasifikasiKerjaId() {
        return klasifikasiKerjaId;
    }

    public void setKlasifikasiKerjaId(Long klasifikasiKerjaId) {
        this.klasifikasiKerjaId = klasifikasiKerjaId;
    }
}
