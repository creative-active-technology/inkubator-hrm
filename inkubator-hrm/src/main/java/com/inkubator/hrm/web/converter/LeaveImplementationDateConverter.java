/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.log4j.Logger;

import com.inkubator.hrm.entity.LeaveImplementationDate;
import com.inkubator.hrm.service.LeaveImplementationDateService;
import com.inkubator.webcore.util.ServiceWebUtil;

/**
 *
 * @author rizkykojek
 */
@FacesConverter(value = "leaveImplementationDateConverter")
public class LeaveImplementationDateConverter implements Converter {
   
    private static final Logger LOGGER = Logger.getLogger(LeaveImplementationDateConverter.class);

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        LeaveImplementationDateService leaveImplementationDateService = (LeaveImplementationDateService) ServiceWebUtil.getService("leaveImplementationDateService");
        Object object = null;
        try {
            object = leaveImplementationDateService.getEntiyByPK(Long.parseLong(string));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return object;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
    	return String.valueOf(((LeaveImplementationDate) o).getId());
    }

}
