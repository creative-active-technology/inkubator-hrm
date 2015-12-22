package com.inkubator.hrm.web.appraisal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.AppraisalSystemScoring;
import com.inkubator.hrm.entity.AppraisalSystemScoringIndex;
import com.inkubator.hrm.service.AppraisalSystemScoringIndexService;
import com.inkubator.hrm.service.AppraisalSystemScoringService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

@ManagedBean(name = "appraisalSystemScoringDetailController")
@ViewScoped
public class AppraisalSystemScoringDetailController extends BaseController{
	@ManagedProperty(value = "#{appraisalSystemScoringService}")
    private AppraisalSystemScoringService appraisalSystemScoringService;
	@ManagedProperty(value = "#{appraisalSystemScoringIndexService}")
    private AppraisalSystemScoringIndexService appraisalSystemScoringIndexService;
	private AppraisalSystemScoring selectedAppraisalSystemScoring;
	private List<AppraisalSystemScoringIndex> listAppraisalSystemScoring;
    
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String appraisalSystemScoringId = FacesUtil.getRequestParameter("execution");
        listAppraisalSystemScoring = new ArrayList<AppraisalSystemScoringIndex>();
        try {
			selectedAppraisalSystemScoring = appraisalSystemScoringService.getEntiyByPK(Long.valueOf(appraisalSystemScoringId.substring(1)));
			listAppraisalSystemScoring = appraisalSystemScoringIndexService.getAllDataByAppraisalSystemScoringId(selectedAppraisalSystemScoring.getId());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@PreDestroy
    public void cleanAndExit() {
		appraisalSystemScoringService = null;
		selectedAppraisalSystemScoring = null;
    }

	public String doEdit(){
		return "";
	}
	
	public String doBack(){
		return "/protected/appraisal/appraisal_system_scoring_view.htm?faces-redirect=true";
	}
	
	public AppraisalSystemScoringService getAppraisalSystemScoringService() {
		return appraisalSystemScoringService;
	}

	public void setAppraisalSystemScoringService(AppraisalSystemScoringService appraisalSystemScoringService) {
		this.appraisalSystemScoringService = appraisalSystemScoringService;
	}

	public AppraisalSystemScoring getSelectedAppraisalSystemScoring() {
		return selectedAppraisalSystemScoring;
	}

	public void setSelectedAppraisalSystemScoring(AppraisalSystemScoring selectedAppraisalSystemScoring) {
		this.selectedAppraisalSystemScoring = selectedAppraisalSystemScoring;
	}

	public AppraisalSystemScoringIndexService getAppraisalSystemScoringIndexService() {
		return appraisalSystemScoringIndexService;
	}

	public void setAppraisalSystemScoringIndexService(
			AppraisalSystemScoringIndexService appraisalSystemScoringIndexService) {
		this.appraisalSystemScoringIndexService = appraisalSystemScoringIndexService;
	}

	public List<AppraisalSystemScoringIndex> getListAppraisalSystemScoring() {
		return listAppraisalSystemScoring;
	}

	public void setListAppraisalSystemScoring(List<AppraisalSystemScoringIndex> listAppraisalSystemScoring) {
		this.listAppraisalSystemScoring = listAppraisalSystemScoring;
	}
	
	
	
	
}
