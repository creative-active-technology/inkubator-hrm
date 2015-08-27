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
 * @author rizkykojek
 */
@FacesConverter(value = "rmbsApplicationStatusConverter")
public class RmbsApplicationStatusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        Integer data= (Integer) obj;
        if(Objects.equals(data, HRMConstant.RMBS_APPLICATION_STATUS_UNDISBURSED)){
        	messages = resourceBundle.getString("rmbs_application.status_undisbursed");
        } else if(Objects.equals(data, HRMConstant.RMBS_APPLICATION_STATUS_DISBURSED)){
        	messages = resourceBundle.getString("rmbs_application.status_disbursed");
        } else if(Objects.equals(data, HRMConstant.RMBS_APPLICATION_STATUS_CANCELED)){
        	messages = resourceBundle.getString("approvalactivity.cancelled");
        } else if(Objects.equals(data, HRMConstant.RMBS_APPLICATION_STATUS_REJECTED)){
        	messages = resourceBundle.getString("approvalactivity.reject");
        } else if(Objects.equals(data, HRMConstant.RMBS_APPLICATION_STATUS_PAID)){
        	messages = resourceBundle.getString("rmbs_application.status_paid");
        } 
        
        return messages;

    }
}
