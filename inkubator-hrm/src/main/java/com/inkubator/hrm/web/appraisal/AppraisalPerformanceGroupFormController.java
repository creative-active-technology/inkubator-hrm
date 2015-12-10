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
import com.inkubator.hrm.entity.Divisi;
import com.inkubator.hrm.service.AppraisalPerformanceGroupService;
import com.inkubator.hrm.web.model.AppraisalPerformanceGroupModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

@ManagedBean(name = "appraisalPerformanceGroupFormController")
@ViewScoped
public class AppraisalPerformanceGroupFormController extends BaseController{
	@ManagedProperty(value = "#{appraisalPerformanceGroupService}")
	private AppraisalPerformanceGroupService service;
	private AppraisalPerformanceGroup selected;
	private AppraisalPerformanceGroupModel model;
	private Boolean isUpdate;
	
	@PostConstruct
	@Override
	public void initialization(){
		super.initialization();
		try{
			String appraisalPerformanceGroupId = FacesUtil.getRequestParameter("appraisalPerformanceGroupId");
			model = new AppraisalPerformanceGroupModel();
			isUpdate = Boolean.FALSE;
			if( StringUtils.isNotEmpty(appraisalPerformanceGroupId)){
                AppraisalPerformanceGroup appraisalPerformanceGroup = service.getEntiyByPK(Long.parseLong(appraisalPerformanceGroupId));
                if(appraisalPerformanceGroupId != null){
                    model = getModelFromEntity(appraisalPerformanceGroup);
                    isUpdate = Boolean.TRUE;
                }
            }
		}catch(Exception ex){
			LOGGER.error("Error ", ex);
		}
	}
	
	@PreDestroy
	private void cleanAndExit(){
		service = null;
		selected = null;
		model = null;
		isUpdate = null;
	}
	
	private AppraisalPerformanceGroupModel getModelFromEntity(AppraisalPerformanceGroup entity){
		AppraisalPerformanceGroupModel model = new AppraisalPerformanceGroupModel();
		model.setId(entity.getId());
		model.setCode(entity.getCode());
		model.setName(entity.getName());
		model.setOrientation(entity.getOrientation());
		model.setAppraiser(entity.getAppraiser());
		model.setDescription(entity.getDescription());
		return model;
	}
	
	private AppraisalPerformanceGroup getEntityFromViewModel(AppraisalPerformanceGroupModel model){
		AppraisalPerformanceGroup entity = new AppraisalPerformanceGroup();
		if(model.getId() != null){
			entity.setId(model.getId());
		}
		entity.setCode(model.getCode());
		entity.setName(model.getName());
		entity.setAppraiser(model.getAppraiser());
		entity.setOrientation(model.getOrientation());
		entity.setDescription(model.getDescription());
		return entity;
	}
	
	public void doSave(){
        AppraisalPerformanceGroup entity = getEntityFromViewModel(model);
        try{
            if(isUpdate){
                service.update(entity);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(entity);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }

	public AppraisalPerformanceGroupService getService() {
		return service;
	}

	public void setService(AppraisalPerformanceGroupService service) {
		this.service = service;
	}

	public AppraisalPerformanceGroup getSelected() {
		return selected;
	}

	public void setSelected(AppraisalPerformanceGroup selected) {
		this.selected = selected;
	}

	public AppraisalPerformanceGroupModel getModel() {
		return model;
	}

	public void setModel(AppraisalPerformanceGroupModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
}
