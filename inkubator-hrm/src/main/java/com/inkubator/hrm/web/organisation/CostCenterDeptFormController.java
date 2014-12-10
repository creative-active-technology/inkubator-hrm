package com.inkubator.hrm.web.organisation;

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
import com.inkubator.hrm.entity.CostCenterDept;
import com.inkubator.hrm.service.CostCenterDeptService;
import com.inkubator.hrm.web.model.CostCenterDeptModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "costCenterDeptFormController")
@ViewScoped
public class CostCenterDeptFormController extends BaseController {

    private CostCenterDeptModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{costCenterDeptService}")
    private CostCenterDeptService costCenterDeptService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new CostCenterDeptModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                CostCenterDept costCenterDept = costCenterDeptService.getEntiyByPK(Long.parseLong(param));
                if (costCenterDept != null) {
                    getViewModelFromEntity(costCenterDept);
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	costCenterDeptService = null;
        model = null;
        isUpdate = null;
    }

	public CostCenterDeptModel getModel() {
		return model;
	}

	public void setModel(CostCenterDeptModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public CostCenterDeptService getCostCenterDeptService() {
		return costCenterDeptService;
	}

	public void setCostCenterDeptService(CostCenterDeptService costCenterDeptService) {
		this.costCenterDeptService = costCenterDeptService;
	}

	public void doSave() {
        CostCenterDept costCenterDept = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
            	costCenterDeptService.update(costCenterDept);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	costCenterDeptService.save(costCenterDept);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private CostCenterDept getEntityFromViewModel(CostCenterDeptModel model) {
    	CostCenterDept costCenterDept = new CostCenterDept();
        if (model.getId() != null) {
        	costCenterDept.setId(model.getId());
        }
        costCenterDept.setCode(model.getCode());
        costCenterDept.setName(model.getName());
        costCenterDept.setDescription(model.getDescription());
        return costCenterDept;
    }
    
    private void getViewModelFromEntity(CostCenterDept costCenterDept){
    	model.setId(costCenterDept.getId());
    	model.setCode(costCenterDept.getCode());
    	model.setName(costCenterDept.getName());
    	model.setDescription(costCenterDept.getDescription());
    }
}
