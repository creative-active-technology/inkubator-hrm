/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.common.util.JsonConverter;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.ReimbursmentSchemaService;
import com.inkubator.hrm.service.ReimbursmentService;
import com.inkubator.hrm.web.model.ReimbursmentModelJsonParsing;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "reimbursmentApprovalFormController")
@ViewScoped
public class ReimbursmentApprovalFormController extends BaseController{
    private Reimbursment selected;
    private String comment;
    private ReimbursmentModelJsonParsing reimbursmentModelJsonParsing;
    private ReimbursmentSchema reimbursmentSchema;
    private EmpData empData;
    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{reimbursmentService}")
    private ReimbursmentService reimbursmentService;
    @ManagedProperty(value = "#{reimbursmentSchemaService}")
    private ReimbursmentSchemaService reimbursmentSchemaService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
            reimbursmentModelJsonParsing = (ReimbursmentModelJsonParsing) JsonConverter.getClassFromJson(selectedApprovalActivity.getPendingData(), ReimbursmentModelJsonParsing.class, "dd-MM-yyyy");
            reimbursmentSchema = reimbursmentSchemaService.getEntiyByPK(reimbursmentModelJsonParsing.getReimbursmentSchemaId());
            empData = empDataService.getByEmpIdWithDetail(reimbursmentModelJsonParsing.getEmpDataId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        selected = null;
        selectedApprovalActivity = null;
        reimbursmentService = null;
        approvalActivityService = null;
        comment = null;
        empData = null;
        empDataService = null;
        reimbursmentModelJsonParsing = null;
        reimbursmentSchemaService = null;
        
    } 
    
    public Reimbursment getSelected() {
        return selected;
    }

    public void setSelected(Reimbursment selected) {
        this.selected = selected;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ApprovalActivity getSelectedApprovalActivity() {
        return selectedApprovalActivity;
    }

    public void setSelectedApprovalActivity(ApprovalActivity selectedApprovalActivity) {
        this.selectedApprovalActivity = selectedApprovalActivity;
    }

    public ApprovalActivityService getApprovalActivityService() {
        return approvalActivityService;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public ReimbursmentService getReimbursmentService() {
        return reimbursmentService;
    }

    public void setReimbursmentService(ReimbursmentService reimbursmentService) {
        this.reimbursmentService = reimbursmentService;
    }
    
    public String doApproved() {
    	try {
            reimbursmentModelJsonParsing = (ReimbursmentModelJsonParsing) JsonConverter.getClassFromJson(selectedApprovalActivity.getPendingData(), ReimbursmentModelJsonParsing.class, "dd-MM-yyyy");
            reimbursmentSchema = reimbursmentSchemaService.getEntiyByPK(reimbursmentModelJsonParsing.getReimbursmentSchemaId());
            empData = empDataService.getByEmpIdWithDetail(reimbursmentModelJsonParsing.getEmpDataId());
            
            reimbursmentService.approved(selectedApprovalActivity.getId(), reimbursmentModelJsonParsing, comment);
            return "/protected/home.htm?faces-redirect=true";
        } catch (Exception e) {
            LOGGER.error("Error when approved process ", e);
        }
    	return null;
    }

    public ReimbursmentSchemaService getReimbursmentSchemaService() {
        return reimbursmentSchemaService;
    }

    public void setReimbursmentSchemaService(ReimbursmentSchemaService reimbursmentSchemaService) {
        this.reimbursmentSchemaService = reimbursmentSchemaService;
    }

    public ReimbursmentModelJsonParsing getReimbursmentModelJsonParsing() {
        return reimbursmentModelJsonParsing;
    }

    public void setReimbursmentModelJsonParsing(ReimbursmentModelJsonParsing reimbursmentModelJsonParsing) {
        this.reimbursmentModelJsonParsing = reimbursmentModelJsonParsing;
    }

    public ReimbursmentSchema getReimbursmentSchema() {
        return reimbursmentSchema;
    }

    public void setReimbursmentSchema(ReimbursmentSchema reimbursmentSchema) {
        this.reimbursmentSchema = reimbursmentSchema;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }
    
    
}
