/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.ServiceWebUtil;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Deni
 */
@FacesConverter(value = "paySalaryComponentConverter")
public class PaySalaryComponentConverter implements Converter{
    
    private static final Logger LOGGER = Logger.getLogger(PaySalaryComponentConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        PaySalaryComponentService paySalaryComponentService = (PaySalaryComponentService) ServiceWebUtil.getService("paySalaryComponentService");
        Object object = null;
        try {
            object = paySalaryComponentService.getEntiyByPK(Long.parseLong(value));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return object;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
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
