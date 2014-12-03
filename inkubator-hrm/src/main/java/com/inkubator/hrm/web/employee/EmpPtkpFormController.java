/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.model.EmpDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "empPtkpFormController")
@ViewScoped
public class EmpPtkpFormController extends BaseController {
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData selectedEmpData;
    private EmpDataModel empDataModel;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String empDataId = FacesUtil.getRequestParameter("empDataId");
            empDataModel = new EmpDataModel();
            if (StringUtils.isNotEmpty(empDataId)) {
                EmpData empData = empDataService.getEmpDataWithBiodata(Long.parseLong(empDataId));
                if(empDataId != null){
                    empDataModel = getModelFromEntity(empData);
                }
            }
            
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        empDataModel = null;
        empDataService = null;
        selectedEmpData = null;
    }
    
    private EmpDataModel getModelFromEntity(EmpData entity) {
        EmpDataModel model = new EmpDataModel();
        model.setId(entity.getId());
        model.setPtkpNumber(entity.getPtkpNumber());
        if(entity.getPtkpStatus().equals(Boolean.FALSE)){
            model.setPtkpStatusInt(0);
        }else if(entity.getPtkpStatus().equals(Boolean.TRUE)){
            model.setPtkpStatusInt(1);
        }
        model.setNikAndName(entity.getNik() + " - " + entity.getBioData().getFirstName() + " " + entity.getBioData().getLastName());
        return model;
    }
    
    private EmpData getEntityFromViewModel(EmpDataModel model) {
        EmpData empData = new EmpData();
        if (model.getId() != null) {
            empData.setId(model.getId());
        }
        empData.setPtkpNumber(model.getPtkpNumber());
        if(model.getPtkpStatusInt().equals(0)){
            empData.setPtkpStatus(Boolean.FALSE);
        }else if(model.getPtkpStatusInt().equals(1)){
            empData.setPtkpStatus(Boolean.TRUE);
        }
        return empData;
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        EmpData empData = getEntityFromViewModel(empDataModel);
        try {
                empDataService.saveForPtkp(empData);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public EmpData getSelectedEmpData() {
        return selectedEmpData;
    }

    public void setSelectedEmpData(EmpData selectedEmpData) {
        this.selectedEmpData = selectedEmpData;
    }

    public EmpDataModel getEmpDataModel() {
        return empDataModel;
    }

    public void setEmpDataModel(EmpDataModel empDataModel) {
        this.empDataModel = empDataModel;
    }
    
    
}
