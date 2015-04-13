package com.inkubator.hrm.web.reimbursement;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RmbsDisbursement;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.web.lazymodel.RmbsApplicationUndisbursedLazyDataModel;
import com.inkubator.hrm.web.model.RmbsDisbursementModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsDisbursementFormController")
@ViewScoped
public class RmbsDisbursementFormController extends BaseController {

	private RmbsApplicationUndisbursedLazyDataModel lazyData;
    private RmbsDisbursementModel model;
    @ManagedProperty(value = "#{rmbsApplicationService}")
    private RmbsApplicationService rmbsApplicationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        model = new RmbsDisbursementModel();
        model.setDisbursementDate(new Date());
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsApplicationService = null;
        model = null;
        lazyData = null;
	}

    public String doBack() {
        return "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
    }
    
    public String doDisbursement() {
    	RmbsDisbursement disbursement = getEntityFromModel(model);
        try {
        	rmbsApplicationService.disbursement(model.getListRmbsApplication(), disbursement);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
            		FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/reimbursement/rmbs_disbursement_form.htm?faces-redirect=true";
            
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        
        return null;
    }

    private RmbsDisbursement getEntityFromModel(RmbsDisbursementModel m) {
    	RmbsDisbursement entity = new RmbsDisbursement();
        entity.setCode(m.getCode());
        entity.setDisbursementDate(m.getDisbursementDate());
        entity.setPayrollPeriodDate(m.getPayrollPeriodDate());
        entity.setDescription(m.getDescription());
        return entity;
    }

	public RmbsApplicationUndisbursedLazyDataModel getLazyData() {
		if(lazyData == null){
			lazyData = new RmbsApplicationUndisbursedLazyDataModel(rmbsApplicationService);
		}
		return lazyData;
	}

	public void setLazyData(RmbsApplicationUndisbursedLazyDataModel lazyData) {
		this.lazyData = lazyData;
	}

	public RmbsDisbursementModel getModel() {
		return model;
	}

	public void setModel(RmbsDisbursementModel model) {
		this.model = model;
	}

	public RmbsApplicationService getRmbsApplicationService() {
		return rmbsApplicationService;
	}

	public void setRmbsApplicationService(RmbsApplicationService rmbsApplicationService) {
		this.rmbsApplicationService = rmbsApplicationService;
	}
    
}
