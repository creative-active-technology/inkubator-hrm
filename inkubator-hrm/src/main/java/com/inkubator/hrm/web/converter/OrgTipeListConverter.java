/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.webcore.util.ServiceWebUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;
import static org.fusesource.hawtbuf.Buffer.string;

/**
 *
 * @author Deni
 */
@FacesConverter(value = "orgTipeListConverter")
public class OrgTipeListConverter implements Converter {

    private static final Logger LOGGER = Logger.getLogger(GolonganJabatanConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        OrgTypeOfSpecListService orgTypeOfSpecListService = (OrgTypeOfSpecListService) ServiceWebUtil.getService("orgTypeOfSpecListService");
        Object object = null;
        try {
            object = orgTypeOfSpecListService.getEntiyByPK(Long.parseLong(value));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return object;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((OrgTypeOfSpecList) value).getId());
    }

}
