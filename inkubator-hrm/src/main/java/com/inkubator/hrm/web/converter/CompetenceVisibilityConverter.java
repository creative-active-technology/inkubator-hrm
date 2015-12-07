package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@FacesConverter(value = "competenceVisibilityConverter")
public class CompetenceVisibilityConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        Byte data= (Byte) obj;
        if(Objects.equals(data, HRMConstant.COMPETENCE_SOFT_SKILL)){
        	messages = resourceBundle.getString("career.competence_soft");
        } else if(Objects.equals(data, HRMConstant.COMPETENCE_HARD_SKILL)){
        	messages = resourceBundle.getString("career.competence_hard");
        } 
        return messages;

    }
}
