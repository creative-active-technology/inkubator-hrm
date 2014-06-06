package com.inkubator.hrm.web.flow;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author rizkykojek
 */
@Component(value = "booleanMessageConverterFlow")
public class BooleanMessageConverterFlow implements Converter,Serializable {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
		
		ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
		return obj == Boolean.TRUE ? messages.getString("global.yes"):messages.getString("global.no");
	}

}
