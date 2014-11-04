/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.hrm.entity.ResourceType;
import com.inkubator.webcore.util.SearchParameter;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
public class ResourceNameSearchParameter extends SearchParameter{
    private String code;
    private String name;
    private String resourceType;
    private String isActive;

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

    public String getResourceType() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "resourceType")){
            resourceType = getParameter();
        } else {
            resourceType = null;
        }
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getIsActive() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "isActive")){
            isActive = getParameter();
        } else {
            isActive = null;
        }
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
