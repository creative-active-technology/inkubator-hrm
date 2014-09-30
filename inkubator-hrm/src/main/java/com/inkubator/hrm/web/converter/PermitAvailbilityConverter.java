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
@FacesConverter(value = "permitAvailbilityConverter")
public class PermitAvailbilityConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {

        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
     
        Integer data = (Integer) obj;
        if (data.equals(HRMConstant.AVAILIBILITY_FULL)) {
            return messages.getString("permitClassification.permitClassification_avail_full");

        }

        if (data.equals(HRMConstant.AVALILIBILITY_PER_MONTH)) {
            return messages.getString("permitClassification.permitClassification_avail_month");

        }
        
        if (data.equals(HRMConstant.AVALILIBILITY_PER_DATE)) {
            return messages.getString("permitClassification.permitClassification_avail_date");

        }
        
        return null;

    }
}
