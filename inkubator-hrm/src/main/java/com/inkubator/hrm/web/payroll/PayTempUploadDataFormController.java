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

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.PayTempUploadDataService;
import com.inkubator.hrm.web.model.PayTempUploadDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "payTempUploadDataFormController")
@ViewScoped
public class PayTempUploadDataFormController extends BaseController {

    private PayTempUploadDataModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{payTempUploadDataService}")
    private PayTempUploadDataService payTempUploadDataService;
    @ManagedProperty(value = "#{paySalaryComponentService}")
    private PaySalaryComponentService paySalaryComponentService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        model = new PayTempUploadDataModel();
        isUpdate = Boolean.FALSE;
        
        try {
        	String payTempUploadDataParam = FacesUtil.getRequestParameter("payTempUploadDataId");        
        	if (StringUtils.isNumeric(payTempUploadDataParam)) {            
                PayTempUploadData payTempUploadData = payTempUploadDataService.getEntityByPkWithDetail(Long.parseLong(payTempUploadDataParam));
                if (payTempUploadData != null) {
                    getViewModelFromEntity(payTempUploadData);
                    isUpdate = Boolean.TRUE;
                }            
        	} else {
        		String paySalaryComponentParam = FacesUtil.getRequestParameter("paySalaryComponentId");
        		PaySalaryComponent paySalaryComponent =  paySalaryComponentService.getEntiyByPK(Long.parseLong(paySalaryComponentParam));
        		model.setPaySalaryComponentName(paySalaryComponent.getName());
        		model.setPaySalaryComponentId(paySalaryComponent.getId());
        	}
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	payTempUploadDataService = null;
    	paySalaryComponentService = null;
    	empDataService = null;
        model = null;
        isUpdate = null;
    }

	public PayTempUploadDataModel getModel() {
		return model;
	}

	public void setModel(PayTempUploadDataModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public PayTempUploadDataService getPayTempUploadDataService() {
		return payTempUploadDataService;
	}

	public void setPayTempUploadDataService(PayTempUploadDataService payTempUploadDataService) {
		this.payTempUploadDataService = payTempUploadDataService;
	}

	public PaySalaryComponentService getPaySalaryComponentService() {
		return paySalaryComponentService;
	}

	public void setPaySalaryComponentService(
			PaySalaryComponentService paySalaryComponentService) {
		this.paySalaryComponentService = paySalaryComponentService;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public void doSave() {
        PayTempUploadData payTempUploadData = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
            	payTempUploadDataService.update(payTempUploadData);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	payTempUploadDataService.save(payTempUploadData);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
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

    private PayTempUploadData getEntityFromViewModel(PayTempUploadDataModel model) {
    	PayTempUploadData payTempUploadData = new PayTempUploadData();
        if (model.getId() != null) {
        	payTempUploadData.setId(model.getId());
        }
        payTempUploadData.setNominalValue(model.getNominalValue());
        payTempUploadData.setPaySalaryComponent(new PaySalaryComponent(model.getPaySalaryComponentId()));
        payTempUploadData.setEmpData(model.getEmpData());
        return payTempUploadData;
    }
    
    private void getViewModelFromEntity(PayTempUploadData payTempUploadData){
    	model.setId(payTempUploadData.getId());
    	model.setEmpData(payTempUploadData.getEmpData());
    	model.setNominalValue(payTempUploadData.getNominalValue());
    	model.setPaySalaryComponentId(payTempUploadData.getPaySalaryComponent().getId());
    	model.setPaySalaryComponentName(payTempUploadData.getPaySalaryComponent().getName());
    }
}
