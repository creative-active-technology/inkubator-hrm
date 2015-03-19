/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reimbursement;

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

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionRmbsSchema;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalDefinitionRmbsSchemaService;
import com.inkubator.hrm.service.RmbsSchemaService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsSchemaDetailController")
@ViewScoped
public class RmbsSchemaDetailController extends BaseController {
	
    private ApprovalDefinition selectedAppDef;
    private RmbsSchema rmbsSchema;
    private List<ApprovalDefinition> listAppDef;
    @ManagedProperty(value = "#{rmbsSchemaService}")
    private RmbsSchemaService rmbsSchemaService;
    @ManagedProperty(value = "#{approvalDefinitionRmbsSchemaService}")
    private ApprovalDefinitionRmbsSchemaService approvalDefinitionRmbsSchemaService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            rmbsSchema = rmbsSchemaService.getEntityByPkFetchApprovalDefinition(Long.parseLong(id.substring(1)));
            listAppDef = Lambda.extract(rmbsSchema.getApprovalDefinitionRmbsSchemas(), Lambda.on(ApprovalDefinitionRmbsSchema.class).getApprovalDefinition());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsSchema = null;
    	rmbsSchemaService = null;
    	selectedAppDef = null;
    	listAppDef = null;
    	approvalDefinitionRmbsSchemaService = null;
    }   

    public String doBack() {
        return "/protected/reimbursement/rmbs_schema_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/reimbursement/rmbs_schema_form.htm?faces-redirect=true&execution=e" + rmbsSchema.getId();
    }
    
    /** Start Approval Definition form */
    public void doDeleteAppDef() {
    	try {
    		rmbsSchemaService.deleteApprovalconf(selectedAppDef.getId(), rmbsSchema.getId());
    		listAppDef = Lambda.extract(approvalDefinitionRmbsSchemaService.getAllDataByRmbsSchemaId(rmbsSchema.getId()), Lambda.on(ApprovalDefinitionRmbsSchema.class).getApprovalDefinition());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }
    
    public void doAddAppDef() {
    	Map<String, List<String>> dataToSend = new HashMap<>();
        
    	List<String> isPersistedToDB = new ArrayList<>();
    	isPersistedToDB.add("true");
        dataToSend.put("isPersistedToDB", isPersistedToDB);
        
        List<String> entityId = new ArrayList<>();
        entityId.add(String.valueOf(rmbsSchema.getId()));
        dataToSend.put("entityId", entityId);
        
    	List<String> appDefName = new ArrayList<>();
        appDefName.add(HRMConstant.REIMBURSEMENT);
        dataToSend.put("appDefName", appDefName);
        
        List<String> specificName = new ArrayList<>();
        specificName.add(rmbsSchema.getName());
        dataToSend.put("specificName", specificName);
    	
        this.showDialogAppDef(dataToSend);
    }
    
    public void doEditAppDef() {    	
    	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
    	Map<String, List<String>> dataToSend = new HashMap<>();
    	
    	List<String> isPersistedToDB = new ArrayList<>();
    	isPersistedToDB.add("true");
        dataToSend.put("isPersistedToDB", isPersistedToDB);
        
        List<String> entityId = new ArrayList<>();
        entityId.add(String.valueOf(rmbsSchema.getId()));
        dataToSend.put("entityId", entityId);
    	
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
        options.put("contentHeight", 400);
        RequestContext.getCurrentInstance().openDialog("appr_def_popup_form", options, params);
    }   
    
    public void onDialogReturnAppDef(SelectEvent event) {
        //re-calculate searching
    	try {
    		listAppDef = Lambda.extract(approvalDefinitionRmbsSchemaService.getAllDataByRmbsSchemaId(rmbsSchema.getId()), Lambda.on(ApprovalDefinitionRmbsSchema.class).getApprovalDefinition());
    		super.onDialogReturn(event);
    	} catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }
    /** End Approval Definition form */
    

	public RmbsSchema getRmbsSchema() {
		return rmbsSchema;
	}

	public void setRmbsSchema(RmbsSchema rmbsSchema) {
		this.rmbsSchema = rmbsSchema;
	}

	public RmbsSchemaService getRmbsSchemaService() {
		return rmbsSchemaService;
	}

	public void setRmbsSchemaService(RmbsSchemaService rmbsSchemaService) {
		this.rmbsSchemaService = rmbsSchemaService;
	}

	public ApprovalDefinition getSelectedAppDef() {
		return selectedAppDef;
	}

	public void setSelectedAppDef(ApprovalDefinition selectedAppDef) {
		this.selectedAppDef = selectedAppDef;
	}

	public List<ApprovalDefinition> getListAppDef() {
		return listAppDef;
	}

	public void setListAppDef(List<ApprovalDefinition> listAppDef) {
		this.listAppDef = listAppDef;
	}

	public ApprovalDefinitionRmbsSchemaService getApprovalDefinitionRmbsSchemaService() {
		return approvalDefinitionRmbsSchemaService;
	}

	public void setApprovalDefinitionRmbsSchemaService(
			ApprovalDefinitionRmbsSchemaService approvalDefinitionRmbsSchemaService) {
		this.approvalDefinitionRmbsSchemaService = approvalDefinitionRmbsSchemaService;
	}

}
