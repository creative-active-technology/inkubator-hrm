/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendance;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendanceDetail;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.ApprovalDefinitionService;
import com.inkubator.hrm.service.WtEmpCorrectionAttendanceService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "empCorrectionAttendanceDetailController")
@ViewScoped
public class EmpCorrectionAttendanceDetailController extends BaseController {
	
    private WtEmpCorrectionAttendance empCorrectionAttendance;
    private List<WtEmpCorrectionAttendanceDetail> listDetail;
    
    @ManagedProperty(value = "#{wtEmpCorrectionAttendanceService}")
    private WtEmpCorrectionAttendanceService wtEmpCorrectionAttendanceService;
    
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    
    private List<EmpData> listApprover;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            listApprover = new ArrayList<EmpData>();
            String id = FacesUtil.getRequestParameter("execution").substring(1);
            empCorrectionAttendance = wtEmpCorrectionAttendanceService.getEntityByPkWithDetail(Long.parseLong(id));
            List<ApprovalActivity> listApprovalActivities = approvalActivityService.getAllDataByActivityNumberWithDetail(empCorrectionAttendance.getApprovalActivityNumber());
            List<ApprovalDefinition> listAppDef = Lambda.extract(listApprovalActivities, Lambda.on(ApprovalActivity.class).getApprovalDefinition());
            listApprover = wtEmpCorrectionAttendanceService.getListApproverByListAppDefintion(listAppDef, empCorrectionAttendance.getEmpData().getId());
            listDetail = Lambda.sort(empCorrectionAttendance.getWtEmpCorrectionAttendanceDetails(), Lambda.on(WtEmpCorrectionAttendanceDetail.class).getAttendanceDate());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	empCorrectionAttendance = null;
    	approvalActivityService = null;
    	wtEmpCorrectionAttendanceService = null;
    	listDetail = null;
    }   

    public String doBack() {
    	return "/protected/working_time/emp_correction_attendance_view.htm?faces-redirect=true";
    }

	public WtEmpCorrectionAttendance getEmpCorrectionAttendance() {
		return empCorrectionAttendance;
	}

	public void setEmpCorrectionAttendance(WtEmpCorrectionAttendance empCorrectionAttendance) {
		this.empCorrectionAttendance = empCorrectionAttendance;
	}

	public WtEmpCorrectionAttendanceService getWtEmpCorrectionAttendanceService() {
		return wtEmpCorrectionAttendanceService;
	}

	public void setWtEmpCorrectionAttendanceService(WtEmpCorrectionAttendanceService wtEmpCorrectionAttendanceService) {
		this.wtEmpCorrectionAttendanceService = wtEmpCorrectionAttendanceService;
	}

	public List<WtEmpCorrectionAttendanceDetail> getListDetail() {
		return listDetail;
	}

	public void setListDetail(List<WtEmpCorrectionAttendanceDetail> listDetail) {
		this.listDetail = listDetail;
	}

	public List<EmpData> getListApprover() {
		return listApprover;
	}

	public void setListApprover(List<EmpData> listApprover) {
		this.listApprover = listApprover;
	}

	public void setApprovalActivityService(
			ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}

	
	
	
}
