package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author deni.fahri
 */
@FacesConverter(value = "letterTypeConverter")
public class LetterTypeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));

        String messages = StringUtils.EMPTY;
        Integer data = (Integer) obj;
        if (data == HRMConstant.LETTER_TYPE_OFFERING) {
            messages = resourceBundle.getString("offering_module.offer");
        } else if (data == HRMConstant.LETTER_TYPE_PROBATION) {
            messages = resourceBundle.getString("offering_module.probation");
        } else if (data == HRMConstant.LETTER_TYPE_REJECT) {
            messages = resourceBundle.getString("offering_module.reject");
        } else if (data == HRMConstant.LETTER_TYPE_RESCHEDULE) {
            messages = resourceBundle.getString("offering_module.reschedule");
        }
        return messages;

    }
}
