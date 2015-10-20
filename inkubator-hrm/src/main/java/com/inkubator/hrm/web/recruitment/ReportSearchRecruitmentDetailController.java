/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import ch.lambdaj.Lambda;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitApplicantSpecList;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author arsyad_
 */
@ManagedBean(name = "recruitSearchRecruitmentDetailController")
@ViewScoped
public class ReportSearchRecruitmentDetailController extends BaseController {

	private RecruitApplicant selectedRecruitApplicant;
	private List<OrgTypeOfSpecList> listJobsSpecification;
	@ManagedProperty(value = "#{recruitApplicantService}")
    private RecruitApplicantService recruitApplicantService;
	
	@PostConstruct
    @Override
    public void initialization() {
		super.initialization();
		try {
	        String id = FacesUtil.getRequestParameter("execution");
	        selectedRecruitApplicant = recruitApplicantService.getEntityByPkWithDetail(Long.parseLong(id.substring(1)));
	        listJobsSpecification = Lambda.extract(selectedRecruitApplicant.getRecruitApplicantSpecLists(), Lambda.on(RecruitApplicantSpecList.class).getOrgTypeOfSpecList());
	        listJobsSpecification = Lambda.sort(listJobsSpecification, Lambda.on(OrgTypeOfSpecList.class).getOrgTypeOfSpec().getName());
		} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
	}
	
	@PreDestroy
    public void cleanAndExit() {
		selectedRecruitApplicant = null;
		listJobsSpecification = null;
		recruitApplicantService = null;
	}

	public String doBack() {
        return "/protected/recruitment/report_search_applicant.htm?faces-redirect=true";
    }

	public RecruitApplicant getSelectedRecruitApplicant() {
		return selectedRecruitApplicant;
	}

	public void setSelectedRecruitApplicant(RecruitApplicant selectedRecruitApplicant) {
		this.selectedRecruitApplicant = selectedRecruitApplicant;
	}

	public List<OrgTypeOfSpecList> getListJobsSpecification() {
		return listJobsSpecification;
	}

	public void setListJobsSpecification(List<OrgTypeOfSpecList> listJobsSpecification) {
		this.listJobsSpecification = listJobsSpecification;
	}

	public RecruitApplicantService getRecruitApplicantService() {
		return recruitApplicantService;
	}

	public void setRecruitApplicantService(RecruitApplicantService recruitApplicantService) {
		this.recruitApplicantService = recruitApplicantService;
	}    
    
}