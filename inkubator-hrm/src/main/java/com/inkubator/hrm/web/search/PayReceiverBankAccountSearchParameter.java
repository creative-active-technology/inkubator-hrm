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
 * @author denifahri
 */
public class PayReceiverBankAccountSearchParameter extends SearchParameter {

    private String nik;
    private String empName;
    private String golJab;

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

    public String getEmpName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "empName")) {
            empName = getParameter();
        } else {
            empName = null;
        }
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGolJab() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "golJab")) {
            golJab = getParameter();
        } else {
            golJab = null;
        }
        return golJab;
    }

    public void setGolJab(String golJab) {
        this.golJab = golJab;
    }

}
