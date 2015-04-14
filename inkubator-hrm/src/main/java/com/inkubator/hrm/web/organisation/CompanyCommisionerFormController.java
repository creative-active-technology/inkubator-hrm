/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.CompanyCommisioner;
import com.inkubator.hrm.service.CompanyCommisionerService;
import com.inkubator.hrm.web.model.CompanyCommisionerModel;
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
@ManagedBean(name = "companyCommisionerFormController")
@ViewScoped
public class CompanyCommisionerFormController extends BaseController {

    @ManagedProperty(value = "#{companyCommisionerService}")
    private CompanyCommisionerService companyCommisionerService;
    private CompanyCommisioner selected;
    private CompanyCommisionerModel model;
    private Boolean isUpdate;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String companyId = FacesUtil.getRequestParameter("companyId");
            model = new CompanyCommisionerModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(companyId)) {
                CompanyCommisioner commisioner = companyCommisionerService.getEntiyByPK(Long.parseLong(companyId));
                if (commisioner != null) {
                    model = getModelFromEntity(commisioner);
                    isUpdate = Boolean.TRUE;
                }
            }

            model.setCompanyId(Long.parseLong(companyId));
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        companyCommisionerService = null;
        selected = null;
    }

    private CompanyCommisionerModel getModelFromEntity(CompanyCommisioner entity) {
        CompanyCommisionerModel modelCommisioner = new CompanyCommisionerModel();
        modelCommisioner.setId(entity.getId());
        modelCommisioner.setName(entity.getName());
        modelCommisioner.setSocialNumber(entity.getSocialNumber());
        return modelCommisioner;
    }

    private CompanyCommisioner getEntityFromViewModel(CompanyCommisionerModel model) {
        CompanyCommisioner companyCommisioner = new CompanyCommisioner();
        if (model.getId() != null) {
            companyCommisioner.setId(model.getId());
        }
        companyCommisioner.setName(model.getName());
        companyCommisioner.setSocialNumber(model.getSocialNumber());
        companyCommisioner.setCompany(new Company(model.getCompanyId()));
        return companyCommisioner;
    }

    public void doSave() {

        CompanyCommisioner companyCommisioner = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                companyCommisionerService.update(companyCommisioner);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                companyCommisionerService.save(companyCommisioner);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public CompanyCommisionerService getCompanyCommisionerService() {
        return companyCommisionerService;
    }

    public void setCompanyCommisionerService(CompanyCommisionerService companyCommisionerService) {
        this.companyCommisionerService = companyCommisionerService;
    }

    public CompanyCommisioner getSelected() {
        return selected;
    }

    public void setSelected(CompanyCommisioner selected) {
        this.selected = selected;
    }

    public CompanyCommisionerModel getModel() {
        return model;
    }

    public void setModel(CompanyCommisionerModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

}
