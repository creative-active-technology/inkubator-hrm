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
public class PersonalDisciplineSearchParameter extends SearchParameter{
    private String empData;
    private String admonitionType;

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

    public String getAdmonitionType() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "admonitionType")) {
            admonitionType = getParameter();
        } else {
            admonitionType = null;
        }
        return admonitionType;
    }

    public void setAdmonitionType(String admonitionType) {
        this.admonitionType = admonitionType;
    }
    
    
}
