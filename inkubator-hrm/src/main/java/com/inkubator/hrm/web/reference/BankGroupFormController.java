/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BankGroup;
import com.inkubator.hrm.service.BankGroupService;
import com.inkubator.hrm.web.model.BankGroupModel;
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
 * @author EKA
 */
@ManagedBean(name = "bankGroupFormController")
@ViewScoped
public class BankGroupFormController extends BaseController{
    @ManagedProperty(value = "#{bankGroupService}")
    private BankGroupService service;
    private BankGroup selected;
    private BankGroupModel model;
    private Boolean isUpdate;
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        try{
            String bankGroupId = FacesUtil.getRequestParameter("bankGroupId");
            model = new BankGroupModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(bankGroupId)){
                BankGroup bankGroup = service.getEntiyByPK(Long.parseLong(bankGroupId));
                if(bankGroupId != null){
                    model = getModelFromEntity(bankGroup);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
        isUpdate = null;
        model = null;
        service = null;
        selected = null;
    }
    
    private BankGroupModel getModelFromEntity(BankGroup entity){
        BankGroupModel model = new BankGroupModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    private BankGroup getEntityFromViewModel(BankGroupModel model){
        BankGroup bankGroup = new BankGroup();
        if(model.getId() != null){
            bankGroup.setId(model.getId());
        }
        bankGroup.setCode(model.getCode());
        bankGroup.setName(model.getName());
        bankGroup.setDescription(model.getDescription());
        return bankGroup;
    }
    
    public void doSave(){
        BankGroup bankGroup = getEntityFromViewModel(model);
        try{
            if(isUpdate){
                service.update(bankGroup);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(bankGroup);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }

    public BankGroupService getService() {
        return service;
    }

    public void setService(BankGroupService service) {
        this.service = service;
    }

    public BankGroup getSelected() {
        return selected;
    }

    public void setSelected(BankGroup selected) {
        this.selected = selected;
    }

    public BankGroupModel getModel() {
        return model;
    }

    public void setModel(BankGroupModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
    
    
}

