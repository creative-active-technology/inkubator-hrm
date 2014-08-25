/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni Husni FR
 */
public class EmpRotasiSearchParameter extends SearchParameter {

    private String NIK;
    private String name;
    private String jabatanLama;
    private String jabatanBaru;

    public String getNIK() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "NIK")) {
            NIK = getParameter();
        } else {
            NIK = null;
        }
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "name")) {
            name = getParameter();
        } else {
            name = null;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJabatanLama() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "jabatanLama")) {
            jabatanLama = getParameter();
        } else {
            jabatanLama = null;
        }
        return jabatanLama;
    }

    public void setJabatanLama(String jabatanLama) {
        this.jabatanLama = jabatanLama;
    }

    public String getJabatanBaru() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "jabatanBaru")) {
            jabatanBaru = getParameter();
        } else {
            jabatanBaru = null;
        }
        return jabatanBaru;
    }

    public void setJabatanBaru(String jabatanBaru) {
        this.jabatanBaru = jabatanBaru;
    }

}
