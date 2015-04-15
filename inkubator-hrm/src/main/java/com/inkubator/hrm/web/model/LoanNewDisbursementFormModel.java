/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanNewDisbursementFormModel implements Serializable {

    private Long id;
    private String disbursementCode;
    private Long coaId;
    private Date disbursementDate;    
    private String description;
    private Map<String,Long> mapCoa;

    public LoanNewDisbursementFormModel() {
        this.mapCoa = new HashMap<>();
    }

    public Map<String, Long> getMapCoa() {
        return mapCoa;
    }

    public void setMapCoa(Map<String, Long> mapCoa) {
        this.mapCoa = mapCoa;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisbursementCode() {
        return disbursementCode;
    }

    public void setDisbursementCode(String disbursementCode) {
        this.disbursementCode = disbursementCode;
    }

    public Long getCoaId() {
        return coaId;
    }

    public void setCoaId(Long coaId) {
        this.coaId = coaId;
    }

    public Date getDisbursementDate() {
        return disbursementDate;
    }

    public void setDisbursementDate(Date disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
