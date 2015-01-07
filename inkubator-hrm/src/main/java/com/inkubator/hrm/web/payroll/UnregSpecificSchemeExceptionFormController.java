package com.inkubator.hrm.web.payroll;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.UnregPayComponentsException;
import com.inkubator.hrm.entity.UnregPayComponentsExceptionId;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.UnregPayComponentsExceptionService;
import com.inkubator.hrm.web.model.UnregSpecificSchemeExceptionModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "unregSpecificSchemeExceptionFormController")
@ViewScoped
public class UnregSpecificSchemeExceptionFormController extends BaseController {
    
	private Long empDataIdParam;
    private Boolean isUpdate;
    private UnregSpecificSchemeExceptionModel model;
    @ManagedProperty(value = "#{unregPayComponentsExceptionService}")
    private UnregPayComponentsExceptionService unregPayComponentsExceptionService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        isUpdate = Boolean.FALSE;
        
        model = new UnregSpecificSchemeExceptionModel();
        String unregPayComponentsId = FacesUtil.getRequestParameter("unregPayComponentsId");
        model.setUnregPayComponentId(Long.parseLong(unregPayComponentsId));
        
        String empDataId = FacesUtil.getRequestParameter("empDataId");
        if (StringUtils.isNumeric(empDataId)) {
            try {
            	empDataIdParam = Long.parseLong(empDataId);
                UnregPayComponentsException unregPayComponentsException = unregPayComponentsExceptionService.getEntityByPK(new UnregPayComponentsExceptionId(Long.parseLong(unregPayComponentsId), empDataIdParam));
                if (unregPayComponentsException != null) {
                	getViewModelFromEntity(unregPayComponentsException);
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

	@PreDestroy
    public void cleanAndExit() {
    	unregPayComponentsExceptionService = null;
        isUpdate = null;
        model = null;
        empDataIdParam = null;
    }
	
	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public UnregSpecificSchemeExceptionModel getModel() {
		return model;
	}

	public void setModel(UnregSpecificSchemeExceptionModel model) {
		this.model = model;
	}

	public UnregPayComponentsExceptionService getUnregPayComponentsExceptionService() {
		return unregPayComponentsExceptionService;
	}

	public void setUnregPayComponentsExceptionService(
			UnregPayComponentsExceptionService unregPayComponentsExceptionService) {
		this.unregPayComponentsExceptionService = unregPayComponentsExceptionService;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public Long getEmpDataIdParam() {
		return empDataIdParam;
	}

	public void setEmpDataIdParam(Long empDataIdParam) {
		this.empDataIdParam = empDataIdParam;
	}

	public void doSave() {
        UnregPayComponentsException unregPayComponentsException = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
            	unregPayComponentsExceptionService.update(unregPayComponentsException, empDataIdParam);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	unregPayComponentsExceptionService.save(unregPayComponentsException);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (DataIntegrityViolationException ex){
        	MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "unregSalary.error_duplicate_employee", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

	private UnregPayComponentsException getEntityFromViewModel(UnregSpecificSchemeExceptionModel model) {
		UnregPayComponentsException unregPayComponentsException = new UnregPayComponentsException();
		if(model.getUnregPayComponentId() != null && model.getEmpData() != null){
			unregPayComponentsException.setId(new UnregPayComponentsExceptionId(model.getUnregPayComponentId(), model.getEmpData().getId()));
		}
		unregPayComponentsException.setNominal(model.getNominal());
		return unregPayComponentsException;
	}
	
	private void getViewModelFromEntity(UnregPayComponentsException unregPayComponentsException) {
		model.setUnregPayComponentId(unregPayComponentsException.getUnregPayComponents().getId());
		model.setEmpData(unregPayComponentsException.getEmpData());
		model.setNominal(unregPayComponentsException.getNominal());		
	}
	
	public List<EmpData> doAutoCompleteEmployee(String param){
		List<EmpData> empDatas = new ArrayList<EmpData>();
		try {
			empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
		return empDatas;
	}
    
}
