/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.InterestType;
import com.inkubator.hrm.service.InterestTypeService;
import com.inkubator.hrm.web.model.InterestTypeModel;
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
@ManagedBean(name = "interestTypeFormController")
@ViewScoped
public class InterestTypeFormController extends BaseController {
    @ManagedProperty(value = "#{interestTypeService}")
    private InterestTypeService service;
    private InterestType selected;
    private InterestTypeModel model;
    private Boolean isEdit;
    
    @PreDestroy
    private void cleanAndExit() {
        model = null;
        service = null;
        selected = null;
        isEdit = null;

    }
    
    @PostConstruct
    @Override
    public void initialization() {
        System.out.println("init");
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new InterestTypeModel();
        try {
            if (param != null) {

                isEdit = Boolean.TRUE;
                InterestType interestType = service.getEntiyByPK(Long.parseLong(param));
                model.setId(interestType.getId());
                model.setName(interestType.getName());
                model.setDescription(interestType.getDescription());
            } else {
                isEdit = Boolean.FALSE;
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        InterestType interestType = getEntityFromViewModel(model);
        try {
            if (isEdit) {
                service.update(interestType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(interestType);
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
    
    private InterestType getEntityFromViewModel(InterestTypeModel model) {
        InterestType interestType = new InterestType();
        if (model.getId() != null) {
            interestType.setId(model.getId());
        }
        interestType.setName(model.getName());
        interestType.setDescription(model.getDescription());
        return interestType;
    }

    public InterestTypeService getService() {
        return service;
    }

    public void setService(InterestTypeService service) {
        this.service = service;
    }

    public InterestType getSelected() {
        return selected;
    }

    public void setSelected(InterestType selected) {
        this.selected = selected;
    }

    public InterestTypeModel getModel() {
        return model;
    }

    public void setModel(InterestTypeModel model) {
        this.model = model;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    
}
