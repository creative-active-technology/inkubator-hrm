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
@FacesConverter(value = "spesificConverter")
public class SpesificConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {

        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
     
        Integer data = (Integer) obj;
        if (data.equals(HRMConstant.BASIC_SALARY)) {
            return messages.getString("modelComponent.modelComponent_basic");

        }

        if (data.equals(HRMConstant.CEIL)) {
            return messages.getString("modelComponent.modelComponent_ceil");

        }
        
        if (data.equals(HRMConstant.TAX_TOTAL)) {
            return messages.getString("modelComponent.modelComponent_tax");

        }
        
        if (data.equals(HRMConstant.UPLOAD)) {
            return messages.getString("modelComponent.modelComponent_upload");

        }
        
        if (data.equals(HRMConstant.LIMITED_TIME)) {
            return messages.getString("modelComponent.modelComponent_limited");

        }
        
         if (data.equals(HRMConstant.FORMULA)) {
            return messages.getString("modelComponent.modelComponent_formula");

        }
        
        return null;

    }
}
