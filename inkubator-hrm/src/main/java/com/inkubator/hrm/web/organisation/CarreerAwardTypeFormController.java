package com.inkubator.hrm.web.organisation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.inkubator.hrm.entity.CarreerAwardType;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.CarreerAwardTypeService;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.hrm.web.model.CarreerAwardTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;


@ManagedBean(name = "carreerAwardTypeFormController")
@ViewScoped
public class CarreerAwardTypeFormController  extends BaseController{
	@ManagedProperty(value = "#{carreerAwardTypeService}")
    private CarreerAwardTypeService service;
    private CarreerAwardType selected;
    private CarreerAwardTypeModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{systemLetterReferenceService}")
    private SystemLetterReferenceService systemLetterReferenceService;
    
    private List<SystemLetterReference> systemLetterReferenceList = new ArrayList<SystemLetterReference>();
    private Map<String, Long> dropDownSystemLetterReference = new TreeMap<String, Long>();
    
    @PostConstruct
    @Override
    public void initialization(){
    	super.initialization();
    	try{
    		String carreerAwardTypeId = FacesUtil.getRequestParameter("carreerAwardTypeId");
    		model = new CarreerAwardTypeModel();
    		isUpdate = Boolean.FALSE;
    		if(StringUtils.isNotEmpty(carreerAwardTypeId)){
    			CarreerAwardType carreerAwardType = service.getEntiyByPK(Long.parseLong(carreerAwardTypeId));
    			if(carreerAwardTypeId != null){
    				model = getModelFromEntity(carreerAwardType);
    				isUpdate = Boolean.TRUE;
    			}
    		}
    		doSelectOneMenuSystemLetterReference();
    	} catch(Exception ex){
    		LOGGER.error("error", ex);
    	}
    }
    
    @PreDestroy
    public void cleanAndExit(){
    	isUpdate = null;
    	model = new CarreerAwardTypeModel();
    	selected = null;
    	service = null;
    }
    
    private CarreerAwardTypeModel getModelFromEntity(CarreerAwardType entity){
    	CarreerAwardTypeModel model = new CarreerAwardTypeModel();
    	model.setId(entity.getId());
    	model.setCode(entity.getCode());
    	model.setName(entity.getName());
    	model.setDescription(entity.getDescription());
    	model.setValidity(entity.getValidity());
    	model.setPoint(entity.getPoint());
    	model.setSystemLetterReferenceByLetterTemplateId(entity.getSystemLetterReferenceByLetterTemplateId().getId());
    	model.setSystemLetterReferenceByCertificateLetterTemplateId(entity.getSystemLetterReferenceByCertificateLetterTemplateId().getId());
    	return model;
    }
    
    private CarreerAwardType getEntityFromViewModel(CarreerAwardTypeModel model){
    	CarreerAwardType carreerAwardType = new CarreerAwardType();
    	if(model.getId() != null){
    		carreerAwardType.setId(model.getId());
    	} 
    	carreerAwardType.setCode(model.getCode());
    	carreerAwardType.setName(model.getName());
    	carreerAwardType.setDescription(model.getDescription());
    	carreerAwardType.setValidity(model.getValidity());
    	carreerAwardType.setPoint(model.getPoint());
    	carreerAwardType.setSystemLetterReferenceByLetterTemplateId(new SystemLetterReference(model.getSystemLetterReferenceByLetterTemplateId()));
    	carreerAwardType.setSystemLetterReferenceByCertificateLetterTemplateId(new SystemLetterReference(model.getSystemLetterReferenceByCertificateLetterTemplateId()));
    	return carreerAwardType;
    }
    
    public void doSave(){
    	CarreerAwardType carreerAwardType = getEntityFromViewModel(model);
        try{
            if(isUpdate){
                service.update(carreerAwardType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
                System.out.println("update");
            } else {
                service.save(carreerAwardType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }

	public CarreerAwardTypeService getService() {
		return service;
	}

	public void setService(CarreerAwardTypeService service) {
		this.service = service;
	}

	public CarreerAwardType getSelected() {
		return selected;
	}

	public void setSelected(CarreerAwardType selected) {
		this.selected = selected;
	}

	public CarreerAwardTypeModel getModel() {
		return model;
	}

	public void setModel(CarreerAwardTypeModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	
	public void doSelectOneMenuSystemLetterReference() throws Exception{
        systemLetterReferenceList = systemLetterReferenceService.getAllData();
    
        for(SystemLetterReference systemLetterReference : systemLetterReferenceList){
            dropDownSystemLetterReference.put(systemLetterReference.getCode() + "-" + systemLetterReference.getName(), systemLetterReference.getId());
        }
    }

	public SystemLetterReferenceService getSystemLetterReferenceService() {
		return systemLetterReferenceService;
	}

	public void setSystemLetterReferenceService(SystemLetterReferenceService systemLetterReferenceService) {
		this.systemLetterReferenceService = systemLetterReferenceService;
	}

	public List<SystemLetterReference> getSystemLetterReferenceList() {
		return systemLetterReferenceList;
	}

	public void setSystemLetterReferenceList(List<SystemLetterReference> systemLetterReferenceList) {
		this.systemLetterReferenceList = systemLetterReferenceList;
	}

	public Map<String, Long> getDropDownSystemLetterReference() {
		return dropDownSystemLetterReference;
	}

	public void setDropDownSystemLetterReference(Map<String, Long> dropDownSystemLetterReference) {
		this.dropDownSystemLetterReference = dropDownSystemLetterReference;
	}
    
    
}