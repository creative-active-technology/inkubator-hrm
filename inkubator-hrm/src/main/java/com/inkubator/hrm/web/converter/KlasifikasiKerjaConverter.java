/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.webcore.util.ServiceWebUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;

/**
 *
 * @author Deni Husni FR
 */
@FacesConverter(value = "klasifikasiKerjaConverter")
public class KlasifikasiKerjaConverter implements Converter {

    private static final Logger LOGGER = Logger.getLogger(KlasifikasiKerjaConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        KlasifikasiKerjaService klasifikasiKerjaService = (KlasifikasiKerjaService) ServiceWebUtil.getService("klasifikasiKerjaService");
        Object object = null;
        try {
            object = klasifikasiKerjaService.getEntiyByPK(Long.parseLong(value));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return object;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf((( KlasifikasiKerja) value).getId());
    }

}
