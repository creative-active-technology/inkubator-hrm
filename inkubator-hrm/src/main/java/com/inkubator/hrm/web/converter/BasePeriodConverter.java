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
 * @author Taufik Hidayat
 */
@FacesConverter(value = "basePeriodConverter")
public class BasePeriodConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {

        ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
     
        Integer data = (Integer) obj;
        if (data.equals(HRMConstant.FROM_JANUARY)) {
            return messages.getString("permitClassification.permitClassification_from_january");

        }

        if (data.equals(HRMConstant.FROM_BEGIN_WORK)) {
            return messages.getString("permitClassification.permitClassification_from_begin");

        }
        
        if (data.equals(HRMConstant.FROM_BEGIN_CONVERT)) {
            return messages.getString("permitClassification.permitClassification_from_converter");

        }
        
        
        
        return null;

    }
}
