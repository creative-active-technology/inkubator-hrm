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
@FacesConverter(value = "bioFamilyDependentsConverter")
public class BioFamilyDependentsConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {

        ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
     
        Integer data = (Integer) obj;
        if (data.equals(HRMConstant.BIOFAMILY_DEPENDENTS_YES)) {
            return messages.getString("bioFamilyRelationship.bioFamilyRelationship_dependents_yes");

        }

        if (data.equals(HRMConstant.BIOFAMILY_DEPENDENTS_NO)) {
            return messages.getString("bioFamilyRelationship.bioFamilyRelationship_dependents_no");

        }
        
        return null;

    }
}
