package com.inkubator.hrm.web.recruitment;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetailRealization;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleDetailRealizationService;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleService;
import com.inkubator.hrm.web.lazymodel.SelectionApplicantPassedLazyDataModel;
import com.inkubator.hrm.web.model.SelectionApplicantPassedViewModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "selectionApplicantPassedFormController")
@ViewScoped
public class SelectionApplicantPassedFormController extends BaseController {

	private Long scheduleId;
	private SelectionApplicantPassedViewModel selected;
	private List<RecruitSelectionApplicantSchedulleDetailRealization> listDetailRealization;
	private RecruitSelectionApplicantSchedulle selectionApplicantSchedulle;
	private RecruitMppApplyDetail mppApplyDetail;
	private LazyDataModel<SelectionApplicantPassedViewModel> lazyData;
	
	@ManagedProperty(value = "#{recruitSelectionApplicantSchedulleService}")
	private RecruitSelectionApplicantSchedulleService recruitSelectionApplicantSchedulleService;
	@ManagedProperty(value = "#{recruitMppApplyDetailService}")
	private RecruitMppApplyDetailService recruitMppApplyDetailService;
	@ManagedProperty(value = "#{recruitSelectionApplicantSchedulleDetailRealizationService}")
	private RecruitSelectionApplicantSchedulleDetailRealizationService recruitSelectionApplicantSchedulleDetailRealizationService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String scheduleIds = FacesUtil.getRequestParameter("execution");
        try {
        	scheduleId = Long.parseLong(scheduleIds.substring(1));
        	selectionApplicantSchedulle = recruitSelectionApplicantSchedulleService.getEntityByPkWithDetail(scheduleId);
        	mppApplyDetail = recruitMppApplyDetailService.getEntityByJabatanIdAndMppPeriodId(selectionApplicantSchedulle.getHireApply().getJabatan().getId(),selectionApplicantSchedulle.getHireApply().getRecruitMppPeriod().getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
	}
	
	@PreDestroy
    public void cleanAndExit() {
		
	}
	
	public String doBack(){
    	return "/protected/recruitment/selection_applicant_passed_view.htm?faces-redirect=true";
    }
    
    public String doSave(){
    	
        return null;
    }
    
    public void doGenerateCV(){
    	
    }
    
    public void doViewDetailScore(){
    	try {
    		listDetailRealization = recruitSelectionApplicantSchedulleDetailRealizationService.getAllDataByApplicantIdAndSelectionApplicantSchedulleId(selected.getApplicantId().longValue(), scheduleId);
    	} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public RecruitSelectionApplicantSchedulle getSelectionApplicantSchedulle() {
		return selectionApplicantSchedulle;
	}

	public void setSelectionApplicantSchedulle(RecruitSelectionApplicantSchedulle selectionApplicantSchedulle) {
		this.selectionApplicantSchedulle = selectionApplicantSchedulle;
	}

	public RecruitMppApplyDetail getMppApplyDetail() {
		return mppApplyDetail;
	}

	public void setMppApplyDetail(RecruitMppApplyDetail mppApplyDetail) {
		this.mppApplyDetail = mppApplyDetail;
	}

	public LazyDataModel<SelectionApplicantPassedViewModel> getLazyData() {
		if(lazyData == null){
			lazyData = new SelectionApplicantPassedLazyDataModel(scheduleId, recruitSelectionApplicantSchedulleService);
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

	public SelectionApplicantPassedViewModel getSelected() {
		return selected;
	}

	public void setSelected(SelectionApplicantPassedViewModel selected) {
		this.selected = selected;
	}

	public List<RecruitSelectionApplicantSchedulleDetailRealization> getListDetailRealization() {
		return listDetailRealization;
	}

	public void setListDetailRealization(List<RecruitSelectionApplicantSchedulleDetailRealization> listDetailRealization) {
		this.listDetailRealization = listDetailRealization;
	}

	public RecruitSelectionApplicantSchedulleDetailRealizationService getRecruitSelectionApplicantSchedulleDetailRealizationService() {
		return recruitSelectionApplicantSchedulleDetailRealizationService;
	}

	public void setRecruitSelectionApplicantSchedulleDetailRealizationService(
			RecruitSelectionApplicantSchedulleDetailRealizationService recruitSelectionApplicantSchedulleDetailRealizationService) {
		this.recruitSelectionApplicantSchedulleDetailRealizationService = recruitSelectionApplicantSchedulleDetailRealizationService;
	}
	
}
