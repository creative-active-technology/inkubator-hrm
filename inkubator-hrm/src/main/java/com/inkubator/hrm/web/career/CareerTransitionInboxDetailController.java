package com.inkubator.hrm.web.career;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

@ManagedBean(name = "careerTransitionInboxDetailController")
@ViewScoped
public class CareerTransitionInboxDetailController extends BaseController {
	@ManagedProperty(value = "#{empCareerHistoryService}")
	private EmpCareerHistoryService empCareerHistoryService;
	private EmpCareerHistory currentEmpDataHistory;
	private EmpCareerHistory previousEmpDataHistory;
	private List<EmpCareerHistory> listAllPreviousCareer;
	
	@PostConstruct
	@Override
	public void initialization() {
		super.initialization();
		String approvalActivityNumber = FacesUtil.getRequestParameter("execution");
		try {
			currentEmpDataHistory = empCareerHistoryService.getEntityByApprovalActivityNumber(approvalActivityNumber.substring(1));
			previousEmpDataHistory = empCareerHistoryService.getPreviousEmpCareerByBioDataIdAndCurrentCreatedOn(currentEmpDataHistory.getBioData().getId(), currentEmpDataHistory.getCreatedOn()).get(0);
			//get all previous career without previousEmpDataHistory
			listAllPreviousCareer = empCareerHistoryService.getPreviousEmpCareerByBioDataIdAndCurrentCreatedOn(previousEmpDataHistory.getBioData().getId(), previousEmpDataHistory.getCreatedOn());
			System.out.println(listAllPreviousCareer.size() + " hohohoho");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PreDestroy
	private void cleanAndExit() {
		empCareerHistoryService = null;
		currentEmpDataHistory = null;
		previousEmpDataHistory = null;
	}

	public String doBack(){
		return "/protected/career/career_transition_inbox_view.htm?faces-redirect=true";
	}
	
	public EmpCareerHistoryService getEmpCareerHistoryService() {
		return empCareerHistoryService;
	}

	public void setEmpCareerHistoryService(EmpCareerHistoryService empCareerHistoryService) {
		this.empCareerHistoryService = empCareerHistoryService;
	}

	public EmpCareerHistory getCurrentEmpDataHistory() {
		return currentEmpDataHistory;
	}

	public void setCurrentEmpDataHistory(EmpCareerHistory currentEmpDataHistory) {
		this.currentEmpDataHistory = currentEmpDataHistory;
	}

	public EmpCareerHistory getPreviousEmpDataHistory() {
		return previousEmpDataHistory;
	}

	public void setPreviousEmpDataHistory(EmpCareerHistory previousEmpDataHistory) {
		this.previousEmpDataHistory = previousEmpDataHistory;
	}

	public List<EmpCareerHistory> getListAllPreviousCareer() {
		return listAllPreviousCareer;
	}

	public void setListAllPreviousCareer(List<EmpCareerHistory> listAllPreviousCareer) {
		this.listAllPreviousCareer = listAllPreviousCareer;
	}

	
}
