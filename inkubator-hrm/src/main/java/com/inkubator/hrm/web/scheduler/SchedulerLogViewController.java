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

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.service.SchedulerLogService;
import com.inkubator.hrm.web.lazymodel.SchedulerLogLazyDataModel;
import com.inkubator.hrm.web.search.SchedulerLogSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "schedulerLogViewController")
@ViewScoped
public class SchedulerLogViewController extends BaseController{
    
	private SchedulerLogSearchParameter searchParameter;
	private LazyDataModel<SchedulerLog> lazyData;
	private SchedulerLog selected;
	@ManagedProperty(value = "#{schedulerLogService}")
	private SchedulerLogService service;
	
	@PostConstruct
	@Override
	public void initialization(){
		searchParameter = new SchedulerLogSearchParameter();
	}
	
	@PreDestroy
	public void cleanAndExit(){
		searchParameter = null;
		service = null;
		selected = null;
		lazyData = null;
	}
	
	public void doSearch(){
		lazyData = null;
	}
	
	public String doDetail(){
		return "/protected/scheduler/scheduler_config_detail.htm?faces-redirect=true&execution=" + selected.getId();
	}

	public SchedulerLogSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(SchedulerLogSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<SchedulerLog> getLazyData() {
		if(lazyData == null){
			lazyData = new SchedulerLogLazyDataModel(searchParameter, service);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<SchedulerLog> lazyData) {
		this.lazyData = lazyData;
	}

	public SchedulerLog getSelected() {
		return selected;
	}

	public void setSelected(SchedulerLog selected) {
		this.selected = selected;
	}

	public void setService(SchedulerLogService service) {
		this.service = service;
	}

	
}
