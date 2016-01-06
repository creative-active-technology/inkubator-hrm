/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.appraisal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.service.AppraisalProgramService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "appraisalProgramDetailController")
@ViewScoped
public class AppraisalProgramDetailController extends BaseController {
	
	private AppraisalProgram appraisalProgram;
    
    @ManagedProperty(value = "#{appraisalProgramService}")
    private AppraisalProgramService appraisalProgramService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution").substring(1);
            appraisalProgram = appraisalProgramService.getEntityByIdWithDetail(Long.parseLong(id));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	appraisalProgram = null;
    	appraisalProgramService = null;
    }   

    public String doBack() {
    	return "/protected/appraisal/appraisal_program_view.htm?faces-redirect=true";
    }
    
    public String doUpdate() {
        return "/protected/appraisal/appraisal_program_form.htm?faces-redirect=true&execution=e" + appraisalProgram.getId();
    }

	public AppraisalProgram getAppraisalProgram() {
		return appraisalProgram;
	}

	public void setAppraisalProgram(AppraisalProgram appraisalProgram) {
		this.appraisalProgram = appraisalProgram;
	}

	public AppraisalProgramService getAppraisalProgramService() {
		return appraisalProgramService;
	}

	public void setAppraisalProgramService(AppraisalProgramService appraisalProgramService) {
		this.appraisalProgramService = appraisalProgramService;
	}

}
