package com.inkubator.hrm.web.workingtime;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LeaveDistributionService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.webcore.controller.BaseController;

@ManagedBean(name = "resumeLeaveEmployeeViewController")
@ViewScoped
public class ResumeLeaveEmployeeViewController extends BaseController {
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{leaveDistributionService}")
    private LeaveDistributionService leaveDistributionService;
    private EmpData selectedEmpData;
    private List<LeaveDistribution> listLeaveDistribution;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            Long empDataId = HrmUserInfoUtil.getEmpId();
			selectedEmpData = empDataService.getByIdWithDetail(empDataId);
			listLeaveDistribution = leaveDistributionService.getAllDataByEmpDataId(selectedEmpData.getId());
			System.out.println(listLeaveDistribution.size() + " leavelolololo");
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
    
    
}