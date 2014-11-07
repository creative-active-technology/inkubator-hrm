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
 * @author Deni
 */
@FacesConverter(value = "financialServiceConverter")
public class FinancialServiceConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        String data = (String) value;
        if(Objects.equals(data, HRMConstant.FINANCIAL_SERVICE_INSURANCE)){
        	messages = resourceBundle.getString("finance.insurance");
        } else if(Objects.equals(data, HRMConstant.FINANCIAL_SERVICE_FINANCING)){
        	messages = resourceBundle.getString("finance.financing");
        } else if(Objects.equals(data, HRMConstant.FINANCIAL_SERVICE_PENSION)){
        	messages = resourceBundle.getString("finance.pension");
        }
        
        
        return messages;
    }
    
}
