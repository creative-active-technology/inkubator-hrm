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
import com.inkubator.hrm.entity.AppraisalCompetencyGroup;
import com.inkubator.hrm.entity.AppraisalCompetencyUnit;
import com.inkubator.hrm.service.AppraisalCompetencyGroupService;
import com.inkubator.hrm.service.AppraisalCompetencyTypeService;
import com.inkubator.hrm.service.AppraisalCompetencyUnitService;
import com.inkubator.hrm.web.model.AppraisalCompetencyUnitModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "competencyUnitFormController")
@ViewScoped
public class CompetencyUnitFormController extends BaseController {

    private AppraisalCompetencyUnitModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{appraisalCompetencyGroupService}")
    private AppraisalCompetencyGroupService appraisalCompetencyGroupService;
    @ManagedProperty(value = "#{appraisalCompetencyTypeService}")
    private AppraisalCompetencyTypeService appraisalCompetencyTypeService;
    @ManagedProperty(value = "#{appraisalCompetencyUnitService}")
    private AppraisalCompetencyUnitService appraisalCompetencyUnitService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
	        model = new AppraisalCompetencyUnitModel();
	        model.setListCompetencyType(appraisalCompetencyTypeService.getAllData());
	        isUpdate = Boolean.FALSE;
	        
	        String param = FacesUtil.getRequestParameter("param");
			if (StringUtils.isNumeric(param)) {
				AppraisalCompetencyUnit appraisalCompetencyUnit = appraisalCompetencyUnitService.getEntityByIdWithDetail(Long.parseLong(param));
				if (appraisalCompetencyUnit != null) {
					this.getModelFromEntity(appraisalCompetencyUnit);
					isUpdate = Boolean.TRUE;
				}
			}
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	model = null;
    	isUpdate = null;
    	appraisalCompetencyGroupService = null;
    	appraisalCompetencyTypeService = null;
    	appraisalCompetencyUnitService = null;
	}

    public void doSave() {
    	AppraisalCompetencyUnit appraisalCompetencyUnit = getEntityFromModel(model);
        try {
            if (isUpdate) {
            	appraisalCompetencyUnitService.update(appraisalCompetencyUnit);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	appraisalCompetencyUnitService.save(appraisalCompetencyUnit);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void onChangeCompetencyType(){
    	try {
			model.setListCompetencyGroup(appraisalCompetencyGroupService.getAllDataByCompetencyTypeId(model.getCompetencyTypeId()));
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    }

    private AppraisalCompetencyUnit getEntityFromModel(AppraisalCompetencyUnitModel m) {
    	AppraisalCompetencyUnit entity = new AppraisalCompetencyUnit();
        if (m.getId() != null) {
            entity.setId(m.getId());
        }
        entity.setName(m.getName());
        entity.setDescription(m.getDescription());
        entity.setCompetencyGroup(new AppraisalCompetencyGroup(model.getCompetencyGroupId()));
        return entity;
    }
    
    private void getModelFromEntity(AppraisalCompetencyUnit entity){
    	model.setId(entity.getId());
    	model.setName(entity.getName());
    	model.setDescription(entity.getDescription());
    	model.setCompetencyGroupId(entity.getCompetencyGroup().getId());
    	model.setCompetencyTypeId(entity.getCompetencyGroup().getCompetencyType().getId());
    	
    	this.onChangeCompetencyType();
    }

	public AppraisalCompetencyUnitModel getModel() {
		return model;
	}

	public void setModel(AppraisalCompetencyUnitModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public AppraisalCompetencyGroupService getAppraisalCompetencyGroupService() {
		return appraisalCompetencyGroupService;
	}

	public void setAppraisalCompetencyGroupService(AppraisalCompetencyGroupService appraisalCompetencyGroupService) {
		this.appraisalCompetencyGroupService = appraisalCompetencyGroupService;
	}

	public AppraisalCompetencyTypeService getAppraisalCompetencyTypeService() {
		return appraisalCompetencyTypeService;
	}

	public void setAppraisalCompetencyTypeService(AppraisalCompetencyTypeService appraisalCompetencyTypeService) {
		this.appraisalCompetencyTypeService = appraisalCompetencyTypeService;
	}

	public AppraisalCompetencyUnitService getAppraisalCompetencyUnitService() {
		return appraisalCompetencyUnitService;
	}

	public void setAppraisalCompetencyUnitService(AppraisalCompetencyUnitService appraisalCompetencyUnitService) {
		this.appraisalCompetencyUnitService = appraisalCompetencyUnitService;
	}
    
}
