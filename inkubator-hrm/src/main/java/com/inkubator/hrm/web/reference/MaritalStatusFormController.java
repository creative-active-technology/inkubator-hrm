/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.MaritalStatus;
import com.inkubator.hrm.service.MaritalStatusService;
import com.inkubator.hrm.web.model.MaritalStatusModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "maritasStatusFormController")
@ViewScoped
public class MaritalStatusFormController extends BaseController{
    @ManagedProperty(value = "#{maritalStatusService}")
    private MaritalStatusService service;
    private MaritalStatus selected;
    private MaritalStatusModel model;
    private Boolean isEdit;

    public MaritalStatusService getService() {
        return service;
    }

    public void setService(MaritalStatusService service) {
        this.service = service;
    }

    public MaritalStatus getSelected() {
        return selected;
    }

    public void setSelected(MaritalStatus selected) {
        this.selected = selected;
    }

    public MaritalStatusModel getModel() {
        return model;
    }

    public void setModel(MaritalStatusModel model) {
        this.model = model;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new MaritalStatusModel();
        try {
            if (param != null) {

                isEdit = Boolean.TRUE;
                MaritalStatus maritalStatus = service.getEntiyByPK(Long.parseLong(param));
                model.setId(maritalStatus.getId());
                model.setName(maritalStatus.getName());


            } else {
                isEdit = Boolean.FALSE;
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSave() {
        
        MaritalStatus maritalStatus = getEntityFromViewModel(model);
        try {
            if (isEdit) {
                service.update(maritalStatus);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(maritalStatus);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
            //cleanAndExit();

//        cleanAndExit();
    }
    
    private MaritalStatus getEntityFromViewModel(MaritalStatusModel model) {
        MaritalStatus maritalStatus = new MaritalStatus();
        if (model.getId() != null) {
            maritalStatus.setId(model.getId());
        }
        maritalStatus.setName(model.getName());
        return maritalStatus;
    }
    
    @PreDestroy
    private void cleanAndExit() {
        model = null;
        service = null;
        selected = null;
        isEdit = null;

    }
}
