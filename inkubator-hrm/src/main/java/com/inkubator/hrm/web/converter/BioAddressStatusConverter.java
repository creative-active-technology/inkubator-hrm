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
@FacesConverter(value = "bioAddressStatusConverter")
public class BioAddressStatusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        Integer data= (Integer) obj;
        if(data == HRMConstant.BIOADDRESS_STATUS_FAMILY){
        	messages = resourceBundle.getString("bioaddress.family");
        } else if(data == HRMConstant.BIOADDRESS_STATUS_RELATIVES){
        	messages = resourceBundle.getString("bioaddress.relatives");
        } else if(data == HRMConstant.BIOADDRESS_STATUS_OWNER){
        	messages = resourceBundle.getString("bioaddress.owner");
        }
        return messages;

    }
}
