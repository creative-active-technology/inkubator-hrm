/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionOT;
import com.inkubator.hrm.entity.WtHitungLembur;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.WtHitungLemburService;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.hrm.web.model.OverTimeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.TreeMap;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "overTimeFormController")
@ViewScoped
public class OverTimeFormController extends BaseController {

    private OverTimeModel overTimeModel;
    @ManagedProperty(value = "#{wtOverTimeService}")
    private WtOverTimeService wtOverTimeService;
    WtOverTime selectedWtOverTime;
    private Boolean isEdit;
    private List<ApprovalDefinition> appDefs;
    private int indexOfAppDefs;
    private ApprovalDefinition selectedAppDef;
    @ManagedProperty(value = "#{wtHitungLemburService}")
    private WtHitungLemburService wtHitungLemburService;
    
    public List<WtHitungLembur> listWtHitungLembur = new ArrayList<WtHitungLembur>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();       
        try {
        	listWtHitungLembur = wtHitungLemburService.getAllData();
            appDefs = new ArrayList<ApprovalDefinition>(); 
            
            String param = FacesUtil.getRequestParameter("execution");
            overTimeModel = new OverTimeModel();
            if (param != null) {

                isEdit = Boolean.TRUE;
                WtOverTime wtOverTime = wtOverTimeService.getEntityByPkFetchApprovalDefinition(Long.parseLong(param.substring(1)));
                overTimeModel.setId(wtOverTime.getId());
                overTimeModel.setCode(wtOverTime.getCode());
                overTimeModel.setDescription(wtOverTime.getDescription());
                overTimeModel.setFinishTimeFactor(wtOverTime.getFinishTimeFactor());
                overTimeModel.setMaximumTime(wtOverTime.getMaximumTime());
                overTimeModel.setMinimumTime(wtOverTime.getMinimumTime());
                overTimeModel.setName(wtOverTime.getName());
                overTimeModel.setOtRounding(wtOverTime.getOtRounding());
                overTimeModel.setOverTimeCalculation(wtOverTime.getOverTimeCalculation());
                overTimeModel.setStartTimeFactor(wtOverTime.getStartTimeFactor());
                overTimeModel.setValuePrice(wtOverTime.getValuePrice());
                overTimeModel.setWtHitungLemburID(wtOverTime.getWtHitungLembur() != null ? wtOverTime.getWtHitungLembur().getId() : null);
                overTimeModel.setIsActive(wtOverTime.getIsActive());
                Set<ApprovalDefinitionOT> approvalDefinitionOTs = wtOverTime.getApprovalDefinitionOTs();
                    for(ApprovalDefinitionOT appDefOverTime : approvalDefinitionOTs){
                    	appDefs.add(appDefOverTime.getApprovalDefinition());
                    }
            } else {
                isEdit = Boolean.FALSE;
            }
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doBack() {
        return "/protected/working_time/over_time_view.htm?faces-redirect=true";
    }
    
    public String doSave() {
        WtOverTime wtOverTime = getEntityFromViewModel(overTimeModel);
        try {
            if (isEdit) {
                wtOverTimeService.update(wtOverTime, appDefs);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                wtOverTimeService.save(wtOverTime, appDefs);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/working_time/over_time_detail.htm?faces-redirect=true&execution=e" + wtOverTime.getId();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }
    


    private WtOverTime getEntityFromViewModel(OverTimeModel overTimeModel) {
        WtOverTime overTime = new WtOverTime();
        if (overTimeModel.getId() != null) {
            overTime.setId(overTimeModel.getId());
        }
        overTime.setCode(overTimeModel.getCode());
        overTime.setDescription(overTimeModel.getDescription());
        overTime.setFinishTimeFactor(overTimeModel.getFinishTimeFactor());
        overTime.setMaximumTime(overTimeModel.getMaximumTime());
        overTime.setMinimumTime(overTimeModel.getMinimumTime());
        overTime.setName(overTimeModel.getName());
        overTime.setOtRounding(overTimeModel.getOtRounding());
        overTime.setOverTimeCalculation(overTimeModel.getOverTimeCalculation());
        overTime.setStartTimeFactor(overTimeModel.getStartTimeFactor());
        overTime.setValuePrice(overTimeModel.getValuePrice());
        overTime.setIsActive(overTimeModel.getIsActive());
        overTime.setWtHitungLembur(new WtHitungLembur(overTimeModel.getWtHitungLemburID()));
        return overTime;
    }

    @PreDestroy
    private void cleanAndExit() {
        overTimeModel = null;
        wtOverTimeService = null;
        selectedWtOverTime = null;
        isEdit = null;
        selectedAppDef = null;
        appDefs = null;
        listWtHitungLembur = null;
    }

    public List<ApprovalDefinition> getAppDefs() {
        return appDefs;
    }

    public void setAppDefs(List<ApprovalDefinition> appDefs) {
        this.appDefs = appDefs;
    }

    public int getIndexOfAppDefs() {
        return indexOfAppDefs;
    }

    public void setIndexOfAppDefs(int indexOfAppDefs) {
        this.indexOfAppDefs = indexOfAppDefs;
    }

    public ApprovalDefinition getSelectedAppDef() {
        return selectedAppDef;
    }

    public void setSelectedAppDef(ApprovalDefinition selectedAppDef) {
        this.selectedAppDef = selectedAppDef;
    }
    
    public OverTimeModel getOverTimeModel() {
        return overTimeModel;
    }

    public void setOverTimeModel(OverTimeModel overTimeModel) {
        this.overTimeModel = overTimeModel;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    /** Start Approval Definition form */
    public void onChangeName(){
    	if(!appDefs.isEmpty()) {
    		Lambda.forEach(appDefs).setSpecificName(overTimeModel.getName());
    	}
    }
    
    public void doDeleteAppDef() {
    	appDefs.remove(selectedAppDef);
    }
    
    public void doAddAppDef() {
    	Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> appDefName = new ArrayList<>();
        appDefName.add(HRMConstant.OVERTIME);
        dataToSend.put("appDefName", appDefName);
        List<String> specificName = new ArrayList<>();
        String name = StringUtils.isEmpty(overTimeModel.getName()) ? StringUtils.EMPTY : overTimeModel.getName();
        specificName.add(name);
        dataToSend.put("specificName", specificName);
    	this.showDialogAppDef(dataToSend);
    }
    
    public void doEditAppDef() {
    	indexOfAppDefs = appDefs.indexOf(selectedAppDef);    	
    	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
    	Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(gson.toJson(selectedAppDef));
        dataToSend.put("jsonAppDef", values);
        this.showDialogAppDef(dataToSend);
    }
    
    private void showDialogAppDef(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 1100);
        options.put("contentHeight", 430);
        RequestContext.getCurrentInstance().openDialog("appr_def_popup_form", options, params);
    }
    
    public void onDialogReturnAddAppDef(SelectEvent event) {
        ApprovalDefinition appDef = (ApprovalDefinition) event.getObject();
        appDefs.add(appDef);
    }
    
    public void onDialogReturnEditAppDef(SelectEvent event) {
        ApprovalDefinition dataUpdated = (ApprovalDefinition) event.getObject();
        appDefs.remove(indexOfAppDefs);
		appDefs.add(indexOfAppDefs, dataUpdated);
    }    
     /** End Approval Definition form */

    public WtHitungLemburService getWtHitungLemburService() {
        return wtHitungLemburService;
    }

    public void setWtHitungLemburService(WtHitungLemburService wtHitungLemburService) {
        this.wtHitungLemburService = wtHitungLemburService;
    }

	public List<WtHitungLembur> getListWtHitungLembur() {
		return listWtHitungLembur;
	}

	public void setListWtHitungLembur(List<WtHitungLembur> listWtHitungLembur) {
		this.listWtHitungLembur = listWtHitungLembur;
	}

	public WtOverTime getSelectedWtOverTime() {
        return selectedWtOverTime;
    }

    public void setSelectedWtOverTime(WtOverTime selectedWtOverTime) {
        this.selectedWtOverTime = selectedWtOverTime;
    }

    public void setWtOverTimeService(WtOverTimeService wtOverTimeService) {
        this.wtOverTimeService = wtOverTimeService;
    }
    
}
