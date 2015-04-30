/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Deni
 */
public class RecruitMppPeriodModel implements Serializable {

    private Long id;
    private String code;
    private String name;
    private Date periodeStart;
    private Date periodeEnd;

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

    public Date getPeriodeStart() {
        return periodeStart;
    }

    public void setPeriodeStart(Date periodeStart) {
        this.periodeStart = periodeStart;
    }

    public Date getPeriodeEnd() {
        return periodeEnd;
    }

    public void setPeriodeEnd(Date periodeEnd) {
        this.periodeEnd = periodeEnd;
    }
    
    
}
