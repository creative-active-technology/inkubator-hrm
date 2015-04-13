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
@FacesConverter(value = "jobCategoryConverter")
public class JobCategoryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {

        ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
  
        Integer data= (Integer) obj;
        if (data.equals(HRMConstant.JOB_DESCRIPTION_PRIMER)) {
           
            return messages.getString("jobs_desk.primer_taks");

        }

        if (obj == HRMConstant.JOB_DESCRIPTION_SEKUNDER) {
            return messages.getString("jobs_desk.sekunder_task");
        }
        return null;

    }
}
