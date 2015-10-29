package com.inkubator.hrm.web.recruitment;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleDetailRealizationService;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleService;
import com.inkubator.hrm.web.model.SelectionApplicantSchedulleDetailRealizationModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "selectionApplicantRealizationFormController")
@ViewScoped
public class SelectionApplicantRealizationFormController extends BaseController {

	private RecruitApplicant applicant;
	private RecruitSelectionApplicantSchedulle selectionApplicantSchedulle;
	private List<SelectionApplicantSchedulleDetailRealizationModel> listModel;
	
	@ManagedProperty(value = "#{recruitSelectionApplicantSchedulleDetailRealizationService}")
    private RecruitSelectionApplicantSchedulleDetailRealizationService recruitSelectionApplicantSchedulleDetailRealizationService;
	@ManagedProperty(value = "#{recruitApplicantService}")
    private RecruitApplicantService recruitApplicantService;
	@ManagedProperty(value = "#{recruitSelectionApplicantSchedulleService}")
    private RecruitSelectionApplicantSchedulleService recruitSelectionApplicantSchedulleService;
	@ManagedProperty(value = "#{empDataService}")
	private EmpDataService empDataService;
	
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
	        String applicantId = FacesUtil.getRequestParameter("execution");
	        String selectionScheduleId = FacesUtil.getRequestParameter("schedule");
	        
	        if(StringUtils.isNotEmpty(applicantId) && StringUtils.isNotEmpty(selectionScheduleId)){
	        	applicant = recruitApplicantService.getEntityByPkWithDetail(Long.parseLong(applicantId.substring(1)));
	        	selectionApplicantSchedulle = recruitSelectionApplicantSchedulleService.getEntityByPkWithDetail(Long.parseLong(selectionScheduleId.substring(1)));
	        	listModel = recruitSelectionApplicantSchedulleDetailRealizationService.getAllDataSelectionScheduleRealization(applicant.getId(), selectionApplicantSchedulle.getId());
	        }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	
    }
    
    public String doBack(){
    	return "/protected/recruitment/selection_applicant_realization_view.htm?faces-redirect=true";
    }
    
    public String doSave(){
    	
    	return "";
    }
    
    public List<EmpData> doAutoCompleteEmployee(String param){
		List<EmpData> empDatas = new ArrayList<EmpData>();
		try {
			empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
		return empDatas;
	}
    
	public List<SelectionApplicantSchedulleDetailRealizationModel> getListModel() {
		return listModel;
	}

	public void setListModel(List<SelectionApplicantSchedulleDetailRealizationModel> listModel) {
		this.listModel = listModel;
	}

	public RecruitSelectionApplicantSchedulleDetailRealizationService getRecruitSelectionApplicantSchedulleDetailRealizationService() {
		return recruitSelectionApplicantSchedulleDetailRealizationService;
	}

	public void setRecruitSelectionApplicantSchedulleDetailRealizationService(
			RecruitSelectionApplicantSchedulleDetailRealizationService recruitSelectionApplicantSchedulleDetailRealizationService) {
		this.recruitSelectionApplicantSchedulleDetailRealizationService = recruitSelectionApplicantSchedulleDetailRealizationService;
	}

	public RecruitApplicant getApplicant() {
		return applicant;
	}

	public void setApplicant(RecruitApplicant applicant) {
		this.applicant = applicant;
	}

	public RecruitSelectionApplicantSchedulle getSelectionApplicantSchedulle() {
		return selectionApplicantSchedulle;
	}

	public void setSelectionApplicantSchedulle(RecruitSelectionApplicantSchedulle selectionApplicantSchedulle) {
		this.selectionApplicantSchedulle = selectionApplicantSchedulle;
	}

	public RecruitApplicantService getRecruitApplicantService() {
		return recruitApplicantService;
	}

	public void setRecruitApplicantService(RecruitApplicantService recruitApplicantService) {
		this.recruitApplicantService = recruitApplicantService;
	}

	public RecruitSelectionApplicantSchedulleService getRecruitSelectionApplicantSchedulleService() {
		return recruitSelectionApplicantSchedulleService;
	}

	public void setRecruitSelectionApplicantSchedulleService(
			RecruitSelectionApplicantSchedulleService recruitSelectionApplicantSchedulleService) {
		this.recruitSelectionApplicantSchedulleService = recruitSelectionApplicantSchedulleService;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}
	
}
