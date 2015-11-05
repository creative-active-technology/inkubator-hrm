package com.inkubator.hrm.web.account;

import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.service.AccessHistoryService;
import com.inkubator.hrm.web.lazymodel.AccessHistoryLazyDataModel;
import com.inkubator.hrm.web.search.AccessHistorySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;


@ManagedBean(name = "accessHistoryViewController")
@ViewScoped
public class AccessHistoryViewController extends BaseController{
	@ManagedProperty(value = "#{accessHistoryService}")
	private AccessHistoryService service;
	private AccessHistorySearchParameter searchParameter;
	private LazyDataModel<RiwayatAkses> lazy;
	private RiwayatAkses selected;
	
	@Override
	public void initialization(){
		super.initialization();
		searchParameter = new AccessHistorySearchParameter();
	}
	
	@PreDestroy
	private void cleanAndExit(){
		searchParameter = null;
		service =  null;
		lazy = null;
		selected = null;
	}
	
	public void doSearch(){
		lazy = null;
	}
	
	public AccessHistoryService getService() {
		return service;
	}
	public void setService(AccessHistoryService service) {
		this.service = service;
	}
	public AccessHistorySearchParameter getSearchParameter() {
		return searchParameter;
	}
	public void setSearchParameter(AccessHistorySearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}
	public LazyDataModel<RiwayatAkses> getLazy() {
		if (lazy == null){
			lazy = new AccessHistoryLazyDataModel(searchParameter, service);
		}
		return lazy;
	}
	public void setLazy(LazyDataModel<RiwayatAkses> lazy) {
		this.lazy = lazy;
	}
	public RiwayatAkses getSelected() {
		return selected;
	}
	public void setSelected(RiwayatAkses selected) {
		this.selected = selected;
	}
	
	
	
	
}
