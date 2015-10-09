package com.inkubator.hrm.web.converter;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;

@FacesConverter(value = "schedulerSchedTypeConverter")
public class SchedulerSchedTypeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
		
		String message = StringUtils.EMPTY;
        String data = (String) value;
        System.out.println("ini value untuk data : " + data);
        switch(data){
        	case "REPEAT"	:
        		message = resourceBundle.getString("scheduler.repeat");
        		break;
        	case "RANGE_TIME"	:
        		message = resourceBundle.getString("scheduler.range_time");
        		break;
        	default :
        		message = data;
        		break;
        }
		return message	;
	}

}
