package com.inkubator.hrm.web.organisation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.OrganizationLetter;
import com.inkubator.hrm.service.OrganizationLetterService;
import com.inkubator.hrm.web.model.OrganizationLetterModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "organizationLetterFormController")
@ViewScoped
public class OrganizationLetterFormController extends BaseController {

    private OrganizationLetterModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{organizationLetterService}")
    private OrganizationLetterService organizationLetterService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new OrganizationLetterModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                OrganizationLetter organizationLetter = organizationLetterService.getEntiyByPK(Long.parseLong(param));
                if (organizationLetter != null) {
                    getViewModelFromEntity(organizationLetter);
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        organizationLetterService = null;
        model = null;
        isUpdate = null;
    }

	public OrganizationLetterModel getModel() {
		return model;
	}

	public void setModel(OrganizationLetterModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setOrganizationLetterService(OrganizationLetterService organizationLetterService) {
		this.organizationLetterService = organizationLetterService;
	}

	public void doSave() {
        OrganizationLetter organizationLetter = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                organizationLetterService.update(organizationLetter);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                organizationLetterService.save(organizationLetter);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private OrganizationLetter getEntityFromViewModel(OrganizationLetterModel model) {
        OrganizationLetter organizationLetter = new OrganizationLetter();
        if (model.getId() != null) {
            organizationLetter.setId(model.getId());
        }
        organizationLetter.setLetterNumber(model.getLetterNumber());
        organizationLetter.setLetterDate(model.getLetterDate());
        return organizationLetter;
    }
    
    private void getViewModelFromEntity(OrganizationLetter organizationLetter){
    	model.setId(organizationLetter.getId());
    	model.setLetterNumber(organizationLetter.getLetterNumber());
    	model.setLetterDate(organizationLetter.getLetterDate());    	
    }
}
