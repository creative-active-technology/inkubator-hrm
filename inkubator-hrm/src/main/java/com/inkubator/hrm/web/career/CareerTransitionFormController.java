/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.career;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CareerEmpStatus;
import com.inkubator.hrm.entity.CareerTerminationType;
import com.inkubator.hrm.entity.CareerTransition;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.SystemCareerConst;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.CareerEmpStatusService;
import com.inkubator.hrm.service.CareerTransitionService;
import com.inkubator.hrm.service.CareerTerminationTypeService;
import com.inkubator.hrm.service.SystemCareerConstService;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.hrm.web.model.CareerTransitionModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "careerTransitionFormController")
@ViewScoped
public class CareerTransitionFormController extends BaseController {
    @ManagedProperty(value = "#{careerTransitionService}")
    private CareerTransitionService careerTransitionService;
    @ManagedProperty(value = "#{careerEmpStatusService}")
    private CareerEmpStatusService careerEmpStatusService;
    @ManagedProperty(value = "#{careerTerminationTypeService}")
    private CareerTerminationTypeService careerTerminationTypeService;
    @ManagedProperty(value = "#{systemCarreerConstService}")
    private SystemCareerConstService systemCarreerConstService;
    @ManagedProperty(value = "#{systemLetterReferenceService}")
    private SystemLetterReferenceService systemLetterReferenceService;
    private CareerTransitionModel careerTransitionModel;
    private Boolean isUpdate;
    private Map<String, Long> dropDownRoleTransitionDetail;
    private Map<String, Long> dropDownSystemLetterReference;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        	careerTransitionModel = new CareerTransitionModel();
        	dropDownSystemLetterReference = new TreeMap<String, Long>();
        	isUpdate = Boolean.FALSE;
        	String careerTransitionId = FacesUtil.getRequestParameter("execution");
        	try {
        		if(StringUtils.isNotEmpty(careerTransitionId)){
        			CareerTransition careerTransition = careerTransitionService.getEntityByPKWithDetail(Long.valueOf(careerTransitionId.substring(1)));
        			if(careerTransition != null){
        				careerTransitionModel = getModelFromEntity(careerTransition);
        				doChangeRoleTransition();
        				isUpdate = Boolean.TRUE;
        			}
        		}
				List<SystemLetterReference> listSystemLetterReference = systemLetterReferenceService.getAllData();
				for(SystemLetterReference systemLetterReference : listSystemLetterReference){
					dropDownSystemLetterReference.put(systemLetterReference.getName(), systemLetterReference.getId());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        careerTransitionModel = null;
        careerTransitionService = null;
        careerEmpStatusService = null;
        careerTerminationTypeService = null;
        systemCarreerConstService = null;
        dropDownRoleTransitionDetail = null;
        systemLetterReferenceService = null;
        dropDownSystemLetterReference = null;
    }

    public void doChangeRoleTransition() throws Exception{
		dropDownRoleTransitionDetail = new TreeMap<String, Long>();
    	if(careerTransitionModel.getRoleTransitionId() == HRMConstant.CAREER_EMPLOYEE_STATUS){
    		List<CareerEmpStatus> listEmpStatus = careerEmpStatusService.getAllData();
    		for(CareerEmpStatus careerEmpStatus : listEmpStatus ){
    			dropDownRoleTransitionDetail.put(careerEmpStatus.getName(), careerEmpStatus.getId());
    		}
    	}else if(careerTransitionModel.getRoleTransitionId() == HRMConstant.CAREER_TERMINATION_TYPE){
    		List<CareerTerminationType> listTerminationType = careerTerminationTypeService.getAllData();
    		for(CareerTerminationType carreerTerminationType : listTerminationType){
    			dropDownRoleTransitionDetail.put(carreerTerminationType.getName(), carreerTerminationType.getId());
    		}
    	}else if(careerTransitionModel.getRoleTransitionId() == HRMConstant.CAREER_TRANSITION){
    		List<SystemCareerConst> listSystemCarrerrConst = systemCarreerConstService.getAllData();
    		for(SystemCareerConst systemCarreerConst : listSystemCarrerrConst){
    			dropDownRoleTransitionDetail.put(systemCarreerConst.getName(), systemCarreerConst.getId());
    		}
    	}
    }
    
    public String doSave(){
        try {
        	CareerTransition careerTransition = getEntityFromViewModel(careerTransitionModel);
            if (isUpdate) {
                careerTransitionService.update(careerTransition);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
            	careerTransitionService.save(careerTransition);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/career/career_transition_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }
    
    public String doBack(){
    	return "/protected/career/career_transition_view.htm?faces-redirect=true";
    }
    
    public void doReset() throws Exception{
    	if(isUpdate){
    		CareerTransition careerTransition = careerTransitionService.getEntityByPKWithDetail(careerTransitionModel.getId());
			if(careerTransition != null){
				careerTransitionModel = getModelFromEntity(careerTransition);
				doChangeRoleTransition();
			}
    	}else{
    		careerTransitionModel.setTransitionCode(null);
    		careerTransitionModel.setTransitionName(null);
    		careerTransitionModel.setDescription(null);
    		careerTransitionModel.setRoleTransitionDetailId(null);
    		careerTransitionModel.setRoleTransitionId(null);
    		careerTransitionModel.setSystemLetterReferenceId(null);
    	}
    }
    
    public CareerTransitionModel getModelFromEntity(CareerTransition careerTransition){
    	CareerTransitionModel careerTransitionModel = new CareerTransitionModel();
    	careerTransitionModel.setId(careerTransition.getId());
    	careerTransitionModel.setDescription(careerTransition.getDescription());
    	careerTransitionModel.setSystemLetterReferenceId(careerTransition.getSystemLetterReference().getId());
    	careerTransitionModel.setTransitionCode(careerTransition.getTransitionCode());
    	careerTransitionModel.setTransitionName(careerTransition.getTransitionName());
    	careerTransitionModel.setRoleTransitionId(careerTransition.getTransitionRole());
    	if(careerTransition.getTransitionRole() == HRMConstant.CAREER_EMPLOYEE_STATUS){
    		careerTransitionModel.setRoleTransitionDetailId(careerTransition.getCareerEmpStatus().getId());
    	}else if(careerTransition.getTransitionRole() == HRMConstant.CAREER_TERMINATION_TYPE){
    		careerTransitionModel.setRoleTransitionDetailId(careerTransition.getCareerTerminationType().getId());
    	}else if(careerTransition.getTransitionRole() == HRMConstant.CAREER_TRANSITION){
    		careerTransitionModel.setRoleTransitionDetailId(careerTransition.getSystemCareerConst().getId());
    	}
    	
    	return careerTransitionModel;
    }
    
    public CareerTransition getEntityFromViewModel(CareerTransitionModel careerTransitionModel){
    	CareerTransition careerTransition = new CareerTransition();
    	if(careerTransitionModel.getId() != null){
			careerTransition.setId(careerTransitionModel.getId());
    	}
    	careerTransition.setTransitionName(careerTransitionModel.getTransitionName());
    	careerTransition.setTransitionCode(careerTransitionModel.getTransitionCode());
    	careerTransition.setTransitionRole(careerTransitionModel.getRoleTransitionId());
    	if(careerTransitionModel.getRoleTransitionId() == HRMConstant.CAREER_EMPLOYEE_STATUS){
    		careerTransition.setCareerEmpStatus(new CareerEmpStatus(careerTransitionModel.getRoleTransitionDetailId()));
    	}else if(careerTransitionModel.getRoleTransitionId() == HRMConstant.CAREER_TERMINATION_TYPE){
    		careerTransition.setCareerTerminationType(new CareerTerminationType(careerTransitionModel.getRoleTransitionDetailId()));
    	}else if(careerTransitionModel.getRoleTransitionId() == HRMConstant.CAREER_TRANSITION){
    		careerTransition.setSystemCareerConst(new SystemCareerConst(careerTransitionModel.getRoleTransitionDetailId()));
    	}
    	
    	careerTransition.setDescription(careerTransitionModel.getDescription());
    	careerTransition.setSystemLetterReference(new SystemLetterReference(careerTransitionModel.getSystemLetterReferenceId()));
    	return careerTransition;
    }
    
	public CareerTransitionService getCareerTransitionService() {
		return careerTransitionService;
	}

	public void setCareerTransitionService(CareerTransitionService careerTransitionService) {
		this.careerTransitionService = careerTransitionService;
	}

	public CareerTransitionModel getCareerTransitionModel() {
		return careerTransitionModel;
	}

	public void setCareerTransitionModel(CareerTransitionModel careerTransitionModel) {
		this.careerTransitionModel = careerTransitionModel;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public CareerEmpStatusService getCareerEmpStatusService() {
		return careerEmpStatusService;
	}

	public void setCareerEmpStatusService(CareerEmpStatusService careerEmpStatusService) {
		this.careerEmpStatusService = careerEmpStatusService;
	}

	public CareerTerminationTypeService getCareerTerminationTypeService() {
		return careerTerminationTypeService;
	}

	public void setCareerTerminationTypeService(CareerTerminationTypeService careerTerminationTypeService) {
		this.careerTerminationTypeService = careerTerminationTypeService;
	}

	public SystemCareerConstService getSystemCarreerConstService() {
		return systemCarreerConstService;
	}

	public void setSystemCarreerConstService(SystemCareerConstService systemCarreerConstService) {
		this.systemCarreerConstService = systemCarreerConstService;
	}

	public Map<String, Long> getDropDownRoleTransitionDetail() {
		return dropDownRoleTransitionDetail;
	}

	public void setDropDownRoleTransitionDetail(Map<String, Long> dropDownRoleTransitionDetail) {
		this.dropDownRoleTransitionDetail = dropDownRoleTransitionDetail;
	}

	public SystemLetterReferenceService getSystemLetterReferenceService() {
		return systemLetterReferenceService;
	}

	public void setSystemLetterReferenceService(SystemLetterReferenceService systemLetterReferenceService) {
		this.systemLetterReferenceService = systemLetterReferenceService;
	}

	public Map<String, Long> getDropDownSystemLetterReference() {
		return dropDownSystemLetterReference;
	}

	public void setDropDownSystemLetterReference(Map<String, Long> dropDownSystemLetterReference) {
		this.dropDownSystemLetterReference = dropDownSystemLetterReference;
	}

	
    
}
