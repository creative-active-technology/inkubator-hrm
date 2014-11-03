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
public class ResourceTypeSearchParameter extends SearchParameter{
    private String code;
    private String resourceType;

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

    public String getResourceType() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "name")){
            resourceType = getParameter();
        } else {
            resourceType = null;
        }
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    
    
}
