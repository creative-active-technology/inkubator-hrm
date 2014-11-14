/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.log4j.Logger;

import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.webcore.util.ServiceWebUtil;

/**
 *
 * @author rizkykojek
 */
@FacesConverter(value = "golonganJabatanConverter")
public class GolonganJabatanConverter implements Converter {
   
    private static final Logger LOGGER = Logger.getLogger(GolonganJabatanConverter.class);

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
    	GolonganJabatanService golonganJabatanService = (GolonganJabatanService) ServiceWebUtil.getService("golonganJabatanService");
        Object object = null;
        try {
            object = golonganJabatanService.getEntiyByPK(Long.parseLong(string));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return object;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
     return String.valueOf(((GolonganJabatan) o).getId());
    }

}
