/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import ch.lambdaj.Lambda;
import com.google.gson.Gson;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLoan;
import com.inkubator.hrm.entity.ApprovalDefinitionRmbsSchema;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.entity.LoanNewSchemaListOfTypeId;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalDefinitionLoanService;
import com.inkubator.hrm.service.ApprovalDefinitionService;
import com.inkubator.hrm.service.LoanNewSchemaListOfTypeService;
import com.inkubator.hrm.service.LoanNewSchemaService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author Deni
 */
@ManagedBean(name = "loanNewSchemaDetailController")
@ViewScoped
public class LoanNewSchemaDetailController extends BaseController {

    private LoanNewSchema selectedLoanSchema;
    private LoanNewSchemaListOfType selectedLoanNewSchemaListOfType;
    @ManagedProperty(value = "#{loanNewSchemaService}")
    private LoanNewSchemaService loanNewSchemaService;
    @ManagedProperty(value = "#{loanNewSchemaListOfTypeService}")
    private LoanNewSchemaListOfTypeService loanNewSchemaListOfTypeService;
    @ManagedProperty(value = "#{approvalDefinitionService}")
    private ApprovalDefinitionService approvalDefinitionService;
    @ManagedProperty(value = "#{approvalDefinitionLoanService}")
    private ApprovalDefinitionLoanService approvalDefinitionLoanService;
    private List<LoanNewSchemaListOfType> listOfLoanNewType;
    private Double totalPinjaman;
    private Double totalAllocation;
    private Double totalInstallment;
    private List<ApprovalDefinition> appDefs;
    private ApprovalDefinition selectedAppDef;
    private int indexOfAppDefs;
    private String name;
    private String loanNewSchemaId;
    private List<ApprovalDefinition> listAppDef;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            loanNewSchemaId = FacesUtil.getRequestParameter("execution");
            selectedLoanSchema = loanNewSchemaService.getEntityByPkFetchApprovalDefinition(Long.parseLong(loanNewSchemaId.substring(1)));
            listOfLoanNewType = loanNewSchemaListOfTypeService.getEntityByLoanNewSchema(selectedLoanSchema.getId());
            totalPinjaman = 0.0;
            totalAllocation = 0.0;
            totalInstallment = 0.0;
            listAppDef = Lambda.extract(selectedLoanSchema.getApprovalDefinitionLoans(), Lambda.on(ApprovalDefinitionLoan.class).getApprovalDefinition());
//            appDefs = approvalDefinitionLoanService.getByLoanId(Long.parseLong(loanNewSchemaId.substring(1)));
//            List<ApprovalDefinitionLoan> approvalDefinitionLoan = approvalDefinitionLoanService.getByLoanId(Long.valueOf(loanNewSchemaId.substring(1)));
            appDefs = approvalDefinitionService.getAllDataByLoanNewSchemaId(Long.valueOf(loanNewSchemaId.substring(1)));
            for (LoanNewSchemaListOfType loanNewSchemaType : listOfLoanNewType) {
                totalPinjaman = totalPinjaman + loanNewSchemaType.getMaximumApproval();
                totalAllocation = totalAllocation + loanNewSchemaType.getMaximumAllocation();
                totalInstallment = totalInstallment + loanNewSchemaType.getMinimumMonthlyInstallment();
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        selectedLoanSchema = null;
        loanNewSchemaService = null;
        selectedLoanNewSchemaListOfType = null;
        listOfLoanNewType = null;
        loanNewSchemaListOfTypeService = null;
        totalPinjaman = null;
        totalAllocation = null;
        totalInstallment = null;
        appDefs = null;
        loanNewSchemaId = null;
        listAppDef = null;
    }

    public String doBack() {
        return "/protected/personalia/loan_new_schema_view.htm?faces-redirect=true";
    }

    public void doAdd() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedLoanSchema.getId()));
        dataToSend.put("loanNewSchemaId", dataIsi);
        showDialog(dataToSend);
    }

    public void doSelectEntity() {
        System.out.println("hoho");
        try {
            selectedLoanNewSchemaListOfType = this.loanNewSchemaListOfTypeService.getEntityByLoanNewSchemaListOfTypeIdWithDetail(new LoanNewSchemaListOfTypeId(selectedLoanNewSchemaListOfType.getLoanNewType().getId(), selectedLoanNewSchemaListOfType.getLoanNewSchema().getId()));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        System.out.println("masuk delete");
        try {
            this.loanNewSchemaListOfTypeService.delete(selectedLoanNewSchemaListOfType);
            listOfLoanNewType = loanNewSchemaListOfTypeService.getEntityByLoanNewSchema(selectedLoanSchema.getId());
            totalPinjaman = 0.0;
            totalAllocation = 0.0;
            totalInstallment = 0.0;
            for (LoanNewSchemaListOfType loanNewSchemaType : listOfLoanNewType) {
                totalPinjaman = totalPinjaman + loanNewSchemaType.getMaximumApproval();
                totalAllocation = totalAllocation + loanNewSchemaType.getMaximumAllocation();
                totalInstallment = totalInstallment + loanNewSchemaType.getMinimumMonthlyInstallment();
            }
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> loanNewTypeId = new ArrayList<>();
        loanNewTypeId.add(String.valueOf(selectedLoanNewSchemaListOfType.getLoanNewType().getId()));
        List<String> loanNewSchemaId = new ArrayList<>();
        loanNewSchemaId.add(String.valueOf(selectedLoanSchema.getId()));
        dataToSend.put("loanNewTypeId", loanNewTypeId);
        dataToSend.put("loanNewSchemaId", loanNewSchemaId);
        showDialog(dataToSend);
    }

    public void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 460);
        RequestContext.getCurrentInstance().openDialog("loan_new_schema_type_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        try {
            listOfLoanNewType = loanNewSchemaListOfTypeService.getEntityByLoanNewSchema(selectedLoanSchema.getId());
            totalPinjaman = 0.0;
            totalAllocation = 0.0;
            totalInstallment = 0.0;
            for (LoanNewSchemaListOfType loanNewSchemaType : listOfLoanNewType) {
                totalPinjaman = totalPinjaman + loanNewSchemaType.getMaximumApproval();
                totalAllocation = totalAllocation + loanNewSchemaType.getMaximumAllocation();
                totalInstallment = totalInstallment + loanNewSchemaType.getMinimumMonthlyInstallment();
            }
        } catch (Exception ex) {
            Logger.getLogger(LoanNewSchemaDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.onDialogReturn(event);

    }

    /** Start Approval Definition form */
    public void doDeleteAppDef() {
    	try {
    		loanNewSchemaService.deleteApprovalconf(selectedAppDef.getId(), selectedLoanSchema.getId());
    		listAppDef = Lambda.extract(approvalDefinitionLoanService.getByLoanId(selectedLoanSchema.getId()), Lambda.on(ApprovalDefinitionLoan.class).getApprovalDefinition());
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
        entityId.add(String.valueOf(selectedLoanSchema.getId()));
        dataToSend.put("entityId", entityId);
        
    	List<String> appDefName = new ArrayList<>();
        appDefName.add(HRMConstant.LOAN);
        dataToSend.put("appDefName", appDefName);
        
        List<String> specificName = new ArrayList<>();
        specificName.add(selectedLoanSchema.getLoanSchemaName());
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
        entityId.add(String.valueOf(selectedLoanSchema.getId()));
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
    		listAppDef = Lambda.extract(approvalDefinitionLoanService.getByLoanId(selectedLoanSchema.getId()), Lambda.on(ApprovalDefinitionLoan.class).getApprovalDefinition());
    		super.onDialogReturn(event);
                System.out.println(listAppDef.size() + " hohohohohohoho");
    	} catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }
    /** End Approval Definition form */

    public void onDialogReturnAddAppDef(SelectEvent event) {
        ApprovalDefinition appDef = (ApprovalDefinition) event.getObject();
        appDefs.add(appDef);
        System.out.println(appDefs.size());
    }

    public void onDialogReturnEditAppDef(SelectEvent event) {
        ApprovalDefinition dataUpdated = (ApprovalDefinition) event.getObject();
        appDefs.remove(indexOfAppDefs);
        appDefs.add(indexOfAppDefs, dataUpdated);
    }

    public void doSaveApprovalDefinition() {
        try {
            loanNewSchemaService.save(selectedLoanSchema, appDefs);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoanNewSchema getSelectedLoanSchema() {
        return selectedLoanSchema;
    }

    public void setSelectedLoanSchema(LoanNewSchema selectedLoanSchema) {
        this.selectedLoanSchema = selectedLoanSchema;
    }

    public LoanNewSchemaService getLoanNewSchemaService() {
        return loanNewSchemaService;
    }

    public void setLoanNewSchemaService(LoanNewSchemaService loanNewSchemaService) {
        this.loanNewSchemaService = loanNewSchemaService;
    }

    public LoanNewSchemaListOfTypeService getLoanNewSchemaListOfTypeService() {
        return loanNewSchemaListOfTypeService;
    }

    public void setLoanNewSchemaListOfTypeService(LoanNewSchemaListOfTypeService loanNewSchemaListOfTypeService) {
        this.loanNewSchemaListOfTypeService = loanNewSchemaListOfTypeService;
    }

    public List<LoanNewSchemaListOfType> getListOfLoanNewType() {
        return listOfLoanNewType;
    }

    public void setListOfLoanNewType(List<LoanNewSchemaListOfType> listOfLoanNewType) {
        this.listOfLoanNewType = listOfLoanNewType;
    }

    public Double getTotalPinjaman() {
        return totalPinjaman;
    }

    public void setTotalPinjaman(Double totalPinjaman) {
        this.totalPinjaman = totalPinjaman;
    }

    public Double getTotalAllocation() {
        return totalAllocation;
    }

    public void setTotalAllocation(Double totalAllocation) {
        this.totalAllocation = totalAllocation;
    }

    public Double getTotalInstallment() {
        return totalInstallment;
    }

    public void setTotalInstallment(Double totalInstallment) {
        this.totalInstallment = totalInstallment;
    }

    public LoanNewSchemaListOfType getLoanNewSchemaListOfType() {
        return selectedLoanNewSchemaListOfType;
    }

    public void setLoanNewSchemaListOfType(LoanNewSchemaListOfType selectedLoanNewSchemaListOfType) {
        this.selectedLoanNewSchemaListOfType = selectedLoanNewSchemaListOfType;
    }

    public LoanNewSchemaListOfType getSelectedLoanNewSchemaListOfType() {
        return selectedLoanNewSchemaListOfType;
    }

    public void setSelectedLoanNewSchemaListOfType(LoanNewSchemaListOfType selectedLoanNewSchemaListOfType) {
        this.selectedLoanNewSchemaListOfType = selectedLoanNewSchemaListOfType;
    }

    public List<ApprovalDefinition> getAppDefs() {
        return appDefs;
    }

    public void setAppDefs(List<ApprovalDefinition> appDefs) {
        this.appDefs = appDefs;
    }

    public ApprovalDefinition getSelectedAppDef() {
        return selectedAppDef;
    }

    public void setSelectedAppDef(ApprovalDefinition selectedAppDef) {
        this.selectedAppDef = selectedAppDef;
    }

    public int getIndexOfAppDefs() {
        return indexOfAppDefs;
    }

    public void setIndexOfAppDefs(int indexOfAppDefs) {
        this.indexOfAppDefs = indexOfAppDefs;
    }

    public ApprovalDefinitionService getApprovalDefinitionService() {
        return approvalDefinitionService;
    }

    public void setApprovalDefinitionService(ApprovalDefinitionService approvalDefinitionService) {
        this.approvalDefinitionService = approvalDefinitionService;
    }

    public ApprovalDefinitionLoanService getApprovalDefinitionLoanService() {
        return approvalDefinitionLoanService;
    }

    public void setApprovalDefinitionLoanService(ApprovalDefinitionLoanService approvalDefinitionLoanService) {
        this.approvalDefinitionLoanService = approvalDefinitionLoanService;
    }

    public String getLoanNewSchemaId() {
        return loanNewSchemaId;
    }

    public void setLoanNewSchemaId(String loanNewSchemaId) {
        this.loanNewSchemaId = loanNewSchemaId;
    }

    public List<ApprovalDefinition> getListAppDef() {
        return listAppDef;
    }

    public void setListAppDef(List<ApprovalDefinition> listAppDef) {
        this.listAppDef = listAppDef;
    }

    
}
