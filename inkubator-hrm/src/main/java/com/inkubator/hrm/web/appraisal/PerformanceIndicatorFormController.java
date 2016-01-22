package com.inkubator.hrm.web.appraisal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AppraisalPerformanceGroup;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicator;
import com.inkubator.hrm.entity.Divisi;
import com.inkubator.hrm.entity.SystemScoring;
import com.inkubator.hrm.service.AppraisalPerformanceGroupService;
import com.inkubator.hrm.web.model.AppraisalPerformanceGroupModel;
import com.inkubator.hrm.web.model.AppraisalPerformanceIndicatorModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

@ManagedBean(name = "performanceIndicatorFormController")
@ViewScoped
public class PerformanceIndicatorFormController extends BaseController{
	@ManagedProperty(value = "#{appraisalPerformanceGroupService}")
	private AppraisalPerformanceGroupService appraisalPerformanceGroupService;
	private AppraisalPerformanceGroup selected;
	private AppraisalPerformanceIndicatorModel model;
	private Boolean isUpdate;
	
	@PostConstruct
	@Override
	public void initialization(){
		super.initialization();
		try{
			String appraisalPerformanceGroupId = FacesUtil.getRequestParameter("appraisalPerformanceGroupId");
			String appraisalperformanceIndicatorId = FacesUtil.getRequestParameter("appraisalperformanceIndicatorId");
			model = new AppraisalPerformanceIndicatorModel();
			isUpdate = Boolean.FALSE;
			if( StringUtils.isNotEmpty(appraisalperformanceIndicatorId)){
                AppraisalPerformanceGroup appraisalPerformanceGroup = appraisalPerformanceGroupService.getEntiyByPK(Long.parseLong(appraisalPerformanceGroupId));
                if(appraisalPerformanceGroupId != null){
                    //model = getModelFromEntity(appraisalPerformanceGroup);
                    isUpdate = Boolean.TRUE;
                }
            }
		}catch(Exception ex){
			LOGGER.error("Error ", ex);
		}
	}
	
	@PreDestroy
	private void cleanAndExit(){
		appraisalPerformanceGroupService = null;
		selected = null;
		model = null;
		isUpdate = null;
	}
	
	private AppraisalPerformanceIndicatorModel getModelFromEntity(AppraisalPerformanceIndicator entity){
		AppraisalPerformanceIndicatorModel model = new AppraisalPerformanceIndicatorModel();
		model.setId(entity.getId());
		model.setDescription(entity.getDescription());
		model.setPerformanceGroupId(entity.getPerformanceGroup().getId());
		model.setPerformanceIndicatorCode(entity.getIndicatorCode());
		model.setPerformanceIndicatorName(entity.getIndicatorLabel());
		model.setSystemScoringId(entity.getSystemScoring().getId());
		return model;
	}
	
	private AppraisalPerformanceIndicator getEntityFromViewModel(AppraisalPerformanceIndicatorModel model){
		AppraisalPerformanceIndicator entity = new AppraisalPerformanceIndicator();
		if(model.getId() != null){
			entity.setId(model.getId());
		}
		entity.setIndicatorCode(model.getPerformanceIndicatorCode());
		entity.setIndicatorLabel(model.getPerformanceIndicatorName());
		entity.setDescription(model.getDescription());
		entity.setPerformanceGroup(new AppraisalPerformanceGroup(model.getPerformanceGroupId()));
		entity.setSystemScoring(new SystemScoring(model.getSystemScoringId()));
		entity.setDescription(model.getDescription());
		return entity;
	}
	
	public void doSave(){
        /*AppraisalPerformanceGroup entity = getEntityFromViewModel(model);
        try{
            if(isUpdate){
                appraisalPerformanceGroupService.update(entity);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                appraisalPerformanceGroupService.save(entity);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }*/
    }

	public AppraisalPerformanceGroupService getService() {
		return appraisalPerformanceGroupService;
	}

	public void setService(AppraisalPerformanceGroupService service) {
		this.appraisalPerformanceGroupService = service;
	}

	public AppraisalPerformanceGroup getSelected() {
		return selected;
	}

	public void setSelected(AppraisalPerformanceGroup selected) {
		this.selected = selected;
	}

	public AppraisalPerformanceIndicatorModel getModel() {
		return model;
	}

	public void setModel(AppraisalPerformanceIndicatorModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
}
