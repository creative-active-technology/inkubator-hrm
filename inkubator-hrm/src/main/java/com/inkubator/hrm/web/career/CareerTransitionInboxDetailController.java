package com.inkubator.hrm.web.career;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.hrm.web.search.CareerTransitionInboxSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

@ManagedBean(name = "careerTransitionInboxDetailController")
@ViewScoped
public class CareerTransitionInboxDetailController extends BaseController {
	@ManagedProperty(value = "#{empCareerHistoryService}")
	private EmpCareerHistoryService empCareerHistoryService;
	private EmpCareerHistory selectedEmpCareerHistory;

	@PostConstruct
	@Override
	public void initialization() {
		super.initialization();
		String approvalActivityNumber = FacesUtil.getRequestParameter("execution");
		try {
			selectedEmpCareerHistory = empCareerHistoryService.getEntityByApprovalActivityNumber(approvalActivityNumber.substring(1));
			System.out.println(selectedEmpCareerHistory.getBioData().getFirstName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PreDestroy
	private void cleanAndExit() {
		empCareerHistoryService = null;
		selectedEmpCareerHistory = null;
	}

	public EmpCareerHistoryService getEmpCareerHistoryService() {
		return empCareerHistoryService;
	}

	public void setEmpCareerHistoryService(EmpCareerHistoryService empCareerHistoryService) {
		this.empCareerHistoryService = empCareerHistoryService;
	}

	public EmpCareerHistory getSelectedEmpCareerHistory() {
		return selectedEmpCareerHistory;
	}

	public void setSelectedEmpCareerHistory(EmpCareerHistory selectedEmpCareerHistory) {
		this.selectedEmpCareerHistory = selectedEmpCareerHistory;
	}

	
}
