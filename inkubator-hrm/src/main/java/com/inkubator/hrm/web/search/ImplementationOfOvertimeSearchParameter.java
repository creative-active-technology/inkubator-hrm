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
public class ImplementationOfOvertimeSearchParameter extends SearchParameter {
    private String employeeName;
    private String overTimeName;

    public String getEmployeeName() {
         if(StringUtils.equalsIgnoreCase(getKeyParam(), "employeeName")){
            employeeName = getParameter();
        } else {
            employeeName = null;
        }
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOverTimeName() {
         if(StringUtils.equalsIgnoreCase(getKeyParam(), "overTimeName")){
            overTimeName = getParameter();
        } else {
            overTimeName = null;
        }
        return overTimeName;
    }

    public void setOverTimeName(String overTimeName) {
        this.overTimeName = overTimeName;
    }
}
