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
 * @author Deni Husni FR
 */
@FacesConverter(value = "jobTypeTimeConverter")
public class JobTypeTimeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {

        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
       
        Integer data= (Integer) obj;
        if (data.equals(HRMConstant.JOB_DESCRIPTION_DAY)) {
           
            return messages.getString("jobs_desk.time_daily");

        }

        if (obj == HRMConstant.JOB_DESCRIPTION_MONTH) {
            return messages.getString("jobs_desk.time_monthly");
        }
        return null;

    }
}
