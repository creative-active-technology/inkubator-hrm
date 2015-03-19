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
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.service.RmbsTypeService;
import com.inkubator.hrm.web.model.RmbsTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsTypeFormController")
@ViewScoped
public class RmbsTypeFormController extends BaseController {

    private RmbsTypeModel model;
    private Boolean isUpdate;
    private List<CostCenter> listCostCenter;
    @ManagedProperty(value = "#{rmbsTypeService}")
    private RmbsTypeService rmbsTypeService;
    @ManagedProperty(value = "#{costCenterService}")
    private CostCenterService costCenterService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
	        listCostCenter = costCenterService.getAllData();
	        model = new RmbsTypeModel();
	        isUpdate = Boolean.FALSE;
	        
	        String param = FacesUtil.getRequestParameter("param");
			if (StringUtils.isNumeric(param)) {
				RmbsType rmbsType = rmbsTypeService.getEntiyByPK(Long.parseLong(param));
				if (rmbsType != null) {
					this.getModelFromEntity(rmbsType);
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
		costCenterService = null;
		listCostCenter = null;
	}

    public void doSave() {
    	RmbsType rmbsType = getEntityFromModel(model);
        try {
            if (isUpdate) {
            	rmbsTypeService.update(rmbsType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	rmbsTypeService.save(rmbsType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private RmbsType getEntityFromModel(RmbsTypeModel m) {
    	RmbsType rmbsType = new RmbsType();
        if (m.getId() != null) {
            rmbsType.setId(m.getId());
        }
        rmbsType.setCode(m.getCode());
        rmbsType.setName(m.getName());
        rmbsType.setDescription(m.getDescription());
        rmbsType.setCostCenter(new CostCenter(m.getCostCenterId()));
        rmbsType.setDaysOfAvailable(m.getDaysOfAvailable());
        rmbsType.setIsActive(m.getIsActive());
        rmbsType.setRoundDigit(m.getRoundDigit());
        return rmbsType;
    }
    
    private void getModelFromEntity(RmbsType entity){
    	model.setId(entity.getId());
    	model.setCode(entity.getCode());
    	model.setName(entity.getName());
    	model.setDaysOfAvailable(entity.getDaysOfAvailable());
    	model.setDescription(entity.getDescription());
    	model.setIsActive(entity.getIsActive());
    	model.setRoundDigit(entity.getRoundDigit());
    	model.setCostCenterId(entity.getCostCenter().getId());
    }

	public RmbsTypeModel getModel() {
		return model;
	}

	public void setModel(RmbsTypeModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public RmbsTypeService getRmbsTypeService() {
		return rmbsTypeService;
	}

	public void setRmbsTypeService(RmbsTypeService rmbsTypeService) {
		this.rmbsTypeService = rmbsTypeService;
	}

	public List<CostCenter> getListCostCenter() {
		return listCostCenter;
	}

	public void setListCostCenter(List<CostCenter> listCostCenter) {
		this.listCostCenter = listCostCenter;
	}

	public CostCenterService getCostCenterService() {
		return costCenterService;
	}

	public void setCostCenterService(CostCenterService costCenterService) {
		this.costCenterService = costCenterService;
	}    
    
}
