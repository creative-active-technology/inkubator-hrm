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
public class NeracaCutiSearchParameter extends SearchParameter{

    private String empData;
    private String leave;
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

    public String getLeave() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "leaveName")) {
            leave = getParameter();
        } else {
            leave = null;
        }
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
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
