/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.web.lazymodel.RecruitMppApplyDetailLazyDataModel;
import com.inkubator.hrm.web.model.RecruitMppApplyDetailViewModel;
import com.inkubator.hrm.web.search.RecruitMppApplyDetailSearchParameter;
import com.inkubator.webcore.controller.BaseController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "mppApplicationHistoryViewController")
@ViewScoped
public class MppApplicationHistoryViewController extends BaseController {

    @ManagedProperty(value = "#{recruitMppApplyDetailService}")
    private RecruitMppApplyDetailService recruitMppApplyDetailService;
    private RecruitMppApplyDetailSearchParameter searchParameter;
    private LazyDataModel<RecruitMppApplyDetailViewModel> lazyDataModel;
    private RecruitMppApplyDetailViewModel selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new RecruitMppApplyDetailSearchParameter();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        recruitMppApplyDetailService = null;
        selected = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public String doDetail() {
    	//return "/protected/recruitment/loan_new_schema_detail.htm?faces-redirect=true&execution=e" + selected.getId();
        return "/protected/recruitment/mpp_application_history_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public void setRecruitMppApplyDetailService(
			RecruitMppApplyDetailService recruitMppApplyDetailService) {
		this.recruitMppApplyDetailService = recruitMppApplyDetailService;
	}

	public RecruitMppApplyDetailSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(RecruitMppApplyDetailSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<RecruitMppApplyDetailViewModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new RecruitMppApplyDetailLazyDataModel(searchParameter, recruitMppApplyDetailService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<RecruitMppApplyDetailViewModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public RecruitMppApplyDetailViewModel getSelected() {
        return selected;
    }

    public void setSelected(RecruitMppApplyDetailViewModel selected) {
        this.selected = selected;
    }

}
