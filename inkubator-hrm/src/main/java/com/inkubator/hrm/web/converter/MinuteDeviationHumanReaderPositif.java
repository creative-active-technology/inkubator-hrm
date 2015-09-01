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
@FacesConverter(value = "minuteDeviationHumanReaderPositif")
public class MinuteDeviationHumanReaderPositif implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));

//        String output = StringUtils.EMPTY;
        Long data = (Long) value;
        if (data < Long.valueOf("0")) {
            data = Long.valueOf("0");
        }
        long jam = data / 60;
        long minute = data % 60;
        String output = null;
        if (jam == 0 && minute > 0) {
            output = minute + " " + resourceBundle.getString("global.minute");
        }

        if (jam > 0 && minute > 0) {
            output = jam + " " + resourceBundle.getString("global.hour") + " " + minute + " " + resourceBundle.getString("global.minute");
        }
        if (jam > 0 && minute == 0) {
            output = jam + " " + resourceBundle.getString("global.hour");
        }

        if (jam == 0 && minute == 0) {
            output = null;
        }
//        if(Objects.equals(data, HRMConstant.NOMINAL)){
//        	messages = resourceBundle.getString("reimbursment.nominal");
//        } else if(Objects.equals(data, HRMConstant.SALARY)){
//        	messages = resourceBundle.getString("reimbursment.salary");
//        }
        return output;
    }

}
