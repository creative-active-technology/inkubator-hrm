/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.career;

import com.google.gson.Gson;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.CareerEmpElimination;
import com.inkubator.hrm.entity.CareerTerminationType;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.CareerEmpEliminationService;
import com.inkubator.hrm.service.CareerTerminationTypeService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.model.EmpEliminationModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "empEliminationDetailController")
@ViewScoped
public class EmpEliminationDetailController extends BaseController {

    private EmpEliminationModel model;
    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{careerEmpEliminationService}")
    private CareerEmpEliminationService careerEmpEliminationService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{careerTerminationTypeService}")
    private CareerTerminationTypeService careerTerminationTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            model = new EmpEliminationModel();
            String id = FacesUtil.getRequestParameter("execution");
            if(StringUtils.isNotBlank(id)){
            	 selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
                 generateEmpEliminationModel();
                 model.setApprovalStatus(selectedApprovalActivity.getApprovalStatus());
            }
           
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    private void generateEmpEliminationModel() throws Exception{
    	 Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
         CareerEmpElimination selectedCareerEmpElimination = gson.fromJson(selectedApprovalActivity.getPendingData(), CareerEmpElimination.class);
         EmpData empData = empDataService.getByIdWithDetail(selectedCareerEmpElimination.getEmpData().getId());
         CareerTerminationType careerTerminationType = careerTerminationTypeService.getEntiyByPK(selectedCareerEmpElimination.getCareerTerminationType().getId());
         model = empDataService.generateEmpEliminationModelByEmpDataId(empData.getId());
         model.setEmpData(empData);
         model.setJabatanName(empData.getJabatanByJabatanId().getName());
         model.setTerminationTypeId(selectedCareerEmpElimination.getCareerTerminationType().getId());
         model.setTerminationTypeName(careerTerminationType.getName());
         model.setReason(selectedCareerEmpElimination.getReason());
         model.setSeparationPay(selectedCareerEmpElimination.getSeparationPay());
         model.setRemainSeparationPay(selectedCareerEmpElimination.getSeparationPay() - selectedCareerEmpElimination.getPendingLoan());
         model.setEffectiveDate(selectedCareerEmpElimination.getEffectiveDate());
    }

    @PreDestroy
    public void cleanAndExit() {
        careerEmpEliminationService = null;
        selectedApprovalActivity = null;
        approvalActivityService = null;
        empDataService = null;
        model = null;
    }

   
    
    public String doBack() {
        return "/protected/career/emp_elimination_view.htm?faces-redirect=true";
    }
    
    public void setCareerEmpEliminationService(CareerEmpEliminationService careerEmpEliminationService) {
		this.careerEmpEliminationService = careerEmpEliminationService;
	}

	public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(
            ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public EmpEliminationModel getModel() {
		return model;
	}

	public void setModel(EmpEliminationModel model) {
		this.model = model;
	}

	public void setApprovalActivityService(
            ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }


    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

	public void setCareerTerminationTypeService(CareerTerminationTypeService careerTerminationTypeService) {
		this.careerTerminationTypeService = careerTerminationTypeService;
	}
    

}
