/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.service.PermitDistributionService;
import com.inkubator.hrm.service.PermitClassificationService;
import com.inkubator.hrm.web.model.PermitDistributionModel;
import com.inkubator.hrm.web.model.PermitDistributionSchemaModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@ManagedBean(name = "permitDistributionEditFormController")
@ViewScoped
public class PermitDistributionEditFormController extends BaseController{
    @ManagedProperty(value = "#{permitDistributionService}")
    private PermitDistributionService service;
    @ManagedProperty(value = "#{permitClassificationService}")
    private PermitClassificationService permitService;
    private PermitDistribution selected;
    private PermitDistributionSchemaModel model;
    private Boolean isEdit;

    private Map<String, Long> permitSchemeDropDown = new HashMap<String, Long>();
    private List<PermitClassification> permitList = new ArrayList<>();
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("empDataId");
        model = new PermitDistributionSchemaModel();
        try {
            permitList = permitService.getAllData();
            if (param != null) {
                
                isEdit = Boolean.TRUE;
                PermitDistribution permitDistribution = service.getEntityByParamWithDetail(Long.parseLong(param));
                model.setId(permitDistribution.getId());
                model.setEmpDataId(permitDistribution.getEmpData().getId());
                model.setEmpData(permitDistribution.getEmpData().getNikWithFullName());
                model.setPermitId(permitDistribution.getPermitClassification().getId());
                model.setBalance(permitDistribution.getBalance());
            } else {
                isEdit = Boolean.FALSE;
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        
        for (PermitClassification permit : permitList) {
                permitSchemeDropDown.put(permit.getName(), permit.getId());
            }
    }
    
    @PreDestroy
    private void cleanAndExit() {
        permitService = null;
        model = null;
        service = null;
        selected = null;
        isEdit = null;
        permitSchemeDropDown = null;
        permitList = null;

    }
    
    public void doSave() {
        PermitDistribution permitDistribution = getEntityFromViewModel(model);
        try {
            if (isEdit) {
                service.update(permitDistribution);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(permitDistribution);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private PermitDistribution getEntityFromViewModel(PermitDistributionSchemaModel model) {
        PermitDistribution permitDistribution = new PermitDistribution();
        if (model.getId() != null) {
            permitDistribution.setId(model.getId());
        }
        permitDistribution.setEmpData(new EmpData(model.getEmpDataId()));
        permitDistribution.setPermitClassification(new PermitClassification(model.getPermitId()));
        permitDistribution.setBalance(model.getBalance());
        return permitDistribution;
    }
    
    public PermitDistributionService getService() {
        return service;
    }

    public void setService(PermitDistributionService service) {
        this.service = service;
    }

    public PermitDistribution getSelected() {
        return selected;
    }

    public void setSelected(PermitDistribution selected) {
        this.selected = selected;
    }

    public PermitDistributionSchemaModel getModel() {
        return model;
    }

    public void setModel(PermitDistributionSchemaModel model) {
        this.model = model;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public PermitClassificationService getPermitClassificationService() {
        return permitService;
    }

    public void setPermitClassificationService(PermitClassificationService permitService) {
        this.permitService = permitService;
    }

    public Map<String, Long> getPermitSchemeDropDown() {
        return permitSchemeDropDown;
    }

    public void setPermitSchemeDropDown(Map<String, Long> permitSchemeDropDown) {
        this.permitSchemeDropDown = permitSchemeDropDown;
    }

    public List<PermitClassification> getPermitList() {
        return permitList;
    }

    public void setPermitList(List<PermitClassification> permitList) {
        this.permitList = permitList;
    }

    public PermitClassificationService getPermitService() {
        return permitService;
    }

    public void setPermitService(PermitClassificationService permitService) {
        this.permitService = permitService;
    }
    
    
}
