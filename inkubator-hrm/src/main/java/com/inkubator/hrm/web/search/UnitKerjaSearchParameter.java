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
public class UnitKerjaSearchParameter extends SearchParameter{
    private String code;
    private String name;
    private String location;

    public String getCode() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "code")){
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
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "name")){
            name = getParameter();
        } else {
            name = null;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "location")){
            location = getParameter();
        } else {
            location = null;
        }
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
}
