/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.service.MajorService;
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
//@ManagedBean(name = "majorConverter")
//@ApplicationScoped
@FacesConverter(value = "majorConverter")
public class MajorConverter implements Converter{
//    @ManagedProperty(value = "#{majorService}")
//    private MajorService majorService;
//
//    public void setMajorService(MajorService majorService) {
//        this.majorService = majorService;
//    }

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!StringUtils.isNumeric(value)) {
            return null;
        }
        MajorService majorService = (MajorService) ServiceWebUtil.getService("majorService");
        Object object = null;
        try {
            Long id = Long.parseLong(value);
//            Major major = majorService.getEntiyByPK(id);
            object = majorService.getEntiyByPK(id);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error when converting to EmpData using EmpDataConverter", ""));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf((( Major) value).getId());
    }
}
