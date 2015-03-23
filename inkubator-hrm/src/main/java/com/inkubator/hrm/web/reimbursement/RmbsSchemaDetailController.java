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
import com.inkubator.hrm.entity.RmbsSchemaListOfType;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalDefinitionRmbsSchemaService;
import com.inkubator.hrm.service.RmbsSchemaListOfTypeService;
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
    private RmbsSchemaListOfType selectedRmbsSchemaListOfType;
    private RmbsSchema rmbsSchema;
    private List<ApprovalDefinition> listAppDef;
    private List<RmbsSchemaListOfType> listRmbsSchemaListOfTypes;
    @ManagedProperty(value = "#{rmbsSchemaService}")
    private RmbsSchemaService rmbsSchemaService;
    @ManagedProperty(value = "#{approvalDefinitionRmbsSchemaService}")
    private ApprovalDefinitionRmbsSchemaService approvalDefinitionRmbsSchemaService;
    @ManagedProperty(value = "#{rmbsSchemaListOfTypeService}")
    private RmbsSchemaListOfTypeService rmbsSchemaListOfTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            rmbsSchema = rmbsSchemaService.getEntiyByPK(Long.parseLong(id.substring(1)));
            listAppDef = Lambda.extract(approvalDefinitionRmbsSchemaService.getAllDataByRmbsSchemaId(rmbsSchema.getId()), Lambda.on(ApprovalDefinitionRmbsSchema.class).getApprovalDefinition());
            listRmbsSchemaListOfTypes = rmbsSchemaListOfTypeService.getAllDataByRmbsSchemaId(rmbsSchema.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsSchema = null;
    	rmbsSchemaService = null;
    	selectedAppDef = null;
    	selectedRmbsSchemaListOfType = null;
    	listAppDef = null;
    	approvalDefinitionRmbsSchemaService = null;
    	rmbsSchemaListOfTypeService = null;
    	listRmbsSchemaListOfTypes = null; 
    }   

    public String doBack() {
        return "/protected/reimbursement/rmbs_schema_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/reimbursement/rmbs_schema_form.htm?faces-redirect=true&execution=e" + rmbsSchema.getId();
    }
    
    /** Start Reimbursement Type tab view process */
    public void doDeleteReimbursementType() {
    	try {
    		rmbsSchemaListOfTypeService.delete(selectedRmbsSchemaListOfType);
    		listRmbsSchemaListOfTypes = rmbsSchemaListOfTypeService.getAllDataByRmbsSchemaId(rmbsSchema.getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }
    
    public void doAddReimbursementType() {
    	Map<String, List<String>> dataToSend = new HashMap<>();
    	
    	List<String> rmbsSchemaId = new ArrayList<>();
        rmbsSchemaId.add(String.valueOf(rmbsSchema.getId()));
        dataToSend.put("rmbsSchemaId", rmbsSchemaId);
        
    	showDialogReimbursementType(dataToSend);
    }

    public void doEditReimbursementType() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        
        List<String> rmbsSchemaId = new ArrayList<>();
        rmbsSchemaId.add(String.valueOf(rmbsSchema.getId()));
        dataToSend.put("rmbsSchemaId", rmbsSchemaId);
        
        List<String> rmbsTypeId = new ArrayList<>();
        rmbsTypeId.add(String.valueOf(selectedRmbsSchemaListOfType.getRmbsType().getId()));
        dataToSend.put("rmbsTypeId", rmbsTypeId);
        
        showDialogReimbursementType(dataToSend);
    }

    private void showDialogReimbursementType(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 350);
        RequestContext.getCurrentInstance().openDialog("rmbs_schema_of_type_form", options, params);
    }
   
    public void onDialogReturnReimbursementType(SelectEvent event) {
        //re-calculate searching
    	try {
    		listRmbsSchemaListOfTypes = rmbsSchemaListOfTypeService.getAllDataByRmbsSchemaId(rmbsSchema.getId());
    		super.onDialogReturn(event);
    	} catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }
    /** End Reimbursement Type tab view process */
    
    /** Start Approval Definition tab view process */
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
    /** End Approval Definition tab view process */
    

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

	public RmbsSchemaListOfTypeService getRmbsSchemaListOfTypeService() {
		return rmbsSchemaListOfTypeService;
	}

	public void setRmbsSchemaListOfTypeService(
			RmbsSchemaListOfTypeService rmbsSchemaListOfTypeService) {
		this.rmbsSchemaListOfTypeService = rmbsSchemaListOfTypeService;
	}

	public RmbsSchemaListOfType getSelectedRmbsSchemaListOfType() {
		return selectedRmbsSchemaListOfType;
	}

	public void setSelectedRmbsSchemaListOfType(
			RmbsSchemaListOfType selectedRmbsSchemaListOfType) {
		this.selectedRmbsSchemaListOfType = selectedRmbsSchemaListOfType;
	}

	public List<RmbsSchemaListOfType> getListRmbsSchemaListOfTypes() {
		return listRmbsSchemaListOfTypes;
	}

	public void setListRmbsSchemaListOfTypes(
			List<RmbsSchemaListOfType> listRmbsSchemaListOfTypes) {
		this.listRmbsSchemaListOfTypes = listRmbsSchemaListOfTypes;
	}
	
}
