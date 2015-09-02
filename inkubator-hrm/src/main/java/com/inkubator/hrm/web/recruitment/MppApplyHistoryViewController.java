package com.inkubator.hrm.web.recruitment;

import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.web.lazymodel.MppApplyHistoryLazyDataModel;
import com.inkubator.hrm.web.model.MppApplyHistoryViewModel;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "mppApplyHistoryViewController")
@ViewScoped
public class MppApplyHistoryViewController extends BaseController {

	private LazyDataModel<MppApplyHistoryViewModel> lazyData;
	private MppApplyHistoryViewModel selected;
	private RecruitMppApplySearchParameter parameter;
	
	@ManagedProperty(value = "#{recruitMppApplyService}")
    private RecruitMppApplyService recruitMppApplyService;
	
	@PostConstruct
    @Override
    public void initialization() {
		super.initialization();
		parameter = new RecruitMppApplySearchParameter();
	}
	
	@PreDestroy
    public void cleanAndExit() {
		lazyData = null;
		parameter = null;
		selected = null;
		recruitMppApplyService = null;
	}
	
	public void doSearch(){
		lazyData = null;
	}
	
	public String doDetail() {
		String path = StringUtils.EMPTY;
		if(Objects.equals(selected.getApprovalStatus(), HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)) {
			path = "/protected/recruitment/recruit_mpp_apply_approval_form.htm?faces-redirect=true&execution=e" + selected.getApprovalActivityId();
		} else {
			path = "/protected/recruitment/recruit_mpp_apply_detail.htm?faces-redirect=true&execution=" + selected.getActivityNumber();
		}
    	
        return path;
    }

	public LazyDataModel<MppApplyHistoryViewModel> getLazyData() {
		if(lazyData == null){
			lazyData =  new MppApplyHistoryLazyDataModel(parameter, recruitMppApplyService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<MppApplyHistoryViewModel> lazyData) {
		this.lazyData = lazyData;
	}

	public MppApplyHistoryViewModel getSelected() {
		return selected;
	}

	public void setSelected(MppApplyHistoryViewModel selected) {
		this.selected = selected;
	}

	public RecruitMppApplyService getRecruitMppApplyService() {
		return recruitMppApplyService;
	}

	public void setRecruitMppApplyService(RecruitMppApplyService recruitMppApplyService) {
		this.recruitMppApplyService = recruitMppApplyService;
	}

	public RecruitMppApplySearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(RecruitMppApplySearchParameter parameter) {
		this.parameter = parameter;
	}	
		
}
