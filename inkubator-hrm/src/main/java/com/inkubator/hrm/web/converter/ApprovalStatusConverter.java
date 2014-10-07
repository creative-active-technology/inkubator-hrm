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
 * @author Deni Husni FR
 */
@FacesConverter(value = "approvalStatusConverter")
public class ApprovalStatusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        Integer data= (Integer) obj;
        if(Objects.equals(data, HRMConstant.APPROVAL_STATUS_APPROVED)){
        	messages = resourceBundle.getString("approvalactivity.approved");
        } else if(Objects.equals(data, HRMConstant.APPROVAL_STATUS_REJECTED)){
        	messages = resourceBundle.getString("approvalactivity.reject");
        } else if(Objects.equals(data, HRMConstant.APPROVAL_STATUS_WAITING)){
        	messages = resourceBundle.getString("approvalactivity.waiting");
        } else if(Objects.equals(data, HRMConstant.APPROVAL_STATUS_DIVERTED)){
        	messages = resourceBundle.getString("approvalactivity.diverted");
        }
        return messages;

    }
}