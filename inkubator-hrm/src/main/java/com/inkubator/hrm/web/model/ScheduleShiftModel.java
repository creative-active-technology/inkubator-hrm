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
public class ScheduleShiftModel implements Serializable {

    private Long jamKerjaId;
    private Date tanggalKerja;
    private int no;
    private Long jamKerjaId2;
    private Date tanggalKerja2;
    private Boolean isRenderCombo;
    private Boolean isRenderCombo2;

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

    public Long getJamKerjaId2() {
        return jamKerjaId2;
    }

    public void setJamKerjaId2(Long jamKerjaId2) {
        this.jamKerjaId2 = jamKerjaId2;
    }

    public Date getTanggalKerja2() {
        return tanggalKerja2;
    }

    public void setTanggalKerja2(Date tanggalKerja2) {
        this.tanggalKerja2 = tanggalKerja2;
    }

    public Boolean getIsRenderCombo() {
        return isRenderCombo;
    }

    public void setIsRenderCombo(Boolean isRenderCombo) {
        this.isRenderCombo = isRenderCombo;
    }

    public Boolean getIsRenderCombo2() {
        return isRenderCombo2;
    }

    public void setIsRenderCombo2(Boolean isRenderCombo2) {
        this.isRenderCombo2 = isRenderCombo2;
    }

    @Override
    public String toString() {
        return "ScheduleShiftModel{" + "jamKerjaId=" + jamKerjaId + ", tanggalKerja=" + tanggalKerja + ", no=" + no + ", jamKerjaId2=" + jamKerjaId2 + ", tanggalKerja2=" + tanggalKerja2 + ", isRenderCombo=" + isRenderCombo + ", isRenderCombo2=" + isRenderCombo2 + '}';
    }

    
    
}
