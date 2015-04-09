/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Deni
 */
@FacesConverter(value = "classificationConverter")
public class ClassificationConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        Integer classificationIndex = (Integer) value;
        String message = "";
        if(value == HRMConstant.KLASIFIKASI_STRENGTHS){
            message = messages.getString("bioPotensi.strength");
        }else if(value == HRMConstant.KLASIFIKASI_WEAKNESSES){
            message = messages.getString("bioPotensi.weaknesses");
        }else if(value == HRMConstant.KLASIFIKASI_OPPORTUNITIES){
            message = messages.getString("bioPotensi.opportunities");
        }else if(value == HRMConstant.KLASIFIKASI_THREATS){
            message = messages.getString("bioPotensi.threats");
        }
        return message;
                
    }

}
