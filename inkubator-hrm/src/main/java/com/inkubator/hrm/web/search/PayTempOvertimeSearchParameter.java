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
 * @author deni
 */
public class PayTempOvertimeSearchParameter extends SearchParameter{
    private String employeeName;
    private String nim;

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

    public String getNim() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "nim")) {
            nim = getParameter();
        } else {
            nim = null;
        }
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
    
    
}
