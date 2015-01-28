/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author EKA
 */
@FacesConverter(value = "companyConverter")
public class CompanyConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String message = StringUtils.EMPTY;
        String data = (String) value;
        if(Objects.equals(data, HRMConstant.ORGANIZATION_LEVEL_HOLDING)){
            message = resourceBundle.getString("organization.holding");
        } else if(Objects.equals(data, HRMConstant.ORGANIZATION_LEVEL_COMPANY)){
            message = resourceBundle.getString("organization.company");
        }
        
        return message;
    }
    
}
