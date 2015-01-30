/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PayComponentDataException;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PayComponentDataExceptionService;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.web.model.PayComponentDataExceptionModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
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
@ManagedBean(name = "payComponentDataExceptionFormController")
@ViewScoped
public class PayComponentDataExceptionFormController extends BaseController{
    @ManagedProperty(value = "#{payComponentDataExceptionService}")
    private PayComponentDataExceptionService payComponentDataExceptionService;
    @ManagedProperty(value = "#{paySalaryComponentService}")
    private PaySalaryComponentService paySalaryComponentService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private PayComponentDataException selected;
    private PayComponentDataExceptionModel model;
    private Boolean isUpdate;
    private String paySalaryComponentId;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String payComponentExceptionId = FacesUtil.getRequestParameter("payComponentExceptionId");
            paySalaryComponentId = FacesUtil.getRequestParameter("paySalaryComponentId");
            model = new PayComponentDataExceptionModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(paySalaryComponentId)) {
                PaySalaryComponent paySalaryComponent = paySalaryComponentService.getEntiyByPK(Long.parseLong(paySalaryComponentId.substring(1)));
                model.setComponentName(paySalaryComponent.getCode() + " - " + paySalaryComponent.getName());
                if(payComponentExceptionId != null){
                    PayComponentDataException payComponentDataException = payComponentDataExceptionService.getByPaySalaryComponentId(Long.parseLong(payComponentExceptionId));
                    model = getModelFromEntity(payComponentDataException);
                    isUpdate = Boolean.TRUE;
                }
            }
            
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        payComponentDataExceptionService = null;
        selected = null;
        paySalaryComponentId = null;
        paySalaryComponentService = null;
        empDataService = null;
    }
    
     private PayComponentDataExceptionModel getModelFromEntity(PayComponentDataException payComponentDataException) {
        PayComponentDataExceptionModel model = new PayComponentDataExceptionModel();
        model.setId(payComponentDataException.getId());
        model.setEmpData(payComponentDataException.getEmpData());
        model.setComponentName(payComponentDataException.getPaySalaryComponent().getName() +" - "+ payComponentDataException.getPaySalaryComponent().getName());
        model.setNominal(payComponentDataException.getNominal());
        model.setResetAfterMonth(payComponentDataException.getResetAfterMonth());
        return model;
    }
    
    private PayComponentDataException getEntityFromViewModel(PayComponentDataExceptionModel model) {
        PayComponentDataException payComponentDataException = new PayComponentDataException();
        if (model.getId() != null) {
            payComponentDataException.setId(model.getId());
        }
        payComponentDataException.setEmpData(model.getEmpData());
        payComponentDataException.setPaySalaryComponent(new PaySalaryComponent(Long.parseLong(paySalaryComponentId.substring(1))));
        payComponentDataException.setNominal(model.getNominal());
        payComponentDataException.setResetAfterMonth(model.getResetAfterMonth());
        return payComponentDataException;
    }
    
    public void doSave() {
        
        PayComponentDataException payComponentDataException = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                payComponentDataExceptionService.update(payComponentDataException);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                payComponentDataExceptionService.save(payComponentDataException);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public List<EmpData> doAutoCompletEmployee(String param){
		List<EmpData> empDatas = new ArrayList<EmpData>();
		try {
			empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
		return empDatas;
	}
    
    public PayComponentDataExceptionService getPayComponentDataExceptionService() {
        return payComponentDataExceptionService;
    }

    public void setPayComponentDataExceptionService(PayComponentDataExceptionService payComponentDataExceptionService) {
        this.payComponentDataExceptionService = payComponentDataExceptionService;
    }

    public PayComponentDataException getSelected() {
        return selected;
    }

    public void setSelected(PayComponentDataException selected) {
        this.selected = selected;
    }

    public PayComponentDataExceptionModel getModel() {
        return model;
    }

    public void setModel(PayComponentDataExceptionModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getPaySalaryComponentId() {
        return paySalaryComponentId;
    }

    public void setPaySalaryComponentId(String paySalaryComponentId) {
        this.paySalaryComponentId = paySalaryComponentId;
    }

    public PaySalaryComponentService getPaySalaryComponentService() {
        return paySalaryComponentService;
    }

    public void setPaySalaryComponentService(PaySalaryComponentService paySalaryComponentService) {
        this.paySalaryComponentService = paySalaryComponentService;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }
    
    
}
