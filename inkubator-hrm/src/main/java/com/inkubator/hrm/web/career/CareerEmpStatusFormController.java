package com.inkubator.hrm.web.career;

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
import com.inkubator.hrm.entity.CareerEmpStatus;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.CareerEmpStatusService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.hrm.web.model.CareerEmpStatusModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "careerEmpStatusFormController")
@ViewScoped
public class CareerEmpStatusFormController extends BaseController {

    private CareerEmpStatusModel model; 
    private Boolean isUpdate;
    private List<EmployeeType> listEmpType;
    private List<SystemLetterReference> listSysLetterRef;
    
    @ManagedProperty(value = "#{careerEmpStatusService}")
    private CareerEmpStatusService careerEmpStatusService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
    @ManagedProperty(value = "#{systemLetterReferenceService}")
    private SystemLetterReferenceService systemLetterReferenceService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	listEmpType = employeeTypeService.getAllData();
        	listSysLetterRef =  systemLetterReferenceService.getAllData();
	        model = new CareerEmpStatusModel();
	        isUpdate = Boolean.FALSE;
	        
	        String param = FacesUtil.getRequestParameter("param");
			if (StringUtils.isNumeric(param)) {
				CareerEmpStatus careerEmpStatus = careerEmpStatusService.getEntityByPkWithDetail(Long.parseLong(param));
				if (careerEmpStatus != null) {
					this.getModelFromEntity(careerEmpStatus);
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
    	listEmpType = null; 
    	listSysLetterRef = null;
    	careerEmpStatusService = null; 
    	employeeTypeService = null; 
    	systemLetterReferenceService = null;
	}

    public void doSave() {
    	CareerEmpStatus careerEmpStatus = getEntityFromModel(model);
        try {
            if (isUpdate) {
            	careerEmpStatusService.update(careerEmpStatus);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	careerEmpStatusService.save(careerEmpStatus);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private CareerEmpStatus getEntityFromModel(CareerEmpStatusModel m) {
    	CareerEmpStatus careerEmpStatus = new CareerEmpStatus();
        if (m.getId() != null) {
            careerEmpStatus.setId(m.getId());
        }
        careerEmpStatus.setCode(m.getCode());
        careerEmpStatus.setName(m.getName());
        careerEmpStatus.setIsAutoMove(m.getIsAutoMove());
        careerEmpStatus.setLimitTime(m.getLimitTime());
        careerEmpStatus.setEmpType(new EmployeeType(m.getEmpTypeId()));
        careerEmpStatus.setSystemLetterReference(new SystemLetterReference(m.getSystemLetterReferenceId()));
        return careerEmpStatus;
    }
    
    private void getModelFromEntity(CareerEmpStatus entity){
    	model.setId(entity.getId());
    	model.setCode(entity.getCode());
    	model.setName(entity.getName());
    	model.setIsAutoMove(entity.getIsAutoMove());
    	model.setLimitTime(entity.getLimitTime());
    	model.setEmpTypeId(entity.getEmpType().getId());
    	model.setSystemLetterReferenceId(entity.getSystemLetterReference().getId());
    }

	public CareerEmpStatusModel getModel() {
		return model;
	}

	public void setModel(CareerEmpStatusModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public List<EmployeeType> getListEmpType() {
		return listEmpType;
	}

	public void setListEmpType(List<EmployeeType> listEmpType) {
		this.listEmpType = listEmpType;
	}

	public List<SystemLetterReference> getListSysLetterRef() {
		return listSysLetterRef;
	}

	public void setListSysLetterRef(List<SystemLetterReference> listSysLetterRef) {
		this.listSysLetterRef = listSysLetterRef;
	}

	public CareerEmpStatusService getCareerEmpStatusService() {
		return careerEmpStatusService;
	}

	public void setCareerEmpStatusService(CareerEmpStatusService careerEmpStatusService) {
		this.careerEmpStatusService = careerEmpStatusService;
	}

	public EmployeeTypeService getEmployeeTypeService() {
		return employeeTypeService;
	}

	public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
		this.employeeTypeService = employeeTypeService;
	}

	public SystemLetterReferenceService getSystemLetterReferenceService() {
		return systemLetterReferenceService;
	}

	public void setSystemLetterReferenceService(SystemLetterReferenceService systemLetterReferenceService) {
		this.systemLetterReferenceService = systemLetterReferenceService;
	}
    
}
