package com.inkubator.hrm.web.reimbursement;

import java.util.List;

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
import com.inkubator.hrm.entity.RmbsSchemaListOfType;
import com.inkubator.hrm.entity.RmbsSchemaListOfTypeId;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.service.RmbsSchemaListOfTypeService;
import com.inkubator.hrm.service.RmbsTypeService;
import com.inkubator.hrm.web.model.RmbsSchemaListOfTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsSchemaOfTypeFormController")
@ViewScoped
public class RmbsSchemaOfTypeFormController extends BaseController {

    private RmbsSchemaListOfTypeModel model;
    private Boolean isUpdate;
    private List<RmbsType> listRmbsType;
    @ManagedProperty(value = "#{rmbsTypeService}")
    private RmbsTypeService rmbsTypeService;
    @ManagedProperty(value = "#{rmbsSchemaListOfTypeService}")
    private RmbsSchemaListOfTypeService rmbsSchemaListOfTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	String rmbsSchemaId = FacesUtil.getRequestParameter("rmbsSchemaId");
	        String rmbsTypeId = FacesUtil.getRequestParameter("rmbsTypeId");
        	listRmbsType = rmbsTypeService.getAllDataByStatusActive();
	        model = new RmbsSchemaListOfTypeModel();
	        model.setRmbsSchemaId(Long.parseLong(rmbsSchemaId));
	        isUpdate = Boolean.FALSE;	        
	        
			if (StringUtils.isNumeric(rmbsTypeId) && StringUtils.isNumeric(rmbsSchemaId)) {
				RmbsSchemaListOfType rmbsSchemaListOfType = rmbsSchemaListOfTypeService.getEntityByPk(new RmbsSchemaListOfTypeId(Long.parseLong(rmbsTypeId), Long.parseLong(rmbsSchemaId)));
				if (rmbsSchemaListOfType != null) {
					this.getModelFromEntity(rmbsSchemaListOfType);
					isUpdate = Boolean.TRUE;
				}
			}
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsTypeService = null;
        model = null;
		isUpdate = null;
		listRmbsType = null;
		rmbsSchemaListOfTypeService = null;
	}

    public void doSave() {
    	RmbsSchemaListOfType rmbsSchemaListOfType = getEntityFromModel(model);
        try {
            if (isUpdate) {
            	rmbsSchemaListOfTypeService.update(rmbsSchemaListOfType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	rmbsSchemaListOfTypeService.save(rmbsSchemaListOfType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private RmbsSchemaListOfType getEntityFromModel(RmbsSchemaListOfTypeModel m) {
    	RmbsSchemaListOfType rmbsSchemaListOfType = new RmbsSchemaListOfType();
    	rmbsSchemaListOfType.setId(new RmbsSchemaListOfTypeId(m.getRmbsTypeId(),m.getRmbsSchemaId()));
    	rmbsSchemaListOfType.setLimitPerClaim(m.getLimitPerClaim());
    	rmbsSchemaListOfType.setMaxPerMonth(m.getMaxPerMonth());
    	rmbsSchemaListOfType.setPeriodMethod(m.getPeriodMethod());
        return rmbsSchemaListOfType;
    }
    
    private void getModelFromEntity(RmbsSchemaListOfType entity){
    	model.setRmbsSchemaId(entity.getId().getRmbsSchemaId());
    	model.setRmbsTypeId(entity.getId().getRmbsTypeId());
    	model.setLimitPerClaim(entity.getLimitPerClaim());
    	model.setMaxPerMonth(entity.getMaxPerMonth());
    	model.setPeriodMethod(entity.getPeriodMethod());
    }

	public RmbsSchemaListOfTypeModel getModel() {
		return model;
	}

	public void setModel(RmbsSchemaListOfTypeModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public List<RmbsType> getListRmbsType() {
		return listRmbsType;
	}

	public void setListRmbsType(List<RmbsType> listRmbsType) {
		this.listRmbsType = listRmbsType;
	}

	public RmbsTypeService getRmbsTypeService() {
		return rmbsTypeService;
	}

	public void setRmbsTypeService(RmbsTypeService rmbsTypeService) {
		this.rmbsTypeService = rmbsTypeService;
	}

	public RmbsSchemaListOfTypeService getRmbsSchemaListOfTypeService() {
		return rmbsSchemaListOfTypeService;
	}

	public void setRmbsSchemaListOfTypeService(
			RmbsSchemaListOfTypeService rmbsSchemaListOfTypeService) {
		this.rmbsSchemaListOfTypeService = rmbsSchemaListOfTypeService;
	}   
    
}
