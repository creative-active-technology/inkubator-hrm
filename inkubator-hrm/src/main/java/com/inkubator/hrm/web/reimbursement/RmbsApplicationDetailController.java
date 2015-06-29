/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reimbursement;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.entity.RmbsSchemaListOfEmp;
import com.inkubator.hrm.entity.RmbsSchemaListOfType;
import com.inkubator.hrm.entity.RmbsSchemaListOfTypeId;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.service.RmbsSchemaListOfEmpService;
import com.inkubator.hrm.service.RmbsSchemaListOfTypeService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "rmbsApplicationDetailController")
@ViewScoped
public class RmbsApplicationDetailController extends BaseController {
	
    private RmbsApplication rmbsApplication;
    private RmbsSchema rmbsSchema;
    private RmbsSchemaListOfType rmbsSchemaListOfType;
    private BigDecimal totalRequestThisMoth;
    private Boolean isHaveAttachment;
    private ApprovalActivity lastApprovalActivity;
    
    @ManagedProperty(value = "#{rmbsApplicationService}")
    private RmbsApplicationService rmbsApplicationService;
    @ManagedProperty(value = "#{rmbsSchemaListOfEmpService}")
    private RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService;
    @ManagedProperty(value = "#{rmbsSchemaListOfTypeService}")
    private RmbsSchemaListOfTypeService rmbsSchemaListOfTypeService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution").substring(1);
            rmbsApplication = rmbsApplicationService.getEntityByPkWithDetail(Long.parseLong(id));
            lastApprovalActivity = approvalActivityService.getEntityByActivityNumberLastSequence(rmbsApplication.getApprovalActivityNumber());            
            RmbsSchemaListOfEmp rmbsSchemaListOfEmp = rmbsSchemaListOfEmpService.getEntityByEmpDataId(rmbsApplication.getEmpData().getId());
            rmbsSchema =  rmbsSchemaListOfEmp.getRmbsSchema();
            rmbsSchemaListOfType = rmbsSchemaListOfTypeService.getEntityByPk(new RmbsSchemaListOfTypeId(rmbsApplication.getRmbsType().getId(), rmbsSchema.getId()));
            totalRequestThisMoth = rmbsApplicationService.getTotalNominalByThisMonth(rmbsApplication.getEmpData().getId(), rmbsApplication.getRmbsType().getId());
            isHaveAttachment = rmbsApplication.getReceiptAttachment() != null && rmbsApplication.getReceiptAttachment().length > 0;
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	rmbsApplication = null;
    	rmbsSchema = null;
    	rmbsSchemaListOfType = null;
    	totalRequestThisMoth = null;
    	rmbsApplicationService = null;
    	rmbsSchemaListOfEmpService = null;
    	rmbsSchemaListOfTypeService = null;
    	isHaveAttachment = null;
    }   

    public String doBack() {
    	return "/protected/reimbursement/rmbs_application_undisbursed_view.htm?faces-redirect=true";
    }

	public RmbsApplication getRmbsApplication() {
		return rmbsApplication;
	}

	public void setRmbsApplication(RmbsApplication rmbsApplication) {
		this.rmbsApplication = rmbsApplication;
	}

	public RmbsApplicationService getRmbsApplicationService() {
		return rmbsApplicationService;
	}

	public void setRmbsApplicationService(
			RmbsApplicationService rmbsApplicationService) {
		this.rmbsApplicationService = rmbsApplicationService;
	}

	public RmbsSchema getRmbsSchema() {
		return rmbsSchema;
	}

	public void setRmbsSchema(RmbsSchema rmbsSchema) {
		this.rmbsSchema = rmbsSchema;
	}

	public RmbsSchemaListOfType getRmbsSchemaListOfType() {
		return rmbsSchemaListOfType;
	}

	public void setRmbsSchemaListOfType(RmbsSchemaListOfType rmbsSchemaListOfType) {
		this.rmbsSchemaListOfType = rmbsSchemaListOfType;
	}

	public BigDecimal getTotalRequestThisMoth() {
		return totalRequestThisMoth;
	}

	public void setTotalRequestThisMoth(BigDecimal totalRequestThisMoth) {
		this.totalRequestThisMoth = totalRequestThisMoth;
	}

	public RmbsSchemaListOfEmpService getRmbsSchemaListOfEmpService() {
		return rmbsSchemaListOfEmpService;
	}

	public void setRmbsSchemaListOfEmpService(
			RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService) {
		this.rmbsSchemaListOfEmpService = rmbsSchemaListOfEmpService;
	}

	public RmbsSchemaListOfTypeService getRmbsSchemaListOfTypeService() {
		return rmbsSchemaListOfTypeService;
	}

	public void setRmbsSchemaListOfTypeService(
			RmbsSchemaListOfTypeService rmbsSchemaListOfTypeService) {
		this.rmbsSchemaListOfTypeService = rmbsSchemaListOfTypeService;
	}

	public Boolean getIsHaveAttachment() {
		return isHaveAttachment;
	}

	public void setIsHaveAttachment(Boolean isHaveAttachment) {
		this.isHaveAttachment = isHaveAttachment;
	}

	public ApprovalActivity getLastApprovalActivity() {
		return lastApprovalActivity;
	}

	public void setLastApprovalActivity(ApprovalActivity lastApprovalActivity) {
		this.lastApprovalActivity = lastApprovalActivity;
	}

	public void setApprovalActivityService(
			ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}
	
	
	
}
