package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Deni Husni FR
 */
@FacesConverter(value = "blodTypeConverter")
public class BlodTypeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {

//        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        System.out.println(" nilai obj aaaaa " + obj);
        Integer data = (Integer) obj;
        if (data.equals(HRMConstant.BLOOD_A_TYPE)) {
            System.out.println(" hehehehehh");
            return "A";

        }

         if (data.equals(HRMConstant.BLOOD_AB_TYPE)) {
            System.out.println(" hehehehehh");
            return "AB";

        }
         if (data.equals(HRMConstant.BLOOD_B_TYPE)) {
            System.out.println(" hehehehehh");
            return "B";

        }
         if (data.equals(HRMConstant.BLOOD_O_TYPE)) {
            System.out.println(" hehehehehh");
            return "O";

        }
        return null;

    }
}
