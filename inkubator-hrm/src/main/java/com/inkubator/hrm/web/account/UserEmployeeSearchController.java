package com.inkubator.hrm.web.account;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.lazymodel.EmpDataNotExistInUserLazyDataModel;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "userEmployeeSearchController")
@ViewScoped
public class UserEmployeeSearchController extends BaseController {
	
	private String param;
	private LazyDataModel<EmpData> lazyDataEmpData;
	@ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
	
	@PostConstruct
    @Override
    public void initialization() {
		
	}
	
	@PreDestroy
    public void cleanAndExit() {
		param = null;
		lazyDataEmpData = null;
                empDataService = null;
	}
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public LazyDataModel<EmpData> getLazyDataEmpData() {
		if(lazyDataEmpData == null){
			lazyDataEmpData = new EmpDataNotExistInUserLazyDataModel(param, empDataService);
		}
		return lazyDataEmpData;
	}

	public void setLazyDataEmpData(LazyDataModel<EmpData> lazyDataEmpData) {
		this.lazyDataEmpData = lazyDataEmpData;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}
	
	public void doSearch(){
		lazyDataEmpData = null;
	}
	
	public void doSelect(EmpData empData){
        RequestContext.getCurrentInstance().closeDialog(empData);
    }
}
