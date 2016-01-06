/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.appraisal;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.service.AppraisalPerformanceGroupService;
import com.inkubator.hrm.service.AppraisalProgramService;
import com.inkubator.hrm.service.CareerAwardTypeService;
import com.inkubator.hrm.service.CareerDisciplineTypeService;
import com.inkubator.hrm.web.model.AppraisalProgramModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "appraisalProgramFormController")
@ViewScoped
public class AppraisalProgramFormController extends BaseController{
	
	private Boolean isUpdate;
    private AppraisalProgramModel model;
    
    @ManagedProperty(value = "#{appraisalProgramService}")
    private AppraisalProgramService appraisalProgramService;
    @ManagedProperty(value = "#{careerAwardTypeService}")
    private CareerAwardTypeService careerAwardTypeService;
    @ManagedProperty(value = "#{careerDisciplineTypeService}")
    private CareerDisciplineTypeService careerDisciplineTypeService;
    @ManagedProperty(value = "#{appraisalPerformanceGroupService}")
    private AppraisalPerformanceGroupService appraisalPerformanceGroupService;
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        try{
            model = new AppraisalProgramModel();
            isUpdate = Boolean.FALSE;
            
            String id = FacesUtil.getRequestParameter("execution");
            if(StringUtils.isNotEmpty(id)){
            	AppraisalProgram appraisalProgram = appraisalProgramService.getEntityByIdWithDetail(Long.parseLong(id.substring(1)));
                if(appraisalProgram != null){
                	isUpdate = Boolean.TRUE;
                	model = getModelFromEntity(appraisalProgram);
                }
            }
            
            this.onChangeIsIndiscipline();
            this.onChangeIsAchievement();
            model.setListPerformanceGroup(appraisalPerformanceGroupService.getAllData());
            
        } catch (Exception e){
            LOGGER.error("error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
    	isUpdate = null;
    	model = null;
    	appraisalProgramService = null;
    	careerDisciplineTypeService = null;
    	careerAwardTypeService = null;
    	appraisalPerformanceGroupService = null;
    }
    
    private AppraisalProgramModel getModelFromEntity(AppraisalProgram appraisalProgram) throws Exception{
    	AppraisalProgramModel model = new AppraisalProgramModel();
        model.setId(appraisalProgram.getId());
        model.setCode(appraisalProgram.getCode());
        model.setName(appraisalProgram.getName());
        model.setDescription(appraisalProgram.getDescription());
        model.setEvalStartDate(appraisalProgram.getEvalStartDate());
        model.setEvalEndDate(appraisalProgram.getEvalEndDate());
        model.setIsAchievement(appraisalProgram.getIsAchievement());
        model.setIsGapCompetency(appraisalProgram.getIsGapCompetency());
        model.setIsIndiscipline(appraisalProgram.getIsIndiscipline());
        model.setIsPerformanceScoring(appraisalProgram.getIsPerformanceScoring());
        model.setAppraisalPerformanceGroupId(appraisalProgram.getAppraisalPerformanceGroup().getId());
        
        Map<Long, BigDecimal> achievements = new HashMap<>();
        appraisalProgram.getAppraisalAchievementPrograms().forEach(entity -> achievements.put(entity.getCareerAwardType().getId(), new BigDecimal(entity.getScore())));
        model.setListAchievementScore(achievements);
        
        Map<Long, BigDecimal> indisciplines = new HashMap<>();
        appraisalProgram.getAppraisalIndisciplinePrograms().forEach(entity -> indisciplines.put(entity.getCareerDisciplineType().getId(), new BigDecimal(entity.getScore())));
        model.setListIndisciplineScore(indisciplines);
        
        return model;
    }
    
    public String doSave(){
    	try {
			if (isUpdate) {
				appraisalProgramService.update(model);
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
	                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
			    return "/protected/appraisal/appraisal_program_view.htm?faces-redirect=true";
			    
			} else {
				appraisalProgramService.save(model);
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
	                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				return "/protected/appraisal/appraisal_program_view.htm?faces-redirect=true";
			}
		} catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }catch (Exception e) {
        	LOGGER.error("error", e);
		}
    	return null;
    }
    
    public void onChangeIsIndiscipline(){
    	try {
    		if(model.getIsIndiscipline()) {
	            model.setListCareerDiscipline(careerDisciplineTypeService.getAllData());
    		} else {
    			model.getListCareerDiscipline().clear();
    		}
    	}catch (Exception e) {
    		LOGGER.error("error", e);
		}
    }
    
    public void onChangeIsAchievement(){
    	try {
    		if(model.getIsAchievement()) {
		    	model.setListCareerAward(careerAwardTypeService.getAllData());
    		} else {
    			model.getListCareerAward().clear();
    		}
    	}catch (Exception e) {
    		LOGGER.error("error", e);
		}
    }
    
	public String doBack(){
		return "/protected/appraisal/appraisal_program_view.htm?faces-redirect=true";
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public AppraisalProgramModel getModel() {
		return model;
	}

	public void setModel(AppraisalProgramModel model) {
		this.model = model;
	}

	public AppraisalProgramService getAppraisalProgramService() {
		return appraisalProgramService;
	}

	public void setAppraisalProgramService(AppraisalProgramService appraisalProgramService) {
		this.appraisalProgramService = appraisalProgramService;
	}

	public CareerAwardTypeService getCareerAwardTypeService() {
		return careerAwardTypeService;
	}

	public void setCareerAwardTypeService(CareerAwardTypeService careerAwardTypeService) {
		this.careerAwardTypeService = careerAwardTypeService;
	}

	public CareerDisciplineTypeService getCareerDisciplineTypeService() {
		return careerDisciplineTypeService;
	}

	public void setCareerDisciplineTypeService(CareerDisciplineTypeService careerDisciplineTypeService) {
		this.careerDisciplineTypeService = careerDisciplineTypeService;
	}

	public AppraisalPerformanceGroupService getAppraisalPerformanceGroupService() {
		return appraisalPerformanceGroupService;
	}

	public void setAppraisalPerformanceGroupService(AppraisalPerformanceGroupService appraisalPerformanceGroupService) {
		this.appraisalPerformanceGroupService = appraisalPerformanceGroupService;
	}
	
}
