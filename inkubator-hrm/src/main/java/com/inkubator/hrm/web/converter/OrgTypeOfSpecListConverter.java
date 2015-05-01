/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.webcore.util.ServiceWebUtil;
import javax.faces.application.FacesMessage;
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
@FacesConverter(value = "orgTypeOfSpecListConverter")
public class OrgTypeOfSpecListConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!StringUtils.isNumeric(value)) {
            return null;
        }
        OrgTypeOfSpecListService orgTypeOfSpecListService = (OrgTypeOfSpecListService) ServiceWebUtil.getService("orgTypeOfSpecListService");
        Object object = null;
        try {
            Long id = Long.parseLong(value);
//            OccupationType occupationType = occupationTypeService.getEntiyByPK(id);
            object = orgTypeOfSpecListService.getEntiyByPK(id);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error when converting to EmpData using EmpDataConverter", ""));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf((( OrgTypeOfSpecList) value).getId());
    }
    
}
