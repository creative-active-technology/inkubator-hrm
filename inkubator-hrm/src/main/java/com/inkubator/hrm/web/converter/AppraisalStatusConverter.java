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
 * @author rizkykojek
 */
@FacesConverter(value = "appraisalStatusConverter")
public class AppraisalStatusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));

        String messages = StringUtils.EMPTY;
        Integer data = (Integer) obj;
        if (data == 0) {
            messages = resourceBundle.getString("appraisal.status_on_going");
        }
        if (data == -1) {
            messages = resourceBundle.getString("appraisal.status_past");
        }

        if (data == 1) {
            messages = resourceBundle.getString("appraisal.status_future");
        }

        return messages;

    }
}
