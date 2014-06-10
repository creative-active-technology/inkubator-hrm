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
 * @author Deni Husni FR
 */
public class ScheduleShiftModel implements Serializable{
    private Long jamKerjaId;
    private Date tanggalKerja;
    private int no;

    public Long getJamKerjaId() {
        return jamKerjaId;
    }

    public void setJamKerjaId(Long jamKerjaId) {
        this.jamKerjaId = jamKerjaId;
    }

    public Date getTanggalKerja() {
        return tanggalKerja;
    }

    public void setTanggalKerja(Date tanggalKerja) {
        this.tanggalKerja = tanggalKerja;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
    
    
}
