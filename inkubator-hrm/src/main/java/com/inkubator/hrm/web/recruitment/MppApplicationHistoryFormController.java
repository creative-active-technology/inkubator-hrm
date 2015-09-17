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
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitMppApplyDetailTime;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.EmpDataService;
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
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	model = new MppApplicationHistoryFormModel();
            String mppApplyDetailId = FacesUtil.getRequestParameter("mppApplyDetailId");
            if(StringUtils.isNotBlank(mppApplyDetailId)){
            	
            	RecruitMppApplyDetail recruitMppApplyDetail = recruitMppApplyDetailService.getEntityWithDetail(Long.parseLong(mppApplyDetailId));
            	model.setMppApplyDetailId(recruitMppApplyDetail.getId());
            	model.setJabatanId(recruitMppApplyDetail.getJabatan().getId());
            	model.setPeriodeStart(recruitMppApplyDetail.getRecruitMppMonth());
            	
            	Long plan = recruitMppApplyDetail.getRecruitPlan().longValue();
            	Long actual = empDataService.getTotalKaryawanByJabatanIdWithJoinDateBeforeOrEqualDate(model.getJabatanId(), model.getPeriodeStart());
            	Integer difference = (int) (plan == actual ? 0 : plan > actual ? (plan - actual) : (actual - plan));
            	
            	model.setMpp(plan);
            	model.setActual(actual);
            	model.setDifference(difference.longValue());
            	
            }
            
            
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

        try {
            RecruitMppApplyDetailTime recruitMppApplyDetailTime = getEntityFromViewModel(model);
            recruitMppApplyDetailTimeService.saveDataAndUpdateMppApplyDetail(recruitMppApplyDetailTime);
            RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
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

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	
    private RecruitMppApplyDetailTime getEntityFromViewModel(MppApplicationHistoryFormModel model) throws Exception {
    	RecruitMppApplyDetailTime recruitMppApplyDetailTime = new RecruitMppApplyDetailTime();
        if (model.getId() != null) {
            recruitMppApplyDetailTime.setId(model.getId());
        }
        recruitMppApplyDetailTime.setMppMonthApply(model.getPeriodeStart());
        recruitMppApplyDetailTime.setPlanningPerson(model.getMpp().intValue());    
        recruitMppApplyDetailTime.setRecruitMppApplyDetail(new RecruitMppApplyDetail(model.getMppApplyDetailId()));
        
        return recruitMppApplyDetailTime;
    }
    
    
}
