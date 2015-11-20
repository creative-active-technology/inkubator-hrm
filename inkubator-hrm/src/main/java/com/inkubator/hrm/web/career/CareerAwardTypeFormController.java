package com.inkubator.hrm.web.career;

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
import com.inkubator.hrm.entity.CareerAwardType;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.CareerAwardTypeService;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.hrm.web.model.CareerAwardTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;


@ManagedBean(name = "careerAwardTypeFormController")
@ViewScoped
public class CareerAwardTypeFormController  extends BaseController{
	@ManagedProperty(value = "#{careerAwardTypeService}")
    private CareerAwardTypeService service;
    private CareerAwardType selected;
    private CareerAwardTypeModel model;
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
    		String careerAwardTypeId = FacesUtil.getRequestParameter("careerAwardTypeId");
    		model = new CareerAwardTypeModel();
    		isUpdate = Boolean.FALSE;
    		if(StringUtils.isNotEmpty(careerAwardTypeId)){
    			CareerAwardType careerAwardType = service.getEntiyByPK(Long.parseLong(careerAwardTypeId));
    			if(careerAwardTypeId != null){
    				model = getModelFromEntity(careerAwardType);
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
    	model = new CareerAwardTypeModel();
    	selected = null;
    	service = null;
    }
    
    private CareerAwardTypeModel getModelFromEntity(CareerAwardType entity){
    	CareerAwardTypeModel model = new CareerAwardTypeModel();
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
    
    private CareerAwardType getEntityFromViewModel(CareerAwardTypeModel model){
    	CareerAwardType careerAwardType = new CareerAwardType();
    	if(model.getId() != null){
    		careerAwardType.setId(model.getId());
    	} 
    	careerAwardType.setCode(model.getCode());
    	careerAwardType.setName(model.getName());
    	careerAwardType.setDescription(model.getDescription());
    	careerAwardType.setValidity(model.getValidity());
    	careerAwardType.setPoint(model.getPoint());
    	careerAwardType.setSystemLetterReferenceByLetterTemplateId(new SystemLetterReference(model.getSystemLetterReferenceByLetterTemplateId()));
    	careerAwardType.setSystemLetterReferenceByCertificateLetterTemplateId(new SystemLetterReference(model.getSystemLetterReferenceByCertificateLetterTemplateId()));
    	return careerAwardType;
    }
    
    public void doSave(){
    	CareerAwardType careerAwardType = getEntityFromViewModel(model);
        try{
            if(isUpdate){
                service.update(careerAwardType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
                System.out.println("update");
            } else {
                service.save(careerAwardType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }

	public CareerAwardTypeService getService() {
		return service;
	}

	public void setService(CareerAwardTypeService service) {
		this.service = service;
	}

	public CareerAwardType getSelected() {
		return selected;
	}

	public void setSelected(CareerAwardType selected) {
		this.selected = selected;
	}

	public CareerAwardTypeModel getModel() {
		return model;
	}

	public void setModel(CareerAwardTypeModel model) {
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