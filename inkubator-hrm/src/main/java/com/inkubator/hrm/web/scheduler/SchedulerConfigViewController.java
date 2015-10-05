/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.scheduler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.service.SchedulerConfigService;
import com.inkubator.hrm.web.lazymodel.SchedulerConfigLazyDataModel;
import com.inkubator.hrm.web.search.SchedulerConfigSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author arsyad_
 */
@ManagedBean(name = "schedulerConfigViewController")
@ViewScoped
public class SchedulerConfigViewController extends BaseController{
    
	private SchedulerConfigSearchParameter searchParameter;
	private LazyDataModel<SchedulerConfig> lazyDataSchedulerConfig;
	private SchedulerConfig selected;
	@ManagedProperty(value = "#{schedulerConfigService}")
	private SchedulerConfigService service;
	
	@PostConstruct
	@Override
	public void initialization(){
		searchParameter = new SchedulerConfigSearchParameter();
	}
	
	@PreDestroy
	public void cleanAndExit(){
		searchParameter = null;
		service = null;
		selected = null;
		lazyDataSchedulerConfig = null;
	}
	
	public void doSearch(){
		lazyDataSchedulerConfig = null;
	}
	
	public String doAdd(){
		return "/protected/scheduler/scheduler_config_form.htm?faces-redirect=true";
	}
	
	public String doUpdate(){
            System.out.println("di doUpdate  " + selected.getId());
		return "/protected/scheduler/scheduler_config_form.htm?faces-redirect=true&execution=e" + selected.getId();
	}
	
	public String doDetail(){
            System.out.println("di doDetail " + selected.getId());
		return "/protected/scheduler/scheduler_config_detail.htm?faces-redirect=true&execution=" + selected.getId();
	}

	public SchedulerConfigSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(SchedulerConfigSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<SchedulerConfig> getLazyDataSchedulerConfig() {
		if(lazyDataSchedulerConfig == null){
			lazyDataSchedulerConfig = new SchedulerConfigLazyDataModel(searchParameter, service);
		}
		return lazyDataSchedulerConfig;
	}

	public void setLazyDataSchedulerConfig(LazyDataModel<SchedulerConfig> lazyDataSchedulerConfig) {
		this.lazyDataSchedulerConfig = lazyDataSchedulerConfig;
	}

	public SchedulerConfig getSelected() {
		return selected;
	}

	public void setSelected(SchedulerConfig selected) {
		this.selected = selected;
	}

	public SchedulerConfigService getService() {
		return service;
	}

	public void setService(SchedulerConfigService service) {
		this.service = service;
	}
	
	@Override
    public void onDialogReturn(SelectEvent event){
        lazyDataSchedulerConfig = null;
        super.onDialogReturn(event);
    }
	
	
}
