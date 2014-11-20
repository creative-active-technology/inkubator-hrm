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
 * @author Deni
 */
public class WtFingerExceptionSearchParameter extends SearchParameter{
    private String empData;
    private String nik;
    private String namaJabatan;
    private String codeJabatan;

    public String getEmpData() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "empData")) {
            empData = getParameter();
        } else {
            empData = null;
        }
        return empData;
    }

    public void setEmpData(String empData) {
        this.empData = empData;
    }

    public String getNik() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "nik")) {
            nik = getParameter();
        } else {
            nik = null;
        }
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaJabatan() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "namaJabatan")) {
            namaJabatan = getParameter();
        } else {
            namaJabatan = null;
        }
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public String getCodeJabatan() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "codeJabatan")) {
            codeJabatan = getParameter();
        } else {
            codeJabatan = null;
        }
        return codeJabatan;
    }

    public void setCodeJabatan(String codeJabatan) {
        this.codeJabatan = codeJabatan;
    }
    
    
}
