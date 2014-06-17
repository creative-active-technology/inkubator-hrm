/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author deniarianto
 */
public class DepartmentSearchParameter extends SearchParameter{
    private String code;
    private String name;

    public String getCode() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "departmentCode")){
            code = getParameter();
        } else {
            code = null;
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "departmentName")){
            name = getParameter();
        } else {
            name = null;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
