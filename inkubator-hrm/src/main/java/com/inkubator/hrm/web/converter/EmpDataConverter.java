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

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "empDataConverter")
@ApplicationScoped
public class EmpDataConverter implements Converter {

	@ManagedProperty(value = "#{empDataService}")
	private EmpDataService empDataService;

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!StringUtils.isNumeric(value)) {
            return null;
        }
        try {
            Long id = Long.parseLong(value);
            EmpData empData = empDataService.getByIdWithDetail(id);
            return empData;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error when converting to EmpData using EmpDataConverter", ""));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return null;
        }
        return String.valueOf(((EmpData) value).getId());
    }
    
}
