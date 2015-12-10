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

@FacesConverter(value = "appraisalPerformanceGroupConverter")
public class AppraisalPerformanceGroupConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
		String messages = StringUtils.EMPTY;
		String data = (String) value;
		switch (data) {
		case HRMConstant.APPRAISAL_PERFORM_GROUP_EMPLOYEE		:
			messages = resourceBundle.getString("performancegroup.employee");
			break;
		case HRMConstant.APPRAISAL_PERFORM_GROUP_POSITION		:
			messages =  resourceBundle.getString("performancegroup.position");
			break;
		case HRMConstant.APPRAISAL_PERFORM_GROUP_PROCESS		:
			messages = resourceBundle.getString("performancegroup.process");
			break;
		case HRMConstant.APPRAISAL_PERFORM_GROUP_RESULT			:
			messages = resourceBundle.getString("performancegroup.result");
			break;
		default:
			messages = data;
			break;
		}
		return messages;
	}

}
