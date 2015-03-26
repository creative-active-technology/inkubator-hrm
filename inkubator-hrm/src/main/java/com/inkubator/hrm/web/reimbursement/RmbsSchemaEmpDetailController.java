/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reimbursement;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.RmbsSchemaListOfEmp;
import com.inkubator.hrm.entity.RmbsSchemaListOfType;
import com.inkubator.hrm.service.RmbsSchemaListOfEmpService;
import com.inkubator.hrm.service.RmbsSchemaListOfTypeService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsSchemaEmpDetailController")
@ViewScoped
public class RmbsSchemaEmpDetailController extends BaseController {
	
    private RmbsSchemaListOfEmp rmbsSchemaListOfEmp;
    private List<RmbsSchemaListOfType> listOfTypes;
    @ManagedProperty(value = "#{rmbsSchemaListOfEmpService}")
    private RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService;
    @ManagedProperty(value = "#{rmbsSchemaListOfTypeService}")
    private RmbsSchemaListOfTypeService rmbsSchemaListOfTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String empDataId = FacesUtil.getRequestParameter("execution").substring(1);
            String rmbsSchemaId = FacesUtil.getRequestParameter("schema").substring(1);
            rmbsSchemaListOfEmp = rmbsSchemaListOfEmpService.getEntityByPkWithDetail(Long.parseLong(empDataId), Long.parseLong(rmbsSchemaId));
            listOfTypes = rmbsSchemaListOfTypeService.getAllDataByRmbsSchemaId(Long.parseLong(rmbsSchemaId));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsSchemaListOfEmp = null;
    	rmbsSchemaListOfEmpService = null;
    	listOfTypes = null;
    	rmbsSchemaListOfTypeService = null;
    }   

    public String doBack() {
        return "/protected/reimbursement/rmbs_schema_emp_view.htm?faces-redirect=true";
    }

	public RmbsSchemaListOfEmp getRmbsSchemaListOfEmp() {
		return rmbsSchemaListOfEmp;
	}

	public void setRmbsSchemaListOfEmp(RmbsSchemaListOfEmp rmbsSchemaListOfEmp) {
		this.rmbsSchemaListOfEmp = rmbsSchemaListOfEmp;
	}

	public List<RmbsSchemaListOfType> getListOfTypes() {
		return listOfTypes;
	}

	public void setListOfTypes(List<RmbsSchemaListOfType> listOfTypes) {
		this.listOfTypes = listOfTypes;
	}

	public RmbsSchemaListOfEmpService getRmbsSchemaListOfEmpService() {
		return rmbsSchemaListOfEmpService;
	}

	public void setRmbsSchemaListOfEmpService(
			RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService) {
		this.rmbsSchemaListOfEmpService = rmbsSchemaListOfEmpService;
	}

	public RmbsSchemaListOfTypeService getRmbsSchemaListOfTypeService() {
		return rmbsSchemaListOfTypeService;
	}

	public void setRmbsSchemaListOfTypeService(
			RmbsSchemaListOfTypeService rmbsSchemaListOfTypeService) {
		this.rmbsSchemaListOfTypeService = rmbsSchemaListOfTypeService;
	}    
	
}
