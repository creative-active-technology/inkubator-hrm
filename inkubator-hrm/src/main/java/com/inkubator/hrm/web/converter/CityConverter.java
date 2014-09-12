/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.City;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Taufik Hidayat
 */
@FacesConverter("cityConverter")
public class CityConverter implements Converter {


    public Object getAsObject(FacesContext contet, UIComponent component, String value) {
        if (value == null || value.equals("")) {
            return null;
        }
        try {
            Long id = Long.parseLong(value);
            
            return new City(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Input tidak valid", ""));
        }
    }

    public String getAsString(FacesContext contet, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return null;
        }
        return String.valueOf(((City) value).getId());
    }
}
