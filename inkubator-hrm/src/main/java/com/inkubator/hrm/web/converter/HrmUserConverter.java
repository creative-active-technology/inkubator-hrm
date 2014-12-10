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

import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.webcore.util.ServiceWebUtil;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rizkykojek
 */
//@ManagedBean(name = "hrmUserConverter")
//@ApplicationScoped
@FacesConverter(value = "hrmUserConverter")
public class HrmUserConverter implements Converter {

//	@ManagedProperty(value = "#{hrmUserService}")
//    private HrmUserService hrmUserService;
//
//	public void setHrmUserService(HrmUserService hrmUserService) {
//		this.hrmUserService = hrmUserService;
//	}

	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!StringUtils.isNumeric(value)) {
            return null;
        }
        HrmUserService hrmUserService = (HrmUserService) ServiceWebUtil.getService("hrmUserService");
        Object object = null;
        try {
            Long id = Long.parseLong(value);
//            HrmUser hrmUser = hrmUserService.getEntiyByPkWithDetail(id);
            object = hrmUserService.getEntiyByPkWithDetail(id);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error when converting to HrmUser using HrmUserConverter", ""));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return null;
        }
        return String.valueOf(((HrmUser) value).getId());
    }
    
}
