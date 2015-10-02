package com.inkubator.hrm.web.recruitment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitAgreementNotice;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitAgreementNoticeService;
import com.inkubator.hrm.web.lazymodel.RecruitAgreementNoticeLazyDataModel;
import com.inkubator.hrm.web.model.RecruitAgreementNoticeViewModel;
import com.inkubator.hrm.web.search.RecruitAgreementNoticeSearchParameter;
import com.inkubator.webcore.controller.BaseController;

@ManagedBean(name = "recruitAgreementNoticeViewController")
@ViewScoped
public class RecruitAgreementNoticeViewController extends BaseController {
	
	@ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
	@ManagedProperty(value = "#{recruitAgreementNoticeService}")
    private RecruitAgreementNoticeService recruitAgreementNoticeService;
    private RecruitAgreementNoticeSearchParameter searchParameter;
    private LazyDataModel<RecruitAgreementNoticeViewModel> lazyDataModel;
    private EmpData selectedEmpData;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new RecruitAgreementNoticeSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        empDataService = null;
        selectedEmpData = null;
        recruitAgreementNoticeService = null;
    }
    
    public void doSearch() {
    	lazyDataModel = null;
    }
    
    public String doDetail() throws Exception{
    	RecruitAgreementNotice isEdit = recruitAgreementNoticeService.getEntityByBioDataId(selectedEmpData.getBioData().getId());
    	if(isEdit != null){
    		return "/protected/recruitment/recruit_agreement_notice_form.htm?faces-redirect=true&execution=e" + selectedEmpData.getBioData().getId();
    	}else{
    		return "/protected/recruitment/recruit_agreement_notice_form.htm?faces-redirect=true&execution=a" + selectedEmpData.getBioData().getId();
    	}
    }

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public RecruitAgreementNoticeSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(
			RecruitAgreementNoticeSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<RecruitAgreementNoticeViewModel> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new RecruitAgreementNoticeLazyDataModel(searchParameter, empDataService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<RecruitAgreementNoticeViewModel> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public EmpData getSelectedEmpData() {
		return selectedEmpData;
	}

	public void setSelectedEmpData(EmpData selectedEmpData) {
		this.selectedEmpData = selectedEmpData;
	}

	public RecruitAgreementNoticeService getRecruitAgreementNoticeService() {
		return recruitAgreementNoticeService;
	}

	public void setRecruitAgreementNoticeService(
			RecruitAgreementNoticeService recruitAgreementNoticeService) {
		this.recruitAgreementNoticeService = recruitAgreementNoticeService;
	}
    
    
}
