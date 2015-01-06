/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.webcore.util.ServiceWebUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author deni
 */
@FacesConverter(value = "religionConverter")
public class ReligionConverter implements Converter{
    private Object LOGGER;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ReligionService religionService = (ReligionService) ServiceWebUtil.getService("religionService");
        Object object = null;
        try {
            object = religionService.getEntiyByPK(Long.parseLong(value));
        } catch (Exception ex) {
            Logger.getLogger(ReligionConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return object;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf((( Religion) value).getId());
    }
    
}
