/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.service.EducationLevelService;
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
//@ManagedBean(name = "educationLevelConverter")
//@ApplicationScoped
@FacesConverter(value = "educationLevelConverter")
public class EducationLevelConverter implements Converter {
//    @ManagedProperty(value = "#{educationLevelService}")
//    private EducationLevelService educationLevelService;
//
//    public void setEducationLevelService(EducationLevelService educationLevelService) {
//        this.educationLevelService = educationLevelService;
//    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!StringUtils.isNumeric(value)) {
            return null;
        }
        EducationLevelService educationLevelService = (EducationLevelService) ServiceWebUtil.getService("educationLevelService");
        Object object = null;
        try {
            Long id = Long.parseLong(value);
//            EducationLevel educationLevel = educationLevelService.getEntiyByPK(id);
            object = educationLevelService.getEntiyByPK(id);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error when converting to EmpData using EmpDataConverter", ""));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf((( EducationLevel) value).getId());
    }
    
    
}
