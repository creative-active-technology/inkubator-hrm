/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;

import java.text.DateFormatSymbols;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author deni
 */
@FacesConverter(value = "monthNumberAsStringConverter")
public class MonthNumberAsStringConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	String monthAsString = StringUtils.EMPTY;
    	String data = value.toString();
        
        if(StringUtils.isNumeric(data)){
	        Integer month = Integer.valueOf(data);
	        DateFormatSymbols dfs = new DateFormatSymbols(new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
	        monthAsString = dfs.getMonths()[month - 1];
        }
        
        return monthAsString;
    }
    
}
