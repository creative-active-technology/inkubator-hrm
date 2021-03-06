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
@FacesConverter(value = "wtPeriodPayrollTypeConverter")
public class WtPeriodPayrollTypeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        String data= (String) obj;
        if(StringUtils.equals(data, HRMConstant.WT_PERIOD_STATUS_ACTIVE)){
        	messages = resourceBundle.getString("periode.current");
        } else if(StringUtils.equals(data, HRMConstant.WT_PERIOD_STATUS_VOID)){
        	messages = resourceBundle.getString("periode.passed");
        } 
        return messages;

    }
}
