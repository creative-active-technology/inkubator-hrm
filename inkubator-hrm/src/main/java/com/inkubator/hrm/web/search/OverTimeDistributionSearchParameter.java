/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
public class OverTimeDistributionSearchParameter extends SearchParameter {
    private String nik;
    private String empData;
    private String wtOverTime;

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

    public String getWtOverTime() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "overTime")) {
            wtOverTime = getParameter();
        } else {
            wtOverTime = null;
        }
        return wtOverTime;
    }

    public void setWtOverTime(String wtOverTime) {
        this.wtOverTime = wtOverTime;
    }
    
    
}
