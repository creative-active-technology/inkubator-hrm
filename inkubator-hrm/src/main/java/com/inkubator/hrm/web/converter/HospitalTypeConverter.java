/*
 * To change this template, choose Tools | Templates
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
 * @author rizkykojek
 */
@FacesConverter(value = "hospitalTypeConverter")
public class HospitalTypeConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        String data= (String) value;
        if(StringUtils.equals(data, HRMConstant.HOSPITAL_TYPE_GOVERNMENT)){
        	messages = resourceBundle.getString("hospital.government");
        } else if(StringUtils.equals(data, HRMConstant.HOSPITAL_TYPE_PRIVATE)){
        	messages = resourceBundle.getString("hospital.private");
        } else if(StringUtils.equals(data, HRMConstant.HOSPITAL_TYPE_INSTITUTION)){
        	messages = resourceBundle.getString("hospital.institution");
        }
        
        
        return messages;
    }
    
}
