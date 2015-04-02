/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.webcore.util.ServiceWebUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Deni Husni FR
 */
@FacesConverter(value = "realNameConverter")
public class RealNameConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            HrmUserService hrmUserService = (HrmUserService) ServiceWebUtil.getService("hrmUserService");
            String userName = (String) value;
            return hrmUserService.getByUserId(userName).getRealName();
        } catch (Exception ex) {
            Logger.getLogger(RealNameConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
