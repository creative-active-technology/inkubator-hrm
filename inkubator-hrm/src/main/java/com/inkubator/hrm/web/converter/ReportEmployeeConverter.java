/*
 * To change this template, choose Tools | Templates
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
 * @author Deni
 */
@FacesConverter(value = "reportEmployeeConverter")
public class ReportEmployeeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
       
        Integer data = (Integer) value;
        if (data.equals(HRMConstant.REPORT_FAMILY_DEPENDENTS_NO)) {
            return messages.getString("global.no");

        }

        if (data.equals(HRMConstant.REPORT_FAMILY_DEPENDENTS_YES)) {
            return messages.getString("global.yes");

        }
        return null;
    }
    
}
