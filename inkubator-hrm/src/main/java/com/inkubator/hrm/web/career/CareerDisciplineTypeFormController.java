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
import com.inkubator.hrm.entity.CareerDisciplineType;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.CareerDisciplineTypeService;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.hrm.web.model.CareerDisciplineTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

@ManagedBean(name = "careerDisciplineTypeFormController")
@ViewScoped
public class CareerDisciplineTypeFormController extends BaseController{
	@ManagedProperty(value = "#{careerDisciplineTypeService}")
	private CareerDisciplineTypeService service;
	private CareerDisciplineType selected;
	private CareerDisciplineTypeModel model;
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
            String careerDisciplineTypeId = FacesUtil.getRequestParameter("careerDisciplineTypeId");
            model = new CareerDisciplineTypeModel();
            isUpdate = Boolean.FALSE;
            if( StringUtils.isNotEmpty(careerDisciplineTypeId)){
            	CareerDisciplineType careerDisciplineType = service.getEntiyByPK(Long.parseLong(careerDisciplineTypeId));
                if(careerDisciplineTypeId != null){
                    model = getModelFromEntity(careerDisciplineType);
                    isUpdate = Boolean.TRUE;
                }
            }
            doSelectOneMenuSystemLetterReference();
        } catch (Exception e){
            LOGGER.error("error", e);
        }
    }
	
	@PreDestroy
	public void cleanAndExit(){
		service = null;
		selected = null;
		isUpdate =  null;
		model = new CareerDisciplineTypeModel();
	}
	
	private CareerDisciplineTypeModel getModelFromEntity(CareerDisciplineType entity){
		CareerDisciplineTypeModel model = new CareerDisciplineTypeModel();
		model.setId(entity.getId());
		model.setCode(entity.getCode());
		model.setName(entity.getName());
		model.setDescription(entity.getDescription());
		model.setValidity(entity.getValidity());
		model.setPoint(entity.getPoint());
		model.setSystemLetterReferenceId(entity.getSystemLetterReference().getId());
		return model;
	}
	
	private CareerDisciplineType getEntityFromViewModel(CareerDisciplineTypeModel model){
		CareerDisciplineType careerDisciplineType = new CareerDisciplineType();
		if(model.getId() != null){
			careerDisciplineType.setId(model.getId());
		}
		careerDisciplineType.setCode(model.getCode());
		careerDisciplineType.setName(model.getName());
		careerDisciplineType.setDescription(model.getDescription());
		careerDisciplineType.setPoint(model.getPoint());
		careerDisciplineType.setValidity(model.getValidity());
		careerDisciplineType.setSystemLetterReference(new SystemLetterReference(model.getSystemLetterReferenceId()));
		return careerDisciplineType;
	}

	public void doSave(){
		CareerDisciplineType careerDisciplineType = getEntityFromViewModel(model);
        try{
            if(isUpdate){
                service.update(careerDisciplineType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(careerDisciplineType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex){
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }
	
	public void doSelectOneMenuSystemLetterReference() throws Exception{
        systemLetterReferenceList = systemLetterReferenceService.getAllData();
    
        for(SystemLetterReference systemLetterReference : systemLetterReferenceList){
            dropDownSystemLetterReference.put(systemLetterReference.getCode() + "-" + systemLetterReference.getName(), systemLetterReference.getId());
        }
    }

	public CareerDisciplineTypeService getService() {
		return service;
	}

	public void setService(CareerDisciplineTypeService service) {
		this.service = service;
	}

	public CareerDisciplineType getSelected() {
		return selected;
	}

	public void setSelected(CareerDisciplineType selected) {
		this.selected = selected;
	}

	public CareerDisciplineTypeModel getModel() {
		return model;
	}

	public void setModel(CareerDisciplineTypeModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
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
