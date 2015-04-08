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
@FacesConverter(value = "bioMedicalHistoryStatusConverter")
public class BioMedicalHistoryStatusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {

        ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        Integer data = (Integer) obj;
        if (data.equals(HRMConstant.BIOMEDICAL_HEALED)) {
            return messages.getString("bioMedicalHistory.bioMedicalHistory_status_healed");

        }

        if (data.equals(HRMConstant.BIOMEDICAL_NOT_HEALED)) {
            return messages.getString("bioMedicalHistory.bioMedicalHistory_status_not_healed");

        }

        return null;

    }
}
