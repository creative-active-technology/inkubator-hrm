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
@FacesConverter(value = "rmbsTypeRoundDigitConverter")
public class RmbsTypeRoundDigitConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        Integer data= (Integer) obj;
        if(data == HRMConstant.RMBS_TYPE_ROUND_DIGIT_0){
        	messages = resourceBundle.getString("rmbs_type.rounding_0");
        } else if(data == HRMConstant.RMBS_TYPE_ROUND_DIGIT_1){
        	messages = resourceBundle.getString("rmbs_type.rounding_1");
        } else if(data == HRMConstant.RMBS_TYPE_ROUND_DIGIT_2){
        	messages = resourceBundle.getString("rmbs_type.rounding_2");
        } else if(data == HRMConstant.RMBS_TYPE_ROUND_DIGIT_3){
        	messages = resourceBundle.getString("rmbs_type.rounding_3");
        }
        return messages;

    }
}
