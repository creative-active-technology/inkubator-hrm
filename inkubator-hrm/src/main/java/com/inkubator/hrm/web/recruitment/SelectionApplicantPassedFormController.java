package com.inkubator.hrm.web.recruitment;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetailRealization;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.RecruitSelectionApplicantPassedService;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleDetailRealizationService;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleService;
import com.inkubator.hrm.web.lazymodel.SelectionApplicantPassedLazyDataModel;
import com.inkubator.hrm.web.model.SelectionApplicantPassedModel;
import com.inkubator.hrm.web.model.SelectionApplicantPassedViewModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "selectionApplicantPassedFormController")
@ViewScoped
public class SelectionApplicantPassedFormController extends BaseController {

	private SelectionApplicantPassedViewModel selectedApplicant;
	private List<RecruitSelectionApplicantSchedulleDetailRealization> listSelectionDetailRealization;
	private LazyDataModel<SelectionApplicantPassedViewModel> lazyData;
	private SelectionApplicantPassedModel model;
	
	@ManagedProperty(value = "#{recruitSelectionApplicantSchedulleService}")
	private RecruitSelectionApplicantSchedulleService recruitSelectionApplicantSchedulleService;
	@ManagedProperty(value = "#{recruitMppApplyDetailService}")
	private RecruitMppApplyDetailService recruitMppApplyDetailService;
	@ManagedProperty(value = "#{recruitSelectionApplicantSchedulleDetailRealizationService}")
	private RecruitSelectionApplicantSchedulleDetailRealizationService recruitSelectionApplicantSchedulleDetailRealizationService;
	@ManagedProperty(value = "#{recruitSelectionApplicantPassedService}")
	private RecruitSelectionApplicantPassedService recruitSelectionApplicantPassedService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String scheduleIds = FacesUtil.getRequestParameter("execution");
        try {
        	model = new SelectionApplicantPassedModel();
        	model.setScheduleId(Long.parseLong(scheduleIds.substring(1)));
        	
        	RecruitSelectionApplicantSchedulle selectionApplicantSchedulle = recruitSelectionApplicantSchedulleService.getEntityByPkWithDetail(model.getScheduleId());
        	RecruitMppApplyDetail mppApplyDetail = recruitMppApplyDetailService.getEntityByJabatanIdAndMppPeriodId(selectionApplicantSchedulle.getHireApply().getJabatan().getId(),selectionApplicantSchedulle.getHireApply().getRecruitMppPeriod().getId());
        	
        	model.setSelectionApplicantSchedulle(selectionApplicantSchedulle);
        	model.setMppApplyDetail(mppApplyDetail);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
	}
	
	@PreDestroy
    public void cleanAndExit() {
		selectedApplicant = null;
		listSelectionDetailRealization = null;
		lazyData = null;
		model = null;
		recruitSelectionApplicantSchedulleService = null;
		recruitMppApplyDetailService = null;
		recruitSelectionApplicantSchedulleDetailRealizationService = null;
		recruitSelectionApplicantPassedService = null;
	}
	
	public String doBack(){
    	return "/protected/recruitment/selection_applicant_passed_view.htm?faces-redirect=true";
    }
    
    public String doSave(){
    	try {
    		Long actual = model.getListApplicantId().size() + recruitSelectionApplicantPassedService.getTotalByHireApplyIdAndNotPlacementStatus(model.getSelectionApplicantSchedulle().getHireApply().getId(), HRMConstant.SELECTION_APPLICANT_PASSED_STATUS_OFFERING_REJECT);
    		Long quotaPlan = (long) model.getSelectionApplicantSchedulle().getHireApply().getCandidateCountRequest();
    		if(actual <= quotaPlan){
    			recruitSelectionApplicantPassedService.save(model.getSelectionApplicantSchedulle().getId(), model.getListApplicantId());
        		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        		return "/protected/recruitment/selection_applicant_passed_view.htm?faces-redirect=true";
    		} else {
    			MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "applicant_passed.error_number_applicant_over_quota", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
    		}    		
    	} catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }
    
    public void doGenerateCV(){
    	
    }
    
    public void doViewSeriesDetailScore(){
    	try {
    		listSelectionDetailRealization = recruitSelectionApplicantSchedulleDetailRealizationService.getAllDataByApplicantIdAndSelectionApplicantSchedulleId(selectedApplicant.getApplicantId().longValue(), model.getScheduleId());
    	} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

	public LazyDataModel<SelectionApplicantPassedViewModel> getLazyData() {
		if(lazyData == null){
			lazyData = new SelectionApplicantPassedLazyDataModel(model.getScheduleId(), recruitSelectionApplicantSchedulleService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<SelectionApplicantPassedViewModel> lazyData) {
		this.lazyData = lazyData;
	}

	public RecruitSelectionApplicantSchedulleService getRecruitSelectionApplicantSchedulleService() {
		return recruitSelectionApplicantSchedulleService;
	}

	public void setRecruitSelectionApplicantSchedulleService(
			RecruitSelectionApplicantSchedulleService recruitSelectionApplicantSchedulleService) {
		this.recruitSelectionApplicantSchedulleService = recruitSelectionApplicantSchedulleService;
	}

	public RecruitMppApplyDetailService getRecruitMppApplyDetailService() {
		return recruitMppApplyDetailService;
	}

	public void setRecruitMppApplyDetailService(RecruitMppApplyDetailService recruitMppApplyDetailService) {
		this.recruitMppApplyDetailService = recruitMppApplyDetailService;
	}

	public SelectionApplicantPassedViewModel getSelectedApplicant() {
		return selectedApplicant;
	}

	public void setSelectedApplicant(SelectionApplicantPassedViewModel selectedApplicant) {
		this.selectedApplicant = selectedApplicant;
	}

	public SelectionApplicantPassedModel getModel() {
		return model;
	}

	public void setModel(SelectionApplicantPassedModel model) {
		this.model = model;
	}

	public List<RecruitSelectionApplicantSchedulleDetailRealization> getListSelectionDetailRealization() {
		return listSelectionDetailRealization;
	}

	public void setListSelectionDetailRealization(
			List<RecruitSelectionApplicantSchedulleDetailRealization> listSelectionDetailRealization) {
		this.listSelectionDetailRealization = listSelectionDetailRealization;
	}

	public RecruitSelectionApplicantSchedulleDetailRealizationService getRecruitSelectionApplicantSchedulleDetailRealizationService() {
		return recruitSelectionApplicantSchedulleDetailRealizationService;
	}

	public void setRecruitSelectionApplicantSchedulleDetailRealizationService(
			RecruitSelectionApplicantSchedulleDetailRealizationService recruitSelectionApplicantSchedulleDetailRealizationService) {
		this.recruitSelectionApplicantSchedulleDetailRealizationService = recruitSelectionApplicantSchedulleDetailRealizationService;
	}

	public RecruitSelectionApplicantPassedService getRecruitSelectionApplicantPassedService() {
		return recruitSelectionApplicantPassedService;
	}

	public void setRecruitSelectionApplicantPassedService(
			RecruitSelectionApplicantPassedService recruitSelectionApplicantPassedService) {
		this.recruitSelectionApplicantPassedService = recruitSelectionApplicantPassedService;
	}
	
}
