package com.inkubator.hrm.web.reimbursement;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hamcrest.Matchers;

import ch.lambdaj.Lambda;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RmbsDisbursement;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.RmbsApplicationUndisbursedLazyDataModel;
import com.inkubator.hrm.web.model.RmbsDisbursementModel;
import com.inkubator.securitycore.util.UserInfoUtil;
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

	private Boolean isAdministator;
	
	private WtPeriode period;
	private RmbsApplicationUndisbursedLazyDataModel lazyData;
    private RmbsDisbursementModel model;
    
    @ManagedProperty(value = "#{rmbsApplicationService}")
    private RmbsApplicationService rmbsApplicationService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));        
        model = new RmbsDisbursementModel();
        model.setDisbursementDate(new Date());
        try {
        	period = wtPeriodeService.getEntityByPayrollTypeActive();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsApplicationService = null;
        model = null;
        lazyData = null;
        isAdministator = null;
        wtPeriodeService = null;
        period = null;
	}

    public String doBack() {
        return "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
    }
    
    public String doDisbursement() {
    	RmbsDisbursement disbursement = getEntityFromModel(model);
        try {
        	rmbsApplicationService.disbursement(model.getListRmbsApplicationId(), disbursement);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
            		FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
            
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        
        return null;
    }

    private RmbsDisbursement getEntityFromModel(RmbsDisbursementModel m) {
    	RmbsDisbursement entity = new RmbsDisbursement();
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

	public Boolean getIsAdministator() {
		return isAdministator;
	}

	public void setIsAdministator(Boolean isAdministator) {
		this.isAdministator = isAdministator;
	}

	public WtPeriode getPeriod() {
		return period;
	}

	public void setPeriod(WtPeriode period) {
		this.period = period;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}
	
}
