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
 * @author EKA
 */

@FacesConverter(value = "periodPayrollConverter")
public class PeriodePayrollConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String data = (String) obj;
        if (data.equals(HRMConstant.PERIODE_PAYROLL_VOID)){
            return messages.getString("attedance.paid");
        }
        if  (data.equals(HRMConstant.PERIODE_PAYROLL_ACTIVE)){
            return messages.getString("periode.process");
        }    
        if (data.equals(HRMConstant.PERIODE_PAYROLL_NOT_ACTIVE)){
            return messages.getString("periode.pending");
        }
        return null;
    }
    
}
