package com.inkubator.hrm.web.converter;

import com.inkubator.common.util.DateTimeUtil;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Deni Husni FR
 */
@FacesConverter(value = "ageByDateConverter")
public class AgeByDateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        Integer umur = null;
        try {
            Date data = (Date) obj;
            umur = DateTimeUtil.getAge(data);

        } catch (Exception ex) {
            Logger.getLogger(AgeByDateConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(umur);
    }
}
