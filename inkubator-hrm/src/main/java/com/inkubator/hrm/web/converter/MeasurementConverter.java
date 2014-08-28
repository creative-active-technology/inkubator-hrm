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
 * @author Taufik Hidayat
 */
@FacesConverter(value = "measurementConverter")
public class MeasurementConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {

        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
       
        Integer data = (Integer) obj;
        if (data.equals(HRMConstant.MEASUREMENT_PACK)) {
            return messages.getString("travelComponent.travelComponent_measurement_pack");

        }

        if (data.equals(HRMConstant.MEASUREMENT_UNIT)) {
            return messages.getString("travelComponent.travelComponent_measurement_unit");

        }
        
        if (data.equals(HRMConstant.MEASUREMENT_DAY)) {
            return messages.getString("travelComponent.travelComponent_measurement_day");

        }
        return null;

    }
}
