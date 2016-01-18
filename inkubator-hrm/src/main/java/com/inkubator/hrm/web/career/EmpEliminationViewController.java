package com.inkubator.hrm.web.career;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.service.CareerEmpEliminationService;
import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.hrm.web.lazymodel.EmpEliminationLazyDataModel;
import com.inkubator.hrm.web.model.EmpEliminationViewModel;
import com.inkubator.hrm.web.search.EmpEliminationSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
*
* @author Ahmad Mudzakkir Amal
*/

@ManagedBean(name = "empEliminationViewController")
@ViewScoped
public class EmpEliminationViewController extends BaseController{
	@ManagedProperty(value = "#{careerEmpEliminationService}")
	private CareerEmpEliminationService careerEmpEliminationService;
	@ManagedProperty(value = "#{empCareerHistoryService}")
	private EmpCareerHistoryService empCareerHistoryService;
	private	EmpEliminationSearchParameter searchParameter;
	private LazyDataModel<EmpEliminationViewModel> lazy;
	private EmpEliminationViewModel selected;
	
	@Override
    public void initialization(){
        super.initialization();
        searchParameter = new EmpEliminationSearchParameter();
    }
	
	@PreDestroy
    private void cleanAndExit(){
        searchParameter = null;
        lazy = null;
        careerEmpEliminationService = null;
        selected = null;
    }
	
	public void doSearch(){
        lazy = null;
    }
    
	public String doAdd() {
        return "/protected/career/emp_elimination_form.htm?faces-redirect=true";
    }
            
    public String doDetail(){
    	return "/protected/career/emp_elimination_detail.htm?faces-redirect=true&execution=e" + selected.getApprovalActivityId();
    }
    
	public void setCareerEmpEliminationService(CareerEmpEliminationService careerEmpEliminationService) {
		this.careerEmpEliminationService = careerEmpEliminationService;
	}

	public void setEmpCareerHistoryService(EmpCareerHistoryService empCareerHistoryService) {
		this.empCareerHistoryService = empCareerHistoryService;
	}

	public EmpEliminationSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(EmpEliminationSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<EmpEliminationViewModel> getLazy() {
		 if(lazy == null){
			 lazy = new EmpEliminationLazyDataModel(searchParameter, careerEmpEliminationService);
	     }
		return lazy;
	}

	public void setLazy(LazyDataModel<EmpEliminationViewModel> lazy) {
		this.lazy = lazy;
	}

	public EmpEliminationViewModel getSelected() {
		return selected;
	}

	public void setSelected(EmpEliminationViewModel selected) {
		this.selected = selected;
	}
	
}
