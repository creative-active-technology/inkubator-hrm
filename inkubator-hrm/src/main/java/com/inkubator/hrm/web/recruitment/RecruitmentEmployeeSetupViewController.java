package com.inkubator.hrm.web.recruitment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.service.RecruitSelectionApplicantPassedService;
import com.inkubator.hrm.web.lazymodel.RecruitSelectionApplicantPassedLazyDataModel;
import com.inkubator.hrm.web.model.RecruitSelectionApplicantPassedViewModel;
import com.inkubator.hrm.web.search.RecruitSelectionApplicantPassedSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "recruitmentEmployeeSetupViewController")
@ViewScoped
public class RecruitmentEmployeeSetupViewController extends BaseController {

    private RecruitSelectionApplicantPassedSearchParameter searchParameter;
    private LazyDataModel<RecruitSelectionApplicantPassedViewModel> lazyData;
    private RecruitSelectionApplicantPassedViewModel selected;
    @ManagedProperty(value = "#{recruitSelectionApplicantPassedService}")
    private RecruitSelectionApplicantPassedService recruitSelectionApplicantPassedService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new RecruitSelectionApplicantPassedSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
    	recruitSelectionApplicantPassedService = null;
        searchParameter = null;
        lazyData = null;
        selected = null;
    }

	public void doSearch() {
        lazyData = null;
    }
    
    public String doDetail() {
    	return "/protected/recruitment/recruitment_employee_setup_form.htm?faces-redirect=true&applicantId=e" + selected.getApplicantId() + "&hireApplyId=e" + selected.getHireApplyId();
    	//return "/protected/recruitment/recruitment_employee_setup_form.htm?faces-redirect=true&execution=e" + selected.getApplicantId();
    }

	public RecruitSelectionApplicantPassedSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(RecruitSelectionApplicantPassedSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<RecruitSelectionApplicantPassedViewModel> getLazyData() {
		if(lazyData == null){
			lazyData = new RecruitSelectionApplicantPassedLazyDataModel(searchParameter, recruitSelectionApplicantPassedService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<RecruitSelectionApplicantPassedViewModel> lazyData) {
		this.lazyData = lazyData;
	}

	public RecruitSelectionApplicantPassedViewModel getSelected() {
		return selected;
	}

	public void setSelected(RecruitSelectionApplicantPassedViewModel selected) {
		this.selected = selected;
	}

	public void setRecruitSelectionApplicantPassedService(RecruitSelectionApplicantPassedService recruitSelectionApplicantPassedService) {
		this.recruitSelectionApplicantPassedService = recruitSelectionApplicantPassedService;
	}
	
}
