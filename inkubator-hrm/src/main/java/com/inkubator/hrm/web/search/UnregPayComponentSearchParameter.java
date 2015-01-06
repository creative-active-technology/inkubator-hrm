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
 * @author deni
 */
public class UnregPayComponentSearchParameter extends SearchParameter{
    private String componentModel;
    private String componentName;

    public String getComponentModel() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "componentModel")){
            componentModel = getParameter();
        } else {
            componentModel = null;
        }
        return componentModel;
    }

    public void setComponentModel(String componentModel) {
        this.componentModel = componentModel;
    }

    public String getComponentName() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "componentName")){
            componentName = getParameter();
        } else {
            componentName = null;
        }
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    
}
