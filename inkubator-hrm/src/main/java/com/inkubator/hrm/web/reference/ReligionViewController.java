package com.inkubator.hrm.web.reference;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.web.lazymodel.ReligionDataModel;
import com.inkubator.webcore.controller.BaseController;

/**
*
* @author rizkykojek
*/
@ManagedBean(name = "religionViewController")
@ViewScoped
public class ReligionViewController extends BaseController {

	private String parameter;
	private LazyDataModel<Religion> lazyDataReligion;	
	@ManagedProperty(value = "#{religionService}")
	private ReligionService religionService;
	
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
	}
	
	@PreDestroy
    public void onPostClose() {
		religionService = null;
		parameter = null;
		lazyDataReligion = null;
	}
	
	public void setReligionService(ReligionService religionService) {
		this.religionService = religionService;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<Religion> getLazyDataReligion() {
		if(lazyDataReligion == null){
			lazyDataReligion = new ReligionDataModel(parameter, religionService);
		}
		return lazyDataReligion;
	}

	public void setLazyDataReligion(LazyDataModel<Religion> lazyDataReligion) {
		this.lazyDataReligion = lazyDataReligion;
	}
	
	public void doSearch(){
		lazyDataReligion = null;
	}
	
}
