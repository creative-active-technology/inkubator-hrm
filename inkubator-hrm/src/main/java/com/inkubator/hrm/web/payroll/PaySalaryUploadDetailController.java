package com.inkubator.hrm.web.payroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.PayTempUploadDataService;
import com.inkubator.hrm.web.lazymodel.PayTempUploadDataLazyDataModel;
import com.inkubator.hrm.web.model.PaySalaryUploadModel;
import com.inkubator.hrm.web.search.PayTempUploadDataSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "paySalaryUploadDetailController")
@ViewScoped
public class PaySalaryUploadDetailController extends BaseController {

	private PaySalaryComponent selectedPaySalaryComponent;
	private PayTempUploadDataLazyDataModel lazyPayTempUploadData;
	private PayTempUploadData selectedPayTempUploadData;
	private PaySalaryUploadModel model;
	private PayTempUploadDataSearchParameter parameter;
	@ManagedProperty(value = "#{payTempUploadDataService}")
	private PayTempUploadDataService payTempUploadDataService;
	@ManagedProperty(value = "#{paySalaryComponentService}")
	private PaySalaryComponentService paySalaryComponentService;
	
	
	@PostConstruct
    @Override
    public void initialization() {
		try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("execution");
            selectedPaySalaryComponent = paySalaryComponentService.getEntityByPkWithDetail(Long.parseLong(param.substring(1)));
            model = this.getModelFromEntity(selectedPaySalaryComponent);
            parameter = new PayTempUploadDataSearchParameter(selectedPaySalaryComponent.getId());
		} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
	}

	@PreDestroy
    public void cleanAndExit() {
		selectedPaySalaryComponent = null;
		lazyPayTempUploadData = null;
		selectedPayTempUploadData = null;
		model = null;
		parameter = null;
		payTempUploadDataService = null;
		paySalaryComponentService = null;
	}
	
	public PaySalaryComponent getSelectedPaySalaryComponent() {
		return selectedPaySalaryComponent;
	}

	public void setSelectedPaySalaryComponent(PaySalaryComponent selectedPaySalaryComponent) {
		this.selectedPaySalaryComponent = selectedPaySalaryComponent;
	}

	public PayTempUploadDataLazyDataModel getLazyPayTempUploadData() {
		if(lazyPayTempUploadData == null){
			lazyPayTempUploadData =  new PayTempUploadDataLazyDataModel(parameter, payTempUploadDataService);
		}
		return lazyPayTempUploadData;
	}

	public void setLazyPayTempUploadData(PayTempUploadDataLazyDataModel lazyPayTempUploadData) {
		this.lazyPayTempUploadData = lazyPayTempUploadData;
	}

	public PayTempUploadData getSelectedPayTempUploadData() {
		return selectedPayTempUploadData;
	}

	public void setSelectedPayTempUploadData(PayTempUploadData selectedPayTempUploadData) {
		this.selectedPayTempUploadData = selectedPayTempUploadData;
	}

	public PaySalaryUploadModel getModel() {
		return model;
	}

	public void setModel(PaySalaryUploadModel model) {
		this.model = model;
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

	public void setPaySalaryComponentService(PaySalaryComponentService paySalaryComponentService) {
		this.paySalaryComponentService = paySalaryComponentService;
	}

	public PayTempUploadDataSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(PayTempUploadDataSearchParameter parameter) {
		this.parameter = parameter;
	}
	
	public String doBack() {
        return "/protected/payroll/pay_salary_upload_view.htm?faces-redirect=true";
    }
	
	public void doSearch(){
		lazyPayTempUploadData = null;
	}
	
	public void doSelectEntity(){
		try {
			selectedPayTempUploadData = payTempUploadDataService.getEntityByPkWithDetail(selectedPayTempUploadData.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("Error ", e);
		}
	}
	
	@Override
	public void onDialogReturn(SelectEvent event) {		
		model = this.getModelFromEntity(selectedPaySalaryComponent);
		super.onDialogReturn(event);
	}
	
	public void doUpload(){
		Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> paySalaryComponentValues = new ArrayList<>();
        paySalaryComponentValues.add(String.valueOf(selectedPaySalaryComponent.getId()));
        dataToSend.put("paySalaryComponentId", paySalaryComponentValues);
		this.showDialogUpload(dataToSend);
	}
	
	private void showDialogUpload(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 600);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("pay_salary_upload_file", options, params);
    }
	
	public void doReuse(){
		
	}
	
	public void doAddData(){
		Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> paySalaryComponentValues = new ArrayList<>();
        paySalaryComponentValues.add(String.valueOf(selectedPaySalaryComponent.getId()));
        dataToSend.put("paySalaryComponentId", paySalaryComponentValues);
		showDialogData(dataToSend);
	}
	
	public void doEditData(){
		Map<String, List<String>> dataToSend = new HashMap<>();
		
        List<String> payTempUploadDataValues = new ArrayList<>();
        payTempUploadDataValues.add(String.valueOf(selectedPayTempUploadData.getId()));
        dataToSend.put("payTempUploadDataId", payTempUploadDataValues);
        
        List<String> paySalaryComponentValues = new ArrayList<>();
        paySalaryComponentValues.add(String.valueOf(selectedPaySalaryComponent.getId()));
        dataToSend.put("paySalaryComponentId", paySalaryComponentValues);
        
        showDialogData(dataToSend);
	}
	
	private void showDialogData(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 320);
        RequestContext.getCurrentInstance().openDialog("pay_temp_upload_data", options, params);
    }
	
	public void doDeleteData(){
		try {
			payTempUploadDataService.delete(selectedPayTempUploadData);
			model = this.getModelFromEntity(selectedPaySalaryComponent);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete ", ex);
        }
	}

	private PaySalaryUploadModel getModelFromEntity(PaySalaryComponent paySalaryComponent) {
		PaySalaryUploadModel model = new PaySalaryUploadModel();
		try {
			model.setPaySalaryComponentId(paySalaryComponent.getId());
			model.setPaySalaryComponentName(paySalaryComponent.getName());
			Long totalEmployee = payTempUploadDataService.getTotalByPaySalaryComponentId(paySalaryComponent.getId());
	        model.setTotalEmployee(totalEmployee);
			Double totalSalary = payTempUploadDataService.getTotalSalaryByPaySalaryComponentId(paySalaryComponent.getId());
			model.setTotalSalary(totalSalary);
		} catch (Exception ex) {
            LOGGER.error("Error when bind to model ", ex);
        }
		
		return model;
	}
}
