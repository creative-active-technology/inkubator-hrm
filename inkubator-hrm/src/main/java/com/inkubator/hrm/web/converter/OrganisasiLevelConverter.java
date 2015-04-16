/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Deni HFR
 */
@FacesConverter(value = "organisasiLevelConverter")
public class OrganisasiLevelConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));

        String messages = StringUtils.EMPTY;
        String data = (String) value;
        if (Objects.equals(data, HRMConstant.ORGANISASI)) {
            messages = resourceBundle.getString("organization.organization");
        }
        if (Objects.equals(data, HRMConstant.DEPARTMENT)) {
            messages = resourceBundle.getString("department.department");
        }
        if (Objects.equals(data, HRMConstant.DIVISI)) {
            messages = resourceBundle.getString("division.division");
        }
        if (Objects.equals(data, HRMConstant.DIREKTORAT)) {
            messages = resourceBundle.getString("org_level.directorate");
        }
        if (Objects.equals(data, HRMConstant.SEKRETARIAT)) {
            messages = resourceBundle.getString("org_level.secretariat");
        }

        return messages;
    }

}
