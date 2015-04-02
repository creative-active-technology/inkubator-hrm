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
public class MedicalCareSearchParameter extends SearchParameter {
    private String employeeName;
    private String jabatan;

    public String getEmployeeName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "employeeName")) {
            employeeName = getParameter();
        } else {
            employeeName = null;
        }
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getJabatan() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "jabatan")) {
            jabatan = getParameter();
        } else {
            jabatan = null;
        }
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    
    
}
