/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Deni
 */
public class OverTimeDistributionSearchParameter extends SearchParameter {
    private String empData;
    private String wtOverTime;

    public String getEmpData() {
        return empData;
    }

    public void setEmpData(String empData) {
        this.empData = empData;
    }

    public String getWtOverTime() {
        return wtOverTime;
    }

    public void setWtOverTime(String wtOverTime) {
        this.wtOverTime = wtOverTime;
    }
    
    
}
