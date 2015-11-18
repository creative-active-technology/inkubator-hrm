/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.career;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.service.CareerTransitionService;
import com.inkubator.hrm.web.model.CareerTransitionModel;
import com.inkubator.hrm.web.model.ReimbursmentSchemaModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "careerTransitionFormController")
@ViewScoped
public class CareerTransitionFormController extends BaseController {
    @ManagedProperty(value = "#{careerTransitionService}")
    private CareerTransitionService careerTransitionService;
    private CareerTransitionModel careerTransitionModel;
    private Boolean isUpdate;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        	careerTransitionModel = new CareerTransitionModel();
        	isUpdate = Boolean.FALSE;
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        careerTransitionModel = null;
        careerTransitionService = null;
    }
}
