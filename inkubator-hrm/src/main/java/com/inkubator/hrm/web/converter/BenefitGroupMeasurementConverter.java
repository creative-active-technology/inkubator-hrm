/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author deni
 */
@FacesConverter(value = "benefitGroupMeasurementConverter")
public class BenefitGroupMeasurementConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
       
        Integer data = (Integer) value;
        if (data.equals(HRMConstant.BENEFIT_GROUP_MEASUREMENT_HOUR)) {
            return messages.getString("benefitGroupMeasurement.perHour");

        }

        if (data.equals(HRMConstant.BENEFIT_GROUP_MEASUREMENT_DAY)) {
            return messages.getString("benefitGroupMeasurement.perDay");

        }
        
        if (data.equals(HRMConstant.BENEFIT_GROUP_MEASUREMENT_PERIOD)) {
            return messages.getString("benefitGroupMeasurement.perPeriode");

        }
        return null;
    }
    
}
