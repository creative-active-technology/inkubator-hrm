/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Deni Husni FR
 */
public class WtPeriodeSearchParameter extends SearchParameter {

    private String tahun;
    private String bulan;

    public String getTahun() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("tahun")) {
                tahun = getParameter();
            }
        }
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBulan() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("bulan")) {
                bulan = getParameter();
            }
        }

        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

}
