package com.inkubator.hrm.web.converter;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;

@FacesConverter(value = "recruitApplicantInternalOrExternalConverter")
public class RecruitApplicantInternalOrExternalConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));    
        return arg2 == Boolean.TRUE || arg2.equals(1) ? messages.getString("recruitApplicant.internal"):messages.getString("recruitApplicant.external");
	}

}
