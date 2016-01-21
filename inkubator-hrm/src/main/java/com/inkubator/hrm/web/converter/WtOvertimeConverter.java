/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.webcore.util.ServiceWebUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@FacesConverter(value = "wtOvertimeConverter")
public class WtOvertimeConverter implements Converter{
    private Object LOGGER;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        WtOverTimeService wtOverTimeService = (WtOverTimeService) ServiceWebUtil.getService("wtOverTimeService");
        Object object = null;
        try {
            object = wtOverTimeService.getEntiyByPK(Long.parseLong(value));
        } catch (Exception ex) {
            Logger.getLogger(WtOvertimeConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return object;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf((( WtOverTime) value).getId());
    }
    
}
