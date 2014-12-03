/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.service.CityService;
import com.inkubator.webcore.util.ServiceWebUtil;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Taufik Hidayat
 */
//@ManagedBean(name = "cityConverter")
//@ApplicationScoped
@FacesConverter(value = "cityConverter")
public class CityConverter implements Converter {

    private static final Logger LOGGER = Logger.getLogger(CityConverter.class);

    @Override
    public Object getAsObject(FacesContext contet, UIComponent component, String value) {
        CityService cityService = (CityService) ServiceWebUtil.getService("cityService");
        if (!StringUtils.isNumeric(value)) {
            return null;
        }
        Object object = null;
        try {
            Long id = Long.parseLong(value);
            object = cityService.getEntiyByPK(id);
//            City city = cityService.getEntiyByPK(id);
            return object;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Input tidak valid", ""));
        }
    }

    @Override
    public String getAsString(FacesContext contet, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return null;
        }
        return String.valueOf(((City) value).getId());
    }
}
