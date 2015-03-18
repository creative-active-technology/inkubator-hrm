package com.inkubator.hrm.web.reimbursement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.service.RmbsSchemaService;
import com.inkubator.hrm.web.model.RmbsSchemaModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsSchemaFormController")
@ViewScoped
public class RmbsSchemaFormController extends BaseController {

    private RmbsSchemaModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{rmbsSchemaService}")
    private RmbsSchemaService rmbsSchemaService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
	        model = new RmbsSchemaModel();
	        isUpdate = Boolean.FALSE;
	        
	        String param = FacesUtil.getRequestParameter("execution").substring(1);
			if (StringUtils.isNumeric(param)) {
				RmbsSchema rmbsSchema = rmbsSchemaService.getEntiyByPK(Long.parseLong(param));
				if (rmbsSchema != null) {
					this.getModelFromEntity(rmbsSchema);
					isUpdate = Boolean.TRUE;
				}
			}
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsSchemaService = null;
        model = null;
		isUpdate = null;
	}

    public String doBack() {
        return "/protected/reimbursement/rmbs_schema_view.htm?faces-redirect=true";
    }
    
    public String doSave() {
    	RmbsSchema rmbsSchema = getEntityFromModel(model);
        try {
            if (isUpdate) {
            	rmbsSchemaService.update(rmbsSchema);
            	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
            	rmbsSchemaService.save(rmbsSchema);
            	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/reimbursement/rmbs_schema_detail.htm?faces-redirect=true&execution=e" + rmbsSchema.getId();
            
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        
        return null;
    }

    private RmbsSchema getEntityFromModel(RmbsSchemaModel m) {
    	RmbsSchema rmbsSchema = new RmbsSchema();
        if (m.getId() != null) {
            rmbsSchema.setId(m.getId());
        }
        rmbsSchema.setCode(m.getCode());
        rmbsSchema.setName(m.getName());
        rmbsSchema.setDescription(m.getDescription());
        rmbsSchema.setNomorSk(m.getNomorSk());
        rmbsSchema.setMaxTotalReimburst(m.getMaxTotalReimburst());
        rmbsSchema.setMaxWithReceiptPerType(m.getMaxWithReceiptPerType());
        rmbsSchema.setMaxWithoutReceiptPerType(m.getMaxWithoutReceiptPerType());
        rmbsSchema.setNoticeForLimit(m.getNoticeForLimit());
        rmbsSchema.setSubmissionDeadline(m.getSubmissionDeadline());
        rmbsSchema.setIsActive(m.getIsActive());
        return rmbsSchema;
    }
    
    private void getModelFromEntity(RmbsSchema entity){
    	model.setId(entity.getId());
    	model.setCode(entity.getCode());
    	model.setName(entity.getName());
    	model.setDescription(entity.getDescription());
    	model.setNomorSk(entity.getNomorSk());
    	model.setMaxTotalReimburst(entity.getMaxTotalReimburst());
    	model.setMaxWithReceiptPerType(entity.getMaxWithReceiptPerType());
    	model.setMaxWithoutReceiptPerType(entity.getMaxWithoutReceiptPerType());
    	model.setNoticeForLimit(entity.getNoticeForLimit());
    	model.setSubmissionDeadline(entity.getSubmissionDeadline());
    	model.setIsActive(entity.getIsActive());    	
    }

	public RmbsSchemaModel getModel() {
		return model;
	}

	public void setModel(RmbsSchemaModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public RmbsSchemaService getRmbsSchemaService() {
		return rmbsSchemaService;
	}

	public void setRmbsSchemaService(RmbsSchemaService rmbsSchemaService) {
		this.rmbsSchemaService = rmbsSchemaService;
	}        
    
}
