/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.apache.commons.lang3.StringUtils;

import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.webcore.util.ServiceWebUtil;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */

@FacesConverter(value = "jabatanUiConverter")
public class JabatanUiConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!StringUtils.isNumeric(value)) {
            return null;
        }
        JabatanService jabatanService = (JabatanService) ServiceWebUtil.getService("jabatanService");
        Object object = null;
        try {
            Long id = Long.parseLong(value);
            object = jabatanService.getEntiyByPK(id);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error when converting to Jabatan using JabatanConverter", ""));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return null;
        }
        
        JabatanService jabatanService = (JabatanService) ServiceWebUtil.getService("jabatanService");
        Jabatan jabatan = null;
        try {
            jabatan = jabatanService.getEntiyByPK((Long) value);
        } catch (Exception ex) {
           
        }

        return jabatan.getCode() + " " + jabatan.getName();
    }

}
