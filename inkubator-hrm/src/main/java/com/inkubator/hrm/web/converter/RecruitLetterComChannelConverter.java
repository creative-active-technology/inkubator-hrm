/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;


import com.inkubator.hrm.entity.RecruitLetterComChannel;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.service.RecruitLetterComChannelService;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
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
@FacesConverter(value = "recruitLetterComChannel")
public class RecruitLetterComChannelConverter implements Converter {

   
    private static final Logger LOGGER = Logger.getLogger(RecruitLetterComChannelConverter.class);

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        RecruitLetterComChannelService recruitLetterComChannelService = (RecruitLetterComChannelService) ServiceWebUtil.getService("recruitLetterComChannelService");
        Object object = null;
        try {
            object = recruitLetterComChannelService.getEntiyByPK(Long.parseLong(string));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return object;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
     return String.valueOf(((RecruitLetterComChannel) o).getId());
    }

}
