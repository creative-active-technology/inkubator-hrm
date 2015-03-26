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
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.entity.RmbsSchemaListOfEmp;
import com.inkubator.hrm.entity.RmbsSchemaListOfEmpId;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RmbsSchemaListOfEmpService;
import com.inkubator.hrm.service.RmbsSchemaService;
import com.inkubator.hrm.web.model.RmbsSchemaListOfEmpModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsSchemaEmpFormController")
@ViewScoped
public class RmbsSchemaEmpFormController extends BaseController {

	private RmbsSchemaListOfEmpId id;
    private RmbsSchemaListOfEmpModel model;
    private EmpData empData;
    private Boolean isUpdate;
    private List<RmbsSchema> listRmbsSchema;
    @ManagedProperty(value = "#{rmbsSchemaService}")
    private RmbsSchemaService rmbsSchemaService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{rmbsSchemaListOfEmpService}")
    private RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	listRmbsSchema = rmbsSchemaService.getAllDataByStatusActive();
	        model = new RmbsSchemaListOfEmpModel();
	        isUpdate = Boolean.FALSE;
	        
	        String param = FacesUtil.getRequestParameter("param");
			if (StringUtils.isNumeric(param)) {
				model.setEmpDataId(Long.parseLong(param));
				empData = empDataService.getByIdWithDetail(Long.parseLong(param));
				RmbsSchemaListOfEmp rmbsSchemaListOfEmp = rmbsSchemaListOfEmpService.getEntityByEmpDataId(Long.parseLong(param));
				if (rmbsSchemaListOfEmp != null) {
					id =  rmbsSchemaListOfEmp.getId();
					this.getModelFromEntity(rmbsSchemaListOfEmp);
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
		rmbsSchemaListOfEmpService = null;
		listRmbsSchema = null;
		empData = null;
		empDataService = null;
		id = null;
	}

    public void doSave() {
    	RmbsSchemaListOfEmp rmbsSchemaListOfEmp = getEntityFromModel(model);
        try {
            if (isUpdate) {
            	rmbsSchemaListOfEmpService.update(id, rmbsSchemaListOfEmp);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	rmbsSchemaListOfEmpService.save(rmbsSchemaListOfEmp);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private RmbsSchemaListOfEmp getEntityFromModel(RmbsSchemaListOfEmpModel m) {
    	RmbsSchemaListOfEmp entity = new RmbsSchemaListOfEmp();
        entity.setId(new RmbsSchemaListOfEmpId(m.getEmpDataId(), m.getRmbsSchemaId()));
        entity.setDescription(m.getDescription());
        entity.setNomorSk(m.getNomorSk());
        return entity;
    }
    
    private void getModelFromEntity(RmbsSchemaListOfEmp entity){
    	model.setEmpDataId(entity.getId().getEmpDataId());
    	model.setRmbsSchemaId(entity.getId().getRmbsSchemaId());
    	model.setNomorSk(entity.getNomorSk());
    	model.setDescription(entity.getDescription());    	
    }

	public RmbsSchemaListOfEmpModel getModel() {
		return model;
	}

	public void setModel(RmbsSchemaListOfEmpModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public List<RmbsSchema> getListRmbsSchema() {
		return listRmbsSchema;
	}

	public void setListRmbsSchema(List<RmbsSchema> listRmbsSchema) {
		this.listRmbsSchema = listRmbsSchema;
	}

	public RmbsSchemaService getRmbsSchemaService() {
		return rmbsSchemaService;
	}

	public void setRmbsSchemaService(RmbsSchemaService rmbsSchemaService) {
		this.rmbsSchemaService = rmbsSchemaService;
	}

	public RmbsSchemaListOfEmpService getRmbsSchemaListOfEmpService() {
		return rmbsSchemaListOfEmpService;
	}

	public void setRmbsSchemaListOfEmpService(
			RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService) {
		this.rmbsSchemaListOfEmpService = rmbsSchemaListOfEmpService;
	}

	public EmpData getEmpData() {
		return empData;
	}

	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}
	
}
