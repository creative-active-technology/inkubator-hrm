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
    private Integer bulan;

    public String getTahun() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("tahun")) {
                tahun = getParameter();
            }else{
                tahun=null;
            }
        }
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public Integer getBulan() {
        if (getKeyParam() != null) {
            if (getKeyParam().equalsIgnoreCase("bulan")) {
                bulan = Integer.parseInt(getParameter());
            }else{
                bulan =null;
            }
        }

        return bulan;
    }

    public void setBulan(Integer bulan) {
        this.bulan = bulan;
    }

}
