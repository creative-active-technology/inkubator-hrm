package com.inkubator.hrm.web.appraisal;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.service.AppraisalProgramService;
import com.inkubator.hrm.web.lazymodel.AppraisalProgramDistributionLazyDataModel;
import com.inkubator.hrm.web.model.AppraisalProgramDistributionViewModel;
import com.inkubator.hrm.web.search.AppraisalProgramSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
*
* @author rizkykojek
*/

@ManagedBean(name = "appraisalProgramDistributionViewController")
@ViewScoped
public class AppraisalProgramDistributionViewController extends BaseController{
	
	private	AppraisalProgramSearchParameter searchParameter;
	private LazyDataModel<AppraisalProgramDistributionViewModel> lazy;
	private AppraisalProgramDistributionViewModel selected;
	
	@ManagedProperty(value = "#{appraisalProgramService}")
	private AppraisalProgramService appraisalProgramService;
	
	@Override
    public void initialization(){
        super.initialization();
        searchParameter = new AppraisalProgramSearchParameter();
    }
	
	@PreDestroy
    private void cleanAndExit(){
        searchParameter = null;
        lazy = null;
        appraisalProgramService = null;
        selected = null;
    }
	
	public void doSearch(){
        lazy = null;
    }
            
    public String doDistribute(){
    	return "/protected/appraisal/appraisal_program_distribution_form.htm?faces-redirect=true&execution=e" + selected.getProgramId();
    }

	public AppraisalProgramSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(AppraisalProgramSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<AppraisalProgramDistributionViewModel> getLazy() {
		if(lazy == null){
			lazy = new AppraisalProgramDistributionLazyDataModel(searchParameter, appraisalProgramService);
		}
		return lazy;
	}

	public void setLazy(LazyDataModel<AppraisalProgramDistributionViewModel> lazy) {
		this.lazy = lazy;
	}

	public AppraisalProgramDistributionViewModel getSelected() {
		return selected;
	}

	public void setSelected(AppraisalProgramDistributionViewModel selected) {
		this.selected = selected;
	}

	public AppraisalProgramService getAppraisalProgramService() {
		return appraisalProgramService;
	}

	public void setAppraisalProgramService(AppraisalProgramService appraisalProgramService) {
		this.appraisalProgramService = appraisalProgramService;
	}
    
}
