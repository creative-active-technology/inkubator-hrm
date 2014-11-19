package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rizkykojek
 */
@FacesConverter(value = "booleanMessageConverter")
public class BooleanMessageConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
		
		ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
		if(obj == 0){
                    obj = Boolean.FALSE;
                }else if (obj == 1){
                    obj = Boolean.TRUE;
                }
                
                return obj == Boolean.TRUE ? messages.getString("global.yes"):messages.getString("global.no");
	}

}
