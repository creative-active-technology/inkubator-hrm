package com.inkubator.hrm.web.reimbursement;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.primefaces.model.LazyDataModel;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.lazymodel.RmbsHistoryLazyDataModel;
import com.inkubator.hrm.web.model.RmbsHistoryViewModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsHistoryViewController")
@ViewScoped
public class RmbsHistoryViewController extends BaseController {

    private LazyDataModel<RmbsApplication> lazyData;
    private List<RmbsHistoryViewModel> listHistoryInYear;
    private Boolean isAdministrator; 
    private EmpData employee;
    
    @ManagedProperty(value = "#{rmbsApplicationService}")
    private RmbsApplicationService rmbsApplicationService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();        
        isAdministrator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
        employee = HrmUserInfoUtil.getEmpData();
        listHistoryInYear =  rmbsApplicationService.getAllDataHistoryThisYear(employee.getId());        
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsApplicationService = null;
        lazyData = null;
        isAdministrator = null;
        employee = null;
    }

    public void doSearch() {
        lazyData = null;
    }
    
    public List<EmpData> doAutoCompleteEmployee(String param) {
        List<EmpData> empDatas = new ArrayList<EmpData>();
        try {
            empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param), HrmUserInfoUtil.getCompanyId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return empDatas;
    }
    
    public void onChangeEmployee(){
    	listHistoryInYear =  rmbsApplicationService.getAllDataHistoryThisYear(employee.getId());
    	this.doSearch();
    }

	public LazyDataModel<RmbsApplication> getLazyData() {
		if(lazyData == null) {
			lazyData =  new RmbsHistoryLazyDataModel(employee.getId(), rmbsApplicationService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<RmbsApplication> lazyData) {
		this.lazyData = lazyData;
	}

	public RmbsApplicationService getRmbsApplicationService() {
		return rmbsApplicationService;
	}

	public void setRmbsApplicationService(RmbsApplicationService rmbsApplicationService) {
		this.rmbsApplicationService = rmbsApplicationService;
	}

	public List<RmbsHistoryViewModel> getListHistoryInYear() {
		return listHistoryInYear;
	}

	public void setListHistoryInYear(List<RmbsHistoryViewModel> listHistoryInYear) {
		this.listHistoryInYear = listHistoryInYear;
	}

	public Boolean getIsAdministrator() {
		return isAdministrator;
	}

	public void setIsAdministrator(Boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}

	public EmpData getEmployee() {
		return employee;
	}

	public void setEmployee(EmpData employee) {
		this.employee = employee;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}
	
}
