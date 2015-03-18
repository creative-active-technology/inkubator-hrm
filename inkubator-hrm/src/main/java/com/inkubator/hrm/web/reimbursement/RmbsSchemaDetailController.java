/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reimbursement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.service.RmbsSchemaService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsSchemaDetailController")
@ViewScoped
public class RmbsSchemaDetailController extends BaseController {

    private RmbsSchema rmbsSchema;
    @ManagedProperty(value = "#{rmbsSchemaService}")
    private RmbsSchemaService rmbsSchemaService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            rmbsSchema = rmbsSchemaService.getEntiyByPK(Long.parseLong(id.substring(1)));
          
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsSchema = null;
    	rmbsSchemaService = null;
    }   

    public String doBack() {
        return "/protected/reimbursement/rmbs_schema_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/reimbursement/rmbs_schema_form.htm?faces-redirect=true&execution=e" + rmbsSchema.getId();
    }

	public RmbsSchema getRmbsSchema() {
		return rmbsSchema;
	}

	public void setRmbsSchema(RmbsSchema rmbsSchema) {
		this.rmbsSchema = rmbsSchema;
	}

	public RmbsSchemaService getRmbsSchemaService() {
		return rmbsSchemaService;
	}

	public void setRmbsSchemaService(RmbsSchemaService rmbsSchemaService) {
		this.rmbsSchemaService = rmbsSchemaService;
	}

}
