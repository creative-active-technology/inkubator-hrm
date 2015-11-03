package com.inkubator.hrm.web.recruitment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleService;
import com.inkubator.hrm.web.lazymodel.SelectionPositionPassedLazyDataModel;
import com.inkubator.hrm.web.model.SelectionPositionPassedViewModel;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "selectionApplicantPassedViewController")
@ViewScoped
public class SelectionApplicantPassedViewController extends BaseController {

    private String parameter;
    private LazyDataModel<SelectionPositionPassedViewModel> lazyData;
    private SelectionPositionPassedViewModel selected;
    @ManagedProperty(value = "#{recruitSelectionApplicantSchedulleService}")
    private RecruitSelectionApplicantSchedulleService recruitSelectionApplicantSchedulleService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = StringUtils.EMPTY;
    }

    @PreDestroy
    public void cleanAndExit() {
    	recruitSelectionApplicantSchedulleService = null;
        parameter = null;
        lazyData = null;
        selected = null;
    }

	public void doSearch() {
        lazyData = null;
    }
    
    public String doDetail() {
    	return "/protected/recruitment/selection_applicant_passed_detail.htm?faces-redirect=true&execution=e" + selected.getPositionId();
    }

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<SelectionPositionPassedViewModel> getLazyData() {
		if(lazyData == null){
			lazyData = new SelectionPositionPassedLazyDataModel(parameter, recruitSelectionApplicantSchedulleService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<SelectionPositionPassedViewModel> lazyData) {
		this.lazyData = lazyData;
	}

	public SelectionPositionPassedViewModel getSelected() {
		return selected;
	}

	public void setSelected(SelectionPositionPassedViewModel selected) {
		this.selected = selected;
	}

	public RecruitSelectionApplicantSchedulleService getRecruitSelectionApplicantSchedulleService() {
		return recruitSelectionApplicantSchedulleService;
	}

	public void setRecruitSelectionApplicantSchedulleService(
			RecruitSelectionApplicantSchedulleService recruitSelectionApplicantSchedulleService) {
		this.recruitSelectionApplicantSchedulleService = recruitSelectionApplicantSchedulleService;
	}

}
