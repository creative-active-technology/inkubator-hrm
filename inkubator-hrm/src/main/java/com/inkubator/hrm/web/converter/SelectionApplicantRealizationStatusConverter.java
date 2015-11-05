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
@FacesConverter(value = "selectionApplicantRealizationStatusConverter")
public class SelectionApplicantRealizationStatusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        String data= (String) obj;
        
        if(StringUtils.equals(data, HRMConstant.SELECTION_APPLICANT_STATUS_NEW)){
        	messages = resourceBundle.getString("global.new");
        } else if(StringUtils.equals(data, HRMConstant.SELECTION_APPLICANT_STATUS_PASS)){
        	messages = resourceBundle.getString("global.pass");
        } else if(StringUtils.equals(data, HRMConstant.SELECTION_APPLICANT_STATUS_FAILED)){
        	messages = resourceBundle.getString("global.failed");
        } else if(StringUtils.equals(data, HRMConstant.SELECTION_APPLICANT_STATUS_IN_PROGRESS)){
        	messages = resourceBundle.getString("global.in_progress");
        } 
        
        return messages;

    }
}
