package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class SubsidiModel implements Serializable {

    private String subsidiName;
    private String nominal;

    public String getSubsidiName() {
        return subsidiName;
    }

    public void setSubsidiName(String subsidiName) {
        this.subsidiName = subsidiName;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

}
