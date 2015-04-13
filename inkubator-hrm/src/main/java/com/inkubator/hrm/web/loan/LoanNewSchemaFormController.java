/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.loan;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.service.LoanNewSchemaService;
import com.inkubator.hrm.web.model.LoanNewSchemaModel;
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
@ManagedBean(name = "loanNewSchemaFormController")
@ViewScoped
public class LoanNewSchemaFormController extends BaseController {
    private LoanNewSchemaModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{loanNewSchemaService}")
    private LoanNewSchemaService loanNewSchemaService;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            model = new LoanNewSchemaModel();
            isUpdate = Boolean.FALSE;
            String loanNewSchemaId = FacesUtil.getRequestParameter("loanNewSchemaId");
            if (StringUtils.isNotEmpty(loanNewSchemaId)) {
                LoanNewSchema savingType = loanNewSchemaService.getEntiyByPK(Long.parseLong(loanNewSchemaId));
                if(loanNewSchemaId != null){
                    model = getModelFromEntity(savingType);
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    private LoanNewSchemaModel getModelFromEntity(LoanNewSchema entity) {
        LoanNewSchemaModel model = new LoanNewSchemaModel();
        model.setId(entity.getId());
        model.setNomorSk(entity.getNomorSk());
        model.setTotalMaximumInstallment(entity.getTotalMaximumInstallment());
        model.setTotalMaximumLoan(entity.getTotalMaximumLoan());
        model.setLoanSchemaCode(entity.getLoanSchemaCode());
        model.setLoanSchemaName(entity.getLoanSchemaName());
        model.setDescription(entity.getDescription());
        return model;
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        loanNewSchemaService = null;
    }

    public void doSave(){
        LoanNewSchema loanNewSchema = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                loanNewSchemaService.update(loanNewSchema);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                loanNewSchemaService.save(loanNewSchema);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private LoanNewSchema getEntityFromViewModel(LoanNewSchemaModel model) {
        LoanNewSchema loanNewSchema = new LoanNewSchema();
        if (model.getId() != null) {
            loanNewSchema.setId(model.getId());
        }
        loanNewSchema.setLoanSchemaCode(model.getLoanSchemaCode());
        loanNewSchema.setLoanSchemaName(model.getLoanSchemaName());
        loanNewSchema.setDescription(model.getDescription());
        loanNewSchema.setNomorSk(model.getNomorSk());
        loanNewSchema.setTotalMaximumInstallment(model.getTotalMaximumInstallment());
        loanNewSchema.setTotalMaximumLoan(model.getTotalMaximumLoan());
        loanNewSchema.setDescription(model.getDescription());
        return loanNewSchema;
    }
    
    public LoanNewSchemaModel getModel() {
        return model;
    }

    public void setModel(LoanNewSchemaModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public LoanNewSchemaService getLoanNewSchemaService() {
        return loanNewSchemaService;
    }

    public void setLoanNewSchemaService(LoanNewSchemaService loanNewSchemaService) {
        this.loanNewSchemaService = loanNewSchemaService;
    }
    
    
}
