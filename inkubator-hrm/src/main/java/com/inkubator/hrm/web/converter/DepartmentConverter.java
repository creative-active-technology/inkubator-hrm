/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;


import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.webcore.util.ServiceWebUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;

/**
 *
 * @author Deni Husni FR
 */
@FacesConverter(value = "departmentConverter")
public class DepartmentConverter implements Converter {

   
    private static final Logger LOGGER = Logger.getLogger(DepartmentConverter.class);

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        DepartmentService departmentService = (DepartmentService) ServiceWebUtil.getService("departmentService");
        Object object = null;
        try {
            object = departmentService.getEntiyByPK(Long.parseLong(string));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return object;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
     return String.valueOf(((Department) o).getId());
    }

}
