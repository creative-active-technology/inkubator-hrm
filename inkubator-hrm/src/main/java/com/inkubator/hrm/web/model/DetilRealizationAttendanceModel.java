/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.TempProcessReadFinger;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author deni.fahri
 */
public class DetilRealizationAttendanceModel implements Serializable {

    private Date stardDate;
    private Date endDate;
    private long totalCuti;
    private long totalIzin;
    private long totalOnDuty;
    private long totalSick;
    private List<TempProcessReadFinger> dataToShow;

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

    public long getTotalCuti() {
        return totalCuti;
    }

    public void setTotalCuti(long totalCuti) {
        this.totalCuti = totalCuti;
    }

    public long getTotalIzin() {
        return totalIzin;
    }

    public void setTotalIzin(long totalIzin) {
        this.totalIzin = totalIzin;
    }

    public long getTotalOnDuty() {
        return totalOnDuty;
    }

    public void setTotalOnDuty(long totalOnDuty) {
        this.totalOnDuty = totalOnDuty;
    }

    public long getTotalSick() {
        return totalSick;
    }

    public void setTotalSick(long totalSick) {
        this.totalSick = totalSick;
    }

    public List<TempProcessReadFinger> getDataToShow() {
        return dataToShow;
    }

    public void setDataToShow(List<TempProcessReadFinger> dataToShow) {
        this.dataToShow = dataToShow;
    }
    
    

}
