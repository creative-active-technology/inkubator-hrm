/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.service.OccupationTypeService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "occupationTypeConverter")
@ApplicationScoped
public class OccupationTypeConverter implements Converter{
    @ManagedProperty(value = "#{occupationTypeService}")
    private OccupationTypeService occupationTypeService;

    public void setOccupationTypeService(OccupationTypeService occupationTypeService) {
        this.occupationTypeService = occupationTypeService;
    }

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!StringUtils.isNumeric(value)) {
            return null;
        }
        try {
            Long id = Long.parseLong(value);
            OccupationType occupationType = occupationTypeService.getEntiyByPK(id);
            return occupationType;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error when converting to EmpData using EmpDataConverter", ""));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf((( OccupationType) value).getId());
    }
}
