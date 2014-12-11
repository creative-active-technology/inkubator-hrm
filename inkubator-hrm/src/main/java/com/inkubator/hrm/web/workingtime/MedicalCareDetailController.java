/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;


import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.service.MedicalCareService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author Taufik
 */
@ManagedBean(name = "medicalCareDetailController")
@ViewScoped
public class MedicalCareDetailController extends BaseController {

    private MedicalCare selectedMedicalCare;
    @ManagedProperty(value = "#{medicalCareService}")
    private MedicalCareService medicalCareService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String execution = FacesUtil.getRequestParameter("execution");
            String param = execution.substring(0, 1);
            if(StringUtils.equals(param, "e")){
            	/* parameter (id) ini datangnya dari leave implementation View */
            	selectedMedicalCare = medicalCareService.getEntityWithDetail(Long.parseLong(execution.substring(1)));
            } 
            
           
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	selectedMedicalCare = null;
        medicalCareService = null;
    }    
    
	public MedicalCare getSelectedMedicalCare() {
		return selectedMedicalCare;
	}

	public void setSelectedMedicalCare(MedicalCare selectedMedicalCare) {
		this.selectedMedicalCare = selectedMedicalCare;
	}

	public void setMedicalCareService(MedicalCareService medicalCareService) {
		this.medicalCareService = medicalCareService;
	}

	

	public String doBack() {
        return "/protected/working_time/medical_care_view.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/medical_care_form.htm?faces-redirect=true&execution=e" + selectedMedicalCare.getId();
    }
    

}
