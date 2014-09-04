/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.webcore.util.ServiceWebUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;

/**
 *
 * @author Deni
 */
@FacesConverter(value = "employeeTypeConverter")
public class EmployeeTypeConverter implements Converter {

    private static final Logger LOGGER = Logger.getLogger(EmployeeTypeConverter.class);
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        EmployeeTypeService employeeTypeService = (EmployeeTypeService) ServiceWebUtil.getService("employeeTypeService");
        Object object = null;
        try {
            object = employeeTypeService.getEntiyByPK(Long.parseLong(value));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return object;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf((( EmployeeType) value).getId());
    }
    
}
