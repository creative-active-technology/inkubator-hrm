/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.hrm.entity.Disease;
import com.inkubator.hrm.service.DiseaseService;
import com.inkubator.webcore.util.ServiceWebUtil;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Taufik Hidayat
 */
//@ManagedBean(name = "diseaseConverter")
//@ApplicationScoped
@FacesConverter(value = "diseaseConverter")
public class DiseaseConverter implements Converter {

//    @ManagedProperty(value = "#{diseaseService}")
//    private DiseaseService diseaseService;
//
//    public void setDiseaseService(DiseaseService diseaseService) {
//        this.diseaseService = diseaseService;
//    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!StringUtils.isNumeric(value)) {
            return null;
        }
        try {
            Long id = Long.parseLong(value);
            DiseaseService diseaseService = (DiseaseService) ServiceWebUtil.getService("diseaseService");
            Disease disease = diseaseService.getEntiyByPK(id);
            return disease;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error when converting to Disease using DiseaseConverter", ""));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return null;
        }
        return String.valueOf(((Disease) value).getId());
    }

}
