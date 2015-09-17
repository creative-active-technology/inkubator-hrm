/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.LoanType;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.service.LoanTypeService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.RecruitMppApplyDetailTimeService;
import com.inkubator.hrm.web.model.LoanNewTypeModel;
import com.inkubator.hrm.web.model.LoanTypeModel;
import com.inkubator.hrm.web.model.MppApplicationHistoryFormModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "mppApplicationHistoryFormController")
@ViewScoped
public class MppApplicationHistoryFormController extends BaseController {

    private MppApplicationHistoryFormModel model;
    @ManagedProperty(value = "#{recruitMppApplyDetailTimeService}")
    private RecruitMppApplyDetailTimeService recruitMppApplyDetailTimeService;
    @ManagedProperty(value = "#{recruitMppApplyDetailService}")
    private RecruitMppApplyDetailService recruitMppApplyDetailService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String param = FacesUtil.getRequestParameter("param");
            model = new MppApplicationHistoryFormModel();
            
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        recruitMppApplyDetailTimeService = null;
        recruitMppApplyDetailService = null;
        model = null;
    }


    public void doSave() {

        /*try {
            LoanNewType loanNewType = getEntityFromViewModel(model);
            if (isUpdate) {
                loanNewTypeService.update(loanNewType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                loanNewTypeService.save(loanNewType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }*/
    }

	public MppApplicationHistoryFormModel getModel() {
		return model;
	}

	public void setModel(MppApplicationHistoryFormModel model) {
		this.model = model;
	}

	public void setRecruitMppApplyDetailTimeService(
			RecruitMppApplyDetailTimeService recruitMppApplyDetailTimeService) {
		this.recruitMppApplyDetailTimeService = recruitMppApplyDetailTimeService;
	}

	public void setRecruitMppApplyDetailService(
			RecruitMppApplyDetailService recruitMppApplyDetailService) {
		this.recruitMppApplyDetailService = recruitMppApplyDetailService;
	}

   /* private LoanNewType getEntityFromViewModel(LoanNewTypeModel loanNewTypeModel) throws Exception {
        LoanNewType loanNewType = new LoanNewType();
        if (loanNewTypeModel.getId() != null) {
            loanNewType.setId(loanNewTypeModel.getId());
        }
        loanNewType.setLoanTypeCode(loanNewTypeModel.getLoanTypeCode());
        loanNewType.setLoanTypeName(loanNewTypeModel.getLoanTypeName());       
        Currency currency = currencyService.getEntiyByPK(currencyId);
        loanNewType.setCurrency(currency);
        loanNewType.setInterestMethod(loanNewTypeModel.getInterestMethod());
        loanNewType.setInterest(new BigDecimal(loanNewTypeModel.getInterest()));
        loanNewType.setRoundingStatus(rounding ? 1 : 0);
        loanNewType.setDescription(loanNewTypeModel.getDescription());
        
        return loanNewType;
    }*/
    
    
}
