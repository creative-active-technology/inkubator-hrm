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
public class DetilAttendateRelaization implements Serializable {

    private Date asentDate;
    private String realisasiStatus;
    private String realisasiAttendace;
    private Boolean isAttendaceKnowing;

    public Date getAsentDate() {
        return asentDate;
    }

    public void setAsentDate(Date asentDate) {
        this.asentDate = asentDate;
    }

    public String getRealisasiStatus() {
        return realisasiStatus;
    }

    public void setRealisasiStatus(String realisasiStatus) {
        this.realisasiStatus = realisasiStatus;
    }

    public String getRealisasiAttendace() {
        return realisasiAttendace;
    }

    public void setRealisasiAttendace(String realisasiAttendace) {
        this.realisasiAttendace = realisasiAttendace;
    }

    public Boolean getIsAttendaceKnowing() {
        return isAttendaceKnowing;
    }

    public void setIsAttendaceKnowing(Boolean isAttendaceKnowing) {
        this.isAttendaceKnowing = isAttendaceKnowing;
    }

    
}
