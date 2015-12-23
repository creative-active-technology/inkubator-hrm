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
import com.inkubator.hrm.entity.AppraisalCompetencyUnit;
import com.inkubator.hrm.entity.AppraisalCompetencyUnitIndicator;
import com.inkubator.hrm.service.AppraisalCompetencyUnitIndicatorService;
import com.inkubator.hrm.service.AppraisalCompetencyUnitService;
import com.inkubator.hrm.web.model.AppraisalCompetencyUnitIndicatorModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "competencyUnitIndicatorFormController")
@ViewScoped
public class CompetencyUnitIndicatorFormController extends BaseController {

    private AppraisalCompetencyUnitIndicatorModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{appraisalCompetencyUnitIndicatorService}")
    private AppraisalCompetencyUnitIndicatorService appraisalCompetencyUnitIndicatorService;
    @ManagedProperty(value = "#{appraisalCompetencyUnitService}")
    private AppraisalCompetencyUnitService appraisalCompetencyUnitService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
	        model = new AppraisalCompetencyUnitIndicatorModel();
	        isUpdate = Boolean.FALSE;
	        
	        String competencyUnitIndicatorId = FacesUtil.getRequestParameter("paramCompetencyUnitIndicatorId");
			if (StringUtils.isNumeric(competencyUnitIndicatorId)) {
				AppraisalCompetencyUnitIndicator appraisalCompetencyUnitIndicator = appraisalCompetencyUnitIndicatorService.getEntityByIdWithDetail(Long.parseLong(competencyUnitIndicatorId));
				if (appraisalCompetencyUnitIndicator != null) {
					this.getModelFromEntity(appraisalCompetencyUnitIndicator);
					isUpdate = Boolean.TRUE;
				}
			}
			
			String competencyUnitId = FacesUtil.getRequestParameter("paramCompetencyUnitId");
	        AppraisalCompetencyUnit competencyUnit = appraisalCompetencyUnitService.getEntityByIdWithDetail(Long.parseLong(competencyUnitId));
	        model.setCompetencyUnitId(competencyUnit.getId());
	        model.setCompetencyUnitName(competencyUnit.getName());
	    	model.setCompetencyGroupName(competencyUnit.getCompetencyGroup().getName());
	    	model.setCompetencyTypeName(competencyUnit.getCompetencyGroup().getCompetencyType().getName());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	model = null;
    	isUpdate = null;
    	appraisalCompetencyUnitIndicatorService = null;
    	appraisalCompetencyUnitService = null;
	}

    public void doSave() {
    	AppraisalCompetencyUnitIndicator appraisalCompetencyUnitIndicator = getEntityFromModel(model);
        try {
            if (isUpdate) {
            	appraisalCompetencyUnitIndicatorService.update(appraisalCompetencyUnitIndicator);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	appraisalCompetencyUnitIndicatorService.save(appraisalCompetencyUnitIndicator);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private AppraisalCompetencyUnitIndicator getEntityFromModel(AppraisalCompetencyUnitIndicatorModel m) {
    	AppraisalCompetencyUnitIndicator entity = new AppraisalCompetencyUnitIndicator();
        if (m.getId() != null) {
            entity.setId(m.getId());
        }
        entity.setIndicator(m.getIndicator());
        entity.setLevelIndex(m.getLevelIndex());
        entity.setAppraisalCompetencyUnit(new AppraisalCompetencyUnit(m.getCompetencyUnitId()));
        return entity;
    }
    
    private void getModelFromEntity(AppraisalCompetencyUnitIndicator entity){
    	model.setId(entity.getId());
    	model.setIndicator(entity.getIndicator());
    	model.setLevelIndex(entity.getLevelIndex());
    }

	public AppraisalCompetencyUnitIndicatorModel getModel() {
		return model;
	}

	public void setModel(AppraisalCompetencyUnitIndicatorModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public AppraisalCompetencyUnitIndicatorService getAppraisalCompetencyUnitIndicatorService() {
		return appraisalCompetencyUnitIndicatorService;
	}

	public void setAppraisalCompetencyUnitIndicatorService(
			AppraisalCompetencyUnitIndicatorService appraisalCompetencyUnitIndicatorService) {
		this.appraisalCompetencyUnitIndicatorService = appraisalCompetencyUnitIndicatorService;
	}

	public AppraisalCompetencyUnitService getAppraisalCompetencyUnitService() {
		return appraisalCompetencyUnitService;
	}

	public void setAppraisalCompetencyUnitService(AppraisalCompetencyUnitService appraisalCompetencyUnitService) {
		this.appraisalCompetencyUnitService = appraisalCompetencyUnitService;
	}

}
