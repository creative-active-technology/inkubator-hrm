/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.service.FacultyService;
import com.inkubator.webcore.util.ServiceWebUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
//@ManagedBean(name = "facultyConverter")
//@ApplicationScoped
@FacesConverter(value = "facultyConverter")
public class FacultyConverter implements Converter {
//    @ManagedProperty(value = "#{facultyService}")
//    private FacultyService facultyService;
//
//    public void setFacultyService(FacultyService facultyService) {
//        this.facultyService = facultyService;
//    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!StringUtils.isNumeric(value)) {
            return null;
        }
        FacultyService facultyService = (FacultyService) ServiceWebUtil.getService("facultyService");
        Object object = null;
        try {
            Long id = Long.parseLong(value);
//            Faculty faculty = facultyService.getEntiyByPK(id);
            object = facultyService.getEntiyByPK(id);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error when converting to EmpData using EmpDataConverter", ""));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf((( Faculty) value).getId());
    }
    
    
}
