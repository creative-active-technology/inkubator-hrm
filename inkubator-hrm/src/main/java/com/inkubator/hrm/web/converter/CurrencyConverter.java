/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.webcore.util.ServiceWebUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@FacesConverter(value = "currencyConverter")
public class CurrencyConverter implements Converter {

    private static final Logger LOGGER = Logger.getLogger(CurrencyConverter.class);
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        CurrencyService currencyService = (CurrencyService) ServiceWebUtil.getService("currencyService");
        Currency object = null;
        try {
            object = currencyService.getEntiyByPK(Long.parseLong(value));
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
        
        CurrencyService currencyService = (CurrencyService) ServiceWebUtil.getService("currencyService");
        Currency currency = null;
        try {
            currency = currencyService.getEntiyByPK((Long) value);
        } catch (Exception ex) {
           
        }

        return currency.getCode() + " " + currency.getName();
    }
    
}
