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
public class PayTempKalkulasiSearchParameter extends SearchParameter {
    private String empData;
    private String paySalaryComponent;

    public String getEmpData() {
        return empData;
    }

    public void setEmpData(String empData) {
        this.empData = empData;
    }

    public String getPaySalaryComponent() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "paySalaryComponent")){
            paySalaryComponent = getParameter();
        } else {
            paySalaryComponent = null;
        }
        return paySalaryComponent;
    }

    public void setPaySalaryComponent(String paySalaryComponent) {
        this.paySalaryComponent = paySalaryComponent;
    }
    
    
}
