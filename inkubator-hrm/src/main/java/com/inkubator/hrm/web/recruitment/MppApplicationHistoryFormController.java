/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitMppApplyDetailTime;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.RecruitMppApplyDetailTimeService;
import com.inkubator.hrm.web.model.MppApplicationHistoryFormModel;
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
            String idMppDetailTime = FacesUtil.getRequestParameter("idMppDetailTime");
            if(StringUtils.isNotBlank(mppApplyDetailId) && StringUtils.isNotBlank(idMppDetailTime)){
            	
            	RecruitMppApplyDetail recruitMppApplyDetail = recruitMppApplyDetailService.getEntityWithDetail(Long.parseLong(mppApplyDetailId));
            	RecruitMppApplyDetailTime recruitMppApplyDetailTime = recruitMppApplyDetailTimeService.getEntiyByPK(Long.parseLong(idMppDetailTime));
            	
            	model.setId(recruitMppApplyDetailTime.getId());
            	model.setMppApplyDetailId(recruitMppApplyDetail.getId());
            	model.setJabatanId(recruitMppApplyDetail.getJabatan().getId());
            	model.setPeriodeStart(recruitMppApplyDetailTime.getMppMonthApply());
            	model.setMaxMpp(recruitMppApplyDetail.getRecruitPlan().longValue());
            	model.setMpp(recruitMppApplyDetailTime.getPlanningPerson().longValue());
            	model.setActual(recruitMppApplyDetailTime.getActual().longValue());
            	model.setDifference(recruitMppApplyDetailTime.getDifference().longValue());
            	
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

    public void onChangeMpp() {
    	Long mpp = model.getMpp();
    	Long actual = model.getActual();
    	Long difference = mpp == actual ? 0 : mpp > actual ? (mpp - actual) : (actual - mpp);
    	model.setDifference(difference);
    }

    public void doSave() {

        try {
            RecruitMppApplyDetailTime recruitMppApplyDetailTime = getEntityFromViewModel(model);
            recruitMppApplyDetailTimeService.updateActualAndDifferenceListMppDetailTimeBasedOnSelectedEntity(recruitMppApplyDetailTime);
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

	public void setRecruitMppApplyDetailTimeService(RecruitMppApplyDetailTimeService recruitMppApplyDetailTimeService) {
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
        recruitMppApplyDetailTime.setActual(model.getActual().intValue());
        recruitMppApplyDetailTime.setDifference(model.getDifference().intValue());
        recruitMppApplyDetailTime.setRecruitMppApplyDetail(new RecruitMppApplyDetail(model.getMppApplyDetailId()));
        
        return recruitMppApplyDetailTime;
    }
    
    
}
