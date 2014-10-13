/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.ReimbursmentSchemaService;
import com.inkubator.hrm.service.ReimbursmentService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "reimbursmentApprovalFormController")
@ViewScoped
public class ReimbursmentApprovalFormController extends BaseController {

    private Reimbursment reimbursment;
    private String comment;
    private Boolean isWaitingApproval;
    String reimbursmentFileName;
    //private ReimbursmentModelJsonParsing reimbursmentModelJsonParsing;
    //private ReimbursmentSchema reimbursmentSchema;
    //private EmpData empData;
    private ApprovalActivity selectedApprovalActivity;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{reimbursmentService}")
    private ReimbursmentService reimbursmentService;
    /*@ManagedProperty(value = "#{reimbursmentSchemaService}")
     private ReimbursmentSchemaService reimbursmentSchemaService;*/
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private Boolean isDownload;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            isDownload = Boolean.TRUE;
            String id = FacesUtil.getRequestParameter("execution");
            selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(id.substring(1)));
            isWaitingApproval = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING;
            
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            reimbursment = gson.fromJson(selectedApprovalActivity.getPendingData(), Reimbursment.class);
            JsonObject jsonObject = gson.fromJson(selectedApprovalActivity.getPendingData(), JsonObject.class);
            JsonElement elReimbursmentFile = jsonObject.get("reimbursmentFileName");
            reimbursmentFileName = elReimbursmentFile.isJsonNull() ? StringUtils.EMPTY : elReimbursmentFile.getAsString();
            if (reimbursmentFileName.equals(StringUtils.EMPTY)) {
                System.out.println("masukk===");
                isDownload = Boolean.FALSE;
            }
            EmpData empData = empDataService.getByIdWithDetail(reimbursment.getEmpData().getId());
            reimbursment.setEmpData(empData);
            /*reimbursmentModelJsonParsing = (ReimbursmentModelJsonParsing) JsonConverter.getClassFromJson(selectedApprovalActivity.getPendingData(), ReimbursmentModelJsonParsing.class, "dd-MM-yyyy");
             reimbursmentSchema = reimbursmentSchemaService.getEntiyByPK(reimbursmentModelJsonParsing.getReimbursmentSchemaId());
             empData = empDataService.getByEmpIdWithDetail(reimbursmentModelJsonParsing.getEmpDataId());*/
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        reimbursment = null;
        selectedApprovalActivity = null;
        reimbursmentService = null;
        approvalActivityService = null;
        comment = null;
        //empData = null;
        empDataService = null;
        //reimbursmentModelJsonParsing = null;
        //reimbursmentSchemaService = null;
        reimbursmentFileName = null;
        isDownload = null;
        isWaitingApproval = null;
    }

    public Reimbursment getReimbursment() {
        return reimbursment;
    }

    public void setReimbursment(Reimbursment reimbursment) {
        this.reimbursment = reimbursment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getIsWaitingApproval() {
		return isWaitingApproval;
	}

	public void setIsWaitingApproval(Boolean isWaitingApproval) {
		this.isWaitingApproval = isWaitingApproval;
	}

	public String getReimbursmentFileName() {
        return reimbursmentFileName;
    }

    public void setReimbursmentFileName(String reimbursmentFileName) {
        this.reimbursmentFileName = reimbursmentFileName;
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

    public Boolean getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(Boolean isDownload) {
        this.isDownload = isDownload;
    }
    
    public String doApproved() {
        try {
            /*reimbursmentModelJsonParsing = (ReimbursmentModelJsonParsing) JsonConverter.getClassFromJson(selectedApprovalActivity.getPendingData(), ReimbursmentModelJsonParsing.class, "dd-MM-yyyy");
             reimbursmentSchema = reimbursmentSchemaService.getEntiyByPK(reimbursmentModelJsonParsing.getReimbursmentSchemaId());
             empData = empDataService.getByEmpIdWithDetail(reimbursmentModelJsonParsing.getEmpDataId());*/

            reimbursmentService.approved(selectedApprovalActivity.getId(), null, comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (Exception e) {
            LOGGER.error("Error when approved process ", e);
        }
        return null;
    }

    public String doRejected() {
        try {
            reimbursmentService.rejected(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.rejected_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/home.htm?faces-redirect=true";
        } catch (Exception e) {
            LOGGER.error("Error when rejected process ", e);
        }
        return null;
    }

    /*public ReimbursmentSchemaService getReimbursmentSchemaService() {
     return reimbursmentSchemaService;
     }

     public void setReimbursmentSchemaService(ReimbursmentSchemaService reimbursmentSchemaService) {
     this.reimbursmentSchemaService = reimbursmentSchemaService;
     }*/

    /*public ReimbursmentModelJsonParsing getReimbursmentModelJsonParsing() {
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
     }*/
    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }
}
