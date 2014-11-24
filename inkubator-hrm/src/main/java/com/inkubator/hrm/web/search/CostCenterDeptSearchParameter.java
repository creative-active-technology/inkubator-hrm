/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class CostCenterDeptSearchParameter extends SearchParameter{
    private String code;
    private String name;

    public String getCode() {
        code = StringUtils.equalsIgnoreCase(getKeyParam(), "code") ? getParameter() : null;
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
    	name = StringUtils.equalsIgnoreCase(getKeyParam(), "name") ? getParameter() : null;
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
