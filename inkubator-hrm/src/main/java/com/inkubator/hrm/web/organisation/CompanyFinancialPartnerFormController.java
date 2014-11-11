package com.inkubator.hrm.web.organisation;

import java.util.List;

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
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.FinancialNonBanking;
import com.inkubator.hrm.entity.FinancialPartner;
import com.inkubator.hrm.service.FinancialNonBankingService;
import com.inkubator.hrm.service.FinancialPartnerService;
import com.inkubator.hrm.web.model.FinancialPartnerModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "companyFinancialPartnerFormController")
@ViewScoped
public class CompanyFinancialPartnerFormController extends BaseController {
	
	private FinancialPartnerModel model; 
	private Boolean isUpdate;
	private List<FinancialNonBanking> financialNonBankings;
	@ManagedProperty(value = "#{financialNonBankingService}")
	private FinancialNonBankingService financialNonBankingService;
	@ManagedProperty(value = "#{financialPartnerService}")
	private FinancialPartnerService financialPartnerService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            isUpdate = Boolean.FALSE;
            model = new FinancialPartnerModel();
            
            String companyId = FacesUtil.getRequestParameter("companyId");
            model.setCompanyId(Long.parseLong(companyId));
            
            String financialPartnerId = FacesUtil.getRequestParameter("financialPartnerId");
            if (StringUtils.isNotEmpty(financialPartnerId)) {
            	FinancialPartner financialPartner = financialPartnerService.getEntityByPKWithDetail(Long.parseLong(financialPartnerId));
            	if(financialPartner != null){            		
            		model = getModelFromEntity(financialPartner);
            		financialNonBankings = financialNonBankingService.getAllDataByFinancialService(model.getFinancialService());
            		isUpdate = Boolean.TRUE;
            	}
            }            	
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
	}

	@PreDestroy
    public void cleanAndExit() {
		model = null;
		isUpdate = null;
		financialNonBankings = null;
		financialNonBankingService = null;
		financialPartnerService = null;
	}
	
	public FinancialPartnerModel getModel() {
		return model;
	}

	public void setModel(FinancialPartnerModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public List<FinancialNonBanking> getFinancialNonBankings() {
		return financialNonBankings;
	}

	public void setFinancialNonBankings(
			List<FinancialNonBanking> financialNonBankings) {
		this.financialNonBankings = financialNonBankings;
	}

	public FinancialNonBankingService getFinancialNonBankingService() {
		return financialNonBankingService;
	}

	public void setFinancialNonBankingService(
			FinancialNonBankingService financialNonBankingService) {
		this.financialNonBankingService = financialNonBankingService;
	}

	public FinancialPartnerService getFinancialPartnerService() {
		return financialPartnerService;
	}

	public void setFinancialPartnerService(
			FinancialPartnerService financialPartnerService) {
		this.financialPartnerService = financialPartnerService;
	}

	public void onChangeFinancialService(){
		try {
			financialNonBankings = financialNonBankingService.getAllDataByFinancialService(model.getFinancialService());
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
	}

	public void doSave() {
        FinancialPartner financialPartner = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
            	financialPartnerService.update(financialPartner);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	financialPartnerService.save(financialPartner);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

	public void doReset() {
        if (isUpdate) {
            try {
                FinancialPartner financialPartner = financialPartnerService.getEntityByPKWithDetail(model.getId());
                if (financialPartner != null) {
                    model = getModelFromEntity(financialPartner);
                    financialNonBankings = financialNonBankingService.getAllDataByFinancialService(model.getFinancialService());
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        } else {
            model = new FinancialPartnerModel();          
        }
    }
	
	private FinancialPartnerModel getModelFromEntity(FinancialPartner entity) {
		FinancialPartnerModel model = new FinancialPartnerModel();
		model.setId(entity.getId());
		model.setCompanyId(entity.getCompany().getId());
		model.setFinancialNonBankingId(entity.getFinancialNonBanking().getId());
		model.setFinancialService(entity.getFinancialNonBanking().getFinancialService());
		model.setAccountNumber(entity.getAccountNumber());
		model.setAccountName(entity.getAccountName());
		model.setProductName(entity.getProductName());
		
		return model;
		
	}
	
	private FinancialPartner getEntityFromViewModel(FinancialPartnerModel model) {
		FinancialPartner entity = new FinancialPartner();
		if(model.getId() != null){
			entity.setId(model.getId());
		}
		entity.setCompany(new Company(model.getCompanyId()));
		entity.setFinancialNonBanking(new FinancialNonBanking(model.getFinancialNonBankingId()));
		entity.setAccountNumber(model.getAccountNumber());
		entity.setAccountName(model.getAccountName());
		entity.setProductName(model.getProductName());
		
	    return entity;
	}
}
