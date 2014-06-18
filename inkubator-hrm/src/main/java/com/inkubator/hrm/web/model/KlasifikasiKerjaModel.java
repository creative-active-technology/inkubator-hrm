package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class KlasifikasiKerjaModel implements Serializable {

    private Long id;
    private String klasifikasiKerjaCode;
    private String klasifikasiKerjaName;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKlasifikasiKerjaCode() {
        return klasifikasiKerjaCode;
    }

    public void setKlasifikasiKerjaCode(String klasifikasiKerjaCode) {
        this.klasifikasiKerjaCode = klasifikasiKerjaCode;
    }

    public String getKlasifikasiKerjaName() {
        return klasifikasiKerjaName;
    }

    public void setKlasifikasiKerjaName(String klasifikasiKerjaName) {
        this.klasifikasiKerjaName = klasifikasiKerjaName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
