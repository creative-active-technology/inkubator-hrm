package com.inkubator.hrm.web.recruitment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.web.model.ApplicantStatisticViewModel;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "applicantStatisticViewController")
@ViewScoped
public class ApplicantStatisticViewController extends BaseController {

	private ApplicantStatisticViewModel model;
	@ManagedProperty(value = "#{recruitApplicantService}")
    private RecruitApplicantService recruitApplicantService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	model = recruitApplicantService.getAllDataApplicantStatistic();
        } catch (Exception ex) {
            LOGGER.error("Error ", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	recruitApplicantService = null;
    	model = null;
    }

	public ApplicantStatisticViewModel getModel() {
		return model;
	}

	public void setModel(ApplicantStatisticViewModel model) {
		this.model = model;
	}

	public RecruitApplicantService getRecruitApplicantService() {
		return recruitApplicantService;
	}

	public void setRecruitApplicantService(RecruitApplicantService recruitApplicantService) {
		this.recruitApplicantService = recruitApplicantService;
	}
    
}
