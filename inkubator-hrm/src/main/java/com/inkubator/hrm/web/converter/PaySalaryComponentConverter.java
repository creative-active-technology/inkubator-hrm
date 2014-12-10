/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Deni
 */
@FacesConverter(value = "paySalaryComponentConverter")
public class PaySalaryComponentConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        Integer data= (Integer) value;
        if(data == HRMConstant.PAY_SALARY_COMPONENT_TUNJANGAN){
        	messages = resourceBundle.getString("paySalaryComponent.paySalaryComponent_tunjangan");
        } else if(data == HRMConstant.PAY_SALARY_COMPONENT_POTONGAN){
        	messages = resourceBundle.getString("paySalaryComponent.paySalaryComponent_potongan");
        } else if(data == HRMConstant.PAY_SALARY_COMPONENT_SUBSIDI){
        	messages = resourceBundle.getString("paySalaryComponent.paySalaryComponent_subsidi");
        } 
        return messages;
    }
    
}
