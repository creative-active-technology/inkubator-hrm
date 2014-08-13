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
@FacesConverter(value = "bioBankSavingTypeConverter")
public class BioBankSavingTypeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {

        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        System.out.println(" nilai obj " + obj);
        Integer data = (Integer) obj;
        if (data.equals(HRMConstant.BANK_SAVING_TYPE_DEPOSITO)) {
            return messages.getString("bioBankAccount.bioBankAccount_type_deposito");

        }

        if (data.equals(HRMConstant.BANK_SAVING_TYPE_SAVING)) {
            return messages.getString("bioBankAccount.bioBankAccount_type_saving");

        }
        
        if (data.equals(HRMConstant.BANK_SAVING_TYPE_GIRO)) {
            return messages.getString("bioBankAccount.bioBankAccount_type_giro");

        }
        
        if (data.equals(HRMConstant.BANK_SAVING_TYPE_CHECKING)) {
            return messages.getString("bioBankAccount.bioBankAccount_type_checking");

        }
        
        return null;

    }
}
