package com.inkubator.hrm.web.converter;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;

@FacesConverter(value = "roleTransitionConverter")
public class RoleTransitionConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
		
		ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));    
		Integer data = (Integer) obj;
		String message = "";
		if(data.equals(HRMConstant.CAREER_EMPLOYEE_STATUS)){
			message =  messages.getString("careerTransition.emp_status");
        }else if(data.equals(HRMConstant.CAREER_TERMINATION_TYPE)){
        	message = messages.getString("careerTransition.emp_termination");
        }else if(data.equals(HRMConstant.CAREER_TRANSITION)){
        	message = messages.getString("careerTransition.career_transition");
        }
		
		return message;
	}

}
