/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementDetailService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "vacancyDetailController")
@ViewScoped
public class VacancyDetailController extends BaseController {
	
    private RecruitVacancyAdvertisementDetail vacancy;    
    @ManagedProperty(value = "#{recruitVacancyAdvertisementDetailService}")
    private RecruitVacancyAdvertisementDetailService recruitVacancyAdvertisementDetailService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String execution = FacesUtil.getRequestParameter("execution");
            vacancy = recruitVacancyAdvertisementDetailService.getEntityByPkWithDetail(Long.parseLong(execution.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	vacancy = null;
    	recruitVacancyAdvertisementDetailService = null;
    }   

    public String doBack() {
        return "/protected/recruitment/vacancy_view.htm?faces-redirect=true";
    }

	public RecruitVacancyAdvertisementDetail getVacancy() {
		return vacancy;
	}

	public void setVacancy(RecruitVacancyAdvertisementDetail vacancy) {
		this.vacancy = vacancy;
	}

	public RecruitVacancyAdvertisementDetailService getRecruitVacancyAdvertisementDetailService() {
		return recruitVacancyAdvertisementDetailService;
	}

	public void setRecruitVacancyAdvertisementDetailService(RecruitVacancyAdvertisementDetailService recruitVacancyAdvertisementDetailService) {
		this.recruitVacancyAdvertisementDetailService = recruitVacancyAdvertisementDetailService;
	}
    
}
