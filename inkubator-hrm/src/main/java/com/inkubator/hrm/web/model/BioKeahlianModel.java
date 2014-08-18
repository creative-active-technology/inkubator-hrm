/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni
 */
public class BioKeahlianModel implements Serializable {

    private Long id;
    private Long biodataId;
    private Integer tingkatKeahlian;
    private String namaKeahlian;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBiodataId() {
        return biodataId;
    }

    public void setBiodataId(Long biodataId) {
        this.biodataId = biodataId;
    }

    public Integer getTingkatKeahlian() {
        return tingkatKeahlian;
    }

    public void setTingkatKeahlian(Integer tingkatKeahlian) {
        this.tingkatKeahlian = tingkatKeahlian;
    }

    public String getNamaKeahlian() {
        return namaKeahlian;
    }

    public void setNamaKeahlian(String namaKeahlian) {
        this.namaKeahlian = namaKeahlian;
    }
}
