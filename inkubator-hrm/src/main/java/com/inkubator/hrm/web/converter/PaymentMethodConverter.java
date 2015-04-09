/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
@FacesConverter(value = "paymentMethodConverter")
public class PaymentMethodConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        Integer data= (Integer) value;
        if(Objects.equals(data, HRMConstant.ANNUITY) || Objects.equals(data, HRMConstant.FLAT)){
        	messages = resourceBundle.getString("loanschema.fixed_payment");
        }  else if(Objects.equals(data, HRMConstant.FLOATING)){
        	messages = resourceBundle.getString("loanschema.effective_payment");
        }
        
        
        return messages;
    }
    
}
