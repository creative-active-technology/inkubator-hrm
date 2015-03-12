/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PaySalaryComponent;
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
@FacesConverter(value = "paySalaryComponentPickListConverter")
public class PaySalaryComponentPickListConverter implements Converter{
    
    private static final Logger LOGGER = Logger.getLogger(PaySalaryComponentPickListConverter.class);

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
       if (value == null || value.equals("")) {
            return null;
        }
        return String.valueOf(((PaySalaryComponent) value).getId());
    }
    
}
