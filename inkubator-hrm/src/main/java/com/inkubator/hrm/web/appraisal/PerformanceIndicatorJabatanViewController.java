package com.inkubator.hrm.web.appraisal;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.web.lazymodel.PerformanceIndicatorJabatanLazyDataModel;
import com.inkubator.hrm.web.model.PerformanceIndicatorJabatanViewModel;
import com.inkubator.hrm.web.search.PerformanceIndicatorJabatanSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
*
* @author rizkykojek
*/

@ManagedBean(name = "performanceIndicatorJabatanViewController")
@ViewScoped
public class PerformanceIndicatorJabatanViewController extends BaseController{
	
	private	PerformanceIndicatorJabatanSearchParameter searchParameter;
	private LazyDataModel<PerformanceIndicatorJabatanViewModel> lazy;
	private PerformanceIndicatorJabatanViewModel selected;
	
	@ManagedProperty(value = "#{jabatanService}")
	private JabatanService jabatanService;
	
	@Override
    public void initialization(){
        super.initialization();
        searchParameter = new PerformanceIndicatorJabatanSearchParameter();
    }
	
	@PreDestroy
    private void cleanAndExit(){
        searchParameter = null;
        lazy = null;
        jabatanService = null;
        selected = null;
    }
	
	public void doSearch(){
        lazy = null;
    }
    
    public String doDetail(){
    	return "/protected/appraisal/performance_indicator_jabatan_detail.htm?faces-redirect=true&execution=e" + selected.getJabatanId();
    }

	public PerformanceIndicatorJabatanSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(PerformanceIndicatorJabatanSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<PerformanceIndicatorJabatanViewModel> getLazy() {
		if(lazy == null){
			lazy =  new PerformanceIndicatorJabatanLazyDataModel(searchParameter, jabatanService);
		}
		return lazy;
	}

	public void setLazy(LazyDataModel<PerformanceIndicatorJabatanViewModel> lazy) {
		this.lazy = lazy;
	}

	public PerformanceIndicatorJabatanViewModel getSelected() {
		return selected;
	}

	public void setSelected(PerformanceIndicatorJabatanViewModel selected) {
		this.selected = selected;
	}

	public JabatanService getJabatanService() {
		return jabatanService;
	}

	public void setJabatanService(JabatanService jabatanService) {
		this.jabatanService = jabatanService;
	}

}
