package com.inkubator.hrm.web.reference;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.service.ReligionService;
import com.inkubator.webcore.controller.BaseController;

/**
*
* @author rizkykojek
*/
@ManagedBean(name = "religionViewController")
@ViewScoped
public class ReligionViewController extends BaseController {

	@ManagedProperty(value = "#religionService")
	private ReligionService religionService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
	}
	
	@PreDestroy
    public void onPostClose() {
		religionService = null;
	}

	public void setReligionService(ReligionService religionService) {
		this.religionService = religionService;
	}
	
}
