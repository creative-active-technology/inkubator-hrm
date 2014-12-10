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
public class PayComponentDataExceptionSearchParameter extends SearchParameter{
    private String empData;
    private String paySalaryComponent;
    private String nominal;

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

    public String getPaySalaryComponent() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "paySalaryComponent")) {
            paySalaryComponent = getParameter();
        } else {
            paySalaryComponent = null;
        }
        return paySalaryComponent;
    }

    public void setPaySalaryComponent(String paySalaryComponent) {
        this.paySalaryComponent = paySalaryComponent;
    }

    public String getNominal() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "nominal")) {
            nominal = getParameter();
        } else {
            nominal = null;
        }
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }
    
    
}
