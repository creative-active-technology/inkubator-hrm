package com.inkubator.hrm.web.workingtime;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.entity.LeaveImplementationDate;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LeaveDistributionService;
import com.inkubator.hrm.service.LeaveImplementationDateService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.LeaveImplementationDateModel;
import com.inkubator.webcore.controller.BaseController;

@ManagedBean(name = "resumeLeaveEmployeeViewController")
@ViewScoped
public class ResumeLeaveEmployeeViewController extends BaseController {
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{leaveDistributionService}")
    private LeaveDistributionService leaveDistributionService;
    @ManagedProperty(value = "#{leaveImplementationDateService}")
    private LeaveImplementationDateService leaveImplementationDateService;
    private EmpData selectedEmpData;
    private List<LeaveDistribution> listLeaveDistribution;
    private List<LeaveImplementationDateModel> listLeaveImplementationDateModel;
    private List<LeaveImplementationDate> listLeaveImplementationDate;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            Long empDataId = HrmUserInfoUtil.getEmpId();
			selectedEmpData = empDataService.getByIdWithDetail(empDataId);
			listLeaveImplementationDateModel = leaveImplementationDateService.getAllDataWithTotalTakenLeaveByEmpDataId(selectedEmpData.getId());
			listLeaveImplementationDate = leaveImplementationDateService.getAllDataByEmpDataId(empDataId);
			
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @PreDestroy
    private void cleanAndExit() {
    	empDataService = null;
    	selectedEmpData = null;
    	leaveDistributionService = null;
    	listLeaveDistribution = null;
    	leaveImplementationDateService = null;
    }

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public EmpData getSelectedEmpData() {
		return selectedEmpData;
	}

	public void setSelectedEmpData(EmpData selectedEmpData) {
		this.selectedEmpData = selectedEmpData;
	}

	public LeaveDistributionService getLeaveDistributionService() {
		return leaveDistributionService;
	}

	public void setLeaveDistributionService(LeaveDistributionService leaveDistributionService) {
		this.leaveDistributionService = leaveDistributionService;
	}

	public List<LeaveDistribution> getListLeaveDistribution() {
		return listLeaveDistribution;
	}

	public void setListLeaveDistribution(List<LeaveDistribution> listLeaveDistribution) {
		this.listLeaveDistribution = listLeaveDistribution;
	}

	public LeaveImplementationDateService getLeaveImplementationDateService() {
		return leaveImplementationDateService;
	}

	public void setLeaveImplementationDateService(
			LeaveImplementationDateService leaveImplementationDateService) {
		this.leaveImplementationDateService = leaveImplementationDateService;
	}

	public List<LeaveImplementationDateModel> getListLeaveImplementationDateModel() {
		return listLeaveImplementationDateModel;
	}

	public void setListLeaveImplementationDateModel(
			List<LeaveImplementationDateModel> listLeaveImplementationDateModel) {
		this.listLeaveImplementationDateModel = listLeaveImplementationDateModel;
	}

	public List<LeaveImplementationDate> getListLeaveImplementationDate() {
		return listLeaveImplementationDate;
	}

	public void setListLeaveImplementationDate(
			List<LeaveImplementationDate> listLeaveImplementationDate) {
		this.listLeaveImplementationDate = listLeaveImplementationDate;
	}
    
    
}