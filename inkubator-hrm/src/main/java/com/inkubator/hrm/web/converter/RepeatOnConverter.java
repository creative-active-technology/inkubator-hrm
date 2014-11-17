/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@FacesConverter(value = "repeatOnConverter")
public class RepeatOnConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        String data= (String) value;
        if(StringUtils.equals(data, HRMConstant.COMP_POLICY_REPEAT_ON_WEEKLY)){
        	messages = resourceBundle.getString("companypolicy.weekly");
        } else if(StringUtils.equals(data, HRMConstant.COMP_POLICY_REPEAT_ON_MONTHLY)){
        	messages = resourceBundle.getString("companypolicy.monthly");
        } else if(StringUtils.equals(data, HRMConstant.COMP_POLICY_REPEAT_ON_QUARTERLY)){
        	messages = resourceBundle.getString("companypolicy.quarterly");
        }
        
        
        return messages;
    }
    
}
