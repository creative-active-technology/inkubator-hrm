/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.webcore.util.ServiceWebUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;

/**
 *
 * @author Deni
 */
@FacesConverter(value = "jabatanDualModelConverter")
public class JabatanDualModelConverter implements Converter {

    private static final Logger LOGGER = Logger.getLogger(JabatanDualModelConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        JabatanService jabatanService = (JabatanService) ServiceWebUtil.getService("jabatanService");
        Object object = null;
        try {
            object = jabatanService.getEntiyByPK(Long.parseLong(value));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return object;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Jabatan) value).getId());
    }

}
