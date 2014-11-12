/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Taufik
 */
public class PermitDistributionSearchParameter extends SearchParameter{

    private String empData;
    private String permitClassification;
    private String nik;

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

    public String getPermitClassification() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "permitName")) {
            permitClassification = getParameter();
        } else {
            permitClassification = null;
        }
        return permitClassification;
    }

    public void setPermitClassification(String permit) {
        this.permitClassification = permit;
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

    
    
    
}
