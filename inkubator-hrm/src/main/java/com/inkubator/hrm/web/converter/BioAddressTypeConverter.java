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
@FacesConverter(value = "bioAddressTypeConverter")
public class BioAddressTypeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        Integer data= (Integer) obj;
        if(data == HRMConstant.BIOADDRESS_TYPE_IDENTITY_CARD){
        	messages = resourceBundle.getString("bioaddress.identityCard");
        } else if(data == HRMConstant.BIOADDRESS_TYPE_RESIDENCE){
        	messages = resourceBundle.getString("bioaddress.residence");
        } else if(data == HRMConstant.BIOADDRESS_TYPE_EMERGENCY){
        	messages = resourceBundle.getString("bioaddress.emergency");
        }
        return messages;

    }
}
