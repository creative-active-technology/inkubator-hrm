package com.inkubator.hrm.web.recruitment;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.primefaces.event.RowEditEvent;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
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
import com.inkubator.webcore.util.MessagesResourceUtil;

import ch.lambdaj.Lambda;

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
	private List<SelectionApplicantSchedulleDetailRealizationModel> copyListModel;
	
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
	        	copyListModel = new ArrayList<>();
	        	for(SelectionApplicantSchedulleDetailRealizationModel model : listModel){
	        		copyListModel.add((SelectionApplicantSchedulleDetailRealizationModel) model.clone());
	        	}
	        }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	applicant = null;
    	selectionApplicantSchedulle = null;
    	listModel = null;
    	copyListModel = null;
    	recruitSelectionApplicantSchedulleDetailRealizationService = null;
    	empDataService = null;
    	recruitApplicantService = null;
    	recruitSelectionApplicantSchedulleService = null;
    }
    
    public String doBack(){
    	return "/protected/recruitment/selection_applicant_realization_view.htm?faces-redirect=true";
    }
    
    public String doSave(){
    	try {
    		recruitSelectionApplicantSchedulleDetailRealizationService.saveOrUpdate(listModel);
    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
    		return "/protected/recruitment/selection_applicant_realization_view.htm?faces-redirect=true";
    	} catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }
    
    public void onRowEdit(RowEditEvent event) throws CloneNotSupportedException {
    	SelectionApplicantSchedulleDetailRealizationModel model = (SelectionApplicantSchedulleDetailRealizationModel) event.getObject();
    	Boolean isValidationFailed = Boolean.FALSE;
    	
    	if(StringUtils.equals(model.getStatus(), HRMConstant.SELECTION_APPLICANT_STATUS_NEW)){
    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "applicant_realization.error_status_selection_stages_should_not_new", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
    		isValidationFailed = Boolean.TRUE;
    	} else if(model.getRealizationDate() == null || model.getRealizationTimeStart() == null || model.getRealizationTimeEnd() == null || 
    			StringUtils.isEmpty(model.getRealizationRoom()) || model.getScoringByEmpData() == null || model.getScoringDate() == null) {
    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "applicant_realization.error_field_should_not_be_empty", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
    		isValidationFailed = Boolean.TRUE;		
    	} else if(model.getRealizationDate().after(model.getScoringDate())){
    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "applicant_realization.error_execution_date_smaller_than_assessed_date", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
    		isValidationFailed = Boolean.TRUE;
    	} else if(model.getRealizationTimeStart().after(model.getRealizationTimeEnd())){
    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "applicant_realization.error_execution_time_start_smaller_than_execution_time_end", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
    		isValidationFailed = Boolean.TRUE;
    	} 
    	
    	if(isValidationFailed){
    		FacesContext.getCurrentInstance().validationFailed();
    	} else {
    		SelectionApplicantSchedulleDetailRealizationModel replaceModel = Lambda.selectFirst(copyListModel, Lambda.having(Lambda.on(SelectionApplicantSchedulleDetailRealizationModel.class).getSelectionTypeName(), Matchers.equalTo(model.getSelectionTypeName())));
    		int indexOf = copyListModel.indexOf(replaceModel);
    		copyListModel.set(indexOf, (SelectionApplicantSchedulleDetailRealizationModel) model.clone());
    	}
    }
    
    public void onRowCancel(RowEditEvent event) throws CloneNotSupportedException {    	
    	SelectionApplicantSchedulleDetailRealizationModel model = ((SelectionApplicantSchedulleDetailRealizationModel) event.getObject());
    	SelectionApplicantSchedulleDetailRealizationModel resetModel = Lambda.selectFirst(copyListModel, Lambda.having(Lambda.on(SelectionApplicantSchedulleDetailRealizationModel.class).getSelectionTypeName(), Matchers.equalTo(model.getSelectionTypeName())));
    	int indexOf = listModel.indexOf(model);
    	listModel.set(indexOf, (SelectionApplicantSchedulleDetailRealizationModel) resetModel.clone());
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
