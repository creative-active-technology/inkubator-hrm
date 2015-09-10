/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.inkubator.hrm.entity.Gender;
import com.inkubator.hrm.service.GenderService;
import com.inkubator.webcore.util.ServiceWebUtil;
/**
 *
 * @author Deni
 */
@FacesConverter(value = "genderPickListConverter")
public class GenderPickListConverter implements Converter{
    private Object LOGGER;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	GenderService genderService = (GenderService) ServiceWebUtil.getService("genderService");
        Object object = null;
        try {
            object = genderService.getEntiyByPK(Long.parseLong(value));
        } catch (Exception ex) {
            Logger.getLogger(ReligionConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return object;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Gender) value).getId());
    }
    
}