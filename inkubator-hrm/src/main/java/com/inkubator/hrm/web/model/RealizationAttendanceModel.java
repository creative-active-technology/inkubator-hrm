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
 * @author deni.fahri
 */
public class RealizationAttendanceModel implements Serializable{
    
    private Date stardDate;
    private Date endDate;
    private Integer totalCuti;
    private Integer totalIzin;
    private Integer totalInDuty;

    public Date getStardDate() {
        return stardDate;
    }

    public void setStardDate(Date stardDate) {
        this.stardDate = stardDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalCuti() {
        return totalCuti;
    }

    public void setTotalCuti(Integer totalCuti) {
        this.totalCuti = totalCuti;
    }

    public Integer getTotalIzin() {
        return totalIzin;
    }

    public void setTotalIzin(Integer totalIzin) {
        this.totalIzin = totalIzin;
    }

    public Integer getTotalInDuty() {
        return totalInDuty;
    }

    public void setTotalInDuty(Integer totalInDuty) {
        this.totalInDuty = totalInDuty;
    }
    
    
    
    
    
}
