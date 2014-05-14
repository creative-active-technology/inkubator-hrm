/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;


import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
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
@FacesConverter(value = "hrmRoleConverter")
public class HrmRoleConverter implements Converter {

   
    private static final Logger LOGGER = Logger.getLogger(HrmRoleConverter.class);

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        HrmRoleService hrmRoleService = (HrmRoleService) ServiceWebUtil.getService("hrmRoleService");
        Object object = null;
        try {
            object = hrmRoleService.getEntiyByPK(Long.parseLong(string));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return object;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
     return String.valueOf(((HrmRole) o).getId());
    }

}
