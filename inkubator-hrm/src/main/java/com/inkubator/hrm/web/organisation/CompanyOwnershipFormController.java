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
import com.inkubator.hrm.entity.CompanyOwnership;
import com.inkubator.hrm.service.CompanyOwnershipService;
import com.inkubator.hrm.web.model.CompanyCommisionerModel;
import com.inkubator.hrm.web.model.CompanyOwnershipModel;
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

@ManagedBean(name = "companyOwnershipFormController")
@ViewScoped
public class CompanyOwnershipFormController extends BaseController {
    @ManagedProperty(value = "#{companyOwnershipService}")
    private CompanyOwnershipService companyOwnershipService;
    private CompanyOwnership selected;
    private CompanyOwnershipModel model;
    private Boolean isUpdate;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String companyId = FacesUtil.getRequestParameter("companyId");
            model = new CompanyOwnershipModel();
            isUpdate = Boolean.FALSE;
            String ownershipId = FacesUtil.getRequestParameter("ownershipId");
            if (StringUtils.isNotEmpty(ownershipId)) {
                CompanyOwnership companyOwnership = companyOwnershipService.getEntiyByPK(Long.parseLong(ownershipId));
                if (companyOwnership != null) {
                    model = getModelFromEntity(companyOwnership);
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
        companyOwnershipService = null;
        selected = null;
    }

    private CompanyOwnershipModel getModelFromEntity(CompanyOwnership entity) {
        CompanyOwnershipModel companyOwnershipModel = new CompanyOwnershipModel();
        companyOwnershipModel.setId(entity.getId());
        companyOwnershipModel.setName(entity.getName());
        companyOwnershipModel.setNominal(entity.getNominal());
        companyOwnershipModel.setPercentage(entity.getPercentage());
        return companyOwnershipModel;
    }

    private CompanyOwnership getEntityFromViewModel(CompanyOwnershipModel model) {
        CompanyOwnership companyOwnership = new CompanyOwnership();
        if (model.getId() != null) {
            companyOwnership.setId(model.getId());
        }
        companyOwnership.setName(model.getName());
        companyOwnership.setNominal(model.getNominal());
        companyOwnership.setPercentage(model.getPercentage());
        companyOwnership.setCompany(new Company(model.getCompanyId()));
        return companyOwnership;
    }

    public void doSave() {

        CompanyOwnership companyOwnership = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                companyOwnershipService.update(companyOwnership);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                companyOwnershipService.save(companyOwnership);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public CompanyOwnershipService getCompanyOwnershipService() {
        return companyOwnershipService;
    }

    public void setCompanyOwnershipService(CompanyOwnershipService companyOwnershipService) {
        this.companyOwnershipService = companyOwnershipService;
    }

    public CompanyOwnership getSelected() {
        return selected;
    }

    public void setSelected(CompanyOwnership selected) {
        this.selected = selected;
    }

    public CompanyOwnershipModel getModel() {
        return model;
    }

    public void setModel(CompanyOwnershipModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
    
    
}
