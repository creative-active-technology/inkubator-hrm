/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@FacesConverter(value = "overtimeImplCalculationMethodConverter")
public class OvertimeImplCalculationMethodConverter implements Converter{
	
    
	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        return null;
    }
	
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value){
		ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
		String messages = StringUtils.EMPTY;
		Integer data = (Integer) value;
		if(Objects.equals(data, HRMConstant.OVERTIME_CALCULATION_STATIC)){
			messages = resourceBundle.getString("overTimeImplementation.static");
		}else if(Objects.equals(data, HRMConstant.OVERTIME_CALCULATION_RELATIVE)){
			messages = resourceBundle.getString("overTimeImplementation.relative");
		}
		return messages;

	}
}
