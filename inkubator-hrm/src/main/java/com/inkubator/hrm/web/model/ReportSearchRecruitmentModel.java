/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arsyad_
 */
public class ReportSearchRecruitmentModel implements Serializable{
    private List<Long> listEducationLevel = new ArrayList<Long>();
    private List<Long> listKlasifikasiKerja = new ArrayList<Long>();

    public List<Long> getListEducationLevel() {
        return listEducationLevel;
    }

    public void setListEducationLevel(List<Long> listEducationLevel) {
        this.listEducationLevel = listEducationLevel;
    }

    public List<Long> getListKlasifikasiKerja() {
        return listKlasifikasiKerja;
    }

    public void setListKlasifikasiKerja(List<Long> listKlasifikasiKerja) {
        this.listKlasifikasiKerja = listKlasifikasiKerja;
    }


}
