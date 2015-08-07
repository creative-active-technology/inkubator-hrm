/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "vacancyAdvertisementDetailController")
@ViewScoped
public class VacancyAdvertisementDetailController extends BaseController {
	
    private RecruitVacancyAdvertisement vacancyAdvertisement;
    private List<RecruitVacancyAdvertisementDetail> listAdvertisementDetail;
    @ManagedProperty(value = "#{recruitVacancyAdvertisementService}")
    private RecruitVacancyAdvertisementService recruitVacancyAdvertisementService;
    @ManagedProperty(value = "#{recruitVacancyAdvertisementDetailService}")
    private RecruitVacancyAdvertisementDetail recruitVacancyAdvertisementDetailService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            vacancyAdvertisement = recruitVacancyAdvertisementService.getEntiyByPK(Long.parseLong(id.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	recruitVacancyAdvertisementService = null;
    	listAdvertisementDetail = null;
    	recruitVacancyAdvertisementService = null;
    	recruitVacancyAdvertisementDetailService = null; 
    }   

    public String doBack() {
        return "/protected/recruitment/vacancy_advertisement_view.htm?faces-redirect=true";
    }

    public void doUpdate() {
    	try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/vacancy_adverstisement?id=" + vacancyAdvertisement.getId());
        } catch (IOException ex) {
          LOGGER.error("Erorr", ex);
        }
    }

	public RecruitVacancyAdvertisement getVacancyAdvertisement() {
		return vacancyAdvertisement;
	}

	public void setVacancyAdvertisement(RecruitVacancyAdvertisement vacancyAdvertisement) {
		this.vacancyAdvertisement = vacancyAdvertisement;
	}

	public List<RecruitVacancyAdvertisementDetail> getListAdvertisementDetail() {
		return listAdvertisementDetail;
	}

	public void setListAdvertisementDetail(List<RecruitVacancyAdvertisementDetail> listAdvertisementDetail) {
		this.listAdvertisementDetail = listAdvertisementDetail;
	}

	public RecruitVacancyAdvertisementService getRecruitVacancyAdvertisementService() {
		return recruitVacancyAdvertisementService;
	}

	public void setRecruitVacancyAdvertisementService(RecruitVacancyAdvertisementService recruitVacancyAdvertisementService) {
		this.recruitVacancyAdvertisementService = recruitVacancyAdvertisementService;
	}

	public RecruitVacancyAdvertisementDetail getRecruitVacancyAdvertisementDetailService() {
		return recruitVacancyAdvertisementDetailService;
	}

	public void setRecruitVacancyAdvertisementDetailService(RecruitVacancyAdvertisementDetail recruitVacancyAdvertisementDetailService) {
		this.recruitVacancyAdvertisementDetailService = recruitVacancyAdvertisementDetailService;
	}
    
    
	
}
